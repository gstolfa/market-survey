package com.stolfa.marketsurveys.response;

import java.util.List;

public class Survey {

	private Long id;
	private String title;
	
	/* Survey category chosen when creating the survey	*/
	private String category;
	
	/* ISO 639-1 code for survey language */
	private String language;
	
	/* Number of questions in survey	 */
	private Integer question_count; 
	
	/* Number of pages in survey */
	private Integer page_count;
	
	/* Date and time when survey was created in format YYYY-MM-DDTHH:MM:SS  */
	private String date_created;
	
	private List<Page> pages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getQuestion_count() {
		return question_count;
	}

	public void setQuestion_count(Integer question_count) {
		this.question_count = question_count;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	
	
	
}
