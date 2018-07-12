package br.ufpi.es.appcrud.config;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Permite devolver o corpo JSON de um recurso em uma requisição
 * @author armandosoaressousa
 *
 */
public class JsonViewResolver implements ViewResolver {
	 @Override
	    public View resolveViewName(String arg0, Locale arg1) throws Exception {
	        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
	        jsonView.setPrettyPrint(true);
	        return jsonView;
	    }
}
