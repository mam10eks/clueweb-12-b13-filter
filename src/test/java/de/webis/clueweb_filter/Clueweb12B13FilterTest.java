package de.webis.clueweb_filter;

import org.junit.Assert;
import org.junit.Test;

public class Clueweb12B13FilterTest {
	@Test
	public void checkThatClueweb12B13HasFiftyMillionIds() {
		long expected = 52000000l;
		long actual = Clueweb12B13Filter.IDS.size();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkThatClueweb12B13HashIsCorrect() {
		int expected = "adsasd".hashCode();
		int actual = Clueweb12B13Filter.IDS.hashCode();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkSomeExistingExampleIds() {
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-0000tw-00-00041"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1914wb-28-24133"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1914wb-28-24250"));
	}
	
	@Test
	public void checkSomeNonExistingExampleIds() {
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("cluseweb12-0000tw-00-00041"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-19s14wb-28-24133"));
		Assert.assertTrue(Clueweb12B13Filter.idIsClueweb12B13("clueweb12-1a914wb-28-24250"));
	}
}
