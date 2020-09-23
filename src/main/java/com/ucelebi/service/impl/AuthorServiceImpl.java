package com.ucelebi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucelebi.models.Author;
import com.ucelebi.repo.AuthorRepository;
import com.ucelebi.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepository; 
	
	@Override
	public boolean createAuthor(Author author) {
		
		try {
			authorRepository.save(author);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAuthor(int id) {
		
		try {
			authorRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Author> allAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author getOne(int id) {
		
		return authorRepository.findById(id);
	}

}
