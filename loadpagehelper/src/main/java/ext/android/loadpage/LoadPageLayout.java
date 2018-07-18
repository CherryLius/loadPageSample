package ext.android.loadpage;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import java.util.Map;

/**
 * Created by ROOT on 2017/9/28.
 */

/*public*/ class LoadPageLayout extends FrameLayout {
    private Map<String, Page> mPageMap = new ArrayMap<>();
    private Page mCurrentPage;

    public LoadPageLayout(@NonNull Context context) {
        super(context);
    }

    public LoadPageLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadPageLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addPage(@NonNull Page page) {
        LoadPageHelper.checkNotNull(page);
        if (!mPageMap.containsValue(page)) {
            final String pageName = page.getClass().getCanonicalName();
            mPageMap.put(pageName, page);
        }
    }

    public void addPages(Map<String, Page> map) {
        if (map != null && !map.isEmpty()) {
            mPageMap.putAll(map);
        }
    }

    public void showPage(@NonNull Class<? extends Page> pageClass) {
        LoadPageHelper.checkNotNull(pageClass);
        final Page page = getPageByClass(pageClass);
        if (page == null)
            throw new IllegalArgumentException("Page Not Config. " + pageClass);
        if (isMainThread()) {
            addViewSafe(page);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    addViewSafe(page);
                }
            });
        }
    }

    @CheckResult
    public Page getPageByClass(@NonNull Class<? extends Page> pageClass) {
        final String pageName = pageClass.getCanonicalName();
        return mPageMap.get(pageName);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private void addViewSafe(@NonNull Page page) {
        final View view = page.getView(getContext());
        if (view == null) {
            return;
        }
        if (mCurrentPage == page) {
            return;
        }
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (mCurrentPage != null) {
            mCurrentPage.onDetach();
        }
        final ViewParent parent = view.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        addView(view);
        mCurrentPage = page;
        mCurrentPage.onAttach();
    }
}
