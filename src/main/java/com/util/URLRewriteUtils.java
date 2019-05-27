package com.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class URLRewriteUtils {
	public static String getUrlStandard(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace(" ", "-").replace(":", "").toLowerCase();
	}
}