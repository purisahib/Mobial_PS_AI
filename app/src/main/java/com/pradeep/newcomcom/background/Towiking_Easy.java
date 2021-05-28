package com.pradeep.newcomcom.background;
//Pradeep puri goswami
public class Towiking_Easy {

    public String getTowking(String getData) {
        String message;
        switch (getData) {
            case "Hello":
                message = "Hello sir";
                break;
            case "What is your name":
                message = "PS 001";
                break;
            case "What are doing":
                message = "Nothing Sir";
                break;
            case "How to help you":
                message = "You visit my Help Desk";
                break;
            case "You some Thing different":
                message = "No Sir, but I am helping you...";
                break;
            case "do":
                message = "I am doing best...";
                break;
            case "how to help you":
                message = "Sir, I am asking Your Problem answer";
                break;

            default:
                message ="";// "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
