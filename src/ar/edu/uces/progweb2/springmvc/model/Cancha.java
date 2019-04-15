package ar.edu.uces.progweb2.springmvc.model;

import java.util.List;

public class Cancha {
	
	private int id;
	private Sede sede;
	private int numero;
	private String tipo;
	private List <Partido> partidos;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + numero;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancha other = (Cancha) obj;
		if (id != other.id)
			return false;
		if (numero != other.numero)
			return false;		
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
