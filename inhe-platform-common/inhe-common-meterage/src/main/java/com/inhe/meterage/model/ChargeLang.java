package com.inhe.meterage.model;

public class ChargeLang {
    private Long langId;

    private String name;

    private String active;

    private String isoCode;

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

	@Override
	public String toString() {
		return "SysLang [langId=" + langId + ", name=" + name + ", active=" + active + ", isoCode=" + isoCode + "]";
	}
    
    
}