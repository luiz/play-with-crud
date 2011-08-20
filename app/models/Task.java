package models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Task extends Model {

	public String title;
	@ManyToOne
	public User owner;
	@Temporal(TemporalType.DATE)
	public Calendar dueTo;

	public Task(String title, User owner, Calendar dueTo) {
		this.title = title;
		this.owner = owner;
		this.dueTo = dueTo;
	}
}
