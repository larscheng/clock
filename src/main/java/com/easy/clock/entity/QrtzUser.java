package com.easy.clock.entity;

import java.util.Date;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/11/7 15:16
 */
public class QrtzUser {

    private Integer id;
    private String userCode;
    private String userPassword;
    private String userClientId;
    private String userEmail;
    private Date gmtCreate;


    public QrtzUser() {
    }

    public QrtzUser(String userCode, String userPassword, String userClientId, String userEmail, Date gmtCreate) {
        this.userCode = userCode;
        this.userPassword = userPassword;
        this.userClientId = userClientId;
        this.userEmail = userEmail;
        this.gmtCreate = gmtCreate;
    }




    public Integer getId() {
        return id;
    }

    public QrtzUser setId(Integer id) {
        this.id = id;
        return this;
    }



    public String getUserCode() {
        return userCode;
    }

    public QrtzUser setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public QrtzUser setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserClientId() {
        return userClientId;
    }

    public QrtzUser setUserClientId(String userClientId) {
        this.userClientId = userClientId;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public QrtzUser setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public QrtzUser setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }


    @Override
    public String toString() {
        return "QrtzUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userClientId='" + userClientId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}
