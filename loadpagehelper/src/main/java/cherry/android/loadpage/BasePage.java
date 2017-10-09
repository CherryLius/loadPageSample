package cherry.android.loadpage;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by ROOT on 2017/10/9.
 */

public class BasePage extends AbstractPage {
    private OnRetryListener mListener;

    public BasePage(@LayoutRes int layoutId) {
        super(layoutId);
    }

    @Override
    public void convert(@NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onRetry(view);
            }
        });
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.mListener = listener;
    }
}
