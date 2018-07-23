package ext.android.loadpage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

/**
 * Created by ROOT on 2017/9/29.
 */

public final class LoadPageHelper {
    private static LoadPageHelper _default;
    private Config mConfig;

    public void setConfig(@Nullable Config config) {
        this.mConfig = config;
    }

    public Config newConfig() {
        final Config config = new Config();
        config.firstPage = this.mConfig.firstPage;
        for (Page page : this.mConfig.getPageMap().values()) {
            config.addPage(page);
        }
        return config;
    }

    public static LoadPageHelper getDefault() {
        if (_default == null) {
            synchronized (LoadPageHelper.class) {
                if (_default == null) {
                    _default = new LoadPageHelper();
                }
            }
        }
        return _default;
    }

    public LoadPager wrap(@NonNull Activity activity) {
        return wrapInternal(activity);
    }

    public LoadPager wrap(@NonNull View view) {
        return wrapInternal(view);
    }


    private LoadPager wrapInternal(@NonNull Object target) {
        final ViewGroup parentView;
        final View originView;
        int index = 0;
        if (target instanceof Activity) {
            final Activity activity = (Activity) target;
            parentView = activity.findViewById(android.R.id.content);
            originView = parentView.getChildAt(0);
        } else if (target instanceof View) {
            originView = (View) target;
            parentView = (ViewGroup) originView.getParent();
            if (parentView != null) {
                index = parentView.indexOfChild(originView);
            }
        } else {
            throw new IllegalArgumentException("arguments should be Activity or View. now is " + target.getClass());
        }
        LoadPager.WrapContent wrap = new LoadPager.WrapContent(parentView, originView, index);
        return new LoadPager(wrap, this.mConfig);
    }

    public static class Config {
        private Map<String, Page> mPageMap;
        private Class<? extends Page> firstPage;

        public Config() {
            mPageMap = new ArrayMap<>(3);
        }

        public Config addPage(@NonNull Page page) {
            checkNotNull(page);
            final String pageName = page.getClass().getCanonicalName();
            mPageMap.put(pageName, page);
            return this;
        }

        public Config first(@NonNull Class<? extends Page> pageClass) {
            this.firstPage = pageClass;
            return this;
        }

        protected Map<String, Page> getPageMap() {
            return mPageMap;
        }

        protected Class<? extends Page> firstPage() {
            return this.firstPage;
        }
    }

    protected static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
