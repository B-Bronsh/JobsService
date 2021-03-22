package com.example.demo.managers;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.jobs.JobBase;
import com.example.demo.model.JobStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

@Component
public class JobsRunner {

    @Autowired
    JobsDao jobsDao;

    int MAX_NUMBER_OF_JOBS = 3;
    ScheduledExecutorService jobs = new ScheduledThreadPoolExecutor(MAX_NUMBER_OF_JOBS);

    HashMap<String, JobBase> allTasks = new HashMap<>();

    public<T extends JobBase> void runImmediateJob(T job) throws ExecutionException, InterruptedException {
        JobWrapper jobWrapper = new JobWrapper(jobsDao);
        jobWrapper.setJob(job);
        jobWrapper.setRunOnce(true);
        jobs.schedule(jobWrapper,0, TimeUnit.HOURS);
        allTasks.put(job.getJobName(), job);
    }

    public<T extends JobBase> void runRecurrentJob(T job) {
        JobWrapper jobWrapper = new JobWrapper(jobsDao);
        jobWrapper.setJob(job);
        jobs.scheduleWithFixedDelay(jobWrapper, 0, job.getRunEveryHours(), TimeUnit.HOURS);
        allTasks.put(job.getJobName(), job);
    }

    public void reportAllJobsStatus(){
        allTasks.forEach((x,v) -> System.out.println("Printing status of " + v.toString()));
    }

    public JobStatusEnum getJobStatus(String jobName){

        return allTasks.get(jobName).getStatus();
    }
}
