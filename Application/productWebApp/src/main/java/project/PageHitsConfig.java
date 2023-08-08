package project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHitsConfig {
	
	@Bean
	public PageHitsCounter getPageHitsCount() {
		return new PageHitsCounter(0);
	}
}
