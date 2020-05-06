package com.hjs.automation.springBootSelenium.constants;

public enum Regions {
    PHL(1, "Philippines"),
    IDN(2, "Indonesia"),
    VNM(3, "Vietnam");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    Regions(int code, String name){
        this.code=code;
        this.name=name;
    }
}


