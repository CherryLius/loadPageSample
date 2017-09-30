package cherry.android.loadpage.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.LoadPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        LoadPageHelper.Config config = new LoadPageHelper.Config()
                .addPage(new LoadingPage(this))
                .first(LoadingPage.class);
        LoadPageHelper.getDefault().setConfig(config);
        final LoadPager loadPager = LoadPageHelper.getDefault().wrap(this);
        tv.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPager.show();
            }
        }, 3000);
    }
}
