package com.qa.tdl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.tdl.seleniumtests.ToDopageTest;

public class ToDoSeleniumTest {
	private WebDriver driver;

	@BeforeEach
	public void setup() {

		/*
		 * ChromeOptions options = new ChromeOptions(); options.setHeadless(true);
		 */

		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

	}

	@Test
	public void checkFooter() throws InterruptedException {
		ToDopageTest ToDopage = PageFactory.initElements(driver, ToDopageTest.class);

		this.driver.get(ToDopage.url);

		assertTrue(ToDopage.checkFooter().contains("Project created by David Algie, August 2021"));
	}

	@Test
	public void checkTitle() throws InterruptedException {
		ToDopageTest ToDopage = PageFactory.initElements(driver, ToDopageTest.class);

		this.driver.get(ToDopage.url);

		assertTrue(ToDopage.checkTitle().contains("Read todo list"));
	}

	@Test
	public void testCreate() throws InterruptedException {

		ToDopageTest ToDopage = PageFactory.initElements(driver, ToDopageTest.class);

		String name = "run a test";

		int priority = 1;

		String create = "To do created";

		this.driver.get(ToDopage.url);

		ToDopage.enterNewToDo(name, priority);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		assertThat(ToDopage.getCreateMsg().getText()).contains(create);

	}

	@Test
	public void testRead() throws InterruptedException {

		ToDopageTest ToDopage = PageFactory.initElements(driver, ToDopageTest.class);

		this.driver.get(ToDopage.url);

		String readTest = "Run a test";

		ToDopage.readBtn();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		assertThat(ToDopage.readAll().getText()).contains(readTest);

	}

	@Test
	public void testUpdate() throws InterruptedException {

		ToDopageTest ToDopage = PageFactory.initElements(driver, ToDopageTest.class);
		this.driver.get(ToDopage.url);
		String update = "To Do Updated";

		ToDopage.insertIdUpdate("37");
		ToDopage.insertNameInputUpdate("Buy a guitar");
		ToDopage.insertPriorityInputUpdate(3);

		ToDopage.updateBtn();

		assertThat(ToDopage.getUpdateMsg().getText()).contains(update);
	}

	@Test
	public void testDelete() throws InterruptedException {
		ToDopageTest ToDopageTest = PageFactory.initElements(driver, ToDopageTest.class);
		this.driver.get(ToDopageTest.url);

		ToDopageTest.insertIdDelete("36");

		ToDopageTest.deleteBtn();

		assertThat(ToDopageTest.getDeleteMsg().getText().contains("Delete To Do"));
	}

	@AfterEach
	public void teardown() {
		this.driver.quit();
	}
}
