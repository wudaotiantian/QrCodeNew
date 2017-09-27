package com.example.administrator.qrcode;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.qrcode.model.QrScanEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import pub.devrel.easypermissions.EasyPermissions;

public class QrCodeActivity extends AppCompatActivity implements QRCodeView.Delegate, EasyPermissions.PermissionCallbacks {
    @BindView(R.id.zxingview)
    QRCodeView mQRCodeView;
    @OnClick(R.id.qr1_iv)
    public void click1(){
        mQRCodeView.changeToScanQRCodeStyle();

    }
    @OnClick(R.id.qr2_iv)
    public void click2(){

    }
    @OnClick(R.id.qr3_iv)
    public void click3(){
        mQRCodeView.changeToScanBarcodeStyle();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        ButterKnife.bind(QrCodeActivity.this);
        initQrCode();

    }

    private void initQrCode() {
        mQRCodeView.changeToScanQRCodeStyle();
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            mQRCodeView.setDelegate(QrCodeActivity.this);
            mQRCodeView.startSpot();
        } else {
            EasyPermissions.requestPermissions(this, "无法扫描二维码，因为无法获取相机权限", 1, Manifest.permission.CAMERA);
        }


    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        EventBus.getDefault().post(new QrScanEvent(result));
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(QrCodeActivity.this,"无法获取相机权限，无法扫描二维码",Toast.LENGTH_SHORT).show();
    }
}
