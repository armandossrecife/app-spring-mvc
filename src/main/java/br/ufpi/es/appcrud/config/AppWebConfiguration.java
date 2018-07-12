package br.ufpi.es.appcrud.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.ufpi.es.appcrud.controller.AcessoController;
import br.ufpi.es.appcrud.controller.AdviceController;
import br.ufpi.es.appcrud.controller.UsuarioController;
import br.ufpi.es.appcrud.dados.LogAcessoDAO;
import br.ufpi.es.appcrud.dados.UsuarioDAO;
import br.ufpi.es.appcrud.infra.FileSaver;

@Configuration
@ComponentScan(basePackageClasses={AcessoController.class, UsuarioController.class, UsuarioDAO.class, AdviceController.class, LogAcessoDAO.class, FileSaver.class})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
	/**
	 * Resolver dos arquivos jsp da aplicação dentro da pasta views. Com isso, não precisa colocar a extensão .jsp
	 * @return resolver
	 */
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * Permite o uso do arquivo de messages.properties
	 * @return recurso: arquivo de mensagens
	 */
	@Bean
	public MessageSource messageSource(){
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("/WEB-INF/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(1);
	    return messageSource;
	}
	
    /**
     * Adicionada configuração para pasta de recursos do projeto.
    */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    /**
     * Nome do DefaultServlet que gerencia a aplicação appcrudmvc
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
    /**
     * Formatação padrão de data para a aplicação appcrudmvc
     * @return conversão da data registrada com o padrão definido
     */
	@Bean
	public FormattingConversionService mvcConversionService(){
	    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy HH:mm:ss"));
	    formatterRegistrar.registerFormatters(conversionService);

	    return conversionService;
	}
	
	/**
	 * Permite que o Servlet Dispatcher manipule arquivos do tipo multipart
	 * @return o resover do Multipart
	 */
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	/**
	 * Permite que o servlet dispatcher devolva o corpo de resposta em formato JSON 
	 * @param manager gerenciador do ContentNegotition
	 * @return resover 
	 */
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
	    List<ViewResolver> viewResolvers = new ArrayList<>();
	    viewResolvers.add(getViewResolver());
	    viewResolvers.add(new JsonViewResolver());

	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setViewResolvers(viewResolvers);
	    resolver.setContentNegotiationManager(manager);
	    return resolver;
	}
	
}