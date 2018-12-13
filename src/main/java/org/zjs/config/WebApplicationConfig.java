package org.zjs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**")
				.addResourceLocations("/image/");
		
		/*registry.addResourceHandler("/js/**")
				.addResourceLocations("/js/");*/
		
		registry.addResourceHandler("/cs/**")
				.addResourceLocations("/cs/");	
	}
	
	
}
