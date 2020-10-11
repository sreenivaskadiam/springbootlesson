package com.roadmap.restservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RouteMapRestService {

	private static Map<String, String> roadMap = new HashMap<String, String>();

	static {
		roadMap.put("boston", "new york");
		roadMap.put("philadelphia", "newark");
		roadMap.put("newark", "boston");
		roadMap.put("trenton", "albany");

	}

	public String getRoadMap(final String origin, final String destination) {
		if (destination.equalsIgnoreCase(roadMap.get(origin)) || origin.equalsIgnoreCase(roadMap.get(destination))) {
			return "yes";
		} else {
			if (!StringUtils.isEmpty(roadMap.get(destination)) && !StringUtils.isEmpty(mapValue(destination, origin))) {
				return "yes";
			} else if (!StringUtils.isEmpty(roadMap.get(origin))
					&& !StringUtils.isEmpty(mapValue(origin, destination))) {
				return "yes";
			}
		}
		return "no";
	}

	public String mapValue(String keyName, final String destination) {
		String value = roadMap.get(keyName);
		if(null!=keyName && null!=value) {
			if (destination.equalsIgnoreCase(value)) {
				return "yes";
			}else {
				return mapValue(value, destination);
			}
			
		}
		return  null;
	}
}
