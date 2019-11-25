package com.deloitte;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deloitte.model.PlayBook;
import com.deloitte.model.PredecessorConstraint;
import com.deloitte.model.PredecessorConstraintType;
import com.deloitte.model.Task;
import com.deloitte.service.JsonService;

@SpringBootTest
class GraphExecutionApplicationTests {

	@Inject
	private JsonService jsonService;

	@Test
	void predFS() {

		Task task1 = new Task();
		task1.setId(1L);
		Task task2 = new Task();
		task2.setId(2L);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(232L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));

	}
	
	@Test
	void predFSLag() {
		
		Task task1 = new Task();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setId(3L);
		preCon.setLag(5000);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("finish-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		Task task2 = new Task();
		task2.setId(4L);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	

	@Test
	void predSSNoLag() {
		
		Task task1 = new Task();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setId(3L);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("start-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		Task task2 = new Task();
		task2.setId(4L);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void predSSLag() {
		
		Task task1 = new Task();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setId(3L);
		preCon.setLag(5000);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("start-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		Task task2 = new Task();
		task2.setId(4L);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void startOnTime() {
		
		Task task1 = new Task();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setId(3L);
		task1.setConstraintDateTime(LocalDateTime.now().plusDays(1));
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void parentChild() {
		
		Task task1 = new Task();
		task1.setId(3L);
		Task task2 = new Task();
		task2.setId(4L);
		task2.setParent(true);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}

}
