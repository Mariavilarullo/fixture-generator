package ar.edu.uces.progweb2.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.springmvc.model.Equipo;
import ar.edu.uces.progweb2.springmvc.model.Partido;
import ar.edu.uces.progweb2.springmvc.model.User;
import ar.edu.uces.progweb2.springmvc.view.form.EquipoForm;

@Component
public class EquipoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EquipoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		EquipoForm equipo = (EquipoForm) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "errors.equipo.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foto", "errors.equipo.foto");
		

		if(equipo.getTipo().equals("AMA"))
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "errors.equipo.categoria");	
		}
		if(equipo.getTipo().equals("UNI"))
		{		
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dt", "errors.equipo.dt");	
			if(equipo.getDt().length()>60)
			{
				errors.rejectValue("st", "errors.equipo.largo.st");
			}
		}
		
		if(equipo.getHistoria().length()>500)
		{
			errors.rejectValue("historia", "errors.equipo.historia");
		}
		if(equipo.getNombre().length()>60)
		{
			errors.rejectValue("nombre", "errors.equipo.largo.nombre");
		}
	
		
	}

}
