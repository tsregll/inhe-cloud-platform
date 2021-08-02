package com.inhe.mdm.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendCmdProxy {
    
    @Autowired
    SendCmd0901 sendCmd0901;
    
    @Autowired
    SendCmd0112 sendCmd0112;
    
    
    /**
     * 发送短信、邮件
     * @param orgId      所属注册用户编号
     * @param ref        外部关联编号
     * @param operator   操作员
     * @param type       事件类型
     * @param params     参数,json类型
     * @param refCode    关联编号,如客户编号
     * @param mails      邮箱列表，逗号隔开
     * @param phones     手机号列表，逗号隔开
     * @param users      用户编号列表，逗号隔开
     */
    public void send0901(String orgId, String ref, String operator, String type, String params, String refCode, 
            String mails, String phones, String users) {
        sendCmd0901.send(orgId, ref, operator, type, params, refCode, mails, phones, users);
    }
    
    public void send0901Simple(String orgId, String type, String params, String refCode) {
        String operator = "mdm";
        sendCmd0901.send(orgId, null, operator, type, params, refCode, null, null, null);
    }
    
    /**
     * VEE 账单数据、瞬时数据、曲线数据
     * @param function    功能 0:VEE 1:VEE结果处理-freezing 2:VEE结果处理-kwhDetail
     * @param deviceId    设备id
     * @param dataType    amr_read的DATA_TYPE 数据类型： 1：普通数据  4：日冻结 6：月冻结   2：曲线数据
     * @param time        时间格式 2020-07-22 12:15:30
     * @param data        校验数据 数值型，曲线数据时为json数组
     * @param sourceCode  关联编号，用于追溯VEE校验的数据来源
     * @param sort        类别   0：VEE校验    1：VEE预估
     * @param type        content外部，数据项类型  0：抄表数据(账单数据、瞬时数据、曲线数据)  2：用电量
     * @param cmd         指令码      
     */
    public void send0112(String function, String deviceId, String dataType, String time, 
            String data, String sourceCode, String sort, String type, String cmd) {
        sendCmd0112.send(function,deviceId,dataType,time,data,sourceCode,sort,type,cmd);
    }
    
    public void send0112Save(String function, String deviceId,String cmd, String dataType, String time, 
            String value, String sort, String type) {
        sendCmd0112.sendSave(function,deviceId,cmd,dataType,time,value,sort,type);
    }
}
