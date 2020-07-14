package com.master.text.master.test;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * Rest Controller to expose the connected service 
 * @author maneesh
 *
 */
@ApiOperation(value = "Connected API to return yes if the given 2 cities are connected", response = String.class)
@RestController
public class ConnectedController{
	
	/**
	 * Get rest method to expose the connected services
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping("/connected")
	public String connected(@RequestParam(value = "origin")String origin,
			@RequestParam(value = "destination")String destination){
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)){
			return "no";
		}
		Boolean sourceToDestination = CitiesGraph.checkSourceToDestination(origin,destination);
		return sourceToDestination==true?"yes":"no";
	}
	
}