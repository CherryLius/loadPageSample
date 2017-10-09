package cherry.android.loadpage.sample.content;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.plus.PlusOneButton;

import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.LoadPager;
import cherry.android.loadpage.sample.R;
import cherry.android.loadpage.sample.page.LoadingPage;

/**
 * A fragment with a Google +1 button.
 */
public class PlusOneFragment extends Fragment {

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    private PlusOneButton mPlusOneButton;
    private LoadPager mLoadPager;


    public PlusOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        //Find the +1 button
        mPlusOneButton = view.findViewById(R.id.plus_one_button);
        mLoadPager = LoadPageHelper.getDefault().wrap(view);

        return mLoadPager.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadPager.show();
            }
        }, 1500);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


}
