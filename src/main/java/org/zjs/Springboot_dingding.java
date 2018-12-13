package org.zjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Springboot_dingding extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Springboot_dingding.class, args);
	}
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Springboot_dingding.class); 
	}
	
	/*@Bean
	public WebMvcConfigurer CorsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("10.10.12.177").allowedMethods("PUT","DELETE","GET","POST")
				.allowedHeaders("*").exposedHeaders("access-control-allow-headers","access-control-allow-methods","access-control-allow-origin","access-control-max-age","X-Frame-Options")
				.allowCredentials(false).maxAge(3600);
			}
		};
	}*/

}
