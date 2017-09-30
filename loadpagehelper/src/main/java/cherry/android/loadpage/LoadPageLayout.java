package cherry.android.loadpage;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROOT on 2017/9/28.
 */

/*public*/ class LoadPageLayout extends FrameLayout {

    private List<Page> mPages = new ArrayList<>();

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
        if (!this.mPages.contains(page))
            this.mPages.add(page);
    }

    public void setPages(List<Page> pages) {
        if (pages != null)
            this.mPages.addAll(pages);
    }

    public void showPage(Class<? extends Page> pageClass) {
        final Page page = getPageByClass(pageClass);
        if (page == null)
            throw new IllegalArgumentException("Page Not Config. " + pageClass);
        if (getChildCount() > 0)
            removeAllViews();
        if (isMainThread()) {
            addView(page.getView());
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    addView(page.getView());
                }
            });
        }
    }

    public Page getPageByClass(Class<? extends Page> pageClass) {
        for (Page page : mPages) {
            if (page.getClass().equals(pageClass)) {
                return page;
            }
        }
        return null;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
