package ar.edu.uces.progweb2.springmvc.controller.simple;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import ar.edu.uces.progweb2.springmvc.model.Language;
import ar.edu.uces.progweb2.springmvc.validator.LanguageValidator;

@Controller
public class LocaleController {

	@Autowired
	private SessionLocaleResolver localeResolver;

	@Autowired
	@Resource(name = "locales")
	private Map<String, Locale> locales;

	@Autowired
	private LanguageValidator validator;

	// private static final Map<String, Locale> LOCALES = createLocaleMap();
	//
	// private static Map<String, Locale> createLocaleMap() {
	// Map<String, Locale> map = new HashMap<String, Locale>();
	// map.put("es", new Locale("es"));
	// map.put("es_AR", new Locale("es", "AR"));
	// map.put("es_AR_other", new Locale("es", "AR", "other"));
	// map.put("en", new Locale("en"));
	// map.put("en_US", new Locale("en", "US"));
	// map.put("en_US_other", new Locale("en", "US", "other"));
	// return map;
	// }

	@RequestMapping(value = "/locale")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		Locale locale = locales.get("en_US");
		System.out.println("Default locale:" + locale);
		localeResolver.setLocale(request, response, locale);
		return new ModelAndView("/views/locale.jsp", "language", new Language());
	}

	@RequestMapping(value = "/localeChange")
	public ModelAndView change(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Language lang,
			BindingResult result) {
		validator.validate(lang, result);
		if (result.hasErrors()) {
			return new ModelAndView("/views/locale.jsp", "language", lang);
		}
		Locale locale = locales.get(lang.getLang());
		System.out.println("Changing to locale: " + locale);
		localeResolver.setLocale(request, response, locale);
		return new ModelAndView("/views/locale.jsp", "language", lang);
	}

}
