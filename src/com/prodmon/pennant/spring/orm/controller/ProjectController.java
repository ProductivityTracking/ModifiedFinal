package com.prodmon.pennant.spring.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.prodmon.pennant.spring.orm.model.ProjectDto;
import com.prodmon.pennant.spring.orm.model.ProjectFilter;
import com.prodmon.pennant.spring.orm.model.ProjectInput;
import com.prodmon.pennant.spring.orm.model.ProjectTask;
import com.prodmon.pennant.spring.orm.model.Task;
import com.prodmon.pennant.spring.orm.model.User;
import com.prodmon.pennant.spring.orm.service.ProjectService;
import com.prodmon.pennant.spring.orm.service.ProjectTaskService;
import com.prodmon.pennant.spring.orm.service.ResourceService;
import com.prodmon.pennant.spring.orm.service.TaskService;

@RestController
public class ProjectController {
	private final ProjectService projectService;
	private final ResourceService resourceService;
	private final TaskService taskService;
	private final ProjectTaskService projectTaskService;

	@Autowired
	public ProjectController(ProjectService projectService, ResourceService resourceService,TaskService taskService,ProjectTaskService projectTaskService) {
		this.projectService = projectService;
		this.resourceService = resourceService;
		this.taskService = taskService;
		this.projectTaskService = projectTaskService;
	}

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String getAllProjects(Model model) {
		System.out.println("projects called");
		List<ProjectDto> projectDto = projectService.getAllProjects();
		model.addAttribute("projectDto", projectDto);
		return "emplist";
	}

	@RequestMapping(value = "/projectfilter", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String filterProjects(@Validated ProjectFilter projectFilter) {
		System.out.println(projectFilter.getProjectId());
		System.out.println(projectFilter.getStatus());
		System.out.println("System.out.println(\"getFilterProjects called\"); called");

		List<ProjectDto> filteredProjects = projectService.filterProjects(projectFilter);
		System.out.println("filteredProjects");

		Gson gson = new Gson();
		String json = gson.toJson(filteredProjects);

		return json;
	}

	@RequestMapping(value = "/savenewproject", method = RequestMethod.GET)
	public String setNewProjectData(@Validated ProjectInput projectInput, Model model) {
		System.out.println("proj manager " + projectInput.getProjectManager());
		projectService.setNewProject(projectInput);
		return "redirect:/projects";

	}

	@RequestMapping(value = "/projectDetails", method = RequestMethod.GET)
	public String projectDetails(@RequestParam("projectId") Integer projectId, Model model) {
		// Retrieve the selected sprint details from the database and add them to the model
		ProjectDto projectDto = projectService.getProjectById(projectId);
		projectService.getProjectStatById(projectDto);
		projectService.setTasksToProjDto(projectId, projectDto);
		model.addAttribute("projectDto", projectDto);
		return "projectDetails"; // Replace with your desired view name
	}

	@RequestMapping(value = "/createproject", method = RequestMethod.GET)
	public String setNewProject(Model model) {
		List<User> allMngs = resourceService.getAllProjManagers();
		System.out.println("proj mngs " + allMngs);
		System.out.println("add new project jsp called");
		model.addAttribute("allMngs", allMngs);
		return "safety";
	}

	@RequestMapping(value = "/resourceDetailsByProjId", method = RequestMethod.GET)
	public String getResourceDetailsByProjectId(@RequestParam("projectId") int projectId, Model model) {
		List<User> resources = resourceService.getUsersByProjectId(projectId);
		System.out.println("resources " + resources);
		model.addAttribute("resources", resources);
		return "projectResourceDetails";
	}
	

}
// @PostMapping
// public ProjectDto createProject(@RequestBody ProjectInput projectInput) {
// return projectService.createProject(projectInput);
// }