package com.roadmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roadmap.restservice.RouteMapRestService;

@RestController
public class RouteMapRestController {

	@Autowired
	private RouteMapRestService routeMapRestService;

	@GetMapping(path = "/connected")
	public String routeMap(@RequestParam(value = "origin", required = true) final String origin,
			@RequestParam(value = "destination", required = true) final String destination) {

		if (!StringUtils.isEmpty(origin) && !StringUtils.isEmpty(destination)) {
			return routeMapRestService.getRoadMap(origin.toLowerCase(), destination.toLowerCase());
		}
		return "no";
	}

}
