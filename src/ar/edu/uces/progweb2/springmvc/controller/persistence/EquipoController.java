package ar.edu.uces.progweb2.springmvc.controller.persistence;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import ar.edu.uces.progweb2.springmvc.validator.PartidoValidator;
import ar.edu.uces.progweb2.springmvc.view.form.EquipoForm;
import ar.edu.uces.progweb2.springmvc.view.form.PartidoForm;
import ar.edu.uces.progweb2.springmvc.view.form.SearchForm;

@Controller
public class EquipoController {
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
	@RequestMapping(value = "/preEquipoForm")
	public ModelAndView getPreEquipoForm(ModelMap mm) {
		System.out.println("MyController - equipoForm");
		EquipoForm equipo = new EquipoForm();
		ModelAndView out = new ModelAndView("/views/preEquipoForm.jsp");
		out.addObject("equipo", equipo);
		return out;
	}
	@RequestMapping(value = "/equipoForm")
	public ModelAndView getEquiposForm(ModelMap mm, @ModelAttribute("equipo") EquipoForm equipo, BindingResult result) {
		System.out.println("MyController - equipoForm");
		ModelAndView out = new ModelAndView("/views/equipoForm.jsp");
		out.addObject("equipo", equipo);
		return out;
	}

	@RequestMapping(value = "/equipos")
	public ModelAndView getEquipos(ModelMap mm) {
		System.out.println("MyController - equipos");
	    List<Equipo> equipos = this.equipoDao.getEquipos();
		ModelAndView out = new ModelAndView("/views/equipos.jsp");
		out.addObject("equipos", equipos);
		return out;
	}
	@RequestMapping(value = "/equiposUsuario")
	public ModelAndView getEquiposUsuario(ModelMap mm, HttpServletRequest request) {
		System.out.println("MyController - equipos");
	    List<Equipo> equiposUsuario = (List<Equipo>) request.getSession().getAttribute("equiposUser");
		ModelAndView out = new ModelAndView("/views/equiposUsuario.jsp");
		out.addObject("equipos", equiposUsuario);
		return out;
	}
	@RequestMapping(value = "/createEquipo")
	public ModelAndView createEquipo(ModelMap mm, @ModelAttribute("equipo") EquipoForm equipo, BindingResult result, @RequestParam("pic") MultipartFile file, HttpServletRequest request) {
		System.out.println("MyController - createEquipo");
		
	    if(!file.isEmpty()){
	        try{
	            byte[] bytes=file.getBytes();
	            System.out.println("Byte Data :"+bytes);
	            String fileName=file.getOriginalFilename();
	            
	            String path = this.getClass().getClassLoader().getResource("").getPath();
	            String fullPath = URLDecoder.decode(path, "UTF-8");
	            String pathArr[] = fullPath.split("/WEB-INF/classes/");

	            fullPath = pathArr[0];
				String serverFile = "";
				serverFile= new File(fullPath).getPath() + File.separatorChar +"img/"+ fileName;
	           BufferedOutputStream stream = new BufferedOutputStream(
	                    new FileOutputStream(serverFile));
	            stream.write(bytes);
	            stream.close();
	           
	            equipo.setFoto(fileName);

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    this.equipoValidator.validate(equipo, result);
	    
		if (result.hasErrors() || equipo == null) {
			
			ModelAndView out = new ModelAndView("/views/equipoForm.jsp");
			out.addObject("equipo", equipo);
			return out;
		}
		HttpSession session = request.getSession();
		List <Equipo> equiposUser = (List<Equipo>) session.getAttribute("equiposUser");
	    if(equipo.getTipo().equals("AMA"))
	    {
	    	EquipoAmateur team =  new EquipoAmateur();
	    	team.setId(equipo.getId());
	    	team.setFoto(equipo.getFoto());
	    	if(equipo.getHistoria()!=null){
	    		team.setHistoria(equipo.getHistoria());
	    	}
	    	team.setNombre(equipo.getNombre());
	    	team.setTipo(equipo.getTipo());
	    	team.setCategoria(equipo.getCategoria());
	    	this.equipoDao.save(team);
	    	equiposUser.add(team);
	    }
	    else
	    {
	 
	    	EquipoUniversitario team =  new EquipoUniversitario();
	    	team.setId(equipo.getId());
	    	team.setFoto(equipo.getFoto());
	    	if(equipo.getHistoria()!=null){
	    		team.setHistoria(equipo.getHistoria());
	    	}
	    	team.setNombre(equipo.getNombre());
	    	team.setTipo(equipo.getTipo());
	    	team.setDt(equipo.getDt());
	    	this.equipoDao.save(team);
	    	equiposUser.add(team);
	    }
	    
	    session.setAttribute("equiposUser", equiposUser);
	    List<Equipo> equipos = this.equipoDao.getEquipos();
		ModelAndView out = new ModelAndView("/views/equipos.jsp");
		out.addObject("equipos", equipos);
		return out;
	}
	


}
