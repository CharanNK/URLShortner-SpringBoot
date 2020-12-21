package com.tinyurl.demo.url;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class URLStoreService {
	private List<URLStore> urls = new ArrayList<>(
			Arrays.asList(
					new URLStore("https://google.com","https://tinyurldemo.com/abc345Q")
					));
	
	public List<URLStore> getAllURLs(){
		return urls;
	}
	
	public URLStore getShortURL(String baseURL) {
		return urls.stream().filter(t -> t.getOriginalUrl().equals(baseURL)).findFirst().get();
	}
	
	public URLStore getBaseURL(String shortURL) {
		return urls.stream().filter(t-> t.getShortUrl().equals(shortURL)).findFirst().get();
	}
	
	public URLStore createShortURL(String baseURL) {

		boolean hasShortURL = urls.stream().anyMatch(url -> url.getOriginalUrl().equals(baseURL));
		
		System.out.println("hasShortURL : "+hasShortURL);
		
		if(hasShortURL)
			return null;
		
		return new URLStore(baseURL,generateShortURL(baseURL));
	}
	
	private static String generateShortURL(String baseURL) {
		// chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder tinyURL = new StringBuilder(7); 
  
        for (int i = 0; i < 7; i++) { 
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() * Math.random()); 
  
            // add Character one by one in end of sb 
            tinyURL.append(AlphaNumericString.charAt(index)); 
        } 
        
        System.out.println("\nBase URL: "+baseURL+"   tinyURL: https://tinyurldemo.com/"+tinyURL.toString());
        
		return "https://tinyurldemo.com/"+tinyURL.toString();
	}
}
