package teikyo.sdl.lec8.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import teikyo.sdl.lec8.dao.Dao;
import teikyo.sdl.lec8.entity.Student;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	@Async // 非同期
	public void sendVerificationCode(String to, String verificationCode) throws MessagingException {
		Dao dao = new Dao();
		Student st = dao.getStudentByEmail(to);
		
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		// メール本文の生成
		Map<String, Object> model = new HashMap<String, Object>();

		StringWriter writer = new StringWriter();
		model.put("studentName", st.getName());
		model.put("verificationCode", verificationCode);
		VelocityContext ctx = new VelocityContext(model);
		velocityEngine.mergeTemplate("HelloWorld.vm", "UTF-8", ctx, writer);

		// メール本文のセット
		helper.setText(writer.toString());

		// メール件名のセット
		helper.setSubject("Your Verification Code");

		// ファイルをメールに添付
		//		FileSystemResource file = new FileSystemResource(new File("c:/hoge.jpg"));
		//		helper.addAttachment("hoge.jpg", file);

		// 送信先を設定
		helper.setTo(to);

		// 送信
		mailSender.send(message);
	}
}
