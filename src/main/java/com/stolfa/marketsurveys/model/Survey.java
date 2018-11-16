package com.stolfa.marketsurveys.model;

import java.sql.Date;

public class Survey {

	private Long id;
	private String title;
	private String category;
	private String language;
	private Date dateCreated;
	private Long idTarget;
	private String country;

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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getIdTarget() {
		return idTarget;
	}

	public void setIdTarget(Long idTarget) {
		this.idTarget = idTarget;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
