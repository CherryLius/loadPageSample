package cherry.android.loadpage.sample.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cherry.android.loadpage.sample.R;

public class DoubleFragmentActivity extends AppCompatActivity {

    private Fragment mFirstFragment;
    private Fragment mSecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_fragment);
        ButterKnife.bind(this);
        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mFirstFragment)
                .add(R.id.container, mSecondFragment).commit();
        getSupportFragmentManager().beginTransaction().show(mFirstFragment).hide(mSecondFragment).commit();
    }

    @OnClick({R.id.button1, R.id.button2})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                getSupportFragmentManager().beginTransaction().show(mFirstFragment).hide(mSecondFragment).commit();
                break;
            case R.id.button2:
                getSupportFragmentManager().beginTransaction().show(mSecondFragment).hide(mFirstFragment).commit();
                break;
        }
    }
}
