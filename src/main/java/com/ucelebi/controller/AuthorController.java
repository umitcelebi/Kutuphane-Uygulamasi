package com.ucelebi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ucelebi.models.Author;
import com.ucelebi.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
		
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ModelAndView createAuthor(	@RequestParam(name = "id",required = false) int id,
										@RequestParam(name = "name") String authorName,
										@RequestParam(name = "description") String description) {
		
		Author author=new Author(id,authorName,description);
		
		authorService.createAuthor(author);

		return new ModelAndView("redirect:/home/authors");
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	public ModelAndView deleteAuthor(@PathVariable(name = "id") int id) {
		
		
		authorService.deleteAuthor(id);

		return new ModelAndView("redirect:/home/authors");
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ModelAndView updateAuthor(@RequestParam(name = "name") int id,@RequestParam(name = "name") String name) {
		
		Author author=authorService.getOne(id);
		author.setName(name);
		
		authorService.createAuthor(author);

		return new ModelAndView("redirect:/home/authors");
	}
}
