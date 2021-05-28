package com.pradeep.newcomcom.Apps;
//Pradeep puri goswami
public class ModelRecordOne {
    // variables
    String id,  name, packageName, path, addedTime, updatedTime;

    public ModelRecordOne() {
    }

    public ModelRecordOne(String id,   String name, String packageName, String path, String addedTime, String updatedTime) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.path = path;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
