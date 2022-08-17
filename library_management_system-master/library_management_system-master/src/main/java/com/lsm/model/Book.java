package com.lsm.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Book {

	private int id;

	@NotBlank(message = "name is required")
	@Size(min = 3,max = 30,message = "name size must be between 3 to 30 character")
	private String name;

	@NotBlank(message = "name is required")
	@Size(min = 5,max = 50,message = "title length should be min-5 char and max-50")
	private String title;

	@NotBlank(message = "name is required")
	@Size(min = 3,max = 20,message = "author name should be of length 3")
	private String author;

	private String description;

	public Book() {
		super();
	}

	public Book(int id, String name, String title, String author, String description) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.author = author;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", title=" + title + ", authors=" + author + ", description="
				+ description + "]";
	}

}
