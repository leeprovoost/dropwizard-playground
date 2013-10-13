package com.leeprovoost.dropwizard_playground.service;

public class RegistrationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1807994310488778032L;

	public RegistrationNotFoundException(String id) {
        super(String.format("Cannot find registration with id %s", id));
    }
}
