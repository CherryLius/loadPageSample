package cherry.android.loadpage.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import cherry.android.loadpage.BasePage;

/**
 * Created by ROOT on 2017/9/30.
 */

public class LoadingPage extends BasePage {

    public LoadingPage(@NonNull Context context) {
        super(context, R.layout.layout_loading);
    }

    @Override
    public void convert(@NonNull View view) {

    }
}
