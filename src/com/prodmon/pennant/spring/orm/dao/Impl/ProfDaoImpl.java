package com.prodmon.pennant.spring.orm.dao.Impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prodmon.pennant.spring.orm.dao.Profdao;
import com.prodmon.pennant.spring.orm.model.ProfileModel;

public class ProfDaoImpl implements Profdao {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL = "select * from vamsi_users where user_id= ? ";

	// private final String SQL_UPDATE_EMPLOYEE = "update vamsi_users set user_password = ?, user_ludate=current_date
	// where where user_id=?";

	// @Autowired
	// public ProfDaoImpl(DataSource dataSource) {
	// System.out.println("came herer");
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }

	public List<ProfileModel> getAllprof() {

		return null;
	}

	// public int updatepassword(String password, int id) {
	// System.out.println("i am updating");
	// return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, password, id);
	// }

	public int updatepassword(String password, int id) {
		System.out.println("Updating password");

		String sql = "UPDATE vamsi_users SET user_password = ? WHERE user_id = ?";

		return jdbcTemplate.update(sql, password, id);
	}

}