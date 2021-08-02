/**
 * @author pfr 2020-12-11
 */
package com.inhe.mdm.vee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inhe.mdm.model.MdmAmDevice;

public interface IRuleProcess {
	Boolean check(MdmAmDevice device,JSONObject rule,JSONObject msgBody,JSONArray result);
}
