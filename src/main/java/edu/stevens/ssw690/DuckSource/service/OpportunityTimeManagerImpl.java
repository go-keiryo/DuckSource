package edu.stevens.ssw690.DuckSource.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityTimeDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityTime;
import edu.stevens.ssw690.DuckSource.model.WorkInterval;

/**
 * 
 * @author susan
 * @see interface (OpportunityTimeManager)
 * 
 */
@Service
// Not to be called directly
public class OpportunityTimeManagerImpl implements OpportunityTimeManager {

	@Autowired
	OpportunityTimeDao opportunityTimeDao;
	
	public void persist(OpportunityTime opportunityTime) {
		opportunityTimeDao.persist(opportunityTime);;
	}

	public void merge(OpportunityTime opportunityTime) {
		opportunityTimeDao.merge(opportunityTime);
	}

	public void remove(OpportunityTime opportunityTime) {
		opportunityTimeDao.remove(opportunityTime);
		
	}

	public OpportunityTime findById(Integer id) {
		return opportunityTimeDao.findById(id);
	}

	public List<OpportunityTime> getByUser(Integer userId) {
		return opportunityTimeDao.getByUser(userId);
	}

	public List<OpportunityTime> getByOpportunity(Integer userId, Integer opportunityId) {
		
		return opportunityTimeDao.getByOpportunity(userId, opportunityId);
		
	}

	public List<OpportunityTime> getByDate(Integer userId, Integer opportunityId, Date startDate, Date endDate) {
		
		return opportunityTimeDao.getByDate(userId, opportunityId, startDate, endDate);
		
	}
	
	 public void clearTime(Integer userId,Integer oppId,Date startDate, Date endDate) {
		 opportunityTimeDao.clearTime(userId, oppId, startDate, endDate);
	 }
	 
	 public List<String> getTimeforDisplay(int userId, int oppId, int month, int year) {
    	 List<String> workDays = new ArrayList<String>();
    	 YearMonth yearMonth = YearMonth.of(year, month+1);
	     int daysInMonth = yearMonth.lengthOfMonth();
    	 for (int i=0; i <daysInMonth ; i++) {
    		 	 LocalDate localDate = LocalDate.of(year, month+1, i+1);
	    		 Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    		 // get work hours for day from database
	    		 List<OpportunityTime> oppTimeList = getByDate(userId, oppId, date, date);
	    		 // initialize with no hours worked
	    		 Integer[] workHours =  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	    		 // if no works hours for day
	    		 if (oppTimeList.isEmpty()) {
	    			 workDays.add(i,Arrays.toString(workHours));
	    		 } else {
	    			 for (OpportunityTime oppTime : oppTimeList) {
	    					Calendar timeCal = Calendar.getInstance();
	    					timeCal.setTime(oppTime.getStartTime());
	    					int start = timeCal.get(Calendar.HOUR_OF_DAY);
	    					timeCal.setTime(oppTime.getEndTime());
	    					int end = timeCal.get(Calendar.HOUR_OF_DAY);
	    					// if 0 end time is midnight
	    					if (end == 0) {
	    						end = 24;
	    					}
	    					for (int j=start; j < end ; j++) {
	    						// mark hours as worked
	    						workHours[j] = 1;
	    					}
	    			 }
	    			 workDays.add(i,Arrays.toString(workHours));
	    		 }
    	 }
		return workDays;
	}
	
	public List<WorkInterval> getTimeforStorage(String timeData, int year, int month) {
		List<WorkInterval> workIntervals = new ArrayList<WorkInterval>();
		String[] days = timeData.split(",");
		 for (int i=0; i < days.length; i++) {
			 // days with no work hours have single space
			 if (days[i] != " ") {
				 LocalDate localDate = LocalDate.of(year, month+1, i+1);
	    		 Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				 // multiple work hour intervals use | delimiter (escape |)
	    		 String[] times = days[i].split("\\|");
				 for (int j=0; j < times.length; j++) {
					 // start time, end time use - delimiter
					 String[] hours = times[j].split("-");
					 // must have both start and end times
					 if (hours.length == 2) {
	    				 Time startTime = Time.valueOf(hours[0] + ":00");
	    				 Time endTime = Time.valueOf(hours[1] + ":00");
	    				 WorkInterval workInterval = new WorkInterval(date, startTime, endTime);
	    				 workIntervals.add(workInterval);
					 }
				 }
			 }
		 }
		 return workIntervals;
	}
}
