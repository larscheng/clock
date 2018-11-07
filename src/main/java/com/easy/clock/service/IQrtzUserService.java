package com.easy.clock.service;


import com.easy.clock.entity.QrtzUser;
import com.github.pagehelper.PageInfo;

public interface IQrtzUserService {
	public PageInfo<QrtzUser> getAllUser(int pageNum, int pageSize);

	void addUser(String userCode, String userPassword, String userClientId, String userEmail) throws Exception;
}
