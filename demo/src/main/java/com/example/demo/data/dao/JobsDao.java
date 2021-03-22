package com.example.demo.data.dao;

import com.example.demo.data.domain.JobData;
import com.example.demo.model.JobStatusEnum;

import java.util.List;

public interface JobsDao {

    void addJob(JobData jobData) throws Exception;

    JobData find(String jobType, String jobName);

    JobStatusEnum getState(String jobType, String jobName);

    List<JobData> getAllRecurrentJobs();

    void setJobState(String jobType, String jobName, JobStatusEnum jobStatus);

    int getNumberOfJobs();

    int getNumberOfRunningJobs();
}
