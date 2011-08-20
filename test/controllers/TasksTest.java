package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class TasksTest extends FunctionalTest {
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
		Fixtures.deleteDatabase();
		Response response = GET("/tasks");
		assertContentMatch("No tasks", response);
	}

	@Test
	public void listsTasksWithAndWithoutDate() throws Exception {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("initial-data.yml");
		Response response = GET("/tasks");
		assertContentMatch("Buy a new PC", response);
		assertContentMatch("One day...", response);
		assertContentMatch("Finish this example", response);
		assertContentMatch("20/08/2011", response);
	}
}
