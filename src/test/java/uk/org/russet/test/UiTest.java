package uk.org.russet.test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import uk.org.russet.identitas.Util;

public class UiTest {

	
	@Test
	public void testProint_damm_valid() throws Exception {
		assertEquals(true, Util.prointDammValid("babab-babab"));
		assertEquals(false, Util.prointDammValid("babab-bagiv"));
	}
	
	@Test
	public void testInt_to_proint() throws IOException {
		assertEquals(0, Util.prointToInt("babab-babab"));
		assertEquals(1, Util.prointToInt("babab-babad"));
		assertEquals(-1, Util.prointToInt("zuzuz-zuzuz"));
		assertEquals(Integer.MAX_VALUE, Util.prointToInt("luzuz-zuzuz"));
		assertEquals(Integer.MIN_VALUE, Util.prointToInt("mabab-babab"));
	}
	
	
	@Test
	public void testShort_to_prshort() throws IOException {
		assertEquals(0, Util.proshortToShort("babab"));
		assertEquals(1, Util.proshortToShort("babad"));
		assertEquals(-1, Util.proshortToShort("zuzuz"));
		assertEquals(Short.MAX_VALUE, Util.proshortToShort("luzuz"));
		assertEquals(Short.MIN_VALUE, Util.proshortToShort("mabab"));
	}
	
	@Test
	public void testLong_to_prolong() throws IOException {
		assertEquals(0, Util.prolongToLong("babab-babab-babab-babab"));
		assertEquals(1, Util.prolongToLong("babab-babab-babab-babad"));
		assertEquals(-1, Util.prolongToLong("zuzuz-zuzuz-zuzuz-zuzuz"));
		assertEquals(Long.MAX_VALUE, Util.prolongToLong("luzuz-zuzuz-zuzuz-zuzuz"));		
		assertEquals(Long.MIN_VALUE, Util.prolongToLong("mabab-babab-babab-babab"));
	}
	
	@Test
	public void testProint_to_int() throws IOException {
		assertEquals("babab-babab", Util.intToProint(0));
		assertEquals("babab-babad", Util.intToProint(1));
		assertEquals("zuzuz-zuzuz", Util.intToProint(-1));
		assertEquals("luzuz-zuzuz", Util.intToProint(Integer.MAX_VALUE));
		assertEquals("mabab-babab", Util.intToProint(Integer.MIN_VALUE));
	}
	
	@Test
	public void testProshort_to_short() throws IOException {
		assertEquals("babab", Util.shortToProshort((short)0));
		assertEquals("babad", Util.shortToProshort((short)1));
		assertEquals("zuzuz", Util.shortToProshort((short)-1));
		assertEquals("luzuz", Util.shortToProshort(Short.MAX_VALUE));
		assertEquals("mabab", Util.shortToProshort(Short.MIN_VALUE));
	}
	
	@Test
	public void testProlong_to_long() throws IOException {
		assertEquals("babab-babab-babab-babab", Util.longToProlong(0));
		assertEquals("babab-babab-babab-babad", Util.longToProlong(1));
		assertEquals("zuzuz-zuzuz-zuzuz-zuzuz", Util.longToProlong(-1));
		assertEquals("luzuz-zuzuz-zuzuz-zuzuz", Util.longToProlong(Long.MAX_VALUE));
		assertEquals("mabab-babab-babab-babab", Util.longToProlong(Long.MIN_VALUE));
	}
	
}
