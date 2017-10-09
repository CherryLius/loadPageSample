package cherry.android.loadpage.sample.content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cherry.android.loadpage.sample.R;

public class SimpleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new PlusOneFragment()).commit();
    }
}
