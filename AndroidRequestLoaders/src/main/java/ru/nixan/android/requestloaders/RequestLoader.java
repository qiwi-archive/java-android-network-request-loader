package ru.nixan.android.requestloaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import ru.nixan.android.requestloaders.IRequest;

/**
 * Created by nixan on 11/26/13.
 */
public class RequestLoader extends AsyncTaskLoader<IRequest> {

    private final IRequest mRequest;

    public RequestLoader(Context context, IRequest request) {
        super(context);
        mRequest = request;
    }

    @Override
    public IRequest loadInBackground() {
        try {
            mRequest.execute(getContext());
        } catch (Exception e) {
            mRequest.setException(e);
        }
        return mRequest;
    }

    @Override
    public boolean cancelLoad() {
        mRequest.cancel();
        return super.cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        if (mRequest.wasExecuted()) deliverResult(mRequest);
        if (takeContentChanged() || !mRequest.wasExecuted()) forceLoad();
    }

    @Override
    public void deliverResult(IRequest data) {
        if (isStarted()) super.deliverResult(mRequest);
    }
}
