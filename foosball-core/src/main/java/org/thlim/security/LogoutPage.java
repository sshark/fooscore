package org.thlim.security;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebSession;
import org.thlim.user.RegisterPlayerPage;

/**
 * A simple login page containing a {@link fiftyfive.wicket.shiro.markup.LoginForm} and {@link org.apache.wicket.markup.html.panel.FeedbackPanel}
 * that uses HTML5 markup. Customize the look and feel to meet the needs of your app.
 */
public class LogoutPage extends WebPage
{
    public LogoutPage()
    {
        SecurityUtils.getSubject().logout();
        WebSession.get().invalidate();
        setResponsePage(RegisterPlayerPage.class);
    }
}
