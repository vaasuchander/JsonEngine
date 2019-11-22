/**
 * 
 */
package com.deloitte.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.Task;
import com.deloitte.model.json.GraphModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vbejjanki
 *
 */
@Service
@SuppressWarnings("rawtypes")
public class JsonService {
	
	@Inject
	private GraphService graphService;
	
	@Inject
	private ObjectMapper objMapper;
	
	public String exec(List<Task> tasksList) {
		
		GraphModel graphModel = graphService.buildGraph(tasksList);
		String json = null;
		try {
			json = objMapper.writeValueAsString(graphModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	
}
