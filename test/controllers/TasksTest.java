package controllers;

import models.Task;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class TasksTest extends FunctionalTest {
	@Before
	public void setUp() throws Exception {
		Fixtures.deleteDatabase();
	}

	@Test
	public void rendersAllTasksInIndex() throws Exception {
		Response response = GET("/tasks");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset("UTF-8", response);
		assertContentMatch("Your tasks", response);
	}

	@Test
	public void showsNiceMessageWhenThereAreNoTasks() throws Exception {
		Response response = GET("/tasks");
		assertContentMatch("No tasks", response);
	}

	@Test
	public void listsTasksWithAndWithoutDate() throws Exception {
		Fixtures.loadModels("initial-data.yml");
		Response response = GET("/tasks");
		assertContentMatch("Buy a new PC", response);
		assertContentMatch("One day...", response);
		assertContentMatch("Finish this example", response);
		assertContentMatch("20/08/2011", response);
	}

	@Test
	public void showASpecificTask() throws Exception {
		Fixtures.loadModels("initial-data.yml");
		Task firstTask = Task.all().first();
		assertNotNull(firstTask);
		Response response = GET("/tasks/" + firstTask.id);
		assertContentMatch(firstTask.title, response);
	}

	@Test
	public void tryToShowAnNonExistingTaskGives404() throws Exception {
		assertEquals(0, Task.count());
		Response response = GET("/tasks/1");
		assertStatus(404, response);
	}
}
