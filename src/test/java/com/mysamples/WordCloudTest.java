package com.mysamples;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WordCloudTest {
	
	private WordCloud wordCloud = null;
	
	@Before
	public void setup() {
		wordCloud = new WordCloud();
	}
	
	@Test
	public void testWithOneWord() {
		Map<String, Integer> expectedMap = new HashMap<>();
		expectedMap.put("After", 1);
		String inputString = "After"; 
		wordCloud.passInputToWordCloud(inputString);		
		assertEquals(true, expectedMap.equals(wordCloud.getWordsToCounts()));	
	}
	
	@Test
	public void testWithEmptyInputString() {
		Map<String, Integer> expectedMap = new HashMap<>();
		wordCloud.passInputToWordCloud("");		
		assertEquals(true, expectedMap.equals(wordCloud.getWordsToCounts()));
	}
	
	@Test
	public void testWithSameWordDifferentCapitalization() {
		Map<String, Integer> expectedMap = new HashMap<>();
		String inputString = "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake."+
				" The bill came to five dollars.";
		expectedMap.put("saw", 1);
		expectedMap.put("conquered", 1);
		expectedMap.put("bill", 1);
		expectedMap.put("Bill\'s", 1);
		expectedMap.put("dollars", 1);
		expectedMap.put("then", 1);
		expectedMap.put("we", 4);		
		expectedMap.put("Mille-Feuille", 1);
		expectedMap.put("The", 1);
		expectedMap.put("ate", 1);
		expectedMap.put("cake", 1);
		expectedMap.put("came", 2);
		expectedMap.put("to", 1);
		expectedMap.put("five", 1);			
		
		wordCloud.passInputToWordCloud(inputString);		
		assertEquals(true, expectedMap.equals(wordCloud.getWordsToCounts()));
	}
	
	@Test
	public void testWithCapitalization() {
		Map<String, Integer> expectedMap = new HashMap<>();
		String inputString = "After beating the eggs, Dana read the next step:\n" + 
				"Add milk and eggs, then add flour and sugar.";
		expectedMap.put("next", 1);
		expectedMap.put("add", 2);
		expectedMap.put("eggs", 2);
		expectedMap.put("read", 1);
		expectedMap.put("beating", 1);
		expectedMap.put("milk", 1);
		expectedMap.put("then", 1);		
		expectedMap.put("the", 2);
		expectedMap.put("Dana", 1);
		expectedMap.put("and", 2);
		expectedMap.put("flour", 1);
		expectedMap.put("After", 1);
		expectedMap.put("step", 1);
		expectedMap.put("sugar", 1);	
		
		wordCloud.passInputToWordCloud(inputString);		
		assertEquals(true, expectedMap.equals(wordCloud.getWordsToCounts()));
		
	}

}
