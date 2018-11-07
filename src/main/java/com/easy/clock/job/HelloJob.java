package com.easy.clock.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author zhengql
 */
public class HelloJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);  
     
    public HelloJob() {  
          
    }  
     
    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        System.out.println(map.getString("param"));
        _log.error("Hello Job :  " +map.getString("param"));
          
    }
}  
