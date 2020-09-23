package com.ucelebi.service;

import java.util.List;

import com.ucelebi.models.Publisher;

public interface PublisherService {
	boolean createPublisher(Publisher publisher);
	boolean deletePublisher(int id);
	
	List<Publisher> allPublisher();
	Publisher getOne(int id);
}
