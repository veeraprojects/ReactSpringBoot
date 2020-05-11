package com.example.reactboot.projectdashboard.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactboot.projectdashboard.domain.ProjectTask;
import com.example.reactboot.projectdashboard.service.ProjectTaskService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
		
		if(result.hasErrors()){
			Map<String,String> errorMap = new HashMap<>();
			
			for(FieldError error:result.getFieldErrors()){
				errorMap.put(error.getField(), error.getDefaultMessage());
				
			}
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);
		return new ResponseEntity<ProjectTask>(newPT,HttpStatus.CREATED);
	}
	

	@GetMapping("/all")
	public Iterable<ProjectTask> getAllTasks(){
		return projectTaskService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPTById(@PathVariable Long id){
		ProjectTask pTask = projectTaskService.findById(id);
		return new ResponseEntity<ProjectTask>(pTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long id){
		projectTaskService.delete(id);
		//return ResponseEntity.ok().build();
		return new ResponseEntity<String>("Project Task deleted", HttpStatus.OK);
	}
}
