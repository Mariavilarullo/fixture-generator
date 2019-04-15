package ar.edu.uces.progweb2.springmvc.model;


public class Equipo {
	
	private int id;
	private String tipo;
	private String nombre;
	private String foto;
	private String historia;

	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	
	
}
