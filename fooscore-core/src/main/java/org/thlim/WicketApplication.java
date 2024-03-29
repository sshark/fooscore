package org.thlim;

import fiftyfive.wicket.js.JavaScriptDependencySettings;
import fiftyfive.wicket.shiro.ShiroWicketPlugin;
import fiftyfive.wicket.spring.FoundationSpringApplication;
import org.apache.wicket.Application;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thlim.error.InternalServerErrorPage;
import org.thlim.home.HomePage;
import org.thlim.security.LoginPage;


/**
 * Wicket framework configuration for foosball-score.
 */
public class WicketApplication extends FoundationSpringApplication
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WicketApplication.class);


    /**
     * Returns the instance of {@code WicketApplication} that is currently
     * running. This method only works inside a Wicket thread.
     */
    public static WicketApplication get()
    {
        return (WicketApplication) Application.get();
    }

    @Override
    public Class<HomePage> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    protected void init()
    {
        super.init();

        // Tell fiftyfive-wicket-js where to find custom JS libs for this app
        // (i.e. those that can be referenced via //= require "lib").
        // This corresponds to src/main/resources/.../scripts.
        // Note: we configure JavaScriptDependencySettings before using MergedJavaScriptBuilder.
        JavaScriptDependencySettings.get().addLibraryPath(WicketApplication.class, "scripts");

        // -- Configure pretty URL mappings ("routes") --
        // Note: merged JavaScript is also configured in WicketMappings.
        mount(new WicketMappings(this));

        // -- Configure resources --
        // Wicket will not allow you to use "../" to construct relative paths to resources
        // (e.g. new PackageResourceReference(MyPage.class, "../common/file.png"))
        // unless you specify a parentFolderPlaceholder. This is a security precaution.
        getResourceSettings().setParentFolderPlaceholder("$up$");

        // -- Configure error pages --
        getApplicationSettings().setPageExpiredErrorPage(getHomePage());
        getApplicationSettings().setInternalErrorPage(InternalServerErrorPage.class);

        // -- Shiro integration --
        new ShiroWicketPlugin().mountLoginPage("/login", LoginPage.class).install(this);

        // -- Configure custom request cycle handling --
        getRequestCycleListeners().add(new WicketRequestCycleListener());

        // -- Custom initialization goes here --

        LOGGER.info("Initialized!");
    }

    /**
     * Returns our custom {@link WicketSession}.
     */
    @Override
    public WicketSession newSession(Request request, Response response)
    {
        return new WicketSession(request);
    }
}
