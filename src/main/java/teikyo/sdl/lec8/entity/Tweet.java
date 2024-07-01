package teikyo.sdl.lec8.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tweets database table.
 * 
 */
@Entity
@Table(name="tweets")
@NamedQuery(name="Tweet.findAll", query="SELECT t FROM Tweet t")
public class Tweet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long tweetid;

	@Lob
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tweettime;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="stid")
	private Student student;

	public Tweet() {
	}

	public long getTweetid() {
		return this.tweetid;
	}

	public void setTweetid(long tweetid) {
		this.tweetid = tweetid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTweettime() {
		return this.tweettime;
	}

	public void setTweettime(Date tweettime) {
		this.tweettime = tweettime;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}