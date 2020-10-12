package com.roadmap.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RouteMapRestService {

	private static Map<String, String> roadMap = new HashMap<String, String>();

//	static {
//		roadMap.put("boston", "new york");
//		roadMap.put("philadelphia", "newark");
//		roadMap.put("newark", "boston");
//		roadMap.put("trenton", "albany");
//
//	}
	
	@PostConstruct
	public void initData() {
		//get the .txt file path
		Resource resource = new ClassPathResource("/static/city.txt");
        try {
			if(null!=resource) {
				BufferedReader bufferedReader =
		                new BufferedReader(new InputStreamReader(resource.getInputStream()));
				if(null!=bufferedReader) {
					String line;
					do {
						line = bufferedReader.readLine();
						if(null!=line) {
							final String []cityData = line.split(",");
							if(null!=cityData && cityData.length>1) {
								roadMap.put(cityData[0].toLowerCase(), cityData[1].toLowerCase());
							}
							
						}
					}while(null!=line);
				}
			}
			
		} catch (IOException e) {
			
		}
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
