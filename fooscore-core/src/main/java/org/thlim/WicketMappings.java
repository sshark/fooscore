package org.thlim;

import fiftyfive.wicket.js.MergedJavaScriptBuilder;
import fiftyfive.wicket.mapper.PatternMountedMapper;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.CompoundRequestMapper;
import org.thlim.admin.AdministrationPage;
import org.thlim.error.ForbiddenErrorPage;
import org.thlim.error.InternalServerErrorPage;
import org.thlim.error.NotFoundErrorPage;
import org.thlim.user.RecordGamePage;
import org.thlim.user.RegisterPlayerPage;


/**
 * All custom mappings (in other words, "mount points" or "routes")
 * for foosball-score.
 * This includes merged JavaScript and pretty URLs for all bookmarkable pages.
 */
public class WicketMappings extends CompoundRequestMapper
{
    public WicketMappings(WebApplication app)
    {
        // Pretty URLs for bookmarkable pages
        addPage("admin", AdministrationPage.class);
        addPage("error/403", ForbiddenErrorPage.class);
        addPage("error/404", NotFoundErrorPage.class);
        addPage("error/500", InternalServerErrorPage.class);
        addPage("/register_player", RegisterPlayerPage.class);
        addPage("/record_game", RecordGamePage.class);

        // Common JavaScript merged together and mapped to scripts/all.js
        add(new MergedJavaScriptBuilder()
            .setPath("/scripts/all.js")
            .addLibrary("55_utils")
            .addLibrary("dump")
            // .addLibrary("cookies")
            // .addLibrary("strftime")
            .addJQueryUI()
            .addLibrary("jquery.55_utils")
            // .addLibrary("jquery.ui.forminputplaceholdertext")
            .addAssociatedScript(EmptyPage.class)
            .addWicketAjaxLibraries()
            .buildRequestMapper(app));
    }

    private void addPage(String path, Class<? extends Page> page)
    {
        add(new PatternMountedMapper(path, page).setExact(true));
    }
}
