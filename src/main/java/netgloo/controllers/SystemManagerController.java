package netgloo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.SystemManagerDao;
import netgloo.models.Friendships;
import netgloo.models.SystemManager;
import netgloo.models.User;
import netgloo.models.UserProba;

@RestController
@RequestMapping("/systemManagerController")
public class SystemManagerController {

	@Autowired
	private SystemManagerDao sysMaDao;

	ArrayList<SystemManager> listOfManagers = new ArrayList<SystemManager>();

	@RequestMapping(value = "/createNewSystemManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createSystemManager(@RequestBody SystemManager sm1) {

		try {
			SystemManager systemManager = null;
			// String user_reg_date = new
			// SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			systemManager = new SystemManager(sm1.getSystem_manager_nick_id(), sm1.getManager_email(),
					sm1.getManager_name(), sm1.getManager_last_name(), sm1.getManager_password());
			sysMaDao.save(systemManager);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/loginSystemManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String loginSystemManager(@RequestBody SystemManager sm1, HttpServletRequest request) {
		// System.out.println("USLO U SYSTEM MANAGER LOGIN!");
		try {
			// SystemManager systemManager =
			// sysMaDao.findBySystem_manager_nick_id(sm1.getSystem_manager_nick_id());
			SystemManager systemManager = sysMaDao.findOne(sm1.getSystem_manager_nick_id());
			// System.out.println(systemManager.getSystem_manager_nick_id());
			if (systemManager != null) {
				String nick = String.valueOf(systemManager.getSystem_manager_nick_id());
				String pass = String.valueOf(systemManager.getManager_password());
				if (nick.equals(sm1.getSystem_manager_nick_id()) && pass.equals(sm1.getManager_password())) {
					request.getSession().setAttribute("systemManager", systemManager);
					// System.out.println("LOGIN SYSTEM MANAGERA JE USPJESAN!");
					return "systemManager";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/checkForSystemManager", method = RequestMethod.GET/*
																				 * ,
																				 * headers
																				 * =
																				 * {
																				 * "content-type=application/json"
																				 * }
																				 */)
	public String chekForSystemManager(HttpServletRequest request) {

		try {
			// request.getSession().invalidate();
			SystemManager sym = null;
			if (request.getSession().getAttribute("systemManager") != null) {
				sym = (SystemManager) request.getSession().getAttribute("systemManager");
			}

			if (sym != null) {
				return "itIsASystemManager";
			} else {
				return "itAintSystemManager";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "itAintSystemManager";
	}

	@RequestMapping(value = "/isBigDeal", method = RequestMethod.GET/*
																	 * , headers
																	 * = {
																	 * "content-type=application/json"
																	 * }
																	 */)
	public String isBigDeal(HttpServletRequest request) {

		try {
			// request.getSession().invalidate();
			SystemManager sym = null;
			if (request.getSession().getAttribute("systemManager") != null) {
				sym = (SystemManager) request.getSession().getAttribute("systemManager");
			}

			if (sym != null) {
				if (sym.getSystem_manager_nick_id().equals("manager1")) {
					return "da";
				} else {
					return "njet";
				}

			} else {
				return "njet";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "njet";
	}

	@RequestMapping(value = "/getSystemManagers", method = RequestMethod.GET)
	public ArrayList<SystemManager> getSystemManagers(HttpServletRequest request) {
		// friends.clear();
		listOfManagers.clear();
		try {
			// User user = (User) request.getSession().getAttribute("user");
			SystemManager sysMa = (SystemManager) request.getSession().getAttribute("systemManager");
			if (sysMa != null && sysMa.getSystem_manager_nick_id().equals("manager1")) {
				// String systemManagerNickString = sys
				listOfManagers = (ArrayList<SystemManager>) sysMaDao.findAll();

				int pos = -1;

				int counter = 0;
				for (SystemManager sm : listOfManagers) {
					if (sm.getSystem_manager_nick_id().equals("manager1")) {
						pos = counter;
						break;
					}
					counter++;
				}
				if (pos != -1) {
					listOfManagers.remove(pos);
				}

				return listOfManagers;
			}
			// Integer userID = user.getUser_id();
			// ArrayList<Friendships> fs = (ArrayList<Friendships>)
			// friendshipsDao.findByLoveGiver(user);
			// for(int i=0; i<fs.size(); i++) {
			// friends.add(fs.get(i).getLove_taker());
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfManagers;
	}

	@RequestMapping(value = "/logoutSystemManager", method = RequestMethod.GET, headers = {
			"content-type=application/json" })
	public String logoutSystemManager(HttpServletRequest request) {

		try {
			request.getSession().invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "logout";
	}

	@RequestMapping(value = "/systemManagerNick", method = RequestMethod.GET)
	public String systemManagerName(HttpServletRequest request) {
		try {
			SystemManager systemManager = (SystemManager) request.getSession().getAttribute("systemManager");
			if (systemManager != null) {
				return systemManager.getSystem_manager_nick_id();
			}
		} catch (Exception e) {
			System.out.println("Nema ulogovanog systemManagera - NullPointerException");
		}
		return "none";
	}

	@RequestMapping(value = "/systemManagerData", method = RequestMethod.GET)
	public SystemManager systemManagertData(HttpServletRequest request) {
		try {
			if (request.getSession().getAttribute("systemManager") != null) {
				SystemManager systemManager = (SystemManager) request.getSession().getAttribute("systemManager");
				return systemManager;
			}

		} catch (Exception e) {
			System.out.println("systemManager is not logged in");
		}
		return null;
	}

	@RequestMapping("/deleteSystemManager")
	@ResponseBody
	public String deleteSystemManager(String system_manager_nick_id) {
		try {
			SystemManager systemManager = new SystemManager(system_manager_nick_id);
			sysMaDao.delete(systemManager);
		} catch (Exception ex) {
			return "Error deleting systemManager: " + ex.toString();
		}
		return "SystemManager succesfully deleted!";
	}

	/*
	 * @RequestMapping("/updateSystemManager")
	 * 
	 * @ResponseBody public String updateSystemManager(String
	 * system_manager_nick_id, String manager_email, String manager_name, String
	 * manager_last_name, String manager_password) { try { SystemManager
	 * systemManager = sysMaDao.findOne(system_manager_nick_id);
	 * systemManager.setSystem_manager_nick_id(system_manager_nick_id);
	 * systemManager.setManager_email(manager_email);
	 * systemManager.setManager_name(manager_name);
	 * systemManager.setManager_last_name(manager_last_name);
	 * systemManager.setManager_password(manager_password);
	 * sysMaDao.save(systemManager); } catch (Exception ex) { return
	 * "Error updating systemManager: " + ex.toString(); } return
	 * "SystemManager succesfully updated!"; }
	 */

	@RequestMapping(value = "/updateSystemManager", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	@ResponseBody
	public String updateSystemManager(@RequestBody SystemManager sm1) {
		try {
			if (sysMaDao.findOne(sm1.getSystem_manager_nick_id()) != null) {

				SystemManager systemManager = sysMaDao.findOne(sm1.getSystem_manager_nick_id());
				systemManager.setSystem_manager_nick_id(sm1.getSystem_manager_nick_id());
				systemManager.setManager_email(sm1.getManager_email());
				systemManager.setManager_name(sm1.getManager_name());
				systemManager.setManager_last_name(sm1.getManager_last_name());
				systemManager.setManager_password(sm1.getManager_password());
				sysMaDao.save(systemManager);
				return "OK";
			} else {
				return "NOT_OK";
			}
		} catch (Exception ex) {
			return "Error updating systemManager: " + ex.toString();
		}

	}

}
