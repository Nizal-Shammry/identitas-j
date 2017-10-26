package com.identitas;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.identitas.proquint.Proquint;
import com.identitas.ui.Util;

public class UiTest {
	Util util;
	Proquint proquint;
	
	@Before
	public void makeUi() {
		util = new Util();
		proquint = new Proquint();
	}
	
	@Test
	public void testProint_damm_valid() throws Exception {
		assertEquals(true, util.proint_damm_valid("babab-babab"));
		assertEquals(false, util.proint_damm_valid("babab-bagiv"));
	}
	
	@Test
	public void testInt_to_proint() throws IOException {
		assertEquals(0, util.proint_to_Int("babab-babab"));
		assertEquals(1, util.proint_to_Int("babab-babad"));
		assertEquals(-1, util.proint_to_Int("zuzuz-zuzuz"));
		assertEquals(Integer.MAX_VALUE, util.proint_to_Int("luzuz-zuzuz"));
		assertEquals(Integer.MIN_VALUE, util.proint_to_Int("mabab-babab"));
	}
	
	
	@Test
	public void testShort_to_prshort() throws IOException {
		assertEquals(0, util.proshort_to_short("babab"));
		assertEquals(1, util.proshort_to_short("babad"));
		assertEquals(-1, util.proshort_to_short("zuzuz"));
		assertEquals(Short.MAX_VALUE, util.proshort_to_short("luzuz"));
		assertEquals(Short.MIN_VALUE, util.proshort_to_short("mabab"));
	}
	
	@Test
	public void testLong_to_prolong() throws IOException {
		assertEquals(0, util.prolong_to_long("babab-babab-babab-babab"));
		assertEquals(1, util.prolong_to_long("babab-babab-babab-babad"));
		assertEquals(-1, util.prolong_to_long("zuzuz-zuzuz-zuzuz-zuzuz"));
		assertEquals(Long.MAX_VALUE, util.prolong_to_long("luzuz-zuzuz-zuzuz-zuzuz"));		
		assertEquals(Long.MIN_VALUE, util.prolong_to_long("mabab-babab-babab-babab"));
	}
	
	@Test
	public void testProint_to_int() throws IOException {
		assertEquals("babab-babab", util.int_to_proint(0));
		assertEquals("babab-babad", util.int_to_proint(1));
		assertEquals("zuzuz-zuzuz", util.int_to_proint(-1));
		assertEquals("luzuz-zuzuz", util.int_to_proint(Integer.MAX_VALUE));
		assertEquals("mabab-babab", util.int_to_proint(Integer.MIN_VALUE));
	}
	
	@Test
	public void testProshort_to_short() throws IOException {
		assertEquals("babab", util.short_to_proshort((short)0));
		assertEquals("babad", util.short_to_proshort((short)1));
		assertEquals("zuzuz", util.short_to_proshort((short)-1));
		assertEquals("luzuz", util.short_to_proshort(Short.MAX_VALUE));
		assertEquals("mabab", util.short_to_proshort(Short.MIN_VALUE));
	}
	
	@Test
	public void testProlong_to_long() throws IOException {
		assertEquals("babab-babab-babab-babab", util.long_to_prolong(0));
		assertEquals("babab-babab-babab-babad", util.long_to_prolong(1));
		assertEquals("zuzuz-zuzuz-zuzuz-zuzuz", util.long_to_prolong(-1));
		assertEquals("luzuz-zuzuz-zuzuz-zuzuz", util.long_to_prolong(Long.MAX_VALUE));
		assertEquals("mabab-babab-babab-babab", util.long_to_prolong(Long.MIN_VALUE));
	}
	
}
