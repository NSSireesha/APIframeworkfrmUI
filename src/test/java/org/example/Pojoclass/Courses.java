package org.example.Pojoclass;

import java.util.List;

public class Courses {

    private List<WebAutomation> WebAutomation;
    private List<API> api;
    private List<Mobile> mobile;

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.WebAutomation = webAutomation;
    }
    public List<WebAutomation> getWebAutomation() {
        return WebAutomation;
    }



    public List<API> getApi() {
        return api;
    }

    public void setApi(List<API> api) {
        this.api = api;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }
}
