package ar.edu.uces.progweb2.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.springmvc.model.Partido;
import ar.edu.uces.progweb2.springmvc.model.User;

@Component
public class PartidoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Partido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Partido partido = (Partido) object;
		
		if(partido.getOrden()>=10)
		{
			errors.rejectValue("orden", "errors.partido.orden");
		}
		if(partido.getEquipo1().getId() == partido.getEquipo2().getId())
		{
			errors.rejectValue("equipo1", "errors.partido.equipo.igual");
		}
		if(partido.getCancha().getTipo().equals("alto rendimiento"))
		{
			if(partido.getEquipo1().getTipo().equals("AMA") && partido.getEquipo2().getTipo().equals("AMA"))
			{
				errors.rejectValue("equipo1", "errors.partido.equipo.universitario");
			}
		}
	}

}
