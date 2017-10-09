package cherry.android.loadpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cherry.java.Function;

/**
 * Created by ROOT on 2017/9/30.
 */

public class LoadPager {
    private WrapContent mWrapContent;
    private LoadPageLayout mLoadPageLayout;
    private LoadPageHelper.Config mConfig;

    LoadPager(@NonNull WrapContent wrapContent, @Nullable LoadPageHelper.Config config) {
        this.mWrapContent = wrapContent;
        this.mConfig = config;
        init();
    }

    private void init() {
        final Context context = this.mWrapContent.originView.getContext();
        mLoadPageLayout = new LoadPageLayout(context);
        if (this.mWrapContent.parentView != null) {
            this.mWrapContent.parentView.removeView(this.mWrapContent.originView);
            this.mWrapContent.parentView.addView(mLoadPageLayout,
                    this.mWrapContent.index,
                    this.mWrapContent.originView.getLayoutParams());
        }
        mLoadPageLayout.addPage(new OriginPage(this.mWrapContent.originView));
        if (this.mConfig != null) {
            final int length = this.mConfig.getPages().size();
            List<Page> clonePages = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                clonePages.add(AbstractPage.clone(this.mConfig.getPages().get(i)));
            }
            mLoadPageLayout.setPages(clonePages);
//            mLoadPageLayout.setPages(this.mConfig.getPages());
            if (this.mConfig.firstPage() != null) {
                showPage(this.mConfig.firstPage());
            }
        }
    }

    public View getLoadLayout() {
        return this.mLoadPageLayout;
    }

    public void showPage(@NonNull Class<? extends Page> pageClass) {
        mLoadPageLayout.showPage(pageClass);
    }

    public <T> void showPage(@Nullable T t, @NonNull Function<T, Class<? extends Page>> map) {
        showPage(map.apply(t));
    }

    public void show() {
        showPage(OriginPage.class);
    }

    public <T extends Page> T getPage(@NonNull Class<T> pageClass) {
        return (T) mLoadPageLayout.getPageByClass(pageClass);
    }

    public Class<? extends Page> originPage() {
        return OriginPage.class;
    }

    static class WrapContent {
        private ViewGroup parentView;
        private View originView;
        private int index;

        public WrapContent(ViewGroup parentView, View originView, int index) {
            this.parentView = parentView;
            this.originView = originView;
            this.index = index;
        }
    }

    static class OriginPage implements Page {

        private View mOriginView;

        OriginPage(@NonNull View originView) {
            this.mOriginView = originView;
        }

        @NonNull
        @Override
        public View getView(@NonNull Context context) {
            return this.mOriginView;
        }

        @Override
        public void convert(@NonNull View view) {
            // do nothing
        }

        @Override
        public void onAttach() {
            // do nothing
        }

        @Override
        public void onDetach() {
            // do nothing
        }

    }
}
