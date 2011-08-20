package controllers;

import java.util.List;

import models.Task;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Tasks extends Controller {
	public static void index() {
		List<Task> tasks = Task.all().fetch();
		render(tasks);
	}
	public static void show(Long id) {
		Task task = Task.findById(id);
		if (task == null) {
			notFound();
		} else {
			render(task);
		}
	}
}
