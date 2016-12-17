package netgloo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.FriendshipsDao;
import netgloo.dao.UserDao;
import netgloo.models.Friendships;
import netgloo.models.User;
import netgloo.models.UserProba;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */

@RestController
@RequestMapping("/users")
public class UserController {
	
	ArrayList<User> friends = new ArrayList<User>();
	
	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String createUser(@RequestBody UserProba user1) {

		try {
			User user = null;
			String user_reg_date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			user = new User(user1.getEmail(), user1.getPassword(), user1.getName(), user1.getSurname(),
					user1.getBirthDate().trim(), user_reg_date, "Guest");
			userDao.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String loginUser(@RequestBody UserProba user1, HttpServletRequest request) {

		try {
			User user = userDao.findByEmail(user1.getEmail());
			String email = String.valueOf(user.getEmail());
			String pass = String.valueOf(user.getUser_password());
			if (email.equals(user1.getEmail()) && pass.equals(user1.getPassword())) {
				request.getSession().setAttribute("user", user);
				return user.getUser_role();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
	
	@RequestMapping(value = "/editGuest", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String editGuest(@RequestBody UserProba user1, HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			user.setUser_password(user1.getPassword().trim());
			user.setUser_name(user1.getName().trim());
			user.setUser_surname(user1.getSurname().trim());
			user.setUser_birth_date(user1.getBirthDate().trim());
			userDao.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/logoutUser", method = RequestMethod.GET, headers = { "content-type=application/json" })
	public String loginUser(HttpServletRequest request) {

		try {
			request.getSession().invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "logout";
	}
	
	@RequestMapping(value = "/checkRole", method = RequestMethod.GET)
	public String checkRole(HttpServletRequest request) {

		try {
			request.getSession().invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "logout";
	}
	
	@RequestMapping(value = "/getFriends", method = RequestMethod.GET)
	public ArrayList<User> getFrinds(HttpServletRequest request) {
		friends.clear();
		try {
			User user = (User) request.getSession().getAttribute("user");
			Integer userID = user.getUser_id();
			ArrayList<Friendships> fs = (ArrayList<Friendships>) friendshipsDao.findByLoveGiver(user);
			for(int i=0; i<fs.size(); i++) {
				friends.add(fs.get(i).getLove_taker());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return friends;
	}

	@RequestMapping(value = "/guestName", method = RequestMethod.GET)
	public String guestName(HttpServletRequest request) {
		try {
			User u = (User) request.getSession().getAttribute("user");
			if (u.getUser_role().equals("Guest")) {
				return u.getUser_name();
			}
		} catch (Exception e) {
			System.out.println("Nema ulogovanog korisnika - NullPointerException");
		}
		return "none";
	}
	
	@RequestMapping(value = "/guestData", method = RequestMethod.GET)
	public User guestData(HttpServletRequest request) {
		try{
		User u =(User) request.getSession().getAttribute("user");
		return u;
		} catch (Exception e) {
			System.out.println("Guest is not logged in");
		}
		return null;
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Integer user_id) {
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
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(Integer user_id, String user_email, String user_password, String user_name,
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
	
	@Autowired
	private FriendshipsDao friendshipsDao;

} // class UserController
