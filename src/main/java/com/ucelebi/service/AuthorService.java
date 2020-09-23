package com.ucelebi.service;

import java.util.List;

import com.ucelebi.models.Author;

public interface AuthorService {
	
	boolean createAuthor(Author author);
	boolean deleteAuthor(int id);
	
	List<Author> allAuthors();
	Author getOne(int id);
}
