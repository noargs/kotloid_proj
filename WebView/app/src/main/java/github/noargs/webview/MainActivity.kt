package github.noargs.webview

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import github.noargs.webview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


            binding.btnOpenInside.setOnClickListener(this)
            binding.btnOpenOutside.setOnClickListener(this)
            binding.btnOpenYoutube.setOnClickListener(this)


    }

    fun openWebInside()
    {
        binding.webView.webViewClient = object : WebViewClient ()
        {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        binding.webView.loadUrl("https://www.duckduckgo.com")
    }

    fun openWebOutside()
    {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.duckduckgo.com"))
        startActivity(browserIntent)

    }

    fun openYoutube()
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.youtube.com/watch?v=oB14FKhqDwA")
        intent.setPackage("com.google.android.youtube")

        val manager : PackageManager = this.packageManager
        val info = manager.queryIntentActivities(intent, 0)

        if (info.size == 0) {
            Toast.makeText(this, "No Such Application", Toast.LENGTH_SHORT)
            return
        }

        startActivity(intent)
    }

    override fun onClick(button: View?) {
        when(button)
        {
            binding.btnOpenInside -> openWebInside()
            binding.btnOpenOutside -> openWebOutside()
            binding.btnOpenYoutube -> openYoutube()

        }
    }
}