package com.pccw.system.model;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private String publishedDate;
	private Float price;
	
	public Book(String title, String author, String isbn, String publishedDate,
			Float price) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publishedDate = publishedDate;
		this.price = price;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
	

}
