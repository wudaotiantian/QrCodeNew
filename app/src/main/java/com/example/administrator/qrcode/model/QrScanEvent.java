package com.example.administrator.qrcode.model;

/**
 * Created by Administrator on 2017/9/21.
 */

public class QrScanEvent {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QrScanEvent(String result) {
        this.result = result;
    }
}
