package edu.stevens.ssw690.DuckSource.model;

import java.sql.Time;
import java.util.Date;

public class WorkInterval {
	
	private Date workDate;
	private Time startTime;
	private Time endTime;
	
	public WorkInterval(Date workDate, Time startTime, Time endTime) {
		super();
		this.workDate = workDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	

}
