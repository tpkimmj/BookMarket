package com.book.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Value("${resources.location}")
    private String resourcesLocation;
    @Value("${resources.uri_path}")
    private String resourcesUriPath;
    
    @Autowired
    AuthenticInterceptor authenticInterceptor;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(resourcesUriPath + "/**")
    	.addResourceLocations("file:///" + resourcesLocation);
    }
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
         registry.addInterceptor(authenticInterceptor);
    }

}