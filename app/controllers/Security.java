package controllers;

import models.User;

public class Security extends Secure.Security {
	static boolean authenticate(String email, String password) {
		return User.connect(email, password) != null;
	}
}
