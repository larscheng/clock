package com.easy.clock.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.easy.clock.dao.JobAndTriggerMapper;
import com.easy.clock.dao.QrztUserMapper;
import com.easy.clock.entity.JobAndTrigger;
import com.easy.clock.entity.ParamJson;
import com.easy.clock.entity.QrtzUser;
import com.easy.clock.job.EveningJob;
import com.easy.clock.job.HelloJob;
import com.easy.clock.job.MorningJob;
import com.easy.clock.service.IJobAndTriggerService;
import com.easy.clock.service.IQrtzUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Calendar.*;
import static java.util.Calendar.FRIDAY;


@Service
public class QrtzUserImpl implements IQrtzUserService {

    @Autowired
    private QrztUserMapper qrztUserMapper;
    //加入Qulifier注解，通过名称注入bean
    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;


    @Override
    public PageInfo<QrtzUser> getAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<QrtzUser> list = qrztUserMapper.getAllUser();
        return new PageInfo<>(list);
    }

    @Override
    @Transactional
    public void addUser(String userCode, String userPassword, String userClientId, String userEmail) throws Exception {
        //添加用户信息
        QrtzUser qrtzUser = new QrtzUser(userCode,userPassword,userClientId,userEmail,new Date());
        qrztUserMapper.addUser(qrtzUser);


        ParamJson paramJson = new ParamJson().setEmployeeNumber(qrtzUser.getUserCode()).setMac(qrtzUser.getUserClientId());
//        System.out.println(JSONObject.toJSONString(paramJson));
        //构建job
        JobDetail jobMorning = JobBuilder.newJob(MorningJob.class).withIdentity("morning:"+qrtzUser.getUserCode(), "group:"+qrtzUser.getUserCode())
                .usingJobData("param",JSONObject.toJSONString(paramJson))
                .build();
        //按新的cronExpression表达式构建一个新的trigger
        DailyTimeIntervalTrigger triggerMorning = TriggerBuilder.newTrigger().withIdentity("trigger:"+qrtzUser.getUserCode(), "group:"+qrtzUser.getUserCode())
                .withSchedule(timeTest1()).build();
        //构建job
        JobDetail jobEvening = JobBuilder.newJob(EveningJob.class).withIdentity("evening:"+qrtzUser.getUserCode(), "group:"+qrtzUser.getUserCode())
                .usingJobData("param",JSONObject.toJSONString(paramJson))
                .build();
        //按新的cronExpression表达式构建一个新的trigger
        DailyTimeIntervalTrigger triggerEvening = TriggerBuilder.newTrigger().withIdentity("trigger:"+qrtzUser.getUserCode(), "group:"+qrtzUser.getUserCode())
                .withSchedule(timeTest2()).build();

        try {
            scheduler.scheduleJob(jobMorning, triggerMorning);
            scheduler.scheduleJob(jobEvening, triggerEvening);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }

    }

    public DailyTimeIntervalScheduleBuilder timeTest1(){
        Integer time = new Random().nextInt(170);
        return DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 50)) //第天8：00开始
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 0)) //8：39 结束
                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周一至周五执行
                .withIntervalInSeconds(time) //每间隔随机时间执行一次
                .withRepeatCount(1);
    }
    public DailyTimeIntervalScheduleBuilder timeTest2(){
        Integer time = new Random().nextInt(170);
        return DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 1)) //第天18：00开始
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 10)) //18：30 结束
                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周一至周五执行
                .withIntervalInSeconds(time) //每间隔随机时间执行一次
                .withRepeatCount(1);
    }

    public DailyTimeIntervalScheduleBuilder timeMorning(){
        Integer time = new Random().nextInt(170);
        return DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(8, 0)) //第天8：00开始
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(8, 39)) //8：39 结束
                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周一至周五执行
                .withIntervalInSeconds(time) //每间隔随机时间执行一次
                .withRepeatCount(1);
    }


    public DailyTimeIntervalScheduleBuilder timeEvening(){
        Integer time = new Random().nextInt(170);
        return DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 0)) //第天18：00开始
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 30)) //18：30 结束
                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周一至周五执行
                .withIntervalInSeconds(time) //每间隔随机时间执行一次
                .withRepeatCount(1);
    }
}