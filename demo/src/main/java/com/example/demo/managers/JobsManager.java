package com.example.demo.managers;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.data.domain.JobData;
import com.example.demo.jobs.JobBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JobsManager {

    @Autowired
    JobsDao jobsDao;

    @Autowired
    TypesManager typesManager;

    @Autowired
    JobsRunner jobsRunner;

    public void addJob(JobBase job) throws Exception {
        JobData jobData = new JobData();
        jobData.setId(job.getJobId());
        jobData.setJobType(job.getType());
        jobData.setName(job.getJobName());
        jobData.setRunningInterval(job.getRunEveryHours());

        jobsDao.addJob(jobData);

        if(job.getRunEveryHours() == 0) {
            jobsRunner.runImmediateJob(job);
            return;
        }
        jobsRunner.runRecurrentJob(job);

    }

    @PostConstruct
    public void init(){
        //Recovery after restart
        jobsDao.getAllRecurrentJobs().forEach(x -> runJob(x));
    }

    public void runJob(JobData jobData) {
        try {
            JobBase job = typesManager.getJobOfType(jobData.getJobType());
            jobsRunner.runRecurrentJob(job);
        } catch (Exception e) {
            System.out.println("Failed to run job" + jobData.toString());
            e.printStackTrace();
        }
    }

    public int getJobsNumber() {
        return jobsDao.getNumberOfJobs();
    }

    public int getRunningJobs() {
        return jobsDao.getNumberOfRunningJobs();
    }
}
