package com.tinyurl.util;

import java.net.URI;
import java.util.*;

public class TinyurlUtil {
	final static Set<String> protocols, protocolsWithHost;

	static {
	  protocolsWithHost = new HashSet<String>( 
	      Arrays.asList( new String[]{ "file", "ftp", "http", "https" } ) 
	  );
	  protocols = new HashSet<String>( 
	      Arrays.asList( new String[]{ "mailto", "news", "urn" } ) 
	  );
	  protocols.addAll(protocolsWithHost);
	}
	
	public static boolean isValidURL(String str) {
		  int colon = str.indexOf(':');
		  if (colon < 3)                      return false;

		  String proto = str.substring(0, colon).toLowerCase();
		  if (!protocols.contains(proto))     return false;

		  try {
		    URI uri = new URI(str);
		    if (protocolsWithHost.contains(proto)) {
		      if (uri.getHost() == null)      return false;

		      String path = uri.getPath();
		      if (path != null) {
		        for (int i=path.length()-1; i >= 0; i--) {
		          if ("?<>:*|\"".indexOf( path.charAt(i) ) > -1)
		            return false;
		        }
		      }
		    }

		    return true;
		  } catch ( Exception ex ) {}

		  return false;
		}
}
