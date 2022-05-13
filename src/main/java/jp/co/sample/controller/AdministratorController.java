package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
		
	}
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	@RequestMapping("/insert")
	public String create(InsertAdministratorForm form
			,RedirectAttributes redirectAttributes) {
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	

}
