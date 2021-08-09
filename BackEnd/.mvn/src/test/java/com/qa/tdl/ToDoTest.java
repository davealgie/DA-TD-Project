package com.qa.tdl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdl.models.ToDo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:todo-schema.sql",
		"classpath:todo-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
 @ActiveProfiles("test") 
public class ToDoTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper; // converts requests to json

	private final ToDo testTodo = new ToDo("This is a test yep", 1);

	private final ToDo testTodoId = new ToDo(1, "This is a test yep", 1);

	@Test
	@Order(1)
	void testPost() throws Exception {

		ToDo todo = new ToDo("This is a test yep", 1);

		String ToDoAsJSON = this.mapper.writeValueAsString(todo);

		RequestBuilder mockRequest = post("/todo/createTodo").contentType(MediaType.APPLICATION_JSON).content(ToDoAsJSON);

		ToDo savedToDo = new ToDo(2, "This is a test yep", 1);

		String savedToDoAsJSON = this.mapper.writeValueAsString(savedToDo);

		ResultMatcher matchStatus = status().isCreated();

		ResultMatcher matchBody = content().json(savedToDoAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	public void testReadAll() throws Exception {
		List<ToDo> todos = new ArrayList<>();
		todos.add(testTodo);

		String result = this.mapper.writeValueAsString(todos);

		RequestBuilder mockRequest = get("/todo/readAll").contentType(MediaType.APPLICATION_JSON).content(result);

		List<ToDo> TodoId = new ArrayList<>();
		TodoId.add(testTodoId);

		String savedResult = this.mapper.writeValueAsString(TodoId);

		ResultMatcher matchBody = content().json(savedResult);

		this.mock.perform(mockRequest).andExpect(status().isOk()).andExpect(matchBody);
	}

	@Test
	@Order(2)

	public void testUpdate() throws Exception {

		ToDo newToDo = new ToDo("Test is jeff", 2);
		ToDo ToDoInDb = new ToDo(1, "Test is jeff", 2);
		String newToDoAsJson = this.mapper.writeValueAsString(newToDo);
		String newToDoAsJsonInDb = this.mapper.writeValueAsString(ToDoInDb);

		RequestBuilder mockRequest = put("/todo/update/1").contentType(MediaType.APPLICATION_JSON).content(newToDoAsJson);

		ResultMatcher matchBody = content().json(newToDoAsJsonInDb);

		this.mock.perform(mockRequest).andExpect(status().isAccepted()).andExpect(matchBody);

	}

	@Test
	public void testDelete() throws Exception {
		this.mock.perform(delete("/todo/delete/1")).andExpect(status().isOk());

	}

}