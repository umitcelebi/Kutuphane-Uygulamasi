package com.ucelebi.service;

import java.util.List;

import com.ucelebi.models.Book;

public interface BookService {
	Book findById(int id);
	
	List<Book> allBook();
	List<Book> findByBookName(String bookName);
	List<Book> findBySeriesName(String seriesName);
	List<Book> findByIsbn(String isbn);
	List<Book> findByAuthorName(String name);
	
	boolean createBook(Book book);
	boolean deleteBook(int id);
}
