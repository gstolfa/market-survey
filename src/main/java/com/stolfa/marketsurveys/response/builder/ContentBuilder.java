package com.stolfa.marketsurveys.response.builder;

import com.stolfa.marketsurveys.response.Content;
import com.stolfa.marketsurveys.response.Image;

public class ContentBuilder {

	private Long id;
	private String text;
	private Image image;
	private Integer position;
	private Boolean visible;
	private Boolean required;
	private String type;
	
	public ContentBuilder id(Long id){
		this.id = id;
		return this;
	}
	public ContentBuilder text(String text){
		this.text = text;
		return this;
	}
	public ContentBuilder image(Image image){
		this.image = image;
		return this;
	}
	public ContentBuilder position(Integer position){
		this.position = position;
		return this;
	}
	public ContentBuilder visible(Boolean visible){
		this.visible = visible;
		return this;
	}
	public ContentBuilder required(Boolean required){
		this.required = required;
		return this;
	}
	public ContentBuilder type(String type){
		this.type = type;
		return this;
	}
	
	public ContentBuilder(Long id){
		this.id = id;
	}

	public ContentBuilder(com.stolfa.marketsurveys.model.Content content){
		this.id = content.getId();
		this.text = content.getText();
		this.image = new Image(content.getImageUrl());
		this.position = content.getPosition();
		this.visible = content.getVisible();
		this.required = content.getRequired();
		this.type = content.getAnswerType();
	}
	
	public Content build(){
		Content content = new Content();

		content.setId(id);
		content.setText(text);
		content.setImage(image);
		content.setPosition(position);
		content.setVisible(visible);
		content.setRequired(required);
		content.setType(type);
		
		return content;
	}
	
	
	
}
