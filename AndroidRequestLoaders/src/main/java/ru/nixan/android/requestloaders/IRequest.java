package ru.nixan.android.requestloaders;

import android.accounts.Account;
import android.content.Context;

/**
 * Created by nixan on 11/26/13.
 */
public interface IRequest {

    /**
     * Executes the request. In case of any error in implemented protocol or actual implementation of network stack please throw an exception.
     * @param context
     * @throws Exception
     */
    public void execute(Context context, Account account) throws Exception;

    /**
     * Cancel the executing request
     */
    public void cancel();

    /**
     *
     * @return if the instance of this request class have already been executed
     */
    public boolean wasExecuted();

    /**
     * Set the result of the request
     * @param exception
     */
    public void setException(Exception exception);

    /**
     *
     * @return the exception that occured during excecution.
     */
    public Exception getException();

    /**
     *
     * @return if the request was succesfull
     */
    public boolean isSuccesfull();
}
