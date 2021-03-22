package com.example.demo.jobs;

import com.example.demo.data.dao.JobsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountJob extends JobBase{

    public int limit = 3;
    public static String TYPE = "COUNTER";

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public CountJob(String jobName, long scheduleDelay) {
        super(jobName, scheduleDelay);
    }

    public CountJob(long scheduleDelay) {
        super(scheduleDelay);
    }

    public CountJob() {
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void processJob() throws Exception {

        for (int index = 0; index < limit; index++) {
            System.out.println(Thread.currentThread().getId() + "( " + getJobName() + " ) CountJob: " + index);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + "( " + getJobName() + " ) And sleep");
        }

    }
}
