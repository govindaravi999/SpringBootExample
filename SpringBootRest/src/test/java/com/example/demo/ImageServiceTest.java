package com.example.demo;

import static org.junit.Assert.*;

import com.example.demo.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
	
	
	public static final Logger logger = LoggerFactory.getLogger(ImageServiceTest.class);
	@Autowired
	ImageService imageService;
	
	 @Autowired
     private TaskExecutor executor;

	@Test
	public void test() throws ExecutionException, TimeoutException {
		
		
		Long jobId=10L;
		ImageUrlRequest request= buildRequest();
		ResultStoreMap.addInitialEntry(new Long(10), request);
		
		
		System.err.println("result is"+ResultStoreMap.getJobStatus(jobId));
		String url="https://www.xoom.com/";
		
		List<ImageCount>  test=buildTestData();
		List<ImageCount> listCount=new ArrayList<ImageCount>();
		
		for(ImageCount image:test) {
		 FutureTask futureTask = new FutureTask<Void>(new Callable<Void>() {
             @Override
             public Void call() {
            	 System.out.println("job id::"+"thhread name"+                             Thread.currentThread().getName());
            	 ImageCount count=imageService.numberOfImages(image, jobId);
             	//	int count1=imageService.numberOfImages("https://www.sakshi.com/", 10);
             		//int count2=imageService.numberOfImages("https://www.greatandhra.com/", 10);
            	 listCount.add(count);
            
                 return null;
             }
         });

		executor.execute(futureTask);
		
		try{
			futureTask.get(100000, TimeUnit.SECONDS);
		//	Thread.sleep(500000);
			//executor.wait();
		} 
		catch (InterruptedException e){
		    e.printStackTrace();
		}
		
		}
		
		retriveResult(listCount);
	//	int count=imageService.numberOfImages("http://www.eenadu.net/", 10);
	//	int count1=imageService.numberOfImages("https://www.sakshi.com/", 10);
		//int count2=imageService.numberOfImages("https://www.greatandhra.com/", 10);
		//System.out.println("count is"+count);
		//System.out.println("count is"+count1);
	//	System.out.println("count is"+count2);
	}
	
	
   
    private List<ImageCount> buildTestData(){
    	
        List<ImageCount> listCount=new ArrayList<ImageCount>();
        
       
        	listCount.add(new ImageCount("https://www.facebook.com","Pending"));
        	listCount.add(new ImageCount("http://www.eenadu.net","Pending"));
        	listCount.add(new ImageCount("https://www.sakshi.com","Pending"));
        	listCount.add(new ImageCount("https://www.xoom.com/","Pending"));
        	return listCount;
       
    	
    }

    private void retriveResult( List<ImageCount> listCount) {
    	
    	
    	for(ImageCount image:listCount) {
    		
    		System.out.println("image url in test:::"+image.getImageUrl());
    		System.out.println("image count in test:::"+image.getCount());
    	}
    	
    	
    }
    private ImageUrlRequest buildRequest() {
    	
    	
    	 List<String> listCount=new ArrayList<String>();
         
         
     	listCount.add("https://www.facebook.com");
     	listCount.add("http://www.eenadu.net");
     	listCount.add("https://www.sakshi.com");
     	listCount.add("https://www.xoom.com/");
    	
    	ImageUrlRequest request=new ImageUrlRequest();
    	request.setUrl(listCount);
    	return request;
    	
    	
    }
}
