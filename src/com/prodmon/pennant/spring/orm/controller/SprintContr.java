package com.prodmon.pennant.spring.orm.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.prodmon.pennant.spring.orm.model.FunctionalTask;
import com.prodmon.pennant.spring.orm.model.FunctionalUnit;
import com.prodmon.pennant.spring.orm.model.FunctionalUnitdto;
import com.prodmon.pennant.spring.orm.model.ModuleDTO;
import com.prodmon.pennant.spring.orm.model.ProjectDto;
import com.prodmon.pennant.spring.orm.model.Sprint;
import com.prodmon.pennant.spring.orm.model.SprintInput;
import com.prodmon.pennant.spring.orm.model.SprintResource;
import com.prodmon.pennant.spring.orm.model.SprintTasks;
import com.prodmon.pennant.spring.orm.model.Task;
import com.prodmon.pennant.spring.orm.model.TaskInput;
import com.prodmon.pennant.spring.orm.model.User;
import com.prodmon.pennant.spring.orm.model.dto.SprintDto;
import com.prodmon.pennant.spring.orm.model.dto.TaskDto;
import com.prodmon.pennant.spring.orm.model.dto.UserDto;
import com.prodmon.pennant.spring.orm.service.ModuleService;
import com.prodmon.pennant.spring.orm.service.ProjectService;
import com.prodmon.pennant.spring.orm.service.ResourceService;
import com.prodmon.pennant.spring.orm.service.SprintService;
import com.prodmon.pennant.spring.orm.service.TaskService;

@Controller
public class SprintContr {

	private final SprintService ss;
	private final ProjectService projectService;
	private final ModuleService moduleService;
	private final ResourceService resourceService;
	private final TaskService taskService;

	@Autowired
	public SprintContr(SprintService ss, ProjectService projectService, ModuleService moduleService,
			ResourceService resourceService, TaskService taskService) {
		this.ss = ss;
		this.projectService = projectService;
		this.moduleService = moduleService;
		this.resourceService = resourceService;
		this.taskService = taskService;

	}

	@RequestMapping(value = "/ShowFunctionalUnits", method = RequestMethod.POST)
	public String createTask(@Validated SprintInput sprintInput, Model model) throws ParseException {
		ss.storeSprint(sprintInput.toEntity());
		System.out.println("Create Tasks Requested" + sprintInput);
		System.out.println("Got this data" + sprintInput.getSprintId());

		List<FunctionalUnit> flist = ss.getFunctionalUnitsByModId(sprintInput.getModuleId());
		List<FunctionalUnitdto> funlistDto = new ArrayList<>();

		for (FunctionalUnit functionalUnit : flist) {
			FunctionalUnitdto funUnitDto = FunctionalUnitdto.fromEntity(functionalUnit);
			funlistDto.add(funUnitDto);
		}

		model.addAttribute("funlist", funlistDto);
		model.addAttribute("pro_id", sprintInput.getProjectId());
		return "ShowFunctionalUnits";
	}

	@RequestMapping(value = "/sprint", method = RequestMethod.GET)
	public String sprint(Model model) {

		System.out.println("sprints_list called");
		List<Sprint> allSprints = ss.getAllSprints();
		model.addAttribute("allSprints", allSprints);
		System.out.println(allSprints);
		return "sprint_home";
	}

	@RequestMapping(value = "/sprint_details", method = RequestMethod.GET)
	public String getSprintDetails(Model model, @RequestParam Integer sprintId) {
		System.out.println("Sprint Details JSP Requested");
		System.out.println("Sprint id " + sprintId);
		SprintDto sprint = ss.getSprintDetails(sprintId);
		// Retrieve the selected sprint details from the database and add them to the model
		model.addAttribute("sprint", sprint);
		List<TaskDto> tasks = taskService.getAllTasks();

		model.addAttribute("tasks", tasks);
		List<SprintTasks> tasksByIdSprints = ss.getAllTasksBySprintId(sprintId);
		model.addAttribute("tasksByIdSprints", tasksByIdSprints);
		return "sprint_details";
	}

	// @RequestMapping(value = "/projectDetails", method = RequestMethod.GET)
	// public String getProjectDetails(Model model) {
	// // System.out.println("Sprint Details JSP Requested");
	//
	// // Retrieve the selected sprint details from the database and add them to the model
	//
	// return "projectDetails";
	// }

	@RequestMapping(value = "/add_sprint", method = RequestMethod.GET)
	public String addSprint(Model model) {
		List<ProjectDto> pl = projectService.getAllProjects();
		model.addAttribute("projects", pl);
		List<UserDto> lu = resourceService.getAllResources();
		model.addAttribute("users", lu);
		return "add_sprint";
	}

	@RequestMapping(value = "/FunctionalUnit", method = RequestMethod.GET)
	public String addSprint() {
		// System.out.println("functional unit jsp");

		// Add any necessary data to the model for rendering the add sprint page

		return "FunctionalUnit";
	}

	@RequestMapping(value = "/SubTaskdetails", method = RequestMethod.GET)
	public String SubtaskDetails() {
		// System.out.println("Subtask Details requested");
		return "SubtaskDetails";
	}

	@RequestMapping(value = "/CreateSubTask", method = RequestMethod.GET)
	public String CreateSubtask() {

		return "CreateSubtask";
	}

	@RequestMapping(value = "/backlogdetails", method = RequestMethod.GET)
	public String pastdue(Model model) {
		ArrayList<Sprint> SprintList = (ArrayList<Sprint>) ss.getBacklogs();

		model.addAttribute("sprintList", SprintList);
		return "backlog";
	}

	@RequestMapping(value = "/BacklogTasks", method = RequestMethod.GET)
	public String getBacklogTasks(Model model, @RequestParam("sprnModlId") int sprnModlId,
			@RequestParam("sprnId") int sprnId) {

		SprintDto sprint = ss.getSprintDetails(sprnId);
		List<Task> taskList = ss.getTasks(sprnModlId);
		model.addAttribute("sprint", sprint);
		model.addAttribute("taskList", taskList);
		return "BacklogTasks";
	}

	@ResponseBody
	@RequestMapping(value = "/getModuleById", method = RequestMethod.POST, produces = "application/json")
	public String getModuleById(@RequestParam("projectId") int projectId) {
		System.out.println("Inside");
		List<ModuleDTO> moduleList = ss.getSprintModulesByProjectId(projectId);
		System.out.println("moduleList" + moduleList.get(0));
		Gson gson = new Gson();
		String json = gson.toJson(moduleList);
		return json;
	}

	@RequestMapping(value = "/Task", method = RequestMethod.POST)
	public String createTask(@ModelAttribute FunctionalTask ft, Model model) {
		System.out.println("Create Task");
		model.addAttribute("funtask", ft);
		System.out.println("tasksare" + ft);
		List<User> lu = ss.getAllUsers();
		model.addAttribute("users", lu);
		List<TaskDto> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "Task";
	}

	@RequestMapping(value = "/TaskAdded", method = RequestMethod.POST)
	public String TaskAdded(@ModelAttribute TaskInput taskInput, @ModelAttribute SprintTasks sprintTask,
			@ModelAttribute SprintResource SprintUserInput, Model model) {
		System.out.println("task" + taskInput);

		ss.storeTask(taskInput.toEntity());
		return "TaskAdded";
	}

	//
	// @RequestMapping(value = "/sprints", method = RequestMethod.GET)
	// public String getAllSprints(Model model) {
	// System.out.println("Sprint List JSP Requested");
	//
	// // Retrieve all sprints from the database
	// List<Sprints> sprints = sprintService.getAllSprints();
	//
	// // Add the sprints to the model
	// model.addAttribute("sprints", sprints);
	//
	// return "Tarak_sprint_home";
	// }
	//
	// @RequestMapping(value = "/sprint_details", method = RequestMethod.GET)
	// public String getSprintDetails(@RequestParam("sprintId")int sprintId, Model model) {
	// // Retrieve the selected sprint details from the database and add them to the model
	//
	// System.out.println("Sprint DETAILS JSP Requested");
	// Sprints sprint = sprintService.getSprintById(sprintId);
	// model.addAttribute("sprint", sprint);
	// return "sprint_details";
	// }
	//
	// @RequestMapping(value = "/new_sprint", method = RequestMethod.GET)
	// public String addSprint(Model model) {
	// System.out.println("Add Sprint JSP Requested");
	//
	// // Add any necessary data to the model for rendering the add sprint page
	//
	// return "newsprint";
	// }
	//
	// @RequestMapping(value = "/create_sprint", method = RequestMethod.POST)
	// public String createSprint(@RequestParam("moduleId") String moduleId,
	// @RequestParam("masterId") String masterId,
	// @RequestParam("projectId") String projectId,
	// @RequestParam("sprintName") String sprintName,
	// @RequestParam("startDate") String startDate,
	// @RequestParam("endDate") String endDate,
	// Model model) {
	// // Create a new sprint and save it to the database
	//
	// System.out.println("Create Sprint Requested");
	//
	// Sprints sprint = new Sprints();
	// sprint.setModuleId(Integer.parseInt(moduleId));
	// sprint.setMasterId(Integer.parseInt(masterId));
	// sprint.setProjectId(Integer.parseInt(projectId));
	// sprint.setSprintName(sprintName);
	// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Replace with your date format
	// Date parsedStartDate;
	// Date parsedEndDate;
	// try {
	// parsedStartDate = dateFormat.parse(startDate);
	// parsedEndDate = dateFormat.parse(endDate);
	// } catch (ParseException e) {
	// // Handle the parsing exception
	// e.printStackTrace();
	// return endDate; // or throw an exception
	// }
	//
	// Timestamp timestampStartDate = new Timestamp(parsedStartDate.getTime());
	// Timestamp timestampEndDate = new Timestamp(parsedEndDate.getTime());
	//
	// sprint.setStartDate(timestampStartDate);
	// sprint.setEndDate(timestampEndDate);
	//
	// sprintService.createSprint(sprint);
	//
	// // Redirect to the sprint list page
	// return "redirect:/sprints";
	// }
	// @RequestMapping(value = "/addsprint", method = RequestMethod.GET)
	// public String saveNewsprint(@Validated Sprints sprint, Model model) {
	// System.out.println("Save New sprint Page Requested");
	// //get all employees from DAO
	//
	// sprintService.createSprint(sprint);
	// //set it to the model
	// //model.addAttribute("emp",emp);
	//
	// return "sprints";
	// }
}