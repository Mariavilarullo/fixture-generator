package ar.edu.uces.progweb2.springmvc.controller.simple;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import ar.edu.uces.progweb2.springmvc.dao.CanchaDao;
import ar.edu.uces.progweb2.springmvc.dao.EquipoDao;
import ar.edu.uces.progweb2.springmvc.dao.PartidoDao;
import ar.edu.uces.progweb2.springmvc.dao.SedeDao;
import ar.edu.uces.progweb2.springmvc.model.Cancha;
import ar.edu.uces.progweb2.springmvc.model.Equipo;
import ar.edu.uces.progweb2.springmvc.model.EquipoAmateur;
import ar.edu.uces.progweb2.springmvc.model.EquipoUniversitario;
import ar.edu.uces.progweb2.springmvc.model.Partido;
import ar.edu.uces.progweb2.springmvc.model.Sede;
import ar.edu.uces.progweb2.springmvc.model.User;
import ar.edu.uces.progweb2.springmvc.validator.EquipoValidator;
import ar.edu.uces.progweb2.springmvc.validator.UserValidator;
import ar.edu.uces.progweb2.springmvc.view.form.EquipoForm;
import ar.edu.uces.progweb2.springmvc.view.form.PartidoForm;
import ar.edu.uces.progweb2.springmvc.view.form.SearchForm;

@Controller
public class HomeController {
	private SedeDao sedeDao;
	private CanchaDao canchaDao;
	private EquipoDao equipoDao;
	private PartidoDao partidoDao;
	private EquipoValidator equipoValidator;

	@Autowired
	public void setSedeDao(SedeDao sedeDao) {
		this.sedeDao = sedeDao;
	}
	@Autowired
	public void setCanchaDao(CanchaDao canchaDao) {
		this.canchaDao = canchaDao;
	}
	@Autowired
	public void setEquipoDao(EquipoDao equipoDao) {
		this.equipoDao = equipoDao;
	}
	@Autowired
	public void setPartidoDao(PartidoDao partidoDao) {
		this.partidoDao = partidoDao;
	}
	@Autowired
	public void setEquipoValidator(EquipoValidator equipoValidator) {
		this.equipoValidator = equipoValidator;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome(ModelMap mm) {
		System.out.println("MyController - home");
		
		///despues filtrar por cancha
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
		SearchForm searchForm = new SearchForm();
		ModelAndView out = new ModelAndView("/views/home.jsp");
		out.addObject("partidos", partidos);
		out.addObject("canchas", canchas);
		out.addObject("sedes", sedes);
		out.addObject("searchForm", searchForm);
		return out;
	}
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView manageAccount(ModelMap mm) {
		System.out.println("MyController - account");
		ModelAndView out = new ModelAndView("/views/account.jsp");
		return out;
		
	}
	@RequestMapping(value = "/search") 
	public ModelAndView search(ModelMap mm, @ModelAttribute("searchForm") SearchForm searchForm, BindingResult result) {
		System.out.println("MyController - search");
		String contenido=searchForm.getContenido();
		int id= searchForm.getId();
		List<Partido> games= new ArrayList<Partido>();
		
		if(id==0 && contenido!="")
		{
			games = this.partidoDao.searchString(contenido);
		}
		if(id!=0 &&  contenido!="")
		{
			games = this.partidoDao.searchStringSede(contenido, id);
		}
		if(id!=0 &&  contenido=="")
		{
			games = this.partidoDao.searchSede(id);
		}
		if(id==0 &&  contenido=="")
		{
			List <Cancha> canchas = this.canchaDao.getCanchas();
			for(Cancha c:canchas)
			{
				List<PartidoForm> pf= this.partidoDao.getPartidosForm(c.getId());
				for(PartidoForm p :pf)
				{
					games.add(p);
				}
			}
		}
		List<PartidoForm> partidos =this.partidoDao.partidosForm(games);
		List <Sede> sedes = this.sedeDao.getSedes();
		ModelAndView out = new ModelAndView("/views/home.jsp");
		out.addObject("partidos", partidos);
		out.addObject("sedes", sedes);
		return out;
	}

	
}
