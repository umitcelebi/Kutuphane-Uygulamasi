package com.ucelebi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ucelebi.models.Author;
import com.ucelebi.models.Book;
import com.ucelebi.models.Publisher;
import com.ucelebi.repo.UserRepository;
import com.ucelebi.service.impl.AuthorServiceImpl;
import com.ucelebi.service.impl.BookServiceImpl;
import com.ucelebi.service.impl.PublisherServiceImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LibraryApplication implements CommandLineRunner {

	@Autowired
	BookServiceImpl bookServiceImpl;

	@Autowired
	AuthorServiceImpl authorServiceImpl;

	@Autowired
	PublisherServiceImpl publisherServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// createBook();
		// allBook();
		// findByBookName();
		// findBySeriesName();
		// findByIsbn();
		// findByAuthorName();

		// addAuthorAndPublisher();

		// createAuthor();
		// deleteAuthor();
		// allAuthors();

		// createPublisher();
		// deletePublisher();
		// allPublisher();
	}

	private void addAuthorAndPublisher() {
		bookServiceImpl.addAuthorAndPublisher(1, 1, 1);
	}

	private void allPublisher() {
		List<Publisher> publishers = publisherServiceImpl.allPublisher();
		for (Publisher publisher : publishers) {
			System.out.println(publisher);
		}
	}

	private void deletePublisher() {
		publisherServiceImpl.deletePublisher(4);
	}

	private void createPublisher() {
		Publisher publisher = new Publisher("İş Bankası");
		publisherServiceImpl.createPublisher(publisher);
	}

	private void allAuthors() {
		List<Author> authors = authorServiceImpl.allAuthors();
		for (Author author : authors) {
			System.out.println(author);
		}
	}

	private void deleteAuthor() {
		authorServiceImpl.deleteAuthor(3);
	}

	private void createAuthor() {
		Author author = new Author("Kemal","açıklama");
		authorServiceImpl.createAuthor(author);
	}

	private void findByAuthorName() {
		List<Book> book = bookServiceImpl.findByAuthorName("Necati Bey");
		for (Book book2 : book) {
			System.out.println(book2);
		}
	}

	private void findByIsbn() {
		List<Book> book = bookServiceImpl.findByIsbn("OOD");
		for (Book book2 : book) {
			System.out.println(book2);
		}
	}

	private void findBySeriesName() {
		List<Book> book = bookServiceImpl.findBySeriesName("OD Serisi");
		for (Book book2 : book) {
			System.out.println(book2);
		}
	}

	private void findByBookName() {
		List<Book> book = bookServiceImpl.findByBookName("Efsane");
		for (Book book2 : book) {
			System.out.println(book2);
		}
	}

	private void allBook() {
		List<Book> allBooks = bookServiceImpl.allBook();
		for (Book book : allBooks) {
			System.out.println(book);
		}
	}

	private void createBook() {
		Book book = new Book();
		book.setBookName("Efsane");
		book.setDescription("efso");
		book.setIsbn("IP");
		book.setSeriesName("OD Serisi");

		bookServiceImpl.createBook(book);
	}

}
