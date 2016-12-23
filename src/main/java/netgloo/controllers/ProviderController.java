package netgloo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import netgloo.dao.ProviderDao;
import netgloo.models.Provider;
import netgloo.models.SystemManager;

@RestController
@RequestMapping("/providerController")
public class ProviderController {

	@Autowired
	private ProviderDao providerDao;

	ArrayList<Provider> listOfProviders = new ArrayList<Provider>();

	@RequestMapping(value = "/createNewProvider", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String createProvider(@RequestBody Provider p1) {

		try {
			Provider provider = null;
			provider = new Provider(p1.getProviderNickId(), p1.getProviderMail(), p1.getProviderName(),
					p1.getProviderSurname(), p1.getProviderPassword());
			providerDao.save(provider);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}

	@RequestMapping(value = "/loginProvider", method = RequestMethod.POST, headers = {
			"content-type=application/json" })
	public String loginProvider(@RequestBody Provider p1, HttpServletRequest request) {
		try {
			Provider provider = providerDao.findOne(p1.getProviderNickId());
			if (provider != null) {
				String nick = String.valueOf(provider.getProviderNickId());
				String pass = String.valueOf(provider.getProviderPassword());
				if (nick.equals(p1.getProviderNickId()) && pass.equals(p1.getProviderPassword())) {
					request.getSession().setAttribute("provider", provider);
					return "provider";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "EXCEPTION";
		}
		return "OK";
	}
	
	@RequestMapping(value = "/providerData", method = RequestMethod.GET)
	public Provider providerData(HttpServletRequest request) {
		try{
			if(request.getSession().getAttribute("provider")!=null)
			{
				Provider provider =(Provider) request.getSession().getAttribute("provider");
				return provider;
			}
			
		} catch (Exception e) {
			System.out.println("Provider is not logged in");
		}
		return null;
	}
	
	@RequestMapping(value="/updateProvider", method = RequestMethod.POST, headers = { "content-type=application/json" })
	@ResponseBody
	public String updateProvider(@RequestBody Provider p1) {
		try {
			if(providerDao.findOne(p1.getProviderNickId())!=null)
			{
				
				Provider provider = providerDao.findOne(p1.getProviderNickId());
				provider.setProviderNickId(p1.getProviderNickId());
				provider.setProviderMail(p1.getProviderMail());
				provider.setProviderName(p1.getProviderName());
				provider.setProviderSurname(p1.getProviderSurname());
				provider.setProviderPassword(p1.getProviderPassword());
				providerDao.save(provider);
				return "OK";
			}
			else
			{
				return "NOT_OK";
			}
		} catch (Exception ex) {
			return "Error updating provider: " + ex.toString();
		}
		
	}
	
}
