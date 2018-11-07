package com.easy.clock.entity;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/11/7 16:08
 */
public class ParamJson {

    private String employeeNumber;

    private String mac;

    private String location = "杭州市文一西路1326号利尔达物联网科技园1号楼";


    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public ParamJson setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public String getMac() {
        return mac;
    }

    public ParamJson setMac(String mac) {
        this.mac = mac;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ParamJson setLocation(String location) {
        this.location = location;
        return this;
    }
}
