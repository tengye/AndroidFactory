package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HybridTextActivity extends FragmentActivity {

    private WebView webView;
    int i= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid_text);
        webView =  findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/nativetojs.html");
        WebSettings webSettings = webView.getSettings();
        //设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        //允许放到或者缩小
        webSettings.setSupportZoom(true);
        //隐藏webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        // 为图片添加放大缩小功能
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //取消滚动条
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        //设置打开别的url是不适用系统浏览器
        webView.setWebViewClient(new MyWebViewClient());
        /** 为webview添加注册js 接口回调监听.
         *
         * 参数一: 接口对象
         * 参数二: 接口别名(别名让js代码使用)
         * 如果别名写"haha". 那么js中就要写window.haha.方法名
         */
        webView.addJavascriptInterface(this, "android");

        findViewById(R.id.button).setOnClickListener(v -> {
            webView.loadUrl("javascript:javacalljs('<br>传个参数吧"+(i++)+"')");
        });

    }

    @JavascriptInterface
    public void showPopup(String params){

        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), params, Toast.LENGTH_SHORT).show();
        });

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按下返回键的处理
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
