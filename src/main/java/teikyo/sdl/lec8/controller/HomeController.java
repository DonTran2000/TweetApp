package teikyo.sdl.lec8.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teikyo.sdl.lec8.dao.Dao;
import teikyo.sdl.lec8.entity.Department;
import teikyo.sdl.lec8.entity.Student;
import teikyo.sdl.lec8.entity.Tweet;
import teikyo.sdl.lec8.forms.LoginSession;
import teikyo.sdl.lec8.forms.RegistrationForm;
import teikyo.sdl.lec8.forms.TweetForm;

@Controller
@SessionAttributes("LoginSes")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@ModelAttribute("LoginSes")
	public LoginSession getLoginSession() {
		return new LoginSession();
	}

	@ModelAttribute("RegistForm")
	public RegistrationForm getRegistrationForm() {
		return new RegistrationForm();
	}

	@ModelAttribute("TweetForm")
	public TweetForm getTweetForm() {
		return new TweetForm();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		if (user.getId() != null && user.getPass() != null) {
			model.addAttribute("user", "Hello!! " + user.getName());
		}
		Dao dao = new Dao();
		List<Tweet> result;
		result = dao.getTweetList();
		model.addAttribute("result", result);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String log(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		if (user.getId() != null && user.getPass() != null) {
			model.addAttribute("LoginSes", new LoginSession()); // logout
			return "redirect:./";
		}
		return "login";
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String login(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		Dao dao = new Dao();
		Student st;
		st = dao.login(user.getId(), user.getPass());
		if (st != null) {
			user.setName(st.getName());
			return "redirect:./";
		}
		model.addAttribute("message", "学籍番号・パスワードは正しくない！");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		if (user.getId() != null && user.getPass() != null) {
			user.setId(null);
			user.setPass(null);
			return "redirect:./";
		}
		return null;
	}

	@RequestMapping(value = "/tweetForm", method = RequestMethod.GET)
	public String tweetForm(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		if (user.getId() != null) {
			model.addAttribute("user", "Hello!! " + user.getName());
			return "tweet_form";
		}
		return "redirect:./";
	}

	@RequestMapping(value = "/tweetProcess", method = RequestMethod.POST)
	public String tweetProcess(@ModelAttribute("LoginSes") LoginSession user,
			@ModelAttribute("TweetForm") TweetForm form, BindingResult result, Model model) throws ParseException {
		if (user.getId() != null) {
			Dao dao = new Dao();

			Tweet newTweet = new Tweet();
			newTweet.setContent(form.getContent());

			newTweet.setTweettime(new Date());

			Student st = dao.getStudentById(user.getId());
			newTweet.setStudent(st);

			boolean r = dao.insertTweet(newTweet);

			System.out.println(r);

			if (r) {
				System.out.println("vao");
				return "redirect:./";
			} else {
				return "tweet_form";
			}
		}
		return "redirect:./";
	}

	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public String registForm(@ModelAttribute("LoginSes") LoginSession user, Model model) {
		if (user.getId() != null) {
			return "redirect:./";
		}
		Dao dao = new Dao();
		model.addAttribute("deptItems", dao.getDepartmentList());

		return "regist_form";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkRegistration(
			@Validated @ModelAttribute("RegistForm") RegistrationForm form,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		Dao dao = new Dao();

		if (result.hasErrors()) {
			model.addAttribute("deptItems", dao.getDepartmentList());
			return "regist_form";
		}

		String stid = form.getId();

		Student stExist = dao.getStudentById(stid);

		//データベースの中には、存在している
		if (stExist != null && stid.equals(stExist.getStid())) {
			String stExist_mess = stid + " 学籍番号は存在している！";
			redirectAttributes.addFlashAttribute("stExist_mess", stExist_mess);
			return "redirect:/registForm";
		}

		// パスワードと確認パスワードは一致しない場合
		if (!form.getPass().equals(form.getConfPass())) {
			redirectAttributes.addFlashAttribute("mess_pass", "パスワードと確認パスワードは一致しない！");
			return "redirect:/registForm";
		}

		// 所属学科は画面に表示する
		Department dept = dao.getDepartment(form.getDept());
		String deptname = dept.getDeptname();
		form.setDeptName(deptname);
		model.addAttribute("RegistForm", form);

		return "regist_check";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submitRegistration(
			@ModelAttribute("RegistForm") RegistrationForm form,
			Model model,
			RedirectAttributes redirectAttributes) {
		Dao dao = new Dao();

		Student newSt = new Student();
		newSt.setStid(form.getId());
		newSt.setName(form.getName());
		newSt.setPassword(form.getPass());

		Department dept = new Department(form.getDept(), "");
		newSt.setDepartment(dept);

		boolean result = dao.insertStudent(newSt);

		if (result) {
			// get deptname, form set deptname and output screen
			Department d = dao.getDepartment(form.getDept());
			String deptname = d.getDeptname();
			form.setDeptName(deptname);
			model.addAttribute("RegistForm", form);
			return "regist_success";
		} else {
			redirectAttributes.addFlashAttribute("mess_error", "学生の登録に失敗しました！");
			return "redirect:/registForm";
		}
	}

}
