package com.easy.clock.dao;

import com.easy.clock.entity.JobAndTrigger;
import com.easy.clock.entity.QrtzUser;

import java.util.List;

public interface QrztUserMapper {
	public List<QrtzUser> getAllUser();

	void addUser(QrtzUser qrtzUser);
}
