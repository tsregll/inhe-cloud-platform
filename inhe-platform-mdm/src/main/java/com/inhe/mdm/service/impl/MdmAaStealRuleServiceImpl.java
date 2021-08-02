package com.inhe.mdm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhe.build.exception.InheExceptionBase;
import com.inhe.build.result.PageBean;
import com.inhe.mdm.dao.MdmAaStealRuleDao;
import com.inhe.mdm.model.MdmAaStealRule;
import com.inhe.mdm.service.IMdmAaStealRuleService;

/**
 * @Description : 窃电规则服务
 * @author : guhf
 * @Date : 2020/12/8
 */
@Service
public class MdmAaStealRuleServiceImpl implements IMdmAaStealRuleService {

	@Autowired
	MdmAaStealRuleDao stealRuleDao;

	private static final Logger logger = LoggerFactory.getLogger(MdmAaStealRuleServiceImpl.class);

	@Override
	public PageBean<MdmAaStealRule> queryList(MdmAaStealRule param, int pageNo, int pageSize) {
		int totalCount = stealRuleDao.selectCount(param);
		List<MdmAaStealRule> rows = stealRuleDao.selectList(param, (pageNo - 1) * pageSize, pageSize);
		logger.info("rows: - {}", rows);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		// 封装分页数据
		PageBean<MdmAaStealRule> pageBean = new PageBean<MdmAaStealRule>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setData(rows);
		return pageBean;
	}

	@Override
	public boolean update(MdmAaStealRule param) {
		Integer rule = stealRuleDao.updateByPrimaryKeySelective(param);
		if (rule == 0) {
			throw new InheExceptionBase(-100053, "更新记录失败");
		}
		return true;
	}
}
