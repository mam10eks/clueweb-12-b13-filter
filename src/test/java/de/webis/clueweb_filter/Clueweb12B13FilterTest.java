package de.webis.clueweb_filter;

import org.junit.Assert;
import org.junit.Test;

public class Clueweb12B13FilterTest {
	@Test
	public void checkThatClueweb12B13HasFiftyMillionIds() {
		long expected = 52343021l;
		long actual = Clueweb12B13Filter.readAllIds().size();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkThatHashOfClueweb12B13Ids() {
		long expected = 477663244;
		long actual = Clueweb12B13Filter.readAllIds().hashCode();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkSomeExistingExampleIds() {
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-0000tw-00-00041"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1914wb-28-24133"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1914wb-28-24250"));
	}
	
	@Test
	public void checkTheFirstIdIsExisting() {
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-0000tw-00-00013"));
	}
	
	@Test
	public void checkTheLastIdIsExisting() {
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1914wb-28-24250"));
	}
	
	@Test
	public void checkSomeNonExistingExampleIds() {
		Assert.assertFalse(Clueweb12B13Filter.idIsClueweb12B13("cluseweb12-0000tw-00-00041"));
		Assert.assertFalse(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-19s14wb-28-24133"));
		Assert.assertFalse(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1a914wb-28-24250"));
	}
}
