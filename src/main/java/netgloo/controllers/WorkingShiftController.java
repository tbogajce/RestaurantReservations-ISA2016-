package netgloo.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.EmployeeDao;
import netgloo.dao.UserDao;
import netgloo.dao.WorkingShiftDao;
import netgloo.models.DatesForWSImitation;
import netgloo.models.Employee;
import netgloo.models.Restaurant;
import netgloo.models.RestaurantManager;
import netgloo.models.User;
import netgloo.models.UserEmployeePom;
import netgloo.models.UserProba;
import netgloo.models.WSOFL;
import netgloo.models.WorkingShift;
import netgloo.models.WorkingShiftPom;

@RestController
@RequestMapping("/workingShiftController")
public class WorkingShiftController {

	@Autowired
	private WorkingShiftDao wsDao;

	@Autowired
	private EmployeeDao empDao;

	@Autowired
	private UserDao userDao;

	private ArrayList<WSOFL> wsoflList = new ArrayList<WSOFL>();

	@RequestMapping(value = "/getWorkingShifts", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public ArrayList<WSOFL> getWorkingShifts(@RequestBody DatesForWSImitation dfwsi, HttpServletRequest request) {

		System.out.println("DOSLO DO OVDE ..................");

		ArrayList<Employee> employeesInRestaurant = new ArrayList<Employee>();

		ArrayList<Employee> employeesInRestaurantWithSameRole = new ArrayList<Employee>();

		ArrayList<WorkingShift> workingShiftsList = new ArrayList<WorkingShift>();

		ArrayList<WSOFL> wsoflList = new ArrayList<WSOFL>();

		wsoflList.clear();

		try {

			String sDate = dfwsi.getStartDate();
			sDate = sDate + " 00:00:00";
			String eDate = dfwsi.getEndDate();
			eDate = eDate + " 00:00:00";

			System.out.println("STRING DATUM: " + sDate);

			Timestamp sTS = Timestamp.valueOf(sDate);
			Timestamp eTS = Timestamp.valueOf(eDate);

			System.out.println("TIMESTAMP DATE: " + sTS);

			Employee emp = (Employee) request.getSession().getAttribute("employee");

			if (emp != null) {
				workingShiftsList = wsDao.findByRestaurant(emp.getRestaurantId());

				for (WorkingShift ws : workingShiftsList) {
					if (ws.getWorker().getEmployeeRole().equals(emp.getEmployeeRole())
							&& ws.getShiftBeginningTime().after(sTS) && ws.getShiftEndTime().before(eTS)) {

						WSOFL primjerak = new WSOFL(ws.getWorker().getUserId().getUserName(),
								ws.getWorker().getUserId().getUserSurname(), ws.getShiftBeginningTime().toString(),
								ws.getShiftEndTime().toString());
						System.out.println("XX: " + ws.getWorker().getUserId().getUserName() + " "
								+ ws.getWorker().getUserId().getUserSurname() + " : "
								+ ws.getShiftBeginningTime().toString() + " to " + ws.getShiftEndTime().toString());
						wsoflList.add(primjerak);
					}
				}

			}

			return wsoflList;

			/*
			 * employeesInRestaurant =
			 * empDao.findByRestaurantId(emp.getRestaurantId());
			 * 
			 * for(Employee emx : employeesInRestaurant) {
			 * if(emx.getEmployeeRole().equals(emp.getEmployeeRole())) {
			 * wsDao.findBy } }
			 * 
			 */

			// wsDao.

			/*
			 * User user = userDao.findByEmail(user1.getEmail()); String email =
			 * String.valueOf(user.getEmail()); String pass =
			 * String.valueOf(user.getUser_password()); if
			 * (email.equals(user1.getEmail()) &&
			 * pass.equals(user1.getPassword())) {
			 * request.getSession().setAttribute("user", user); return
			 * user.getUser_role(); }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("--------------EXCEPTION ... ---------------------");
			return wsoflList;
		}
	}

	@RequestMapping(value = "/createNewShift", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createEmployee(@RequestBody WorkingShiftPom ws1, HttpServletRequest request) {

		try {

			RestaurantManager rmkkk = (RestaurantManager) request.getSession().getAttribute("restaurantManager");
			Restaurant restaurant = rmkkk.getRestaurantId();

			User workerr = userDao.findByEmail(ws1.getWorker());
			// int id = workerr.getUserId();
			Employee worker = empDao.findByUserId(workerr);

			// System.out.println("Worker ID: " + ws1.getWorker());
			// System.out.println("Worker: " + worker);

			WorkingShift workingshift = null;

			/*
			 * String sDate = new SimpleDateFormat("yyyy-mm-dd").format(new
			 * Date()); sDate = sDate + " 00:00:00"; String eDate = new
			 * SimpleDateFormat("yyyy-mm-dd").format(new Date()); eDate = eDate
			 * + " 00:00:00";
			 * 
			 * Timestamp sTS = Timestamp.valueOf(sDate); Timestamp eTS =
			 * Timestamp.valueOf(eDate);
			 */

			workingshift = new WorkingShift(restaurant, worker, ws1.getShiftBeginningTime(),
					ws1.getShiftEndTime(), ws1.getNote());
			wsDao.save(workingshift);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

}
