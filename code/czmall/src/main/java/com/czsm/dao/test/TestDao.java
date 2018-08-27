package com.czsm.dao.test;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.test.Test;
import com.github.pagehelper.Page;

public interface TestDao {
	//    @Insert({ "insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})" })
	public int addTest(Test test);
	@Select("select * from test ")
	public List<Test> findAllTest();
	public Page<BuyerUserInfo> findAllInfo();
}
