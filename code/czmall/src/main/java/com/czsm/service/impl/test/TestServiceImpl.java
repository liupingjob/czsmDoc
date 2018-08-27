package com.czsm.service.impl.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czsm.dao.test.TestDao;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.test.Test;
import com.czsm.service.test.TestService;
import com.github.pagehelper.PageHelper;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;

	@Override
	public boolean addTest(Test test) {

		return testDao.addTest(test) > 0;
	}

	@Override
	public List<Test> findAllTest() {
		return testDao.findAllTest();
	}

	@Override
	public List<BuyerUserInfo> findUserPager(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		return testDao.findAllInfo().getResult();
	}

}
