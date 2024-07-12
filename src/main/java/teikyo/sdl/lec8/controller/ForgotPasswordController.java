package teikyo.sdl.lec8.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teikyo.sdl.lec8.forms.ForgotPasswordForm;
import teikyo.sdl.lec8.forms.VerificationCodeForm;
import teikyo.sdl.lec8.service.ForgotPasswordService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@ModelAttribute("ForgotPasswordForm")
	public ForgotPasswordForm getForgotPasswordForm() {
		return new ForgotPasswordForm();
	}

	@ModelAttribute("VerificationCodeForm")
	public VerificationCodeForm getVerificationCodeForm() {
		return new VerificationCodeForm();
	}

	@GetMapping("/forgotPassword")
	public String forgotPasswordPage(Model model) {
		model.addAttribute("deptItems", forgotPasswordService.getDepartmentList());
		return "forgot_password";
	}

	@PostMapping("/sendVerificationCode")
	public String sendVerificationCode(@ModelAttribute("ForgotPasswordForm") ForgotPasswordForm form,
			Model model) throws MessagingException {
		try {
			forgotPasswordService.sendVerificationCode(form);
			model.addAttribute("stid", form.getStid());
			return "verify_code";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "forgot_password";
		}
	}

	@PostMapping("/verifyCode")
	public String verifyCode(@RequestParam String stid, @RequestParam String code, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			forgotPasswordService.verifyCode(stid, code);
			redirectAttributes.addFlashAttribute("stid", stid);
			return "redirect:/resetPassword";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "verify_code";
		}
	}

	@GetMapping("/resetPassword")
	public String resetPasswordPage() {
		return "reset_password";
	}

	@PostMapping("/checkResetPass")
	public String checkResetPass(@RequestParam String pass, @RequestParam String confPass, @RequestParam String stid,
			Model model) {
		if (!pass.equals(confPass)) {
			model.addAttribute("error", "パスワードと確認用パスワードが一致しません!");
			return "reset_password";
		}

		try {
			forgotPasswordService.resetPassword(stid, pass);
			model.addAttribute("success", "パスワードは正常にリセットされました.");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "reset_password";
	}
}
