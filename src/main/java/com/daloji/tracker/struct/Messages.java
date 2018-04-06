package com.daloji.tracker.struct;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {


	private static final String BUNDLE ="com.daloji.tracker.struct.messages";

	private static final ResourceBundle RESOURCES_BUNDLE = ResourceBundle.getBundle(Messages.BUNDLE);

	public static String getString(String key) {
		try  {
			return Messages.RESOURCES_BUNDLE.getString(key);
		}catch(MissingResourceException e) {
			return '!' + key +'!';
		}
	}
	
	public static String getString(String pattern,Object ...args) {
		return MessageFormat.format(pattern, args);
	
	}
	
	private Messages() {
		
	}
}
