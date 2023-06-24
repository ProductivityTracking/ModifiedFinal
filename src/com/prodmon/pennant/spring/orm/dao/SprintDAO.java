package com.prodmon.pennant.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.prodmon.pennant.spring.orm.model.FunctionalUnit;
import com.prodmon.pennant.spring.orm.model.Module;
import com.prodmon.pennant.spring.orm.model.Sprint;
import com.prodmon.pennant.spring.orm.model.SprintTasks;
import com.prodmon.pennant.spring.orm.model.Task;
import com.prodmon.pennant.spring.orm.model.User;
import com.prodmon.pennant.spring.orm.model.dto.SprintDto;

@Component
@Transactional

@Service
public interface SprintDAO {
	List<Sprint> getBaskLogs();

	SprintDto getSprintDetails(int sprintId);

	List<Task> getTasks(int modlId);

	List<Sprint> getAllSprints();

	List<SprintTasks> allTaskBySprintId(Integer sprintId);

	void storeSprint(Sprint sprint);

	List<Module> getSprintModulesByProjectId(int projectId);

	List<FunctionalUnit> getFunctionalUnitsByModId(int modl_id);

	public void storeTask(Task task);

	public List<User> getAllUsers();
	// Add other methods as needed

	SprintDto getSprintById(int sprintId);

	void createSprint(Sprint sprint);

	void deleteSprint(int sprintId);

	void updateSprint(Sprint sprint);

	List<SprintDto> getSprintbyprojectId(Integer projectId);

}
