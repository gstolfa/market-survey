package com.stolfa.marketsurveys.util;

public enum QuestionFamily {

	SINGLE_CHOICE(1, "Single Choice"), 
	MULTIPLE_CHOICE(2, "Multiple Choice"), 
	IMAGE_CHOICE(3, "Image Choice"),
	MATRIX(4, "Matrix - Single"),
	OPEN_ENDED(5, "Open Ended"),
	DEMPGRAPHIC(6, "Demographic");

	private Integer id;
	private String description;

	QuestionFamily(Integer id, String description) {
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
