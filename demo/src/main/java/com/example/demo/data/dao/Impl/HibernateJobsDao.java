package com.example.demo.data.dao.Impl;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.data.domain.JobData;
import com.example.demo.model.JobStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class HibernateJobsDao implements JobsDao {
    //TODO: implement with real storage

    @Override
    public void addJob(JobData jobData) throws Exception {

    }

    @Override
    public JobData find(String jobType, String jobName) {
        return null;
    }

    @Override
    public JobStatusEnum getState(String jobType, String jobName) {
        return null;
    }

    @Override
    public List<JobData> getAllRecurrentJobs() {
        return null;
    }

    @Override
    public void setJobState(String jobType, String jobName, JobStatusEnum jobStatus) {

    }

    @Override
    public int getNumberOfJobs() {
        return 0;
    }

    @Override
    public int getNumberOfRunningJobs() {
        return 0;
    }
}
