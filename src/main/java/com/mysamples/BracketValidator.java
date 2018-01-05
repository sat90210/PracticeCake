package com.mysamples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BracketValidator {
	
	public static boolean isValid(String code) {
		
		Map<Character, Character> openersToClosers = new HashMap<>();
		
		openersToClosers.put('(',')');
		openersToClosers.put('[',']');
		openersToClosers.put('{','}');
		
		Set<Character> openers = openersToClosers.keySet();
		Set<Character> closers = new HashSet<>(openersToClosers.values());
		
		Stack<Character> openersStack = new Stack<>();
		
		for (char c : code.toCharArray()) {
			
			if (openers.contains(c)) {
				openersStack.push(c);				
			}
			else if (closers.contains(c)) {
				if (openersStack.isEmpty()) {
					return false;
				}
				else {
					char lastUnclosedOpener = openersStack.pop();
					
					if (openersToClosers.get(lastUnclosedOpener) != c) {
						return false;
					}
				}
			}			
		}
		
		return openersStack.empty();
	}
	
	public static void main(String args[]) {
		
		String testInput = "{testing brackets}[()]";
		System.out.println("running with " + testInput + ": " + isValid(testInput));
		
	}

}
