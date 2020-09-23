package com.ucelebi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucelebi.models.Author;
import com.ucelebi.models.Book;
import com.ucelebi.models.Publisher;
import com.ucelebi.repo.AuthorRepository;
import com.ucelebi.repo.BookRepository;
import com.ucelebi.repo.PublisherRepository;
import com.ucelebi.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PublisherRepository publisherRepository; 
	
	@Override
	public Book findById(int id) {
		
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> allBook() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}

	@Override
	public List<Book> findBySeriesName(String seriesName) {
		return bookRepository.findBySeriesName(seriesName);
	}

	@Override
	public List<Book> findByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}

	@Override
	public List<Book> findByAuthorName(String name) {
		return bookRepository.findByAuthorName(name);
	}
	
	@Override
	public boolean createBook(Book book) {
		try {
			bookRepository.save(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean addAuthorAndPublisher(int bookId,int authorId,int publisherId) {
		try {
			Book book=bookRepository.findById(bookId);
			Author author=authorRepository.findById(authorId);
			Publisher publisher=publisherRepository.findById(publisherId);
			book.setAuthor(author);
			book.setPublisher(publisher);
			bookRepository.save(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("HATA...******"+e);
			return false;
		}
	}

	@Override
	public boolean deleteBook(int id) {
		try {
			bookRepository.deleteBook(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
