package com.prodmon.pennant.spring.orm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.prodmon.pennant.spring.orm.model.Subtask;

@Repository
public class SubtaskDao {

	@PersistenceContext
	private EntityManager entityManager;
	private Object subtaskId;

	public Subtask save(Subtask subtask) {
		System.out.println(subtask);
		entityManager.persist(subtask);
		return subtask;
	}

	public void saveSubtask(Subtask subtask) {
		entityManager.persist(subtask);
	}

	public void setNewSubTask(Subtask subtask) {
		entityManager.persist(subtask);
	}
}
