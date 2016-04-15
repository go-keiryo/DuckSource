package edu.stevens.ssw690.DuckSource.utilities.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

public class DuckUtilitiesTest {

	@Test
	public final void testGetBigDecimalFromString() {
		
		BigDecimal bigDecimal;
		bigDecimal = new BigDecimal(1.25);
		
		assertEquals("String must convert to Big Decimal", bigDecimal,  DuckUtilities.getBigDecimalFromString("1.25"));
	}

	@Test
	public final void testGetDateFromString() {
		
		Date date = new GregorianCalendar(2016, 1, 11).getTime();
		
		assertEquals("String must convert to Date", date,  DuckUtilities.getDateFromString("02/11/2016"));
	}
	

	@Test
	public final void testIsStringPopulated() {
		
		assertTrue("String is populated", DuckUtilities.isStringPopulated("test"));
	}

}
