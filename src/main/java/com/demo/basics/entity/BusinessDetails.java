package com.demo.basics.entity;

public class BusinessDetails {

    private Long id;

    private Integer dealValidity;

    private String sessionId;

    private String userIndetify;

    private String systemId;

    private String systemname;

    private String businessProcessCode;

    private String businessCode;

    private String businessName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDealValidity() {
        return dealValidity;
    }

    public void setDealValidity(Integer dealValidity) {
        this.dealValidity = dealValidity;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserIndetify() {
        return userIndetify;
    }

    public void setUserIndetify(String userIndetify) {
        this.userIndetify = userIndetify;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getBusinessProcessCode() {
        return businessProcessCode;
    }

    public void setBusinessProcessCode(String businessProcessCode) {
        this.businessProcessCode = businessProcessCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
