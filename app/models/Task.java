package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Task extends Model {

	@Required
	public String title;
	@ManyToOne
	public User owner;
	@Temporal(TemporalType.DATE)
	public Date dueTo;

	public Task(String title, User owner, Date dueTo) {
		this.title = title;
		this.owner = owner;
		this.dueTo = dueTo;
	}
}
