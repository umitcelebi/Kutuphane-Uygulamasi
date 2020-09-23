package com.ucelebi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucelebi.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	Author findById(int id);
}
