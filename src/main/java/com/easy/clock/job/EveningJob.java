package com.easy.clock.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:下班打卡
 *
 * @author zhengql
 * @date 2018/8/30 20:45
 */
public class EveningJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(EveningJob.class);
    public EveningJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("evening:--------------"+map.getString("param"));
    }
}
