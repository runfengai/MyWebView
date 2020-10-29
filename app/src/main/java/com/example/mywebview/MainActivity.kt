package com.example.mywebview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.mywebview.Constant.URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_URL = "extra_url"
        fun jump(activity: Context, url: String) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra(EXTRA_URL, url)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            initView()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val url: String = URL
    private fun initView() {
//        url = intent.getStringExtra(EXTRA_URL) ?: URL
        webview.post {
            webview.requestFocus()// 获取焦点  或者使用 webView.requestFocus()同样的效果
        }
        webview.loadUrl(url)
    }

    /**
     * 返回
     */
    private fun handleBack() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                handleBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }



}
