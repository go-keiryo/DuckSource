package edu.stevens.ssw690.DuckSource.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailValidatorTest {

	EmailValidator emailValidator = new EmailValidator();
	
	@Test
	public final void testValidateEmail() {
		
		assertTrue("Email Valid", emailValidator.validateEmail("shague@stevens.edu"));
		assertFalse("Email Invalid", emailValidator.validateEmail("shague"));
	}

}
