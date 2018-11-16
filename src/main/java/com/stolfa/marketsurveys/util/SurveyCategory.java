package com.stolfa.marketsurveys.util;

public enum SurveyCategory {

	COMPUTING_INTERNET (1, "Computing & Internet"), 
	SPORT (2, "Sports"), 
	FOOD (3, "Food"), 
	CARS_MOTORS (4,"Cars & Motors"), 
	HOME_GARDEN (5, "Home & Garden"), 
	BUSINESS (6, "Business"), 
	HEALTH (7, "Health"), 
	TRAVEL (8,"Travel"), 
	BEAUTY_COSMETICS (9,"Beauty & Cosmetics"), 
	NEWS (10, "News"), 
	LIFESTYLE (11, "Lifestyle"), 
	OTHER (12, "Other");

	private Integer id;
	private String description;

	SurveyCategory(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer id() {
		return id;
	}

	public String description() {
		return description;
	}

}
