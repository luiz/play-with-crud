package controllers;

import java.util.List;

import models.Task;
import models.User;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Tasks extends Controller {
	private static User loggedUser;
	@Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            loggedUser = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", loggedUser);
        }
    }
	public static void index() {
		List<Task> tasks = loggedUser.tasks;
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

	public static void newTaskForm() {
		render();
	}

	public static void create(@Valid Task task) {
		if (validation.hasErrors()) {
			validation.keep();
			newTaskForm();
		} else {
			task.owner = loggedUser;
			task.save();
			index();
		}
	}
}
