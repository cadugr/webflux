package com.apirest.webflux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


//@Configuration
public class PlaylistRouter {

	//@Bean
	RouterFunction<ServerResponse> route(PlaylistHandler handler) {
		return RouterFunctions
				.route(GET("/playlists").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/playlists/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/playlists").and(accept(MediaType.APPLICATION_JSON)), handler::save);
	}
	
}
