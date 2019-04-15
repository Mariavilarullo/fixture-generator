package ar.edu.uces.progweb2.springmvc.view.form;


import ar.edu.uces.progweb2.springmvc.model.Partido;

public class PartidoForm extends Partido {
	
	private String horario;

	public String getHorario() {
		return horario;
	}

	public void setHorario() {
		this.horario = generadorHorarios();
	}
	public String generadorHorarios(){
		
		int orden= this.getOrden();
		String time="";
		if(orden==1)
		{
			time="10:00hs.";
		}
		if(orden==2)
		{
			time="11:35hs.";
		}
		if(orden==3)
		{
			time="13:10hs.";
		}
		if(orden==4)
		{
			time="14:45hs.";
		}
		if(orden==5)
		{
			time="16:20hs.";
		}
		if(orden==6)
		{
			time="17:55hs.";
		}
		if(orden==7)
		{
			time="19:30hs.";
		}
		if(orden==8)
		{
			time="21:05hs.";
		}
		return time;
	}
	

}
