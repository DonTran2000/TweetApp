package teikyo.sdl.lec8.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class RegistrationForm {

	@NotEmpty(message = "学籍番号を正しく入力してください")
	private String id;

	@NotEmpty(message = "パスワードは必須項目です")
	@Size(min = 8, message = "8 文字以上のパスワードを入力してください．")
	private String pass;

	@NotEmpty(message = "パスワードは必須項目です")
	@Size(min = 8, message = "8 文字以上のパスワードを入力してください．")
	private String confPass;

	@Range(min = 1, message = "所属学科は必須項目です")
	int dept;

	@NotEmpty(message = "名前を正しく入力してください")
	private String name;

	private String deptName; // New field for department name
	
	@NotEmpty(message = "メールは必ず入力してください")
	private String email;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
