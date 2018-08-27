package com.czsm.service.test;

import java.util.List;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.test.Test;

public interface TestService {
	public boolean addTest(Test test);
	public List<Test> findAllTest();
	public List<BuyerUserInfo> findUserPager(int pageNo,int pageSize);
}
