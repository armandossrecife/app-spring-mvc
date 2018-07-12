package br.ufpi.es.appcrud.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Faz a Configuracao do Servlet Dispatcher da Aplicacao
 * @author armandosoaressousa
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	/**
	 * Carrega as configurações logo ao iniciar a aplicação
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
	}

	/**
	 * Define a Classe de Configuracao da aplicacao
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	/**
	 * Define o path principal da aplicacao
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	/**
	 * Faz uso do enconding UTF-8 para todos os arquivos da aplicação
	 */
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[] {encodingFilter};
	}
	
	/**
	 * Lança uma exceção RuntimeException para ser tratada pelo controlador AdviceController
	 * Também faz o registro de configuração de tratamento de arquivos Multipart
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		//TODO fazer o tratamento quando um recurso não existir 
	    boolean done = registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); 
	    if(!done) throw new RuntimeException();
	    
	    registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
