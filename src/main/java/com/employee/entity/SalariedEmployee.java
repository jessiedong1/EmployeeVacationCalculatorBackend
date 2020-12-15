package com.employee.entity;

public class SalariedEmployee extends Employee{

	public SalariedEmployee(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double work(int days) {
		// TODO Auto-generated method stub
//		if(days <= 0 || days >260) {
//			throw new RuntimeException("Please provide a value between 0(exclude) to 260");
//		}
//		else {
			//Hourly employees accumulate 10 vacation days during the work year.so the rate should be 
			super.workdays=days;
			super.vacationDays = Math.round((1.5/26.0)*days*100)/100;
			return super.vacationDays;
//		}

	}
}
