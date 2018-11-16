package com.stolfa.marketsurveys.response;

import java.util.List;

public class Answers {
	private List<Content> choices;
	private List<Content> rows;

	public List<Content> getChoices() {
		return choices;
	}

	public void setChoices(List<Content> choices) {
		this.choices = choices;
	}

	public List<Content> getRows() {
		return rows;
	}

	public void setRows(List<Content> rows) {
		this.rows = rows;
	}

}