package ext.android.loadpage.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ext.android.loadpage.LoadPageHelper;
import ext.android.loadpage.LoadPager;
import ext.android.loadpage.sample.content.DoubleFragmentActivity;
import ext.android.loadpage.sample.content.FunctionActivity;
import ext.android.loadpage.sample.content.SecondActivity;
import ext.android.loadpage.sample.content.SimpleFragmentActivity;
import ext.android.loadpage.sample.content.ViewLoadingActivity;
import ext.android.loadpage.sample.content.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final LoadPager loadPager = LoadPageHelper.getDefault().wrap(this);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPager.show();
            }
        }, 2000);
    }

    @OnClick({R.id.tv,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, FunctionActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, SimpleFragmentActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, DoubleFragmentActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, ViewLoadingActivity.class));
                break;
            case R.id.tv:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
