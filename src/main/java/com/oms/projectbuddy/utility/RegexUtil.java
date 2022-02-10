package com.oms.projectbuddy.utility;

import jakarta.inject.Singleton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class RegexUtil {
	public boolean isInputSatisfyRegex(CharSequence input, String regex) {
		final Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
