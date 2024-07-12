package teikyo.sdl.lec8.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teikyo.sdl.lec8.dao.Dao;
import teikyo.sdl.lec8.entity.Department;
import teikyo.sdl.lec8.entity.Student;
import teikyo.sdl.lec8.entity.VerificationCode;
import teikyo.sdl.lec8.forms.ForgotPasswordForm;

@Service
public class ForgotPasswordService {

	@Autowired
	private Dao dao;

	@Autowired
	private EmailService emailService;

	public List<Department> getDepartmentList() {
		return dao.getDepartmentList();
	}

	public void sendVerificationCode(ForgotPasswordForm form) throws Exception {
		try {
			Department department = dao.getDeptById(form.getDeptcode());

			Student student = dao.getByIdAndDeptAndEmail(form.getStid(), department, form.getEmail());

			if (student == null) {
				throw new Exception("情報が正しくありません");
			}
			// Check if the last verification code was sent within the last minute
			VerificationCode lastCode = dao.getLastVerificationCodeByEmail(form.getEmail());
			if (lastCode != null && (new Date().getTime() - lastCode.getCreatedAt().getTime()) < 60 * 1000) {
				throw new Exception("前回のコード送信から1分以内に再送信はできません");
			}
			// Check if more than 5 verification codes were sent in the last hour
			long codeCount = dao.countVerificationCodesByEmailInLastHour(form.getEmail());
			if (codeCount >= 5) {
				throw new Exception("1時間以内に5回以上のコード送信はできません");
			}
			
			String verificationCode = generateRandomCode();
			Date expiryTime = calculateExpiryTime(1); // 1 minute expiry time
			VerificationCode verification = new VerificationCode(student.getEmail(), verificationCode, expiryTime);
			dao.saveVerificationCode(verification);
			emailService.sendVerificationCode(student.getEmail(), verificationCode);
		} catch (NoResultException e) {
			throw new Exception("情報が正しくありません", e);
		}
	}

	public void verifyCode(String stid, String code) throws Exception {
		Student student = dao.getStudentById(stid);
		VerificationCode verificationCode = dao.getVerificationCodeByEmailAndCode(student.getEmail(), code);

		if (!verificationCode.getEmail().equals(student.getEmail())) {
			throw new Exception("無効な検証コード.");
		}

		Date currentTime = new Date();
		if (currentTime.after(verificationCode.getExpiryTime())) {
			throw new Exception("認証コードの有効期限が切れています.");
		}
	}

	public void resetPassword(String stid, String pass) throws Exception {
		Student student = dao.getStudentById(stid);
		boolean result = dao.updatePassword(student, pass);
		if (!result) {
			throw new Exception("パスワードのリセット中にエラーが発生しました. もう一度試してください.");
		}
	}

	private String generateRandomCode() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(1000000));
	}

	private Date calculateExpiryTime(int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
}
