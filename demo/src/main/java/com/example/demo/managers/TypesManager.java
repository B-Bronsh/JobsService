package com.example.demo.managers;

import com.example.demo.jobs.JobBase;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * Manager to keep all types of jobs and their implementation class.
 */
@Component
public class TypesManager {

    private HashMap<String, Class<? extends JobBase>> jobTypes = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends JobBase> T getJobOfType(String typeName) throws Exception {
        try {
            Class<?> typeClass = jobTypes.get(typeName);
            return (T) typeClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new Exception(String.format("Could not acquire type {}", typeName));
        }
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ListableBeanFactory beanFactory = event.getApplicationContext();
        Collection<? extends JobBase> implementations = beanFactory.getBeansOfType(JobBase.class).values();
        implementations.forEach(jobType -> jobTypes.put(jobType.getType(), jobType.getClass()));
        jobTypes.forEach((key, value) -> System.out.println(key + " the type is " + value.getName()));
    }
}