package com.diansetiyadi.latihanspringboot.util;

public class PaddingString {

	public static String PAD_LEFT_ZEROS(String str, int n) {
		return String.format("%1$" + n + "s", str).replace(' ', '0');
	}
	
}
