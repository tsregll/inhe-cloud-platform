package com.inhe.admin.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * @author JXINHE1548
 *
 */
public class SysRole {

    private Integer roleId;

    private String orgId;

    private String roleName;

    private String permissions;
    
    private String roleType;

    private String roleStatus;

    private String remark;

    private String coperator;

    private Date cdate;

    private String uoperator;

    private Date udate;
    
    private Boolean isMain;
    
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCoperator() {
        return coperator;
    }

    public void setCoperator(String coperator) {
        this.coperator = coperator;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getUoperator() {
        return uoperator;
    }

    public void setUoperator(String uoperator) {
        this.uoperator = uoperator;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
    
	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}

	/**
	 * 角色对应的functionId和ActionID
	 * 
	 * @param role
	 * @return Map结构：key:functionID,value:actions
	 */
	public Map<String, Set<String>> parseRolePermission() {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		String functionsTmp =this.permissions;
		String[] functions = null;
		if (!"".equals(functionsTmp) && functionsTmp != null) {
			functions = permissions.split("\\|");
		} else {
			return null;
		}
		for (String f : functions) {
			String[] fAction = f.split(":");
			if (fAction.length == 2) {
				String[] actions = fAction[1].split(",");
				
				Set<String> set = map.get(fAction[0]); 
				if(set == null){
					set = new HashSet<String>();
				}
				for (String s : actions) {
					set.add(s);
				}
				map.put(fAction[0], set);
			}
		}
		return map;
	}
}