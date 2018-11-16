package com.stolfa.marketsurveys.response.builder;

import com.stolfa.marketsurveys.response.Question;

public class QuestionBuilder {

	private Long id;
	private String heading;
	private Integer position;
	private String family;

	public QuestionBuilder(Long id) {
		this.id = id;
	}

	public QuestionBuilder(com.stolfa.marketsurveys.model.Question question) {
		this.id = question.getId();
		this.heading = question.getHeading();
		this.position = question.getPosition();
		this.family = question.getFamily();
	}

	public QuestionBuilder heading(String heading) {
		this.heading = heading;
		return this;
	}

	public QuestionBuilder position(Integer position) {
		this.position = position;
		return this;
	}

	public QuestionBuilder family(String family) {
		this.family = family;
		return this;
	}

	public Question build() {
		Question question = new Question();
		question.setId(id);
		question.setHeading(heading);
		question.setPosition(position);
		question.setFamily(family);
		return question;
	}

}
