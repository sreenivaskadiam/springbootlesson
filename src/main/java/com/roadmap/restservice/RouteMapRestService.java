package com.roadmap.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RouteMapRestService {

	private static Map<String, String> roadMap = new HashMap<String, String>();

	static {
		roadMap.put("boston", "newyork");
		roadMap.put("philadelphia", "newark");
		roadMap.put("newark", "boston");
		roadMap.put("trenton", "albany");

	}

	public String getRoadMap(final String origin, final String destination) {
		return destination.equalsIgnoreCase(roadMap.get(origin)) ? "yes" : mapValue(origin, destination);
	}

	public String mapValue(final String origin, final String destination) {
		List<String> result = new ArrayList<String>();
		roadMap.forEach((K, V) -> {
			if (destination.equalsIgnoreCase(V)) {
				result.add("yes");
			}
		});

		return null != result && result.size() > 0 ? "yes" : "no";
	}
}
