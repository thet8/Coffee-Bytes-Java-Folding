/*
 * Created on Sep 23, 2004
 */
package com.cb.eclipse.folding.util;

import java.util.regex.Pattern;

/**
 * TODO Javadoc.
 * @author R.J. Lorimer
 */
public class StringUtil {

	public static String innerTrim(String s) {
		return removeDuplicates(s, ' ');
	}
	public static String removeDuplicates(String s, char c) {
		StringBuffer newResult = new StringBuffer();
		
		boolean removing = false;
		for(int i=0; i<s.length(); i++) {
			char aChar = s.charAt(i);
			if(aChar == c) {
				if(!removing) {
					removing = true;
					newResult.append(c);
				}				
			}
			else {
				removing = false;
				newResult.append(c);
			}
		}
		return newResult.toString();
	}
	
	public static boolean isAlphaNumeric(String str) {
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
}
