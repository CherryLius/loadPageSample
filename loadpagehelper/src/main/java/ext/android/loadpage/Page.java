package ext.android.loadpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by ROOT on 2017/9/29.
 */

public interface Page {
    @NonNull
    View getView(@NonNull Context context);

    void convert(@NonNull View view);

    void onAttach();

    void onDetach();
}
