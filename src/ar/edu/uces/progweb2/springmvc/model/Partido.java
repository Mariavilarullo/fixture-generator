package ar.edu.uces.progweb2.springmvc.model;

import java.util.List;

public class Partido {
	
	private int id;
	private Cancha cancha;
	private int orden;
	private Equipo equipo1;
	private Equipo equipo2;
	private boolean jugado;
	
	
	
	
	public boolean isJugado() {
		return jugado;
	}
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cancha getCancha() {
		return cancha;
	}
	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}
	
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public Equipo getEquipo1() {
		return equipo1;
	}
	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}
	public Equipo getEquipo2() {
		return equipo2;
	}
	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
}
