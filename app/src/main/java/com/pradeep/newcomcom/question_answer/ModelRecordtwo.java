package com.pradeep.newcomcom.question_answer;
//Pradeep puri goswami
public class ModelRecordtwo {
    // variables
    String id, question, answer, addedTime, updatedTime;

    public ModelRecordtwo() {
    }

    public ModelRecordtwo(String id, String question, String answer, String addedTime, String updatedTime) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
