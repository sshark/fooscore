package org.thlim.error;

import org.apache.wicket.request.http.WebResponse;
import org.thlim.EmptyPage;

import static fiftyfive.wicket.util.Shortcuts.cssClass;

/**
 * Base class for custom error pages.
 */
public abstract class BaseErrorPage extends EmptyPage
{
    /**
     * Error pages are not bookmarkable, hence no PageParameters.
     */
    protected BaseErrorPage()
    {
        super(null);
        getBody().add(cssClass("server-error"));
    }

    /**
     * Subclasses must implement to provide the HTTP status error code.
     * For example, 404 for the {@link NotFoundErrorPage}.
     */
    protected abstract int getErrorCode();

    /**
     * Make sure we emit the proper HTTP status.
     */
    @Override
    protected void configureResponse(final WebResponse response)
    {
        super.configureResponse(response);
        response.setStatus(getErrorCode());
    }

    /**
     * Returns {@code true}.
     */
    @Override
    public boolean isErrorPage()
    {
        return true;
    }
}
