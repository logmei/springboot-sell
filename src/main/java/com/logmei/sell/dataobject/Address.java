package com.logmei.sell.dataobject;

import javax.persistence.Embeddable;

@Embeddable//嵌入类代表是其他类的属性
public class Address {
    private String postCode;//邮编
    private String phone;//电话
    private String address;//地址

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
