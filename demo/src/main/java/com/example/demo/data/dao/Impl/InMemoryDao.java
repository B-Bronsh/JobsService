package com.example.demo.data.dao.Impl;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.data.domain.JobData;
import com.example.demo.model.JobStatusEnum;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryDao implements JobsDao {

    List<JobData> jobs = new ArrayList<>();

    @Override
    public void addJob(JobData jobData) throws Exception {
        jobs.add(jobData);
    }

    @Override
    public JobData find(String jobType, String jobName) {
        return jobs.stream().filter(x -> x.getJobType().equals(jobType) && x.getName().equals(jobName))
                .findAny().orElse(null);

    }

    @Override
    public JobStatusEnum getState(String jobType, String jobName) {
        JobData jobData = find(jobType, jobName);
        if(jobData != null) {
            return jobData.getStatus();
        };
        return null;
    }

    @Override
    public List<JobData> getAllRecurrentJobs() {
        return jobs.stream().filter(x -> !(x.getStatus().equals(JobStatusEnum.FINISHED)))
                .collect(Collectors.toList());
    }

    @Override
    public void setJobState(String jobType, String jobName, JobStatusEnum jobStatus) {
        JobData jobData = find(jobType, jobName);
        if(jobData != null) {
            jobData.setStatus(jobStatus);
        }
    }

    @Override
    public int getNumberOfJobs() {
        return jobs.size();
    }

    @Override
    public int getNumberOfRunningJobs() {
        return jobs.stream().filter(x -> (x.getStatus().equals(JobStatusEnum.RUNNING)))
                .collect(Collectors.toList()).size();
    }
}
