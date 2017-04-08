package example.alvarezkevin.udacitynews.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import example.alvarezkevin.udacitynews.R;

public class WebsiteActivity extends AppCompatActivity {

    private static final String ARTICLE_INTENT_URL_EXTRA = "ARTICLE_URL";
    private static final String ARTICLE_INTENT_TITLE_EXTRA = "ARTICLE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String url = getIntent().getStringExtra(ARTICLE_INTENT_URL_EXTRA);
        String title = getIntent().getStringExtra(ARTICLE_INTENT_TITLE_EXTRA);

        getSupportActionBar().setTitle(title);

        WebView webView = (WebView)findViewById(R.id.webview_article);
        webView.loadUrl(url);

    }

}
