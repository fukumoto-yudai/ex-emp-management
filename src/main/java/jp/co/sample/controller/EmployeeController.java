package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	
	@ModelAttribute
	private UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	
	
	/*
	 * employeeControllerクラスの定義
	 * showListメソッドを呼び出して、従業員一覧（List)を取得
	 *
	 */
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeesList=employeeService.showList();
		model.addAttribute("employeeList", employeesList);
		return "employee/list";	
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		Employee employee=employeeService.showDetail(Integer.parseInt(id));
			model.addAttribute("employee", employee);
			return "employee/detail";
	}
	
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		int dependentsCount=Integer.parseInt(form.getId());
		Employee employee=employeeService.showDetail(dependentsCount);
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
		
	}


	
	

}
