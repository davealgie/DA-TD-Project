package com.qa.tdl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.models.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>{

}