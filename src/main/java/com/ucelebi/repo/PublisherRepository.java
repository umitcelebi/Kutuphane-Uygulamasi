package com.ucelebi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucelebi.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	Publisher findById(int id);
}
