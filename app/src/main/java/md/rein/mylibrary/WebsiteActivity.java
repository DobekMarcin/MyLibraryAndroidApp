package md.rein.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_website);

        Intent intent =getIntent();

        if(intent!=null){
            String url = intent.getStringExtra("url");
            webView = findViewById(R.id.webView);

            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
        }



    }

    @Override
    public void onBackPressed() {
    if(webView.canGoBack()){
        webView.goBack();

    }else{
        super.onBackPressed();
    }
    }
}