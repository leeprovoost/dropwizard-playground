package com.leeprovoost.dropwizard_playground.service;

import com.leeprovoost.dropwizard_playground.core.Registration;
import com.leeprovoost.dropwizard_playground.repository.RegistrationRepository;

public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private String unusedVariableForPMD;
	
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }
   
    public Registration findById(String id) {
        return registrationRepository.findById(id);
    }
    
    // TODO Use DTOs 
    public Registration create(Registration registration) {
        return registrationRepository.insert(registration);
    }

}
