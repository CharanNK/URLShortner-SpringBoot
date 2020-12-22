package com.tinyurl.util;

import java.net.URI;
import java.util.*;

public class TinyurlUtil {
	final static Set<String> protocols, protocolsWithHost;

	static {
		protocolsWithHost = new HashSet<String>(Arrays.asList(new String[] { "file", "ftp", "http", "https" }));
		protocols = new HashSet<String>(Arrays.asList(new String[] { "mailto", "news", "urn" }));
		protocols.addAll(protocolsWithHost);
	}

	public static boolean isValidURL(String str) {
		int colon = str.indexOf(':');
		if (colon < 3)
			return false;

		String proto = str.substring(0, colon).toLowerCase();
		if (!protocols.contains(proto))
			return false;

		try {
			URI uri = new URI(str);
			if (protocolsWithHost.contains(proto)) {
				if (uri.getHost() == null)
					return false;

				String path = uri.getPath();
				if (path != null) {
					for (int i = path.length() - 1; i >= 0; i--) {
						if ("?<>:*|\"".indexOf(path.charAt(i)) > -1)
							return false;
					}
				}
			}

			return true;
		} catch (Exception ex) {
		}

		return false;
	}

	public static String generateShortURL(String baseURL) {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder tinyURL = new StringBuilder(7);

		for (int i = 0; i < 7; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			tinyURL.append(AlphaNumericString.charAt(index));
		}

		System.out.println("\nBase URL: " + baseURL + "   tinyURL: https://tinyurldemo.com/" + tinyURL.toString());

		return "https://tinyurldemo.com/" + tinyURL.toString();
	}
}
