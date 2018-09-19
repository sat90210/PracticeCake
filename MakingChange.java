package com.mysamples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakingChange {
	
	private static Map<String, Integer> memo = new HashMap<String, Integer>();

	public static void main(String[] args) {
		
		int amount = 4;
		int denominations[] = {1,2,3};
		int waysToCount = 0;
		
		waysToCount = changePossibilitiesTopDown(amount, denominations);
		
		System.out.println("Using TopDown approach, No Of Ways to Count: "+waysToCount);
		
		waysToCount = changePossibilitiesBottomUp(amount, denominations);
		
		System.out.println("Using BottomUp approach, No Of Ways to Count: "+waysToCount);

	}
	
	public static int changePossibilitiesBottomUp(int amount, int[] denominations) {
		int[] waysofDoingNCents = new int[amount + 1]; // array of zeros from 0... amount
		waysofDoingNCents[0] = 1;
		
		for (int coin : denominations) {
			for (int higherAmount = coin; higherAmount <= amount; higherAmount++) {
				int higherAmountReminder = higherAmount - coin;
				waysofDoingNCents[higherAmount] += waysofDoingNCents[higherAmountReminder];				
			}
		}
		
		return waysofDoingNCents[amount];
				
	}

	public static int changePossibilitiesTopDown(int amount, int[] denominations) {
		return changePossibilitiesTopDown(amount, denominations, 0);
	}

	private static int changePossibilitiesTopDown(int amountLeft, int[] denominations, int currentIndex) {
		
		// check our memo and short circuit if we've already solved this one
		String memoKey = amountLeft + "," + currentIndex;
		if (memo.containsKey(memoKey)) {
			System.out.println("grabbing memo [" + memoKey + "]");
			return memo.get(memoKey);
		}

		// base cases:
		// we hit the amount spot on
		if (amountLeft == 0) {
			return 1;
		}

		// we overshot the amount left (used too many coins)
		if (amountLeft < 0) {
			return 0;
		}

		// we are out of denominations
		if (currentIndex == denominations.length) {
			return 0;
		}

		System.out.println(String.format("Checking ways to make %d with %s", amountLeft,
				Arrays.toString(Arrays.copyOfRange(denominations, currentIndex, denominations.length))));
		
		
		//Choose a current coin
		int currentCoin = denominations[currentIndex];
		
		//see how many possibilities we can get
		//for each number of times to use currentCoin
		int numPossibilities = 0;
		while (amountLeft > 0) {
			numPossibilities += changePossibilitiesTopDown(amountLeft, denominations, currentIndex + 1);
			amountLeft -= currentCoin;
		}
		
		//save the answer in our memo so we don't compute it again
		memo.put(memoKey, numPossibilities);
		
		return numPossibilities;		
	}

}
