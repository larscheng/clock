package com.easy.clock.controller;

import com.easy.clock.entity.JobAndTrigger;
import com.easy.clock.entity.QrtzUser;
import com.easy.clock.service.IQrtzUserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/11/7 15:21
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IQrtzUserService iQrtzUserService;

    @PostMapping(value = "/addUser")
    public void addUser(@RequestParam(value = "userCode") String userCode,
                       @RequestParam(value = "userPassword") String userPassword,
                       @RequestParam(value = "userClientId") String userClientId,
                       @RequestParam(value = "userEmail") String userEmail) throws Exception {
        iQrtzUserService.addUser(userCode, userPassword, userClientId, userEmail);
    }

    @GetMapping(value = "/queryUser")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<QrtzUser> qrtzUserPageInfo = iQrtzUserService.getAllUser(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("QrtzUser", qrtzUserPageInfo);
        map.put("number", qrtzUserPageInfo.getTotal());
        return map;
    }
}
