package teikyo.sdl.lec8.forms;

public class LoginSession {
	private String id;
	private String pass;
	private String confPass;
	int dept;
	private String name;

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

}
