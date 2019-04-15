package ar.edu.uces.progweb2.springmvc.controller.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.sun.javafx.collections.MappingChange.Map;

import ar.edu.uces.progweb2.springmvc.dao.CanchaDao;
import ar.edu.uces.progweb2.springmvc.dao.EquipoDao;
import ar.edu.uces.progweb2.springmvc.dao.PartidoDao;
import ar.edu.uces.progweb2.springmvc.dao.SedeDao;
import ar.edu.uces.progweb2.springmvc.dao.UserDao;
import ar.edu.uces.progweb2.springmvc.model.Cancha;
import ar.edu.uces.progweb2.springmvc.model.Equipo;
import ar.edu.uces.progweb2.springmvc.model.Sede;
import ar.edu.uces.progweb2.springmvc.model.User;
import ar.edu.uces.progweb2.springmvc.validator.PartidoValidator;
import ar.edu.uces.progweb2.springmvc.validator.UserValidator;
import ar.edu.uces.progweb2.springmvc.view.form.PartidoForm;
import ar.edu.uces.progweb2.springmvc.view.form.SearchForm;

@Controller
public class LoginController {

	
	private UserDao userDao;
	private UserValidator userValidator;
	private SedeDao sedeDao;
	private CanchaDao canchaDao;
	private PartidoDao partidoDao;
	
	@Autowired
	public void setSedeDao(SedeDao sedeDao) {
		this.sedeDao = sedeDao;
	}
	@Autowired
	public void setCanchaDao(CanchaDao canchaDao) {
		this.canchaDao = canchaDao;
	}
	@Autowired
	public void setPartidoDao(PartidoDao partidoDao) {
		this.partidoDao = partidoDao;
	}
	
	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(ModelMap mm) {
		System.out.println("MyController - loginForm");
		User user = new User();
		ModelAndView out = new ModelAndView("/views/login.jsp");
		out.addObject("user", user);
		return out;
	}
	
	@RequestMapping(value = "/userHome", method = RequestMethod.POST)
	public ModelAndView userHome(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("MyController - home");
		User usr = userDao.validate(user.getUsername(), user.getPassword());
		this.userValidator.validate(usr, result);
		if (result.hasErrors() || usr == null) {
			return new ModelAndView("redirect:/login.htm");
		}
		usr= this.userDao.get(usr.getId());
		session.setAttribute("user", usr);
		List <Cancha> canchas = this.canchaDao.getCanchas();
		List <Sede> sedes = this.sedeDao.getSedes();
		List <PartidoForm> partidos = new ArrayList <PartidoForm>();
		for(Cancha c:canchas)
		{
			List<PartidoForm> pf= this.partidoDao.getPartidosForm(c.getId());
			for(PartidoForm p :pf)
			{
				partidos.add(p);
			}
		}
		List<Equipo> equiposUsuario = new ArrayList<Equipo>();
		session.setAttribute("equiposUser", equiposUsuario);
		SearchForm searchForm= new SearchForm();
		ModelAndView out = new ModelAndView("/views/home.jsp");
		out.addObject("partidos", partidos);
		out.addObject("canchas", canchas);
		out.addObject("sedes", sedes);
		out.addObject("user", usr);
		out.addObject("searchForm", searchForm);
		return out;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model, HttpSession session) {
		
		session.setAttribute("user", null);
		return new ModelAndView("redirect:/home.htm");
	}
	
}
