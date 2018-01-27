package com.square.surf;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    TextView t;
    Button seekButton;
    WebView myWebView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t= (EditText) findViewById(R.id.textview);
        url=t.getText().toString();

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                String[] urlSplit=url.split(" ");
                String urlKeywords="";
                for(int i=0;i<urlSplit.length;i++){
                    if(i==0) urlKeywords=urlKeywords+urlSplit[i];
                    else urlKeywords=urlKeywords+"+"+urlSplit[i];
                }
                view.loadUrl("http://www.google.com/search?q="+urlKeywords);

            }
        });
        myWebView.loadUrl("http://www.google.com/ncr");
        seekButton = (Button) findViewById(R.id.seekButton);
        t.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     t.setText("");
                                 }
                             }
        );
        t.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    loadURL();
                }

                return false;
            }

            ;

        });
        seekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadURL();
            }
        });
    }

    private void loadURL() {
        try {
url=t.getText().toString();
            if (url.startsWith("http://"))
                myWebView.loadUrl(url);
            else
                myWebView.loadUrl("http://" + url);

        } catch (Exception e) {
            myWebView.loadUrl("https://www.google.com/search?q=" + url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            myWebView.goBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
