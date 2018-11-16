package com.stolfa.marketsurveys.response.builder;

import java.util.List;

import com.stolfa.marketsurveys.response.Page;
import com.stolfa.marketsurveys.response.Survey;

public class SurveyBuilder {

	private Long id;
	private String title;
	private String category;
	private String language;
	private Integer question_count;
	private Integer page_count;
	private String date_created;
	private List<Page> pages;
	
	public SurveyBuilder(com.stolfa.marketsurveys.model.Survey survey){
		this.id = survey.getId();
		this.title = survey.getTitle();
		this.category = survey.getCategory();
		this.language = survey.getLanguage();
		this.date_created = survey.getDateCreated().toString();
	}

	public SurveyBuilder pages(List<Page> pages){
		this.pages = pages;
		return this;
	}
	
	public SurveyBuilder(Long id){
		this.id = id;
	}
	
	public SurveyBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public SurveyBuilder title(String title) {
		this.title = title;
		return this;
	}

	public SurveyBuilder category(String category) {
		this.category = category;
		return this;
	}

	public SurveyBuilder language(String language) {
		this.language = language;
		return this;
	}

	public SurveyBuilder question_count(Integer question_count) {
		this.question_count = question_count;
		return this;
	}

	public SurveyBuilder page_count(Integer page_count) {
		this.page_count = page_count;
		return this;
	}

	public SurveyBuilder date_created(String date_created) {
		this.date_created = date_created;
		return this;
	}

	public Survey build() {
		Survey survey = new Survey();
		survey.setId(id);
		survey.setTitle(title);
		survey.setCategory(category);
		survey.setLanguage(language);
		survey.setQuestion_count(question_count);
		survey.setPage_count(page_count);
		survey.setDate_created(date_created);
		survey.setPages(pages);
		return survey;
	}

}
