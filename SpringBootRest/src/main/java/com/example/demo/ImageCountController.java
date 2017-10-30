package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImageCountController {

	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	ImageService imageService;
	
	//private ResultStoreMap resultMap;
	
	   private static final String template = "Hello, %s!";
		 @Autowired
	     private TaskExecutor executor;
    public static final Logger logger = LoggerFactory.getLogger(ImageCountController.class);

    // -------------------Create a User-------------------------------------------
    
    @RequestMapping(value = "/imageCount/", method = RequestMethod.POST)
    public ResponseEntity<?> processImageCount(@RequestBody ImageUrlRequest imageUrl, UriComponentsBuilder ucBuilder) {
        logger.info("processImageCount");
 
       logger.info("request"+imageUrl.getUrl());;
 
        HttpHeaders headers = new HttpHeaders();
        long jobId=counter.incrementAndGet();
        String resultLocation="/api/imageCount/"+jobId;
        ResultStoreMap.addInitialEntry(jobId, imageUrl);
        
        
        processImages(jobId);
        headers.setLocation(ucBuilder.path("/api/imageCount/{id}").buildAndExpand(jobId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/imageCount/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getImageCount(@PathVariable("id") long jobId, UriComponentsBuilder ucBuilder) {
        logger.info("getImageCount"+jobId);
 
       
        ImageUrlResponse response=ResultStoreMap.getJobStatus(jobId);
       
        
        if (response == null) {
            logger.error("Job with id {} not found.", jobId);
            return new ResponseEntity(new JobNotFound("job not found::"+jobId), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ImageUrlResponse>(response, HttpStatus.OK);
        
        
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
   public void processImages(long jobId) {
	   
	   ImageUrlResponse response=ResultStoreMap.getJobStatus(jobId);
	   List<ImageCount>  inputImages=response.getImageCount();
	   
	   List<ImageCount> listCount=new ArrayList<ImageCount>();
		System.out.println("size"+listCount.size());
		for(ImageCount image:inputImages) {
			System.out.println("image is"+image);
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
			futureTask.get(1, TimeUnit.SECONDS);
			//executor.wait();
		} 
		catch (InterruptedException e){
		    e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		}
		
		retriveResult(listCount);
	   
   }
   
   private void retriveResult( List<ImageCount> listCount) {
   	
   	
   	for(ImageCount image:listCount) {
   		
   		System.out.println("image url in test:::"+image.getImageUrl());
   		System.out.println("image count in test:::"+image.getCount());
   	}
   	
   	
   }

}