package cherry.android.loadpage.sample.content;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cherry.android.loadpage.LoadPageHelper;
import cherry.android.loadpage.LoadPager;
import cherry.android.loadpage.OnRetryListener;
import cherry.android.loadpage.sample.R;
import cherry.android.loadpage.sample.page.EmptyPage;
import cherry.android.loadpage.sample.page.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private LoadPager loadPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        loadPager = LoadPageHelper.getDefault().wrap(rootView);
        return loadPager.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPager.showPage(EmptyPage.class);
            }
        }, 1000);
        EmptyPage emptyPage = loadPager.getPage(EmptyPage.class);
        emptyPage.setOnRetryListener(new OnRetryListener() {
            @Override
            public void onRetry(View view) {
                loadPager.showPage(LoadingPage.class);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadPager.show();
                    }
                }, 1000);
            }
        });
    }

}
