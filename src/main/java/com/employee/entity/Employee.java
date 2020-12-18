package com.employee.entity;

import com.employee.exception.InvalidInputException;

public abstract class Employee {
	private int id;
	private String name;
	protected double vacationDays;
	protected int workdays;

	
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.vacationDays = 0; 

	}
	
	public int getWorkdays() {
		return workdays;
	}

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getVacationDays() {
		return vacationDays;
	}

	public abstract double work(int days);
	public double takeVacation(double days) throws InvalidInputException {
		if(days > vacationDays) {
			throw new InvalidInputException("The vacation days you applied are bigger than your accumulated days, your available vacation days are "+vacationDays);
		}
		else {
			this.vacationDays -= days;
			return this.vacationDays;
		}
	}
	
	
	

}
