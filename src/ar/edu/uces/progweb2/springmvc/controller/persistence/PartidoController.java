package ar.edu.uces.progweb2.springmvc.controller.persistence;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import ar.edu.uces.progweb2.springmvc.model.EquipoUniversitario;
import ar.edu.uces.progweb2.springmvc.model.Partido;
import ar.edu.uces.progweb2.springmvc.model.Sede;
import ar.edu.uces.progweb2.springmvc.model.User;
import ar.edu.uces.progweb2.springmvc.validator.PartidoValidator;
import ar.edu.uces.progweb2.springmvc.view.form.PartidoForm;
import ar.edu.uces.progweb2.springmvc.view.form.SearchForm;

@Controller
public class PartidoController {
	private SedeDao sedeDao;
	private CanchaDao canchaDao;
	private EquipoDao equipoDao;
	private PartidoDao partidoDao;
	private PartidoValidator partidoValidator;
	
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
	public void setPartidoValidator(PartidoValidator partidoValidator) {
		this.partidoValidator = partidoValidator;
	}
	@RequestMapping(value = "/prePartidoForm")
	public ModelAndView getprePartidoForm(ModelMap mm) {
		System.out.println("MyController - partidoForm");

		Cancha cancha = new Cancha();
		List<Cancha> canchas=(List<Cancha>) canchaDao.getCanchas();////
		ModelAndView out = new ModelAndView("/views/prePartidoForm.jsp");
		out.addObject("canchas", canchas);
		out.addObject("cancha", cancha);
		return out;
	}
	@RequestMapping(value = "/partidoForm")
	public ModelAndView getPartidosForm(ModelMap mm, @ModelAttribute("cancha") Cancha cancha, BindingResult result) {
		System.out.println("MyController - partidoForm");
		Partido partido = new Partido();	
		Cancha c= (Cancha)this.canchaDao.get(cancha.getId());
		List<Partido> games = this.partidoDao.getPartidos(c.getId());
		c.setPartidos(games);
		List <PartidoForm> partidos = this.partidoDao.partidosForm(games);
		List<Equipo> equipos =(List<Equipo>) equipoDao.getEquipos();
		ModelAndView out = new ModelAndView("/views/partidoForm.jsp");
		out.addObject("partido", partido);
		out.addObject("cancha", c);
		out.addObject("equipos", equipos);
		out.addObject("partidos", partidos);
		return out;
	}

	@RequestMapping(value = "/createPartido") ///, @RequestParam(value="cancha", required =false) Cancha c,
	public ModelAndView createPartido(@ModelAttribute("partido") Partido partido, BindingResult result) {
		System.out.println("MyController - createPartido");

		Equipo equipo1= this.equipoDao.get(partido.getEquipo1().getId());
		Equipo equipo2= this.equipoDao.get(partido.getEquipo2().getId());
		Cancha cancha = this.canchaDao.get(partido.getCancha().getId());


		partido.setEquipo1(equipo1);
		partido.setEquipo2(equipo2);
		
		
		List<Partido> partidos = (List<Partido>)this.partidoDao.getPartidos(cancha.getId());
		cancha.setPartidos(partidos);
		partido.setCancha(cancha);
		List <PartidoForm> partidosForm = (List<PartidoForm>)this.partidoDao.getPartidosForm(cancha.getId());
		int orden= generarOrden(cancha);
		partido.setOrden(orden);
		
		this.partidoValidator.validate(partido, result);
		if (result.hasErrors() || partido == null) {
			
			List<Cancha> canchas=(List<Cancha>) canchaDao.getCanchas();
			List<Equipo> equipos =(List<Equipo>) equipoDao.getEquipos();
			ModelAndView out = new ModelAndView("/views/partidoForm.jsp");
			out.addObject("partido", partido);
			out.addObject("canchas", canchas);
			out.addObject("equipos", equipos);
			out.addObject("cancha", cancha);
			return out;
		}

		this.partidoDao.save(partido);
		
		return new ModelAndView("redirect:/home.htm");
	}
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public ModelAndView verEquipo(@PathVariable("id") int id) {
		System.out.println("MyController - verEquipo");
		Equipo equipo = this.equipoDao.get(id);
		List<Partido> partidosE = this.partidoDao.getPartidosEquipo(id);
		List<PartidoForm> partidos = this.partidoDao.partidosForm(partidosE);
		ModelAndView out = new ModelAndView("/views/equipo.jsp");
		out.addObject("equipo", equipo);
		out.addObject("partidos", partidos);
		return out;
	}
	@RequestMapping(value = "/deletePartido/{id}", method = RequestMethod.GET)
	public ModelAndView deletePartido(@PathVariable("id") int id) {
		System.out.println("MyController - deletePartido");
		Partido p = this.partidoDao.get(id);
		if(p!=null)
		{
			this.partidoDao.delete(p.getId());
		}
		return new ModelAndView("redirect:/home.htm"); 
	}
	@RequestMapping(value = "/deleteAll/{id}", method = RequestMethod.GET)
	public ModelAndView deleteAaLL(@PathVariable("id") int id) {
		System.out.println("MyController - deleteAllPartidosCancha");
		
		List<Partido> partidos = (List<Partido>)this.partidoDao.getPartidos(id);
		if(partidos!=null)
		{
			this.partidoDao.deleteAll(id);
		}
		return new ModelAndView("redirect:/home.htm"); 
	}
	public int generarOrden(Cancha cancha)
	{
		int orden;
		int index;
		List <Partido> partidos = (List<Partido>)this.partidoDao.getPartidos(cancha.getId());
		if(partidos.size()==0)
		{
			return 1;
		}

		index=partidos.size()-1;
		Partido last = partidos.get(index);
		orden= last.getOrden()+1;
		return orden;
		
	}


}
