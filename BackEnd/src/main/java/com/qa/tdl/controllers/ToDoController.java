package com.qa.tdl.controllers;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.models.ToDo;
import com.qa.tdl.service.ToDoService;

@RestController
@RequestMapping("/todo")
@CrossOrigin
public class ToDoController {
	
	private ToDoService todoService;
	
	@Autowired //triggers dependency injection
	public ToDoController(ToDoService ToDoService) {
		this.todoService = ToDoService;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDo> updateTodo(@PathVariable("id") int id, @RequestBody ToDo todo) {
		ToDo data = this.todoService.updateToDo(id, todo);

		return new ResponseEntity<ToDo>(data, HttpStatus.ACCEPTED);
	}
	
	@GetMapping ("/readAll")
	public ResponseEntity<List<ToDo>> getToDos() {
		List<ToDo> data = this.todoService.getToDos();
		
		return new ResponseEntity<List<ToDo>>(data, HttpStatus.OK);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<ToDo> getTodobyId(@PathVariable("id")int id) {
		ToDo data = this.todoService.getToDobyId(id);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	@PostMapping("/createTodo")
	public ResponseEntity<ToDo> createTodo(@Valid @RequestBody ToDo todo) {
		ToDo data = this.todoService.createToDo(todo);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(URI.create("localhost:8080/" + data.getId())); // setter for Location header already exists
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<ToDo>(data, headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> deleteTodo(@PathVariable("id") Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("localhost:8080/"));
		this.todoService.deleteToDo(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

