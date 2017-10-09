package cherry.android.loadpage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROOT on 2017/9/29.
 */

public final class LoadPageHelper {
    private static LoadPageHelper _default;
    private Config mConfig;

    public void setConfig(@Nullable Config config) {
        this.mConfig = config;
    }

    public static LoadPageHelper getDefault() {
        if (_default == null) {
            synchronized (LoadPageHelper.class) {
                if (_default == null)
                    _default = new LoadPageHelper();
            }
        }
        return _default;
    }

    public LoadPager wrap(@NonNull Object target) {
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
                final int childCount = parentView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    final View child = parentView.getChildAt(i);
                    if (child == originView) {
                        index = i;
                        break;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("arguments should be Activity or View. now is " + target.getClass());
        }
//        if (parentView == null) {
//            throw new IllegalArgumentException("cannot catch parent view for target. " + target.getClass());
//        }
        LoadPager.WrapContent wrap = new LoadPager.WrapContent(parentView, originView, index);
        return new LoadPager(wrap, this.mConfig);
    }

    public static class Config {
        private List<Page> mPages;
        private Class<? extends Page> firstPage;

        public Config() {
            //loading err empty
            mPages = new ArrayList<>(3);
        }

        public Config addPage(@NonNull Page page) {
            mPages.add(page);
            return this;
        }

        public Config first(@NonNull Class<? extends Page> pageClass) {
            this.firstPage = pageClass;
            return this;
        }

        protected List<Page> getPages() {
            return mPages;
        }

        protected Class<? extends Page> firstPage() {
            return this.firstPage;
        }
    }
}
