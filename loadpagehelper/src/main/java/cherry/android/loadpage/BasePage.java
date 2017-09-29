package cherry.android.loadpage;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by ROOT on 2017/9/29.
 */

public abstract class BasePage implements Page {

    private Context mContext;
    @LayoutRes
    private int mLayoutId;

    public BasePage(@LayoutRes int layoutId) {
        this.mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView() {
        View view = View.inflate(this.mContext, this.mLayoutId, null);
        convert(view);
        return view;
    }
}
