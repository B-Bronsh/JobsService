package com.example.demo.data.domain;

import com.example.demo.model.JobStatusEnum;

import java.util.Date;

//uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "jobType" }, name = "job_type_name_UNIQUE") })
public class JobData {

    private String id;
    private String name;
    private String jobType;
    private long runningInterval;
    private JobStatusEnum status;
    private Date lastRun;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRunningInterval() {
        return runningInterval;
    }

    public void setRunningInterval(long runningInterval) {
        this.runningInterval = runningInterval;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public void setStatus(JobStatusEnum status) {
        this.status = status;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return "JobData [id=" + id + ", name=" +
                name + ", type=" +
                jobType + ", runningInterval=" +
                runningInterval +", status=" +
                status + ", lastRun= " +
                lastRun + "]";
    }
}
