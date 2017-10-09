package cherry.android.loadpage.sample.content;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.LoadPager;
import cherry.android.loadpage.OnRetryListener;
import cherry.android.loadpage.sample.R;
import cherry.android.loadpage.sample.page.EmptyPage;
import cherry.android.loadpage.sample.page.ErrorPage;
import cherry.android.loadpage.sample.page.LoadingPage;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private LoadPager mLoadPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View content = findViewById(R.id.content);
        mLoadPager = LoadPageHelper.getDefault().wrap(content);
        loading(new Runnable() {
            @Override
            public void run() {
                mLoadPager.showPage(EmptyPage.class);
            }
        });

        ErrorPage errorPage = mLoadPager.getPage(ErrorPage.class);
        errorPage.setOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry(View view) {
                loadToSuccess();
            }
        });
        EmptyPage emptyPage = mLoadPager.getPage(EmptyPage.class);
        emptyPage.setOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry(View view) {
                loadToSuccess();
            }
        });
    }

    @OnClick({R.id.button1, R.id.button2})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                loading(new Runnable() {
                    @Override
                    public void run() {
                        mLoadPager.showPage(EmptyPage.class);
                    }
                });
                break;
            case R.id.button2:
                loading(new Runnable() {
                    @Override
                    public void run() {
                        mLoadPager.showPage(ErrorPage.class);
                    }
                });
                break;
        }
    }

    private void loadToSuccess() {
        loading(new Runnable() {
            @Override
            public void run() {
                mLoadPager.show();
            }
        });
    }

    private void loading(Runnable runnable) {
        mLoadPager.showPage(LoadingPage.class);
        toolbar.postDelayed(runnable, 2000);
    }

}
