package com.example.demo.managers;

import com.example.demo.data.dao.JobsDao;
import com.example.demo.jobs.CountJob;
import com.example.demo.jobs.DivJob;
import com.example.demo.jobs.DoNothingJob;
import com.example.demo.jobs.JobBase;
import com.example.demo.model.JobStatusEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({JobsRunner.class})
public class JobsRunnerTest {

    @Mock
    JobsDao jobsDao;

    @InjectMocks
    JobsRunner theTestedRunner;

    @Test
    public void getJobStatus_shouldReturnRunningForLongRunJob() throws Exception {
        String jobName = "test1";
        CountJob job = new CountJob(jobName,20);
        job.setLimit(20000);
        theTestedRunner.runImmediateJob(job);
        Thread.sleep(200); //wait for the job to start
        Assert.assertTrue(theTestedRunner.getJobStatus(jobName) == JobStatusEnum.RUNNING);
    }

    @Test
    public void getJobStatus_shouldReturnFailedForFailedJob() throws Exception {
        String jobName = "test2";
        DivJob job = new DivJob(jobName,20);
        job.setDenominator(0);
        theTestedRunner.runImmediateJob(job);
        Thread.sleep(200); //wait for the job to start
        Assert.assertTrue(theTestedRunner.getJobStatus(jobName) == JobStatusEnum.FAILED);
    }

    @Test
    public void getJobStatus_shouldReturnIdleForWaitingJob() throws Exception {
        String jobName = "test3";
        DoNothingJob job = new DoNothingJob(jobName,20);
        theTestedRunner.runRecurrentJob(job);
        Thread.sleep(200); //wait for the job to start
        Assert.assertTrue(theTestedRunner.getJobStatus(jobName) == JobStatusEnum.IDLE);
    }

    @Test
    public void getJobStatus_shouldReturnFinishedForFinishedJob() throws Exception {
        String jobName = "test4";
        DoNothingJob job = new DoNothingJob(jobName,20);
        theTestedRunner.runImmediateJob(job);
        Thread.sleep(200); //wait for the job to start
        Assert.assertTrue(theTestedRunner.getJobStatus(jobName) == JobStatusEnum.FINISHED);
    }
}
