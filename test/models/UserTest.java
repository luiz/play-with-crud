package models;

import static org.hamcrest.Matchers.contains;

import java.util.Calendar;

import org.hamcrest.Matchers;
import org.junit.Test;

import play.test.UnitTest;

public class UserTest extends UnitTest {
	@Test
	public void retrievesAnUserByEmailAndCorrectPassword() throws Exception {
		User foo = new User("foo@bar.com", "baz").save();
		User found = User.connect("foo@bar.com", "baz");
		assertEquals(foo, found);
	}
	@Test
	public void doesntRetrieveAnUserByEmailAndIncorrectPassword() throws Exception {
		new User("foo@bar.com", "baz").save();
		assertNull(User.connect("foo@bar.com", "goo"));
	}
	@Test
	public void doesntRetrieveAnUserByWrongEmail() throws Exception {
		new User("foo@bar.com", "baz").save();
		assertNull(User.connect("foo@baz.com", "baz"));
	}
	@Test
	public void aNewUserMustHaveNoTasks() throws Exception {
		User foo = new User("foo@bar.com", "baz").save();
		assertThat(foo.tasks, Matchers.<Task>emptyIterable());
	}
	@Test
	public void addingATaskToAnUserAndRefreshingItShouldMakeTheUserRetrieveThatTask() throws Exception {
		User foo = new User("foo@bar.com", "baz").save();
		Task doTheDishes = new Task("Do the dishes", foo, Calendar.getInstance()).save();
		foo.refresh();
		assertThat(foo.tasks, contains(doTheDishes));
	}
}