package com.qa.tdl.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.models.ToDo;
import com.qa.tdl.repositories.ToDoRepository;

@Service
public class ToDoService {
	
	private ToDoRepository todoRepository;
	
	@Autowired
	public ToDoService(ToDoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<ToDo> getToDos() {
		List<ToDo> todosInDb = todoRepository.findAll();
		
		return todosInDb;
	}
	
	public ToDo updateToDo(int id, ToDo todo) {
		// if (!todoRepository.existsById(id)) throw new EntityNotFoundException();
		ToDo todoInDb = todoRepository.findById(id).get();
		
		todoInDb.setName(todo.getName());
		todoInDb.setPriority(todo.getPriority());
		
		ToDo updatedToDo = todoRepository.saveAndFlush(todoInDb);
		return updatedToDo;
		
	}
	
	public ToDo getToDobyId(int id) {
		if (!todoRepository.existsById(id)) throw new EntityNotFoundException();
		return todoRepository.getById(id);
	}
	
	
	public ToDo createToDo(ToDo todo) {
		ToDo savedToDo = this.todoRepository.saveAndFlush(todo);
		
		return savedToDo;
	}
	
		public String deleteToDo(int id) {
	        todoRepository.deleteById(id);
	        if (todoRepository.existsById(id)) {
	            return "Not deleted" + id;
	        }
	        else {
	            return id + "has been deleted.";
	        }
	    
	        
	}
	
	
}
