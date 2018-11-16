package com.stolfa.marketsurveys.response;

import java.util.List;

public class Page {

	private Integer number;

	private List<Question> questions;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}