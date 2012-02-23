package org.thlim.error;

public class NotFoundErrorPage extends BaseErrorPage
{
    /**
     * Returns 404.
     */
    protected int getErrorCode()
    {
        return 404;
    }
}
