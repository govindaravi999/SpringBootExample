package com.example.demo;

import static org.junit.Assert.*;
import com.example.demo.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
	
	@Autowired
	ImageService imageService;

	@Test
	public void test() {
		
		
		int count=imageService.numberOfImages("http://google.com", 10);
		System.out.println("count is"+count);
	}

}
