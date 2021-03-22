package com.example.demo.interfaces;

/***
 * Interface for all types of job.
 * Each job must implement the processJob function which is the actually work done by job.
 */
public interface JobInterface {

    public String getType();

    public void processJob() throws Exception;

}
