package com.employee.entity;

public class SalariedEmployee extends Employee{

	public SalariedEmployee(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double work(int days) {
		// TODO Auto-generated method stub
			super.workdays=days;
			super.vacationDays = Math.round((1.5/26.0)*days*100)/100;
			return super.vacationDays;
//		}

	}
}
