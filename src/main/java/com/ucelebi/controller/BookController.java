package com.ucelebi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublisherService publisherService;
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ModelAndView createBook(@RequestParam(name = "id",required = false) int id,
									@RequestParam(name = "bookName") String bookName,
									@RequestParam(name = "subName") String subname,
									@RequestParam(name = "seriesName") String seriesName,
									@RequestParam(name = "isbn") String isbn,
									@RequestParam(name = "description") String description,
									@RequestParam(name = "author",required = false) int authorId,
									@RequestParam(name = "publisher",required = false) int publisherId) {
		
		Author author=authorService.getOne(authorId);
		Publisher publisher=publisherService.getOne(publisherId);
		
		Book book=new Book(id,bookName, subname, seriesName, isbn, description,author,publisher);
				
		bookService.createBook(book);

		return new ModelAndView("redirect:/home/books");
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	public ModelAndView deleteBook(@PathVariable int id) {
		
		//Book book=bookService.findById(id);
		
		bookService.deleteBook(id);

		return new ModelAndView("redirect:/home/books");
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ModelAndView updateBook(@RequestParam(name = "id") int id,
									@RequestParam(name = "bookName") String bookName,
									@RequestParam(name = "subName") String subname,
									@RequestParam(name = "seriesName") String seriesName,
									@RequestParam(name = "isbn") String isbn,
									@RequestParam(name = "description") String description) {
		
		Book book=new Book(bookName, subname, seriesName, isbn, description);
		book.setId(id);
		
		bookService.createBook(book);

		return new ModelAndView("redirect:/home/books");
	}
}
