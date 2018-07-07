package br.ufpi.es.appcrud.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Faz a Configuracao do Servlet Dispatcher da Aplicacao
 * @author armandosoaressousa
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Define a Classe de Configuracao da aplicacao
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}

	/**
	 * Define o path principal da aplicacao
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[] {encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    boolean done = registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); 
	    if(!done) throw new RuntimeException();
	    
	    registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
