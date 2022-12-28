package com.snort.intelli.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.snort.intelli.app.repository.TodosRepository;
import com.snort.intelli.app.service.TodosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.snort.intelli.app.entites.Todos;

@RestController
@RequestMapping("/todos")
public class TodosController {

	private Logger log = LoggerFactory.getLogger(TodosController.class);

	//JPA repository
	@Autowired
	private TodosService todosService;

	@PostMapping("/create")
	public Todos createTask(@RequestBody Todos todos) {
		log.info("TodosController : createTask executed!");
		if(todos.getAssignedDate()==null) todos.setAssignedDate(new Date());
		Todos creTodos1 = todosService.createTask(todos);
		return creTodos1;
	}

	@GetMapping("/")
	public List<Todos> findAll() {
		log.info("TodosController : findAll executed!");
		return (List<Todos>) todosService.findAll();
	}
	
	@GetMapping("/{id}")
	public Todos findOneTodo(@PathVariable Long id) {
		log.info("TodosController : findOneTodo executed!");
		return todosService.findOneTodo(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteOneTodo(@PathVariable Long id) {
		log.info("TodosController : deleteOneTodo executed!");
		return todosService.deleteOneTodo(id);
	}
	
	@PutMapping("/update/")
	public Todos updateOneTodo(@RequestBody Todos newTodo) {
		return todosService.updateTask(newTodo);
	}
	
}
