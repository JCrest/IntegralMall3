package com.csii.integralmall.bean;

public class AddressListInfo {
    /**
     * tbmemberlocationid : 1
     * tbmemberid : 81
     * contactperson : 张先生
     * sex : 男
     * phonenum : 17614525478
     * deliveraddress : 北京市西二旗中关村
     * detailaddress : 软件园南路11号2栋1单元211室
     * isdefault : 1
     */

    private int tbmemberlocationid;
    private int tbmemberid;
    private String contactperson;
    private String sex;
    private String phonenum;
    private String deliveraddress;
    private String detailaddress;
    private String isdefault;

    public AddressListInfo(int tbmemberlocationid, int tbmemberid, String contactperson, String sex, String phonenum, String deliveraddress, String detailaddress, String isdefault) {
        this.tbmemberlocationid = tbmemberlocationid;
        this.tbmemberid = tbmemberid;
        this.contactperson = contactperson;
        this.sex = sex;
        this.phonenum = phonenum;
        this.deliveraddress = deliveraddress;
        this.detailaddress = detailaddress;
        this.isdefault = isdefault;
    }

    public int getTbmemberlocationid() {
        return tbmemberlocationid;
    }

    public void setTbmemberlocationid(int tbmemberlocationid) {
        this.tbmemberlocationid = tbmemberlocationid;
    }

    public int getTbmemberid() {
        return tbmemberid;
    }

    public void setTbmemberid(int tbmemberid) {
        this.tbmemberid = tbmemberid;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getDeliveraddress() {
        return deliveraddress;
    }

    public void setDeliveraddress(String deliveraddress) {
        this.deliveraddress = deliveraddress;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }
}
