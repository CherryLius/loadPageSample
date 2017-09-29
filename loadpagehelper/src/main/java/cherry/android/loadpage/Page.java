package cherry.android.loadpage;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by ROOT on 2017/9/29.
 */

public interface Page {
    @NonNull
    View getView();

    void convert(@NonNull View view);
}
