package com.inhe.mdm.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.inhe.node.service.impl.SysLangPackageServiceImpl;

/**
 * mdm工具类
 * @author 
 * @date 2020/12/10
 */
@Service
public class MdmUtil {
	
	@Autowired
	SysLangPackageServiceImpl sysLangPackageServiceImpl;
	
	/**
	 * 日冻结电量
	 */
	//电表日冻结普通
	public static String[] METER_DAY_TOTAL_STAND = {"CTDC00[0-4][0-9]"};
	//电表日冻结曲线
	public static String[] METER_DAY_TOTAL_PROF = {"0710[0-9A-F]{2}02", "07409002", "07409102"};
	//一代表日冻结曲线
    public static String[] METER_DAY_I_PROF = {"0710[0-9A-F]{2}02"};
	//二代表日冻结曲线
	public static String[] METER_DAY_II_PROF = {"07409102", "07409002"};
	//集中器日冻结曲线-抄读
	public static String[] CON_DAY_TOTAL_PROF_READ = {"CON0D229", "CON0D280", "CON0D281", "CONMN229"};
	//集中器日冻结曲线-推送
	public static String[] CON_DAY_TOTAL_PROF_PUSH = {"CON0D235"};

	//总有功电量
    public static String[] METER_DAY_TOTAL = {"0300[0-8]002"};
    //电表日冻结正向有功
    public static String[] METER_DAY_FORWARD_ACTIVE = {"0303[0-8]002"};
    //电表日冻结反向有功
    public static String[] METER_DAY_REVERSE_ACTIVE = {"0304[0-8]002"};
    //电表日冻结正向无功
    public static String[] METER_DAY_FORWARD_REACTIVE = {"0305[0-8]002"};
    //电表日冻结反向无功
    public static String[] METER_DAY_REVERSE_REACTIVE = {"0306[0-8]002"};
	/**
	 * 日使用电量
	 */
	//电表日使用普通
	public static String[] METER_DAY_USE_STAND = {"CTDU00[0-4][0-9]"};
	//电表日使用曲线
	public static String[] METER_DAY_USE_PROF = {"0730[0-9A-F]{2}02"};
	//集中器日使用曲线-抄读
	public static String[] CON_DAY_USE_PROF_READ = {"CON0D232"};
	/**
	 * 月冻结电量
	 */
	//电表月冻结普通
    public static String[] METER_MONTH_TOTAL_STAND = {"03000[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T1 = {"03001[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T2 = {"03002[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T3 = {"03003[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T4 = {"03004[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T5 = {"03005[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T6 = {"03006[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T7 = {"03007[1-9A-D]02"};
    public static String[] METER_MONTH_TOTAL_STAND_T8 = {"03008[1-9A-D]02"};
    //电表月冻结正向有功
    public static String[] METER_MONTH_FORWARD_ACTIVE = {"03030[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_ACTIVE_T1 = {"03031[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_ACTIVE_T2 = {"03032[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_ACTIVE_T3 = {"03033[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_ACTIVE_T4 = {"03034[1-9A-D]02"};
    //电表月冻结反向有功
    public static String[] METER_MONTH_REVERSE_ACTIVE = {"03040[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_ACTIVE_T1 = {"03041[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_ACTIVE_T2 = {"03042[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_ACTIVE_T3 = {"03043[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_ACTIVE_T4 = {"03044[1-9A-D]02"};
    //电表月冻结正向无功
    public static String[] METER_MONTH_FORWARD_REACTIVE = {"03050[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_REACTIVE_T1 = {"03051[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_REACTIVE_T2 = {"03052[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_REACTIVE_T3 = {"03053[1-9A-D]02"};
    public static String[] METER_MONTH_FORWARD_REACTIVE_T4 = {"03054[1-9A-D]02"};
    //电表月冻结反向无功
    public static String[] METER_MONTH_REVERSE_REACTIVE = {"03060[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_REACTIVE_T1 = {"03061[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_REACTIVE_T2 = {"03062[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_REACTIVE_T3 = {"03063[1-9A-D]02"};
    public static String[] METER_MONTH_REVERSE_REACTIVE_T4 = {"03064[1-9A-D]02"};
	//电表月冻结曲线
	public static String[] METER_MONTH_TOTAL_PROF = {"07408002", "07408102"};
	//集中器月冻结曲线-抄读
	public static String[] CON_MONTH_TOTAL_PROF_READ = {"CON0D226", "CON0D282", "CON0D283", "CONMN226"};
	//集中器月冻结曲线-推送
	public static String[] CON_MONTH_TOTAL_PROF_PUSH = {"CON0D236"};
	/**
	 * 月使用电量
	 */
	//电表月使用普通
	public static String[] METER_MONTH_USE_STAND = {"03000[1-9A-D]12"};
	//电表月使用曲线
	public static String[] METER_MONTH_USE_PROF = {""};
	//集中器月使用曲线-抄读
	public static String[] CON_MONTH_USE_PROF_READ = {"CON0D231"};
	/**
	 * 事件
	 */
	//电表事件曲线
	public static String[] METER_EVENT_PROF = {"0700[0-9A-F]{2}02"};
	//集中器事件曲线
	public static String[] CON_EVENT_PROF = {"CON0D225" ,"CON0D227" ,"CON0D228" ,"CON0D237" ,"CON0D230" ,
											 "CON0D239" ,"CON0D240" ,"CON0D284" ,"CON0D285" ,
											 "CON0D286" ,"CON0D287" ,"CON0D288" ,"CON0D289" ,
											 "CONMN225" ,"CONMN227" ,"CONMN228"};
	/**
	 * 负荷
	 */
	//电表负荷曲线
	public static String[] METER_POWER_PROF = {"072[0-9]{3}0[2|9]"};
	//集中器负荷曲线
	public static String[] CON_POWER_PROF = {"CON0D233" ,"CON0D234" ,"CONMN233", "CONMN234"};
	
	
	public static String[] RF_VER = {"CON39003"};
	public static String[] NODE_PATH = {"NW000014"};
		
	
	/**
	 * 检查数组中是否存在某个值，可以是正则表达式
	 * @param field
	 * @return true:存在,flase:不存在
	 */
	public static boolean contains(String [] arr, String value){
		for(int i=0;i<arr.length;i++){
			if(Pattern.matches(arr[i], value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查满足条件的指令，可以是正则表达式
	 * @param field
	 * @return Map<index, field>
	 */
	public static Map<Integer, String> findField(String [] arr, String value){
		Map<Integer, String> result = new HashMap<Integer, String>();
		for(int i=0;i<arr.length;i++){
			if(Pattern.matches(value, arr[i])){
				result.put(i, arr[i]);
			}
		}
		return result;
	}
	
	public static boolean isAmrNwDevice(String field){
        if(contains(MdmUtil.RF_VER, field) || contains(MdmUtil.NODE_PATH, field)){
            return true;
        }
        return false;
    }
	
	/**
	 * 判断是否为日数据
	 * @param field
	 * @return true:是,flase:否
	 */
	public static boolean isDayField(String field){
		if(contains(MdmUtil.METER_DAY_TOTAL_STAND, field) || contains(MdmUtil.METER_DAY_TOTAL_PROF, field) || 
		   contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field) ||
		   contains(MdmUtil.METER_DAY_USE_STAND, field) || contains(MdmUtil.METER_DAY_USE_PROF, field) || 
		   contains(MdmUtil.CON_DAY_USE_PROF_READ, field) || contains(MdmUtil.METER_DAY_TOTAL, field) || 
		   contains(MdmUtil.METER_DAY_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_REACTIVE, field)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为月数据
	 * @param field
	 * @return true:是,flase:否
	 */
	public static boolean isMonthField(String field){
        if(contains(MdmUtil.METER_MONTH_TOTAL_STAND, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T1, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T2, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T3, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T4, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T5, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T6, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T7, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T8, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_PROF, field) || 
                contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || 
                contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field) || 
                contains(MdmUtil.METER_MONTH_USE_STAND, field) || 
                contains(MdmUtil.METER_MONTH_USE_PROF, field) || 
                contains(MdmUtil.CON_MONTH_USE_PROF_READ, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T1, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T2, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T3, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T4, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T1, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T2, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T3, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T4, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T1, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T2, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T3, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T4, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T1, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T2, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T3, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T4, field) 
              ){
            return true;
        }
        return false;
    }
	
	/**
	 * 判断是否为曲线
	 * @param field
	 * @return true:是,false:否
	 */
	public static boolean isProfileField(String field){
		if(contains(MdmUtil.METER_DAY_TOTAL_PROF, field) || contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || 
		   contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field) || contains(MdmUtil.METER_DAY_USE_PROF, field) || 
		   contains(MdmUtil.CON_DAY_USE_PROF_READ, field) || contains(MdmUtil.METER_MONTH_TOTAL_PROF, field) || 
		   contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field) ||
		   contains(MdmUtil.METER_MONTH_USE_PROF, field) || contains(MdmUtil.CON_MONTH_USE_PROF_READ, field) || 
		   contains(MdmUtil.METER_EVENT_PROF, field) || contains(MdmUtil.CON_EVENT_PROF, field) || 
		   contains(MdmUtil.METER_POWER_PROF, field) || contains(MdmUtil.CON_POWER_PROF, field)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为集中器指令
	 * @param field
	 * @return true:是,false:否
	 */
	public static boolean isConField(String field){
		if(contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field) ||
		   contains(MdmUtil.CON_DAY_USE_PROF_READ, field) || contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || 
		   contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field) || contains(MdmUtil.CON_MONTH_USE_PROF_READ, field) || 
		   contains(MdmUtil.CON_EVENT_PROF, field) || contains(MdmUtil.CON_POWER_PROF, field)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为事件曲线
	 * @param field
	 * @return true:是,false:否
	 */
	public static boolean isEvent(String field){
		if(contains(MdmUtil.METER_EVENT_PROF, field) || contains(MdmUtil.CON_EVENT_PROF, field)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为负荷曲线
	 * @param field
	 * @return true:是,false:否
	 */
	public static boolean isPower(String field){
		if(contains(MdmUtil.METER_POWER_PROF, field) || contains(MdmUtil.CON_POWER_PROF, field)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为日正反向电量
     * @param field
     * @return true:是,false:否
     */
    public static boolean isDayForwardAndReverse(String field){
        if(contains(MdmUtil.METER_DAY_TOTAL, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_REACTIVE, field)){
            return true;
        }
        return false;
    }
    
    /**
     * 判断是否为月正反向电量
     * @param field
     * @return true:是,false:否
     */
    public static boolean isMonthForwardAndReverse(String field){
        if(contains(MdmUtil.METER_MONTH_TOTAL_STAND, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T1, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T2, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T3, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T4, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T5, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T6, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T7, field) ||
                contains(MdmUtil.METER_MONTH_TOTAL_STAND_T8, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T1, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T2, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T3, field) ||
                contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE_T4, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T1, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T2, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T3, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE_T4, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T1, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T2, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T3, field) || 
                contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE_T4, field) || 
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T1, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T2, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T3, field) ||
                contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE_T4, field) 
              ){
            return true;
        }
        return false;
    }
	
	/**
	 * 判断电量类型
	 * @param field
	 * @return 1:冻结电量,2:使用电量
	 */
	public static String checkKwhType(String field){
		String kwhType = "";
		if(contains(MdmUtil.METER_DAY_TOTAL_STAND, field) || contains(MdmUtil.METER_DAY_TOTAL_PROF, field) || 
		   contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field)||
		   contains(MdmUtil.METER_MONTH_TOTAL_STAND, field) || contains(MdmUtil.METER_MONTH_TOTAL_PROF, field)||
		   contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field) ||
		   contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, field)|| 
           contains(MdmUtil.METER_DAY_TOTAL, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_REACTIVE, field)){
			kwhType = "1";
		}
		else
		if(contains(MdmUtil.METER_DAY_USE_STAND, field) || contains(MdmUtil.METER_DAY_USE_PROF, field) || 
		   contains(MdmUtil.CON_DAY_USE_PROF_READ, field) || contains(MdmUtil.METER_MONTH_USE_STAND, field) || 
		   contains(MdmUtil.METER_MONTH_USE_PROF, field) || contains(MdmUtil.CON_MONTH_USE_PROF_READ, field)){
			kwhType = "2";
		}
		return kwhType;
	}
	
	/**
	 * 判断抄读类型
	 * @param field
	 * @return 1:集中器抄表,2:直接抄表,3:集中器推送
	 */
	public static String checkReadType(String field){
		String readType = "";
		if(contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_DAY_USE_PROF_READ, field) ||
		   contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_MONTH_USE_PROF_READ, field)){
			readType = "1";
		}
		else
		if(contains(MdmUtil.METER_DAY_TOTAL_STAND, field) || contains(MdmUtil.METER_DAY_TOTAL_PROF, field) ||
		   contains(MdmUtil.METER_DAY_USE_STAND, field) || contains(MdmUtil.METER_DAY_USE_PROF, field) ||
		   contains(MdmUtil.METER_MONTH_TOTAL_STAND, field) || contains(MdmUtil.METER_MONTH_TOTAL_PROF, field) ||
		   contains(MdmUtil.METER_MONTH_USE_STAND, field) || contains(MdmUtil.METER_MONTH_USE_PROF, field) ||
		   contains(MdmUtil.METER_EVENT_PROF, field) || contains(MdmUtil.METER_POWER_PROF, field) ||
		   contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, field)){
			readType = "2";
		}
		else
		if(contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field) || contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field)){
			readType = "3";
		}
		return readType;
	}
	
	/**
	 * 判断数据源
	 * @param cmdType
	 * @return 1:主站,2:APP,6:SmartTool
	 */
	public static String checkDateSource(String cmdType){
		String dateSource = "1";
		if("APP_TASK".equals(cmdType)){
			dateSource = "2";
		}
		else
		if("SMARTTOOLS".equals(cmdType)){
			dateSource = "6";
		}
		return dateSource;
	}
	
	/**
	 * 判断数据类型
	 * @param field
	 * @return 1:普通数据,2:负荷曲线,3:其他曲线,4:日用电曲线(冻结),5:日用电曲线(使用),6:月冻结电量,7:月使用电量
	 */
	public static String checkDataType(String field){
		String dataType = "1";
		if(isPower(field)){
			dataType = "2";
		}
		else
		if(contains(MdmUtil.METER_DAY_TOTAL_PROF, field) || contains(MdmUtil.CON_DAY_TOTAL_PROF_READ, field) || 
		   contains(MdmUtil.CON_DAY_TOTAL_PROF_PUSH, field) || contains(MdmUtil.METER_DAY_TOTAL, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_DAY_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_DAY_REVERSE_REACTIVE, field)){	
			dataType = "4";
		}
		else
		if(contains(MdmUtil.METER_DAY_USE_PROF, field) || contains(MdmUtil.CON_DAY_USE_PROF_READ, field)){
			dataType = "5";
		}
		else
		if(Pattern.matches("^(07)[0-9]{4}[0][2|9]", field)){	
			dataType = "3";
		}
		else
		if(contains(MdmUtil.METER_MONTH_TOTAL_STAND, field) || contains(MdmUtil.METER_MONTH_TOTAL_PROF, field)||
		   contains(MdmUtil.CON_MONTH_TOTAL_PROF_READ, field) || contains(MdmUtil.CON_MONTH_TOTAL_PROF_PUSH, field) ||
		   contains(MdmUtil.METER_MONTH_FORWARD_ACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_ACTIVE, field) || 
           contains(MdmUtil.METER_MONTH_FORWARD_REACTIVE, field) || contains(MdmUtil.METER_MONTH_REVERSE_REACTIVE, field)){		
			dataType = "6";
		}
		else
		if(contains(MdmUtil.METER_MONTH_USE_STAND, field) || contains(MdmUtil.METER_MONTH_USE_PROF, field) || 
		   contains(MdmUtil.CON_MONTH_USE_PROF_READ, field)){		
			dataType = "7";
		}
		return dataType;
	}
	
	/**
	 * 判断操作员
	 * @param cmdType
	 * @return 0:设备调试,1:实时抄读,2:任务模板抄读,3:自定义任务抄读,255:人工抄读
	 */
	public static String checkOperator(String cmdType){
		String operator = "Realtime Read";
		if("0".equals(cmdType)){
			operator = "Device Test";
		}
		else
		if("2".equals(cmdType)){
			operator = "Template Task Read";
		}
		else
		if("3".equals(cmdType)){
			operator = "Defined Task Read";
		}
		else
        if("255".equals(cmdType)){
            operator = "Manual Read";
        }
		return operator;
		
	}
	
    public static String[] getPtctValueEx(String data){
        String[] result = {"", ""};
        String[] arr = data.split("=");
        if(arr.length < 2){
            return result;
        }
        if(!arr[1].contains("/")){
            result[0] = arr[1];
            result[1] = arr[1];
        }
        else{
            String[] v = arr[1].split("/"); 
            result[0] = v[0];
            result[1] = v[1];
        }
        return result;
    }
	
    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
    	Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();
    }
    
    /**
	 * 字符串转Map
	 * @param nw_params
	 * @return
	 */
	public static Map<String,String> strToMap(String str ,String d1, String d2) {
		Map<String,String> map = new HashMap<String,String>();
		String[] params = str.split(d1);
	    for (int i = 0 ; i <params.length ; i++ ) {
	    	String[] temp = params[i].split(d2);
	    	if(temp.length > 0){
	    		map.put(temp[0], temp.length<2?"":temp[1]);
	    	}
	    } 
	    return map;
	}
	
	/**
	 * 获取多语言
	 * @param isoCode langType
	 * @return
	 */
	public JSONObject getLang(String isoCode, String langType) {
		JSONObject json = sysLangPackageServiceImpl.getLangPackage(isoCode);
		JSONObject detailJson = json.getJSONObject("detail");
		JSONObject resultJson = detailJson.getJSONObject(langType);
		return resultJson;
	}
}
