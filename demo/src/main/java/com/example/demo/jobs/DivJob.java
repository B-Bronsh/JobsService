package com.example.demo.jobs;

import org.springframework.stereotype.Component;

@Component
public class DivJob extends JobBase{

    public int numerator = 3;
    public int denominator = 3;

   public static String TYPE = "COUNTER";

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }


    public DivJob(String jobName, long scheduleDelay) {
        super(jobName, scheduleDelay);
    }

    public DivJob(long scheduleDelay) {
        super(scheduleDelay);
    }

    public DivJob() {
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void processJob() throws Exception {
            System.out.println(Thread.currentThread().getId() + "( " + getJobName() + " ) DivJob: " + (numerator / denominator));
        }


}
