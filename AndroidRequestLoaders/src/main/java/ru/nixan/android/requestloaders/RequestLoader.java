package ru.nixan.android.requestloaders;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import ru.nixan.android.requestloaders.IRequest;

/**
 * Created by nixan on 11/26/13.
 */
public class RequestLoader extends AsyncTaskLoader<IRequest> {

    private final IRequest mRequest;
    private final Account mAccount;

    public RequestLoader(Context context, IRequest request, Account account) {
        super(context);
        mRequest = request;
        mAccount = account;
    }

    @Override
    public IRequest loadInBackground() {
        try {
            mRequest.execute(getContext(), getAccount());
        } catch (Exception e) {
            mRequest.setException(e);
        }
        return mRequest;
    }

    public Account getAccount() {
        return mAccount;
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
