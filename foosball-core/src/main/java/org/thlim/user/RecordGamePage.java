package org.thlim.user;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.thlim.EmptyPage;
import org.thlim.model.Game;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/24/12
 * Time: 10:41 AM
 *
 */

//@RequiresAuthentication
public class RecordGamePage extends EmptyPage
{
    public RecordGamePage()
    {
        getBody().setMarkupId("record-a-game");
        add(new FeedbackPanel("feedback"));

        Form recordGameForm = new Form<Game>("recordGameForm")
        {
            @Override
            protected void onSubmit()
            {

            }
        };
        add(recordGameForm);
    }
}
