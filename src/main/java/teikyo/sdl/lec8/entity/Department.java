package teikyo.sdl.lec8.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name = "departments")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int deptcode;

	private String deptname;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy = "department")
	private List<Student> students;

	public Department() {
	}

	public Department(int deptcode, String deptname) {
		this.deptcode = deptcode;
		this.deptname = deptname;
	}

	public int getDeptcode() {
		return this.deptcode;
	}

	public void setDeptcode(int deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDepartment(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDepartment(null);

		return student;
	}

}