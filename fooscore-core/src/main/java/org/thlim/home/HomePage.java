package org.thlim.home;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.thlim.BasePage;

/**
 * Home page for foosball-score.
 */
public class HomePage extends BasePage
{
    public HomePage(PageParameters parameters)
    {
        super(parameters);
        getBody().setMarkupId("home");
        // Add your components here
    }
}
