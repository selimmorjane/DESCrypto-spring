package com.supgalilee.DESCrypto;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@RestController
public class Api {
	Logger logger = Logger.getLogger(Api.class);
	@GetMapping("/")
	public ModelAndView index(HttpSession session) {
		logger.info("Initialisation de la session: "+session.getId());
		ModelAndView model = new ModelAndView("index.html");
		model.addObject("message", new Message());
	    return model;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/hello")
    public String showHello(@RequestBody String m, HttpSession session) {
		session.setAttribute("user_id", session.getId());
		logger.info( DES.cle);
		return DES.cle;
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/encrypt")
    public String encryptMessage(@RequestBody String message, HttpSession session) {
		if(session.getAttribute("user_id") != null){
			return DES.crypt(message);
		}
		return "Invalid session";
    }

	@RequestMapping(method = RequestMethod.POST, value = "/decrypt")
    public String decryptMessage(@RequestBody String crypted, HttpSession session) {
		if(session.getAttribute("user_id") != null){
			return DES.decrypt(crypted);
		}
		return "Invalid session";

    }

   @RequestMapping(method = RequestMethod.GET, value = "/disconnect")
	public String disconnect(HttpSession session){
	   logger.info("Fin de la session: "+session.getId());
	   session.removeAttribute("user_id");
	   return "disconnected";
   }
}