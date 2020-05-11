package com.example.reactboot.projectdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactboot.projectdashboard.domain.ProjectTask;
import com.example.reactboot.projectdashboard.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private ProjectTaskRepository repository;
	
	public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask){
		
		if(projectTask.getStatus()==null || projectTask.getStatus()==""){
			
			projectTask.setStatus("TO-DO");
		}
		return repository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findAll(){
		return repository.findAll();
	}

	public ProjectTask findById(Long id){
		return repository.getById(id);
	}
	
	public void delete(Long id){
		ProjectTask task = findById(id);
		repository.delete(task);
		
		
	}
}
