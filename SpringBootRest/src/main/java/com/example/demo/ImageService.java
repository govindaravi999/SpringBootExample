package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

	public List<ImageCount> numberOfImages(List<ImageCount> ulist, Long id){

		URL url;

		try {
			// get URL content

			for(ImageCount urllist : ulist){
				imagecount=0;
				String a=urllist.getImageUrl();
				url = new URL(a);
				URLConnection conn = url.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

				String inputLine;
				StringBuffer sb = new StringBuffer(); 

				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
					//System.out.println(inputLine);
				}
				br.close();

				// the pattern we want to search for
			//	Pattern p = Pattern.compile("(?m)(?s)<img\\s+(.*)src\\s*=\\s*\"([^\"]+)\"(.*)");
				Pattern p = Pattern.compile("<img[^>]*src=[\\\\\\\"']([^\\\\\\\"^']*)");
				Matcher m = p.matcher(sb.toString());

				while (m.find())
				{
					imagecount++;
				}	
				urllist.setCount(String.valueOf(imagecount));
				System.out.println("image conunt"+imagecount);
				ResultStoreMap.updateEntry(id, urllist);
				System.err.println("result is"+ResultStoreMap.getJobStatus(id));

			} 
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		retriveResult(ulist);
		
		

		return ulist;

	}
	
	public ImageCount numberOfImages(ImageCount image, Long id){

		
		System.out.println("numbe of images for job id "+id);
		URL url;
		 int imagecount1=0;
		try {
			// get URL content

				url = new URL(image.getImageUrl());
				URLConnection conn = url.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

				String inputLine;
				StringBuffer sb = new StringBuffer(); 
				PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
					//writer.println(inputLine);
					//System.out.println(inputLine);
				}
				br.close();
				writer.close();
				// the pattern we want to search for
				
			//	<img src="http://b.scorecardresearch.com/p?c1=2&c2=20416623&cv=2.0&cj=1" />
				Pattern p = Pattern.compile("<img[^>]*src=[\\\\\\\"']([^\\\\\\\"^']*)");
				
				// Pattern p =				        //   Pattern.compile( "'<img+src=\"([\\w/\\._\\-]+)\"/\n'" );
				Matcher m = p.matcher(sb.toString());

				while (m.find())
				{
					//System.out.println(m);
					imagecount1++;
				}	
				image.setCount(String.valueOf(imagecount1));
				
				ResultStoreMap.updateEntry(id, image);
				

			
		}
		catch (MalformedURLException e) {
			System.out.println("exceptin 1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("exceptin 2");
			e.printStackTrace();
		}
		System.out.println("count:"+imagecount1);
		return image;

	}
	
    private void retriveResult( List<ImageCount> listCount) {
    	
    	
    	for(ImageCount image:listCount) {
    		
    		System.out.println("image url"+image.getImageUrl());
    		System.out.println("image count"+image.getCount());
    	}
    	
    	
    }
}
