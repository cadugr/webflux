package com.apirest.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping(value = "/playlists")
	public Flux<Playlist> getPlaylists() {
		return playlistService.findAll();
	}
	
	@GetMapping(value = "/playlists/{id}")
	public Mono<Playlist> getPlaylistById(@PathVariable String id) {
		return playlistService.findById(id);
	}
	
	@PostMapping(value = "/playlists")
	public Mono<Playlist> save(@RequestBody Playlist playList) {
		return playlistService.save(playList);
	}
	
	@GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10)); //intervalo de cada resposta que vamos enviar para o cliente.
		Flux<Playlist> events = playlistService.findAll(); //busca as playlists
		System.out.println("Passou aqui no events");
		return Flux.zip(interval, events);
	}

}
