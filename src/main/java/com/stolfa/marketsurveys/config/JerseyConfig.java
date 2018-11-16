package com.stolfa.marketsurveys.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.stolfa.marketsurveys.controller.MarketSurveyController;
import com.stolfa.marketsurveys.errorhandling.BadRequestException;
import com.stolfa.marketsurveys.errorhandling.NotFoundException;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(MarketSurveyController.class);
		register(NotFoundException.class);
		register(BadRequestException.class);
	}

}