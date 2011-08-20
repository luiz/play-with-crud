package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	@Required
	public String email;
	@Required
	public String password;
	@OneToMany(mappedBy="owner")
	public List<Task> tasks = new ArrayList<Task>();

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public static User connect(String email, String password) {
		return find("byEmailAndPassword", email, password).first();
	}
}
