package com.inhe.admin.model;

public class SysLang {
    private Integer langId;

    private String name;

    private String isoCode;
    
    private String img;

    private String active;

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode == null ? null : isoCode.trim();
    }
    
    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }
}