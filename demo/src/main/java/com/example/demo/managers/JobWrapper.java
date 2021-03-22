package com.example.demo.managers;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.jobs.JobBase;
import com.example.demo.model.JobStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class JobWrapper implements Runnable{

    private JobsDao jobsDao;

    private JobBase job;

    private boolean runOnce;

    public JobWrapper(JobsDao jobsDao)
    {
        this.jobsDao = jobsDao;
    }

    public <T extends JobBase>void setJob(T job) {
        this.job = job;
    }

    public void run(){
        try {
            job.setStatus(JobStatusEnum.RUNNING);
            jobsDao.setJobState(job.getType(), job.getJobName(), JobStatusEnum.RUNNING);
            job.processJob();
            job.setStatus((runOnce ? JobStatusEnum.FINISHED : JobStatusEnum.IDLE));
            jobsDao.setJobState(job.getType(), job.getJobName(), (runOnce ? JobStatusEnum.FINISHED : JobStatusEnum.IDLE));

        } catch (Exception e) {
            System.out.println("Failed to run job " + job.toString() + e);
            job.setStatus(JobStatusEnum.FAILED);
            jobsDao.setJobState(job.getType(), job.getJobName(), JobStatusEnum.FAILED);

        }
    }

    public boolean isRunOnce() {
        return runOnce;
    }

    public void setRunOnce(boolean runOnce) {
        this.runOnce = runOnce;
    }
}
