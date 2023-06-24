package com.prodmon.pennant.spring.orm.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.prodmon.pennant.spring.orm.model.Project;
import com.prodmon.pennant.spring.orm.model.ProjectDto;
import com.prodmon.pennant.spring.orm.model.ProjectFilter;
import com.prodmon.pennant.spring.orm.model.Sprint;

@Component
public class ProjectDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ProjectDto> getAllProjects() {
		TypedQuery<Project> query = entityManager.createQuery("SELECT pt FROM Project pt", Project.class);
		List<Project> projects = query.getResultList();
		List<ProjectDto> convertedProjects = projects.stream().map(ProjectDto::fromEntity).collect(Collectors.toList());
		return convertedProjects;
	}

	public Project findById(short id) {
		return entityManager.find(Project.class, id);
	}

	public ProjectDto getProjectExpiredSprints(ProjectDto projectDto) {
		TypedQuery<Sprint> query = entityManager.createQuery(
				"SELECT s FROM Sprint s WHERE s.endDate < current_date() AND s.project.projectId = :projectId",
				Sprint.class);
		query.setParameter("projectId", projectDto.getProjectId());
		List<Sprint> expiredSprints = query.getResultList();
		projectDto.setCompletedSprints(expiredSprints.size());
		return projectDto;
	}

	public ProjectDto getProjectTotalSprints(ProjectDto projectDto) {
		TypedQuery<Sprint> query = entityManager
				.createQuery("SELECT s FROM Sprint s WHERE s.project.projectId = :projectId", Sprint.class);
		query.setParameter("projectId", projectDto.getProjectId());
		List<Sprint> expiredSprints = query.getResultList();
		projectDto.setTotalSprints(expiredSprints.size());
		return projectDto;
	}

	public List<ProjectDto> getFilterProjects(ProjectFilter projectFilter) {
		String jpql = "SELECT p FROM Project p WHERE 1 = 1";
		TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);

		String projectId = projectFilter.getProjectId();
		String status = projectFilter.getStatus();

		if (projectId != null && !projectId.isEmpty()) {
			jpql += " AND p.projectId = :projectId";
			query.setParameter("projectId", projectId);
		}
		if (status != null && !status.isEmpty()) {
			jpql += " AND p.status = :status";
			query.setParameter("status", status);
		}

		List<Project> projects = query.getResultList();
		List<ProjectDto> convertedProjects = projects.stream().map(ProjectDto::fromEntity).collect(Collectors.toList());
		return convertedProjects;
	}

	public ProjectDto getProjectById(Integer proj_id) {
		short getProjectId = proj_id.shortValue();
		TypedQuery<Project> query = entityManager.createQuery("SELECT pt FROM Project pt WHERE pt.projectId = :proj_id",
				Project.class);
		query.setParameter("proj_id", getProjectId);

		List<Project> projects = query.getResultList();

		if (!projects.isEmpty()) {
			Project project = projects.get(0);
			return ProjectDto.fromEntity(project);
		} else {

			return null;
		}
	}

	public void setNewProject(Project project) {
		entityManager.persist(project);
	}

	// public Integer getProjectTasksById(Integer projId) {
	// short getProjectId = projId.shortValue();
	// TypedQuery<Project> query = entityManager.createQuery("SELECT pt FROM project pt WHERE pt.projectId = :proj_id",
	// Project.class);
	// query.setParameter("proj_id", getProjectId);
	//
	// List<Project> projects = query.getResultList();
	//
	// // if (!projects.isEmpty()) {
	// // Project project = projects.get(0);
	// // return ProjectDto.fromEntity(project);
	// // }
	// return null;
	// }

	public Integer getAllSprintsByProjectId(Integer projId) {
		short getProjectId = projId.shortValue();
		System.out.println("before " + getProjectId);
		TypedQuery<Sprint> query = entityManager
				.createQuery("SELECT sp FROM Sprint sp WHERE sp.project.projectId = :proj_id", Sprint.class);

		query.setParameter("proj_id", getProjectId);

		List<Sprint> sprintList = query.getResultList();
		System.out.println("Trying" + getProjectId);

		// Process the sprintList or perform any other operations
		System.out.println("after " + getProjectId);
		List<Sprint> projects = query.getResultList();

		return projects.size();

	}

	public Integer getCompletedSprintsByProjectId(Integer projId) {
		short getProjectId = projId.shortValue();

		TypedQuery<Sprint> query = entityManager.createQuery(
				" select sp FROM Sprint sp WHERE project.projectId = :projId AND endDate < :current_date",
				Sprint.class);
		query.setParameter("projId", getProjectId);
		query.setParameter("current_date", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		List<Sprint> projects = query.getResultList();
		return projects.size();
	}
	


}
