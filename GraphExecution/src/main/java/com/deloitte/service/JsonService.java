/**
 * 
 */
package com.deloitte.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.Node;
import com.deloitte.model.PlayBook;
import com.deloitte.model.Task;
import com.deloitte.model.json.GraphJson;
import com.deloitte.model.json.GraphModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vbejjanki
 *
 */
@Service
public class JsonService {

	Logger log = Logger.getLogger(JsonService.class.getName());

	@Inject
	private GraphService graphService;

	@Inject
	private ObjectMapper objMapper;

	@Inject
	private CacheService cService;

	AtomicLong seq = new AtomicLong();

	public String exec(final PlayBook playBook) {

		final GraphModel graphModel = graphService.buildGraph(playBook);
		String json = null;
		try {
			GraphJson graphJson = processJson(graphModel, playBook.getId());
			json = objMapper.writeValueAsString(graphJson);
		} catch (JsonProcessingException e) {
			log.warning("The Json Parsing has been Failed.");
		}
		
		log.info("The Graph Json: " +json);
		
		return json;
	}

	private GraphJson processJson(GraphModel graphModel, long playBookId) {

		GraphJson gJson = new GraphJson();
		gJson.setExecutionGraph(graphModel);
		Map<Long, Task> mapTasks = cService.getMapTaskList(playBookId);
		Map<Long,Task> execTasks =  new HashMap<>();
		for(Node node: graphModel.getNodes()) {
			execTasks.putIfAbsent(node.getExecutionId(), mapTasks.get(node.getTaskId()));
		}
		
		
		//Map<Long,Task> execTasks = graphModel.getNodes().stream().collect(Collectors.toMap(Node::getExecutionId, s -> mapTasks.getOrDefault(s.getTaskId(),)));
		gJson.setMetadata(execTasks);
		return gJson;
	}

}
