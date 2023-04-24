package com.esp.gestionusers.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@EnableWebMvc
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final Environment env;

  

    public WebConfigurer(Environment env) {
        this.env = env;
      
    }



    /*
     * @Bean
     * public CorsFilter corsFilter() {
     * UrlBasedCorsConfigurationSource source = new
     * UrlBasedCorsConfigurationSource();
     * CorsConfiguration config = jHipsterProperties.getCors();
     * if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) ||
     * !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
     * log.debug("Registering CORS filter");
     * source.registerCorsConfiguration("/api/**", config);
     * source.registerCorsConfiguration("/management/**", config);
     * source.registerCorsConfiguration("/v3/api-docs", config);
     * source.registerCorsConfiguration("/swagger-ui/**", config);
     * }
     * return new CorsFilter(source);
     * }
     */
    // Enable Global CORS support for the application

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/management/**", config);
            source.registerCorsConfiguration("/v2/api-docs", config);
        }
        return new CorsFilter(source);
    }

	@Override
	public void onStartup(jakarta.servlet.ServletContext servletContext) throws jakarta.servlet.ServletException {
		// TODO Auto-generated method stub
		
	}
}
