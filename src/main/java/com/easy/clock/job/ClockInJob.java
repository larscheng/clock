package com.easy.clock.job;

import com.easy.clock.service.impl.JobAndTriggerImpl;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/8/28 09:55
 */
public class ClockInJob implements Job {
    @Autowired
    private JobAndTriggerImpl jobAndTrigger;
    public ClockInJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        System.out.println(map.getString("param"));
        System.out.println(jobAndTrigger.sendPost("http://javaoa.lierda.com:19090/Attendance/attendanceMobile/clockIn?token=f3db8aa4-8e54-404c-acf6-668f8fddfb4f003360",
                map.getString("param")));
    }
}
