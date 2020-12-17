package com.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.entity.HourlyEmployee;
import com.employee.entity.Manager;
import com.employee.entity.SalariedEmployee;
import com.employee.exception.InvalidInputException;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static Map<Integer,Employee> empList;
	static {
		String[] firstnames = {"Benjamin", "Angel", "Oliver", "Lucas", "Nora"};
		String[] lastnames = {" Roberts", " Lee", " Winterberg", " Smith", " Brown"};
		empList = new HashMap<>();
		int count = 0;
		while (count<30) {
			if(count<10)
				empList.put(1000+count,new HourlyEmployee(1000+count, firstnames[count%5]+lastnames[count%5]));
			else if(count>=10 && count<20)
				empList.put(1000+count,new SalariedEmployee(1000+count, firstnames[count%5]+lastnames[count%5]));
			if(count>=20)
				empList.put(1000+count,new Manager(1000+count, firstnames[count%5]+lastnames[count%5]));
			count++;
		}
		empList.get(1001).work(100);
	}
	@GetMapping("/all")
	public Object[] getAllEmployee(){
		return empList.values().toArray();
	}
	@GetMapping("/updateworkday")
	public ResponseEntity<Double> updateWorkday(@RequestParam(value="id") int id, @RequestParam(value="days")int days) {
		double vacationDays = empList.get(id).work(days);
		return new ResponseEntity<>(vacationDays, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/takevacation")
	public ResponseEntity<Double> takeVavation(@RequestParam(value="id") int id, @RequestParam(value="days")int days)
	{
		Employee emp = empList.get(id);
		if(emp == null)
			return new ResponseEntity<>(0.0, HttpStatus.NOT_FOUND);
		else {
			try {
				double leftDays = emp.takeVacation(days);
				return new ResponseEntity<>(leftDays, HttpStatus.ACCEPTED);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
			}
		}
		
	}
	

}
