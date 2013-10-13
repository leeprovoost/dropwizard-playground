package com.leeprovoost.dropwizard_playground.health;

import com.yammer.metrics.core.HealthCheck;

public class RegistrationHealthCheck extends HealthCheck {

	// TODO implement metrics properly
    public RegistrationHealthCheck() {
        super("registration");
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}