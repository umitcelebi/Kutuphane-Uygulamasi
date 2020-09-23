package com.ucelebi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ucelebi.models.Author;
import com.ucelebi.models.Book;
import com.ucelebi.models.Publisher;
import com.ucelebi.service.AuthorService;
import com.ucelebi.service.BookService;
import com.ucelebi.service.PublisherService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublisherService publisherService;
	
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	
	@RequestMapping(value = "/books",method = RequestMethod.GET)
	public ModelAndView allBook() {
		
		List<Book> books=bookService.allBook();

		return new ModelAndView("home","books",books);
	}
	
	
	@RequestMapping(value = "/searc_book",method = RequestMethod.GET)
	public ModelAndView searchBook(@RequestParam(value = "bookName",required = false)String bookName,
									@RequestParam(value = "seriesName",required = false)String seriesName,
									@RequestParam(value = "isbn",required = false)String isbn,
									@RequestParam(value = "authorName",required = false)String authorName) {
		
		List<Book> books = null;
		
		if (!bookName.isEmpty()) {
           books=bookService.findByBookName(bookName);
        }else if(!seriesName.isEmpty()) {
        	books=bookService.findBySeriesName(seriesName);
        }else if(!isbn.isEmpty()) {
        	books=bookService.findByIsbn(isbn);
        }else if(!authorName.isEmpty()) {
        	books=bookService.findByAuthorName(authorName);
        }else {
        	
        }
		return new ModelAndView("home","books",books);
	}
	
	@RequestMapping(value = "/authors",method = RequestMethod.GET)
	public ModelAndView allAuthors() {
		
		List<Author> authors=authorService.allAuthors();

		return new ModelAndView("home","authors",authors);
	}
	
	@RequestMapping(value = "/publishers",method = RequestMethod.GET)
	public ModelAndView allPublishers() {
		
		List<Publisher> publishers=publisherService.allPublisher();

		return new ModelAndView("home","publishers",publishers);
	}
	
	@GetMapping("/cbook")
	public ModelAndView createBook(@ModelAttribute Book book) {
		
		List<Author> authors=authorService.allAuthors();
		List<Publisher> publishers=publisherService.allPublisher();
		
		ModelAndView modelAndView=new ModelAndView("createBook");
		modelAndView.addObject("authors", authors);
		modelAndView.addObject("publishers", publishers);
				
		return modelAndView;
	}
	
	@GetMapping("/cauthor")
	public String createAuthor(@ModelAttribute Author author) {
		
		return "createAuthor";
	}
	
	@GetMapping("/cpublisher")
	public String createPublisher(@ModelAttribute Publisher publisher) {
		
		return "createPublisher";
	}
	
	@GetMapping("/uBook/{id}")
	public ModelAndView updateBook(@PathVariable int id) {
		
		List<Author> authors=authorService.allAuthors();
		List<Publisher> publishers=publisherService.allPublisher();
		Book book=bookService.findById(id);
		
		ModelAndView modelAndView=new ModelAndView("createBook");
		modelAndView.addObject("authors", authors);
		modelAndView.addObject("publishers", publishers);
		modelAndView.addObject("book", book);
		
		return modelAndView;
	}
	
	@GetMapping("/uAuthor/{id}")
	public ModelAndView updateAuthor(@PathVariable int id) {
		
		Author author=authorService.getOne(id);
		ModelAndView modelAndView=new ModelAndView("createAuthor");
		modelAndView.addObject("author", author);
		
		return modelAndView;
	}
	
	@GetMapping("/uPublisher/{id}")
	public ModelAndView updatePublisher(@PathVariable int id) {
		
		Publisher publisher=publisherService.getOne(id);
		ModelAndView modelAndView=new ModelAndView("createPublisher");
		modelAndView.addObject("publisher", publisher);
		
		return modelAndView;
	}
}
