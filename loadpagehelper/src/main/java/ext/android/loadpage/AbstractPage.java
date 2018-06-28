package ext.android.loadpage;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ROOT on 2017/9/29.
 */

public abstract class AbstractPage implements Page, Serializable {
    @LayoutRes
    private int mLayoutId;
    private View mView;

    public AbstractPage(@LayoutRes int layoutId) {
        this.mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(@NonNull Context context) {
        if (mView == null) {
            mView = LayoutInflater.from(context).inflate(this.mLayoutId, null);
            convert(mView);
        }
        return mView;
    }

    static Page clone(@NonNull Page page) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(page);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Page) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("clone Exception.", e);
        }
    }
}
