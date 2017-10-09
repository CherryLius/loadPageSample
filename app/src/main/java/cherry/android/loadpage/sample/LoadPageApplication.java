package cherry.android.loadpage.sample;

import android.app.Application;

import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.sample.page.EmptyPage;
import cherry.android.loadpage.sample.page.ErrorPage;
import cherry.android.loadpage.sample.page.LoadingPage;

/**
 * Created by ROOT on 2017/10/9.
 */

public class LoadPageApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadPageHelper.Config config = new LoadPageHelper.Config()
                .addPage(new LoadingPage())
                .addPage(new ErrorPage())
                .addPage(new EmptyPage())
                .first(LoadingPage.class);
        LoadPageHelper.getDefault().setConfig(config);
    }
}
