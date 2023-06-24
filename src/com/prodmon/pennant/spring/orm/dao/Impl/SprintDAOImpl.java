package com.prodmon.pennant.spring.orm.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.prodmon.pennant.spring.orm.dao.SprintDAO;
import com.prodmon.pennant.spring.orm.model.FunctionalUnit;
import com.prodmon.pennant.spring.orm.model.Module;
import com.prodmon.pennant.spring.orm.model.Sprint;
import com.prodmon.pennant.spring.orm.model.SprintTasks;
import com.prodmon.pennant.spring.orm.model.Sprints;
import com.prodmon.pennant.spring.orm.model.Task;
import com.prodmon.pennant.spring.orm.model.User;
import com.prodmon.pennant.spring.orm.model.dto.SprintDto;

@Component
@Transactional

public class SprintDAOImpl implements SprintDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SprintDto getSprintById(int sprintId) {
		Sprint sprint = entityManager.find(Sprint.class, sprintId);

		SprintDto sprintdto = SprintDto.fromEntity(sprint);

		// SprintDto sprintdto = sprint.stream().map(SprintDto::fromEntity).collect(Collectors.toList());
		return sprintdto;
	}

	@Override
	public List<Sprint> getAllSprints() {
		String query = "SELECT s FROM Sprint s";
		return entityManager.createQuery(query, Sprint.class).getResultList();
	}

	@Override
	@Transactional
	public void createSprint(Sprint sprint) {
		entityManager.persist(sprint);
	}

	@Override
	@Transactional
	public void updateSprint(Sprint sprint) {
		entityManager.merge(sprint);
	}

	@Override
	@Transactional
	public void deleteSprint(int sprintId) {
		Sprints sprint = entityManager.find(Sprints.class, sprintId);
		if (sprint != null) {
			entityManager.remove(sprint);
		}
	}

	@Override
	public List<SprintDto> getSprintbyprojectId(Integer projectId) {
		short getProjectId = projectId.shortValue();
		TypedQuery<Sprint> query = entityManager
				.createQuery("SELECT s FROM Sprint s WHERE s.project.projectId = :proj_id", Sprint.class);
		query.setParameter("proj_id", getProjectId);
		List<Sprint> sprints = query.getResultList();

		List<SprintDto> sprintDTOList = new ArrayList<>();

		for (Sprint sprint : sprints) {
			SprintDto Sprintdto = SprintDto.fromEntity(sprint);
			sprintDTOList.add(Sprintdto);
		}

		return sprintDTOList;
	}

	@Override
	public List<Sprint> getBaskLogs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SprintDto getSprintDetails(int sprintId) {
		Sprint sprint = entityManager.find(Sprint.class, sprintId);

		SprintDto sprintdto = SprintDto.fromEntity(sprint);

		// SprintDto sprintdto = sprint.stream().map(SprintDto::fromEntity).collect(Collectors.toList());
		return sprintdto;
	}

	@Override
	public List<SprintTasks> allTaskBySprintId(Integer sprintId) {
		String query = "SELECT st FROM SprintTasks st WHERE st.id.sprnId = :sprintId";

		return entityManager.createQuery(query, SprintTasks.class).setParameter("sprintId", sprintId).getResultList();
	}

	@Override
	public void storeSprint(Sprint sprint) {
		if (sprint.getSprintId() == 0) {
			entityManager.persist(sprint); // New entity, use persist
		} else {
			entityManager.merge(sprint); // Existing entity, use merge
		}
	}

	@Override
	public List<Module> getSprintModulesByProjectId(int projectId) {
		short projid = (short) projectId;
		String query = "SELECT m FROM Module m WHERE m.project.projectId = :projectId AND m.id NOT IN (SELECT s.moduleId.id FROM Sprint s)";
		TypedQuery<Module> typedQuery = entityManager.createQuery(query, Module.class);
		typedQuery.setParameter("projectId", projid);
		List<Module> modulesList = typedQuery.getResultList();
		return modulesList;
	}

	@Override
	public List<FunctionalUnit> getFunctionalUnitsByModId(int modl_id) {
		String query = "SELECT distinct fu FROM FunctionalUnit fu WHERE fu.id.module.id = :modlId";

		return entityManager.createQuery(query, FunctionalUnit.class).setParameter("modlId", modl_id).getResultList();
	}

	public void storeTask(Task task) {
		if (task.getTaskId() == 0) {
			entityManager.persist(task); // New entity, use persist
		} else {
			entityManager.merge(task); // Existing entity, use merge
		}
	}

	public List<User> getAllUsers() {
		String query = "SELECT u FROM Sprint u";
		return entityManager.createQuery(query, User.class).getResultList();
	}

	@Override
	public List<Task> getTasks(int modlId) {
		String query = "SELECT t FROM Task t WHERE t.module.id = :modlId";

		return entityManager.createQuery(query, Task.class).setParameter("modlId", modlId).getResultList();
	}
}