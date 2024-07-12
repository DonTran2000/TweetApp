package teikyo.sdl.lec8.forms;

public class ForgotPasswordForm {
	private String stid;
	private int deptcode;
	private String email;

	// Constructors
	public ForgotPasswordForm() {
	}

	public ForgotPasswordForm(String stid, int deptcode, String email) {
		this.stid = stid;
		this.deptcode = deptcode;
		this.email = email;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getStid() {
		return stid;
	}

	public int getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(int deptcode) {
		this.deptcode = deptcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
