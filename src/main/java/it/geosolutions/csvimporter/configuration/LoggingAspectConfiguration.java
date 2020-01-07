package it.geosolutions.csvimporter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import it.geosolutions.csvimporter.logging.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {
	
	@Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
