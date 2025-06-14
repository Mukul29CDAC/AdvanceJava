package com.cdac.initialise;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.cdac.config.WebMvcConfigurator;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class MvcInitialiser implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		
		webContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic frontController = servletContext.addServlet("primary",new DispatcherServlet(webContext));
		
		frontController.setLoadOnStartup(10);
		
		frontController.addMapping("/spring/*");
		
		webContext.register(WebMvcConfigurator.class);
		
	}

}
