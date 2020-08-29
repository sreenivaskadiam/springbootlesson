package com.roadmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roadmap.restservice.RouteMapRestService;

@RestController
public class RouteMapRestController {

	@Autowired
	private RouteMapRestService routeMapRestService;

	@GetMapping(path = "/connected")
	public String routeMap(@RequestParam String origin, @RequestParam String destination) {
		
		if (null != origin && null != destination && origin.length() > 1 && destination.trim().length() > 1) {
			return routeMapRestService.getRoadMap(origin.toLowerCase(), destination.toLowerCase());
		}
		return "no";
	}

}
