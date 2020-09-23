package com.ucelebi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ucelebi.models.Publisher;
import com.ucelebi.service.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ModelAndView createPublisher(@RequestParam(name = "id",required = false) int id,
										@RequestParam(name = "name") String publisherName,
										@RequestParam(name = "description") String description) {
		
		Publisher publisher=new Publisher(id,publisherName,description);
		
		publisherService.createPublisher(publisher);

		return new ModelAndView("redirect:/home/publishers");
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	public ModelAndView deletePublisher(@PathVariable(name = "id") int id) {
		
		
		publisherService.deletePublisher(id);

		return new ModelAndView("redirect:/home/publishers");
	}
	
	
}
