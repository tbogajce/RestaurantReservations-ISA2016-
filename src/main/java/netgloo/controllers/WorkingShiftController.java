package netgloo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.WorkingShiftDao;
import netgloo.models.DatesForWSImitation;
import netgloo.models.User;
import netgloo.models.UserProba;

@RestController
@RequestMapping("/workingShiftController")
public class WorkingShiftController {
	
	@Autowired
	private WorkingShiftDao wsDao;
	
	@RequestMapping(value = "/getWorkingShifts", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String getWorkingShifts(@RequestBody DatesForWSImitation dfwsi, HttpServletRequest request) {

		try {
			
			
			
			/*
			User user = userDao.findByEmail(user1.getEmail());
			String email = String.valueOf(user.getEmail());
			String pass = String.valueOf(user.getUser_password());
			if (email.equals(user1.getEmail()) && pass.equals(user1.getPassword())) {
				request.getSession().setAttribute("user", user);
				return user.getUser_role();
			}
			*/
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

}
