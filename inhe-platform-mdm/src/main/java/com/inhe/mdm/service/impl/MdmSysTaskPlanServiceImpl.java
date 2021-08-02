package com.inhe.mdm.service.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhe.mdm.dao.MdmSysTaskPlanDao;
import com.inhe.mdm.service.IMdmSysTaskPlanService;
import com.inhe.utils.DateUtil;


@Service
public class MdmSysTaskPlanServiceImpl implements IMdmSysTaskPlanService {
    
    @Autowired
    MdmSysTaskPlanDao sysTaskPlanDao;

    @Override
    public String selectPlanCodeByTaskCode(String taskCode, String dataTime) throws Exception {
        String[] dayTaskCode = {"004","006","008"};
        String[] monthTaskCode = {"005","007","009","065"};
        String planCode = null;
        if(Arrays.asList(dayTaskCode).contains(taskCode)) {
            Date date = DateUtil.stringToDate(dataTime, DateUtil.DATE_WITH_LINE);
            Date startDate = DateUtil.rollByDay(date, 1);
            String newDateTime = DateUtil.dateToString(startDate, DateUtil.DATE_WITH_LINE);
            planCode = sysTaskPlanDao.selectPlanCodeByTaskCode(taskCode, newDateTime);
        }else if(Arrays.asList(monthTaskCode).contains(taskCode)) {
            Date date = DateUtil.stringToDate(dataTime, DateUtil.DATE_MONTH_LINE);
            Date startDate = DateUtil.rollByMonth(date, 1);
            String newDateTime = DateUtil.dateToString(startDate, DateUtil.DATE_MONTH_LINE);
            planCode = sysTaskPlanDao.selectPlanCodeByTaskCode(taskCode, newDateTime);
        }
        return planCode;
    }
    
}
