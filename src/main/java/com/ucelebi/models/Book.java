package com.ucelebi.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bookName;
	private String subName;
	private String seriesName;
	private String isbn;
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	public Book() {}
	
	
	public Book(int id, String bookName, String subName, String seriesName, String isbn, String description,
			Author author, Publisher publisher) {
		this.id = id;
		this.bookName = bookName;
		this.subName = subName;
		this.seriesName = seriesName;
		this.isbn = isbn;
		this.description = description;
		this.author = author;
		this.publisher = publisher;
	}

	public Book(String bookName, String subName, String seriesName, String isbn, String description) {
		this.bookName = bookName;
		this.subName = subName;
		this.seriesName = seriesName;
		this.isbn = isbn;
		this.description = description;
	}

	public Book(String bookName, String subName, String seriesName, String isbn, String description, Author author,
			Publisher publisher) {
		this.bookName = bookName;
		this.subName = subName;
		this.seriesName = seriesName;
		this.isbn = isbn;
		this.description = description;
		this.author = author;
		this.publisher = publisher;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "Book ["+ "bookName=" + bookName + ", subName=" + subName  +"]";
	}
	
}
