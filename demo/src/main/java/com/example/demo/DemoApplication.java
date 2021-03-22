package com.example.demo;

import com.example.demo.jobs.CountJob;
import com.example.demo.jobs.DoNothingJob;
import com.example.demo.managers.JobsManager;
import com.example.demo.managers.JobsRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	JobsManager jobsManager;

	@Autowired
	JobsRunner jobsRunner;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Going to add jobs ");

			jobsManager.addJob(new CountJob("Sason", 2));
			jobsManager.addJob(new DoNothingJob(0));
			jobsManager.addJob(new DoNothingJob(5));
			jobsManager.addJob(new CountJob("Moshe", 15));

			Thread.sleep(500);
			jobsRunner.reportAllJobsStatus();
			jobsRunner.getJobStatus("Sason");

		};
	}

}
