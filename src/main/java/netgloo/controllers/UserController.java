package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
public class UserController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(String user_email, String user_password, String user_name, String user_surname, String user_role) {
		User user = null;
		String user_birth_date = "juce";
		String user_registration_date = "prekjuce";
		try {
			user = new User(user_email, user_password, user_name, user_surname, user_birth_date,
					user_registration_date, user_role);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + user.getUser_id() + ")";
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long user_id) {
		try {
			User user = new User(user_id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(String user_email) {
		String user_id;
		try {
			User user = userDao.findByEmail(user_email);
			user_id = String.valueOf(user.getUser_id());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + user_id;
	}

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 * 
	 * @param id
	 *            The id for the user to update.
	 * @param email
	 *            The new email.
	 * @param name
	 *            The new name.
	 * @return A string describing if the user is succesfully updated or not.
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(Long user_id, String user_email, String user_password, String user_name, 
			String user_surname, String user_birth_date, String user_registration_date, String user_role) {
		try {
			User user = userDao.findOne(user_id);
			user.setEmail(user_email);
			user.setUser_password(user_password);
			user.setUser_name(user_name);
			user.setUser_surname(user_surname);
			user.setUser_birth_date(user_birth_date);
			user.setUser_registration_date(user_registration_date);
			user.setUser_role(user_role);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UserDao userDao;

} // class UserController
