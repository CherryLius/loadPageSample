package cherry.android.loadpage;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ROOT on 2017/9/29.
 */

public abstract class BasePage implements Page {
    private Context mContext;
    @LayoutRes
    private int mLayoutId;

    public BasePage(@NonNull Context context, @LayoutRes int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView() {
        View view = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, null);
        convert(view);
        return view;
    }
}
