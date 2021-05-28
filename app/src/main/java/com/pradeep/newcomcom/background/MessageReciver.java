package com.pradeep.newcomcom.background;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.pradeep.newcomcom.MainActivity;
//Pradeep puri goswami
public class MessageReciver extends ResultReceiver {

    private MainActivity.Message message;
    public MessageReciver(MainActivity.Message message) {
        super(new Handler());

        this.message=message;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        message.displayMessage(resultCode, resultData);
    }
}
