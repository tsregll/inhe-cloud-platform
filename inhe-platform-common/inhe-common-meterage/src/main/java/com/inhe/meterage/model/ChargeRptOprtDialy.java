package com.inhe.meterage.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeRptOprtDialy {
	
	private Date transDate;

	private String orgId;

	private String deptId;

	private String operator;
	
    private BigDecimal totalAmt;

    private Integer totalCount;

    private BigDecimal amount0;

    private BigDecimal amount1;

    private BigDecimal amount2;

    private BigDecimal amount3;

    private BigDecimal amount4;

    private BigDecimal amount5;

    private BigDecimal amount6;

    private BigDecimal amount7;

    private BigDecimal amount8;

    private BigDecimal amount9;

    private BigDecimal amount10;
    
    // 供客户查询功能使用  2020-06-29  add wdk
    private String startDate;
    
    // 供客户查询功能使用  2020-06-29  add wdk
    private String endDate;
    
    // 供客户查询功能使用  2020-06-29  add wdk
    private String transMonth;
    
    // 供客户查询功能使用  2020-06-29  add wdk
    private Integer chargeOptCount;

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getAmount0() {
        return amount0;
    }

    public void setAmount0(BigDecimal amount0) {
        this.amount0 = amount0;
    }

    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }

    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }

    public BigDecimal getAmount3() {
        return amount3;
    }

    public void setAmount3(BigDecimal amount3) {
        this.amount3 = amount3;
    }

    public BigDecimal getAmount4() {
        return amount4;
    }

    public void setAmount4(BigDecimal amount4) {
        this.amount4 = amount4;
    }

    public BigDecimal getAmount5() {
        return amount5;
    }

    public void setAmount5(BigDecimal amount5) {
        this.amount5 = amount5;
    }

    public BigDecimal getAmount6() {
        return amount6;
    }

    public void setAmount6(BigDecimal amount6) {
        this.amount6 = amount6;
    }

    public BigDecimal getAmount7() {
        return amount7;
    }

    public void setAmount7(BigDecimal amount7) {
        this.amount7 = amount7;
    }

    public BigDecimal getAmount8() {
        return amount8;
    }

    public void setAmount8(BigDecimal amount8) {
        this.amount8 = amount8;
    }

    public BigDecimal getAmount9() {
        return amount9;
    }

    public void setAmount9(BigDecimal amount9) {
        this.amount9 = amount9;
    }

    public BigDecimal getAmount10() {
        return amount10;
    }

    public void setAmount10(BigDecimal amount10) {
        this.amount10 = amount10;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTransMonth() {
		return transMonth;
	}

	public void setTransMonth(String transMonth) {
		this.transMonth = transMonth;
	}

	public Integer getChargeOptCount() {
		return chargeOptCount;
	}

	public void setChargeOptCount(Integer chargeOptCount) {
		this.chargeOptCount = chargeOptCount;
	}
    
	
	
	
}