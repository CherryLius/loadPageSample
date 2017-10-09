package cherry.android.loadpage.sample.content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.LoadPager;
import cherry.android.loadpage.OnRetryListener;
import cherry.android.loadpage.sample.R;
import cherry.android.loadpage.sample.page.ErrorPage;
import cherry.android.loadpage.sample.page.LoadingPage;

public class ViewLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loading);
        final TextView textView = (TextView) findViewById(R.id.text);
        LoadPageHelper.Config config = new LoadPageHelper.Config()
                .addPage(new ErrorPage())
                .addPage(new LoadingPage())
                .first(LoadingPage.class);
        LoadPageHelper loadPageHelper = new LoadPageHelper();
        loadPageHelper.setConfig(config);
        final LoadPager loadPager = loadPageHelper.wrap(textView);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPager.showPage(ErrorPage.class);
            }
        }, 1000);
        ErrorPage errorPage = loadPager.getPage(ErrorPage.class);
        errorPage.setOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry(View view) {
                loadPager.showPage(LoadingPage.class);
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadPager.show();
                    }
                }, 1000);
            }
        });
    }
}
