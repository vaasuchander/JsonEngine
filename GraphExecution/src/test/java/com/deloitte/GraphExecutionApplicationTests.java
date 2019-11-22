package com.deloitte;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deloitte.model.Task;
import com.deloitte.service.JsonService;

@SpringBootTest
class GraphExecutionApplicationTests {

	@Inject
	private JsonService jsonService;
	
	@Test
	void predecessorNoLag() {
		
		Task task1 =  new Task();
		task1.setId(1L);
		Task task2 =  new Task();
		task1.setId(2L);
		List<Task> list =  new ArrayList<>();
		list.add(task1);
		list.add(task2);
		System.out.println(jsonService.exec(list));
		assertThat(jsonService.exec(list),any(String.class));
		jsonService.exec(list);
		
	}

}
