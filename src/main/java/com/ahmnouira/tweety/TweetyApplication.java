package com.ahmnouira.tweety;

import java.util.Arrays;
import java.util.UUID;

import com.ahmnouira.tweety.model.Role;
import com.ahmnouira.tweety.model.Tweet;
import com.ahmnouira.tweety.model.User;
import com.ahmnouira.tweety.service.TweetService;
import com.ahmnouira.tweety.service.UserService;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class TweetyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetyApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserService userService, TweetService tweetService) {
		return args -> {
			// assets/images/rgcB.jpg
			userService.save(new User(UUID.randomUUID().toString(), "noop}abv123", "ahmnouira", Role.ADMIN, null,
					"https://i.pravatar.cc/500?img=4", null)).subscribe(user -> {
						tweetService.save(new Tweet(null, null, user, "Hi this is a Tweet")).subscribe();
					});

			userService.save(new User(UUID.randomUUID().toString(), "{noop}abc123", "shadi", Role.USER, null,
					"https://i.pravatar.cc/500?img=12", null)).subscribe(user -> {
						tweetService.save(new Tweet(null, null, user, "Hi @shazin")).subscribe();
					});

		};

	}

	/**
	 * Cors configuration
	 * 
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsFilterRegistration() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
