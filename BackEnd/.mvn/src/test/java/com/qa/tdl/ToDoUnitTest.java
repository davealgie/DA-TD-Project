package com.qa.tdl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;
import com.qa.tdl.models.ToDo;
import com.qa.tdl.repositories.ToDoRepository;
import com.qa.tdl.service.ToDoService;

@SpringBootTest
@ActiveProfiles("test")
public class ToDoUnitTest {

	@MockBean
	private ToDoRepository repo;

	@Autowired
	private ToDoService service;

	@Test
	void testCreateUnit() {

		ToDo runtest = new ToDo("Run a test", 2);
		ToDo runtestWithId = new ToDo(1, "Run a test", 2);

		Mockito.when(this.repo.saveAndFlush(runtest)).thenReturn(runtestWithId);

		assertThat(this.service.createToDo(runtest)).isEqualTo(runtestWithId);

		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(runtest);
	}

	@Test
	void testReadAll() {

		int id = 1;
		ToDo testToDo = new ToDo("This is a test yep", 1);
		testToDo.setId(id);
		List<ToDo> todos = List.of(testToDo);

		// WHEN

		Mockito.when(this.repo.findAll()).thenReturn(todos);

		// THEN
		Assertions.assertThat(this.service.getToDos()).isEqualTo(todos);

		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testUpdate() {

		ToDo newTask = new ToDo(1, "This is a test yep", 1);
		ToDo existingTask = new ToDo(1, "Run some more jeff", 2);

		System.out.println(existingTask);

		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(newTask));
		Mockito.when(this.repo.saveAndFlush(existingTask)).thenReturn(existingTask);

		System.out.println(existingTask);

		assertThat(existingTask).isEqualTo(this.service.updateToDo(1, newTask));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(existingTask);
	}
	
	@Test
	void testDelete() {

		int id = 1;

		// WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		// THEN
		assertThat(this.service.deleteToDo(id)).isEqualTo(id + "has been deleted.");

		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
	}
	 
}