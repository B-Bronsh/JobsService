package com.example.demo.managers;

import com.example.demo.data.dao.Impl.InMemoryDao;
import com.example.demo.jobs.CountJob;
import com.example.demo.jobs.DivJob;
import com.example.demo.jobs.DoNothingJob;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest({JobsManager.class})
@RunWith(MockitoJUnitRunner.class)
public class JobsManagerTest {
    @Spy
    InMemoryDao inMemoryDao;

    @Mock
    TypesManager typesManager;

    @Mock
    JobsRunner jobsRunner;

    @InjectMocks
    JobsManager theTestedManager;

    @Test
    public void addJob_shouldAddJobToJobsStorage() throws Exception {
        DivJob divJob = new DivJob("test1",1);
        theTestedManager.addJob(divJob);
        Assert.assertTrue(theTestedManager.getJobsNumber() == 1);
    }

}
