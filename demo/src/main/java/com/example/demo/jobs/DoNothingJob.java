package com.example.demo.jobs;

import com.example.demo.data.dao.JobsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoNothingJob extends JobBase {

    public static String TYPE = "DO_NOTHING";

    public DoNothingJob(){
        super();
    };

    public DoNothingJob(String jobName, long scheduleDelay) {
        super(jobName, scheduleDelay);
    }

    public DoNothingJob( long scheduleDelay) {
        super(scheduleDelay);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void processJob() throws Exception {
        System.out.println(Thread.currentThread().getId() + " Job that do nothing " + getJobName());
   //     throw new Exception();
    }




}
