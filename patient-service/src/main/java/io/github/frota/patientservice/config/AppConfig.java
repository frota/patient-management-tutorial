package io.github.frota.patientservice.config;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfig {

	private static final Locale APP_LOCALE = Locale.of("en", "US");
	private static final TimeZone APP_TIMEZONE = TimeZone.getTimeZone("UTC");
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(APP_LOCALE, APP_TIMEZONE);
	}
	
	@PostConstruct
	public void init() {
		Locale.setDefault(APP_LOCALE);
		TimeZone.setDefault(APP_TIMEZONE);
	}

}
