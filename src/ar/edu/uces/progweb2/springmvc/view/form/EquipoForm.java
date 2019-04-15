package ar.edu.uces.progweb2.springmvc.view.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.uces.progweb2.springmvc.dao.EquipoDao;
import ar.edu.uces.progweb2.springmvc.model.Equipo;
import ar.edu.uces.progweb2.springmvc.model.EquipoAmateur;
import ar.edu.uces.progweb2.springmvc.model.EquipoUniversitario;

public class EquipoForm extends Equipo {
	
	private String dt;
	private String categoria;
	private EquipoDao equipoDao;
	
	@Autowired
	public void setEquipoDao(EquipoDao equipoDao) {
		this.equipoDao = equipoDao;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
