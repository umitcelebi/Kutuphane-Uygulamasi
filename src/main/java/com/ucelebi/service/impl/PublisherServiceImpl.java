package com.ucelebi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucelebi.models.Publisher;
import com.ucelebi.repo.PublisherRepository;
import com.ucelebi.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public boolean createPublisher(Publisher publisher) {
		try {
			publisherRepository.save(publisher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletePublisher(int id) {
		try {
			publisherRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Publisher> allPublisher() {
		return publisherRepository.findAll();
	}

	@Override
	public Publisher getOne(int id) {
		return publisherRepository.findById(id);
	}

}
