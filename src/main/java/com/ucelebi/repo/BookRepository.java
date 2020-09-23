package com.ucelebi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ucelebi.models.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findById(int id);
	List<Book> findByBookName(String bookName);
	List<Book> findBySeriesName(String seriesName);
	List<Book> findByIsbn(String isbn);
	
	@Query("select b from Book b where b.author.name=?1")
	List<Book> findByAuthorName(String name);
	
	@Modifying
	@Transactional
	@Query("delete from Book b where b.id=?1")
	int deleteBook(int id); 
}
