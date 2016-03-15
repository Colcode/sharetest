package com.bqs.wetime.fruits;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class ShareActivity extends Activity implements PlatformActionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button4 = (Button) findViewById(R.id.qq);

        ShareSDK.initSDK(this);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMethod();
            }
        });
    }


    public void shareMethod() {
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(false);
        HashMap<Platform, HashMap<String, Object>> shareData = new HashMap<>();
        HashMap<String, Object> reqData = new HashMap<>();
        reqData.put("site", "XX");
        reqData.put("siteUrl", "xxx");
        reqData.put("titleUrl", "xxx");
        reqData.put("dialogMode", true);
        reqData.put("title", "xxx");
        reqData.put("text", "推荐您也来使用");
        shareData.put(plat, reqData);
        oks.setCallback(this);
        oks.share(shareData);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.e("onComplete-->", "this is onComplete");
        Toast.makeText(ShareActivity.this, "123456", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.e("onError-->", "this is onError");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.e("onCancel-->", "this is onCancel");
    }
}
