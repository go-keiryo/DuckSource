package edu.stevens.ssw690.DuckSource.utilities;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import edu.stevens.ssw690.DuckSource.model.OpportunityTime;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManager;

public class DuckUtilities {
	
	public static BigDecimal getBigDecimalFromString(String string) {
		BigDecimal bd = null;
		
		if (string == null || string.isEmpty()) {
			return bd;
		}
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		try {
			bd = (BigDecimal) decimalFormat.parse(string);
		} catch (ParseException e) {
			bd = null;
		}
		return bd;
	}
	
	public static Date getDateFromString(String string) {
		Date dt = null;
		
		if (string == null || string.isEmpty()) {
			return dt;
		}
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        
        try {
        	dt = format.parse(string);
        } catch (ParseException e) {
        	dt = null;
        }
        	return dt;
	}
	
	public static boolean isStringPopulated(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	 
}
