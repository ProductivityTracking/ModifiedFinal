package com.prodmon.pennant.spring.orm.dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.prodmon.pennant.spring.orm.dao.UserDAO;
import com.prodmon.pennant.spring.orm.model.Subtask;
import com.prodmon.pennant.spring.orm.model.Task;
import com.prodmon.pennant.spring.orm.model.User;

public class UserDaoImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void UpdatePassword(Integer id, String password) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
		query.setParameter("id", id);

		User user = query.getSingleResult();
		if (user != null) {
			user.setUserPassword(password);
		}
	}

	@Override
	public List<Task> getUserActivities(String taskStatus) {
		TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.taskStatus = :taskStatus",
				Task.class);
		query.setParameter("taskStatus", taskStatus);

		List<Task> tasks = query.getResultList();
		return tasks;
	}
	
	@Override
	public List<Subtask> getUserSubtaskActivities(String sbts_status) {
		TypedQuery<Subtask> query = entityManager.createQuery("SELECT s FROM Subtask s WHERE s.sbts_status = :sbts_status",
				Subtask.class);
		query.setParameter("sbts_status", sbts_status);

		List<Subtask> tasks = query.getResultList();
		return tasks;
	}

}