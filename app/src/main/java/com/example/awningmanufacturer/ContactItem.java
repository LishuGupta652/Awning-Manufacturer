package com.example.awningmanufacturer;

/*
Developed by lishu gupta
web: https://www.pakkabaniya.ml
 */


public class ContactItem {
    private String address;
    private String email;
    private String mobile;

    public ContactItem(String address, String email, String mobile) {
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
