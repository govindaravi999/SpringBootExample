package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



/**
 * @author Rushit
 *
 */
@Service

public class ImageService {

	private int imagecount;

	public int numberOfImages(List<String> ulist, int id){

		URL url;

		try {
			// get URL content

			for(String urllist : ulist){

				String a=urllist;
				url = new URL(a);
				URLConnection conn = url.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

				String inputLine;
				StringBuffer sb = new StringBuffer(); 

				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
					System.out.println(inputLine);
				}
				br.close();

				// the pattern we want to search for
				Pattern p = Pattern.compile("(?m)(?s)<img\\s+(.*)src\\s*=\\s*\"([^\"]+)\"(.*)");
				Matcher m = p.matcher(sb.toString());

				while (m.find())
				{
					imagecount++;
				}		

			} 
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imagecount;

	}
	
	public int numberOfImages(String imageUrl, int id){

		URL url;
		 int imagecount1=0;
		try {
			// get URL content

				url = new URL(imageUrl);
				URLConnection conn = url.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

				String inputLine;
				StringBuffer sb = new StringBuffer(); 

				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
					System.out.println(inputLine);
				}
				br.close();

				// the pattern we want to search for
				Pattern p = Pattern.compile("(?m)(?s)<img\\s+(.*)src\\s*=\\s*\"([^\"]+)\"(.*)");
				Matcher m = p.matcher(sb.toString());

				while (m.find())
				{
					imagecount1++;
				}		

			
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imagecount1;

	}
}
