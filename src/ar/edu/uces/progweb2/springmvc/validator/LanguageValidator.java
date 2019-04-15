package ar.edu.uces.progweb2.springmvc.validator;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.springmvc.model.Language;

@Component
public class LanguageValidator implements Validator {

	@Autowired
	@Resource(name = "locales")
	private Map<String, Locale> locales;

	@Override
	public boolean supports(Class<?> clazz) {
		return Language.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Language language = (Language) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lang",
				"erros.language.lang.empty");
		if (!errors.hasFieldErrors("lang") // to avoid show double error
				&& locales.get(language.getLang()) == null) {
			errors.rejectValue("lang", "erros.language.lang.invalid");
		}

	}

}
