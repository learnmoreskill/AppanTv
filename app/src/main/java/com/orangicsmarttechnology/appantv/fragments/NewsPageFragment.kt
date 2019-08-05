package com.orangicsmarttechnology.appantv.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import com.orangicsmarttechnology.appantv.Constants

import com.orangicsmarttechnology.appantv.R
import kotlinx.android.synthetic.main.fragment_news_page.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsPageFragment : Fragment() {

    var proressbar1 : ProgressBar? = null

    @SuppressLint("NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_page, container, false)


        val webView = view.findViewById<WebView>(R.id.webView_news)

        // Get the web view settings instance
        val webSettings = webView.settings

        // Enable java script in web view
        webSettings.javaScriptEnabled = true

        //scrolll overlay
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

        // Enable and setup web view cache
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
        webSettings.setAppCacheEnabled(true)


        webSettings.setDomStorageEnabled(true)
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
        webSettings.setUseWideViewPort(true)
        webSettings.setEnableSmoothTransition(true)

        // Enable zooming in web view
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false


        //Improve webView performance
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.setSupportMultipleWindows(true)
        webSettings.loadWithOverviewMode = true
        webSettings.allowContentAccess = true
        webSettings.setGeolocationEnabled(true)
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.allowFileAccess = true

        // WebView settings
        webView.fitsSystemWindows = true

        //important to open url in your app
        webView.webViewClient = object : WebViewClient() {
            //No internet message handle
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                webView.loadUrl("file:///android_asset/error.html")
            }
        }


        //IF INTERNET CONNECTION IS NOT AVAILABLE
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected){
            Toast.makeText(context, "Internet connection is not available!!", Toast.LENGTH_LONG).show()
            //Snackbar.make(view, "Internet connection is not available!!", Snackbar.LENGTH_LONG).setAction("Action", null).show()

        }



        webView.loadUrl(Constants.NEWS_URL)

        // Set web view client
        webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
                //toast("Page loading.")

                // Enable disable back forward button
                //button_back.isEnabled = web_view.canGoBack()
                //button_forward.isEnabled = web_view.canGoForward()
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Display the loaded page title in a toast message
                //toast("Page loaded: ${view.title}")
                activity?.title =" ${view.title}"

                // Enable disable back forward button
                //button_back.isEnabled = web_view.canGoBack()
                //button_forward.isEnabled = web_view.canGoForward()
            }
        }

        // Set web view chrome client
        webView.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar?.progress = newProgress
            }
        }


        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return true
                }
                return false
            }
        })


        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity?.title = "News"

    }


}
