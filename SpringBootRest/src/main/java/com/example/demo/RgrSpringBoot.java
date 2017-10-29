package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class RgrSpringBoot {
	
	


	public static void main(String[] args) {
		
		System.out.println("start boot");
		
		SpringApplication.run(RgrSpringBoot.class, args);
	// ApplicationContext context =
          //      new AnnotationConfigApplicationContext(MyConfig.class);
		
		// MyBean bean = context.getBean(MyBean.class);
	       // bean.runTasks();
	//        ThreadPoolTaskExecutor t = context.getBean(ThreadPoolTaskExecutor.class);
	 //       t.shutdown();
	}
	
	
	/* @Configuration
	    public static class MyConfig {

	        @Bean
	        MyBean myBean () {
	            return new MyBean();
	        }

	        @Bean
	        TaskExecutor taskExecutor () {
	            ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
	            t.setCorePoolSize(10);
	            t.setMaxPoolSize(100);
	            t.setQueueCapacity(50);
	            t.setAllowCoreThreadTimeOut(true);
	            t.setKeepAliveSeconds(120);
	            return t;
	        }
	    }
	 
	 private static class MyBean {
	        @Autowired
	        private TaskExecutor executor;

	        public void runTasks () {
	            for (int i = 0; i < 10; i++) {
	                executor.execute(getTask(i));
	            }
	        }

	        private Runnable getTask (int i) {
	             return () -> {
	                System.out.printf("running task %d. Thread: %s%n",
	                                  i,
	                                  Thread.currentThread().getName());
	            };
	        }
	    }*/
}
