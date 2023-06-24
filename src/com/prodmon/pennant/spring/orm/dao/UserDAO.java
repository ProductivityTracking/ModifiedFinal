package com.prodmon.pennant.spring.orm.dao;

import java.util.List;

import com.prodmon.pennant.spring.orm.model.Subtask;
import com.prodmon.pennant.spring.orm.model.Task;

public interface UserDAO {

	public void UpdatePassword(Integer id, String password);

	public List<Task> getUserActivities(String taskType);

	List<Subtask> getUserSubtaskActivities(String taskStatus);
}
