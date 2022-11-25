package ua.foxminded.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DivisionTest {

	Division testObject = new Division();

	@Test
	void shouldMakeDivisionTest() {

		String expected = "_14789|20\n" + " 140  |---\n" + " ---  |739\n" + "  _78\n" + "   60\n" + "   --\n"
				+ "  _189\n" + "   180\n" + "   ---\n" + "     9\n";
		assertEquals(expected, testObject.division(14789, 20));
	}

	@Test
	void checkNullDivisorTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testObject.division(14789, 0);
		});
	}

	@Test
	void checkDividendLessDivisorTest() {
		assertEquals("0/5=0", testObject.division(0, 5));
	}
}
