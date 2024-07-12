package teikyo.sdl.lec8.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String stid;

	private String email;

	private String name;

	private String password;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="deptcode")
	private Department department;

	//bi-directional many-to-one association to Tweet
	@OneToMany(mappedBy="student")
	private List<Tweet> tweets;

	public Student() {
	}

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Tweet> getTweets() {
		return this.tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Tweet addTweet(Tweet tweet) {
		getTweets().add(tweet);
		tweet.setStudent(this);

		return tweet;
	}

	public Tweet removeTweet(Tweet tweet) {
		getTweets().remove(tweet);
		tweet.setStudent(null);

		return tweet;
	}

	@Override
	public String toString() {
		return "Student [stid=" + stid + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", department=" + department + ", tweets=" + tweets + "]";
	}
	

}