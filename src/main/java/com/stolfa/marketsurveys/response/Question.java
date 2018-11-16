package com.stolfa.marketsurveys.response;

public class Question {

	private Long id;
	
	/* heading or question */
	private String heading;

	private Integer position;

	/*
	 * Single Choice, Multiple Choice, Image Choice, Matrix - Single, Open
	 * Ended, Demographic
	 */
	private String family;

	private Answers answers;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
