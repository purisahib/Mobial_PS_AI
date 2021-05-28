package com.pradeep.newcomcom.call;
//Pradeep puri goswami
public class ModelRecord {
    // variables
    String id, nameOne, nameTwo, nameThree, phone, addedTime, updatedTime;

    //constractor


    public ModelRecord() {
    }

    public ModelRecord(String id, String nameOne, String nameTwo, String nameThree, String phone, String addedTime, String updatedTime) {
        this.id = id;
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
        this.nameThree = nameThree;
        this.phone = phone;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOne() {
        return nameOne;
    }

    public void setNameOne(String nameOne) {
        this.nameOne = nameOne;
    }

    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    public String getNameThree() {
        return nameThree;
    }

    public void setNameThree(String nameThree) {
        this.nameThree = nameThree;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
