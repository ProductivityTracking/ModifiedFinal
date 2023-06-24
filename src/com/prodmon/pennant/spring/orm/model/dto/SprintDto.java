package com.prodmon.pennant.spring.orm.model.dto;

import java.sql.Timestamp;

public class SprintDto {
	private int sprn_id;
	private int sprn_modl_id;
	private int sprn_master;
	private Timestamp startDate;

	public Timestamp getStartDate() {
		return startDate;
	}

	private Timestamp endDate;
	private int proj_id;
	private String sprint_name;

	public SprintDto() {

	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public SprintDto(int sprn_id, int sprn_modl_id, int sprn_master, Timestamp startDate, Timestamp endDate,
			int proj_id, String sprint_name) {
		super();
		this.sprn_id = sprn_id;
		this.sprn_modl_id = sprn_modl_id;
		this.sprn_master = sprn_master;
		this.startDate = startDate;
		this.endDate = endDate;
		this.proj_id = proj_id;
		this.sprint_name = sprint_name;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public int getSprn_id() {
		return sprn_id;
	}

	public void setSprn_id(int sprn_id) {
		this.sprn_id = sprn_id;
	}

	public int getSprn_modl_id() {
		return sprn_modl_id;
	}

	public void setSprn_modl_id(int sprn_modl_id) {
		this.sprn_modl_id = sprn_modl_id;
	}

	public int getSprn_master() {
		return sprn_master;
	}

	public void setSprn_master(int sprn_master) {
		this.sprn_master = sprn_master;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public String getSprint_name() {
		return sprint_name;
	}

	public void setSprint_name(String sprint_name) {
		this.sprint_name = sprint_name;
	}

	@Override
	public String toString() {
		return "SprintDto [sprn_id=" + sprn_id + ", sprn_modl_id=" + sprn_modl_id + ", sprn_master=" + sprn_master
				+ ", startDate=" + startDate + ", sprn_enddate=" + endDate + ", proj_id=" + proj_id + ", sprint_name="
				+ sprint_name + "]";
	}

	public static SprintDto fromEntity(Sprint sprint) {
		SprintDto Sprintdto = new SprintDto();

		Sprintdto.setProj_id(sprint.getProjectId().getProjectId());
		Sprintdto.setSprint_name(sprint.getSprintName());
		Sprintdto.setSprn_id(sprint.getSprintId());
		Sprintdto.setSprn_master(sprint.getMasterId().getUserId());
		Sprintdto.setSprn_modl_id(sprint.getModuleId().getId());
		Sprintdto.setEndDate(sprint.getEndDate());
		Sprintdto.setStartDate(sprint.getStartDate());
		return Sprintdto;
	}

}
