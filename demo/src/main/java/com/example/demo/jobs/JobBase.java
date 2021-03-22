package com.example.demo.jobs;

import com.example.demo.data.domain.JobData;
import com.example.demo.interfaces.JobInterface;
import com.example.demo.model.JobStatusEnum;

import java.util.UUID;


public abstract class JobBase implements Runnable, JobInterface {

    private String jobName;
    private String jobId;
    private long runEveryHours;
    private JobStatusEnum status;

    public JobBase(long scheduleDelay) {
        this(UUID.randomUUID().toString(), scheduleDelay);
    }

    public JobBase(String jobName, long runEveryHours) {
        this.runEveryHours = runEveryHours;
        this.jobName = jobName;
    }

    public JobBase() {

    }

    @Override
    public void run() {
        try {
            processJob();
        } catch (Exception e) {
            System.out.println("Failed to run job" + toString());
        }

    }

    public abstract String getType();

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public long getRunEveryHours() {
        return runEveryHours;
    }

    public void setRunEveryHours(long runEveryHours) {
        this.runEveryHours = runEveryHours;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public void setStatus(JobStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job [name=" +
                jobName + ", type=" +
                getType() + ", runningInterval=" +
                runEveryHours +", status=" +
                status + "]";
    }
}
