package scratch;

import junit.framework.TestCase;

public class SctatchTest extends TestCase{
	public void testScratch() {
		char capitalA = 'A';
		assertEquals(65, capitalA);
		assertEquals('\u0041', capitalA);
		assertEquals('\101', capitalA);
		
		assertEquals("abcd", "ab".concat("cd"));
		
	}

}
