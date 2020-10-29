package com.example.mywebview.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.mywebview.BuildConfig
import com.example.mywebview.R
import com.example.mywebview.getLollipopFixWebView
import com.example.mywebview.util.DensityUtil


class MyWebView : WebView {
    private lateinit var progressBar: ProgressBar
    private var titleText: String? = null

    private val webChromeClient = object : android.webkit.WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.visibility = View.VISIBLE
            progressBar.progress = newProgress
            if (newProgress == 100) {
                progressBar.visibility = View.GONE
            }
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            titleText = title
        }
    }

    constructor(context: Context) : super(context.getLollipopFixWebView()) {
        init()
    }

    //
//
    constructor(context: Context, attrs: AttributeSet?) : super(
        context.getLollipopFixWebView(),
        attrs
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int = 0
    ) : super(context.getLollipopFixWebView(), attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        //WebView 的一些设置
        val webSettings = settings
        webSettings.javaScriptCanOpenWindowsAutomatically = false
        isVerticalScrollBarEnabled = false
        webSettings.domStorageEnabled = true //允许JS使用localStotage
        webSettings.javaScriptEnabled = false //与JS交互
        webSettings.useWideViewPort = true //设置网页的宽度与屏幕宽度一致
        webSettings.loadWithOverviewMode = true  //缩小内容适应屏幕
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //每次走网络
        webSettings.builtInZoomControls = true  //支持缩放
        webSettings.displayZoomControls = false //不显示缩放按钮
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            DensityUtil.dip2px(context,2f)
        )
        progressBar.layoutParams = layoutParams
        progressBar.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progress_bg)
        progressBar.max = 100
        addView(progressBar)
        setWebChromeClient(webChromeClient)
//        webViewClient = mWebViewClient
        if (BuildConfig.DEBUG) {
            setWebContentsDebuggingEnabled(true)
        }

    }



    //js调用android方法
    @SuppressLint("SetJavaScriptEnabled")
    fun needJsEnable() {
        settings.javaScriptEnabled = true //与JS交互
        addInterface()
    }

    /**
     * 添加js交互
     */
    companion object {
        //登录
        const val ACTION_LOGIN =1
    }

    private fun addInterface() {
        this.addJavascriptInterface(object : Any() {
            @JavascriptInterface
            fun action(param: Int) {
                when (param) {
                    ACTION_LOGIN -> {

                    }
                }
            }

        }, "nativeMethod")
    }

    //android调用js方法
    fun evaluateToken() {
//        this.evaluateJavascript("javascript:loadToken('" + MyApplication.app.user.token + "')") {
//            //            WonderfulToastUtils.showToast(it)
//        }
    }




}
