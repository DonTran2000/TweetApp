package teikyo.sdl.lec8.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the verificationCode database table.
 * 
 */
@Entity
@NamedQuery(name = "VerificationCode.findAll", query = "SELECT v FROM VerificationCode v")
public class VerificationCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryTime;

	public VerificationCode() {
	}

	public VerificationCode(String email, String code, Date expiryTime) {
		this.email = email;
		this.code = code;
		this.expiryTime = expiryTime;
		this.createdAt = new Date();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpiryTime() {
		return this.expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

}