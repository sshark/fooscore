package org.thlim.user;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.thlim.dao.PlayerDao;
import org.thlim.model.Player;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 2:05 PM
 *
 */
public class RegisterPlayerPage extends WebPage
{
    @SpringBean
    private PlayerDao dao;

    public RegisterPlayerPage()
    {
        Form newPlayerForm = new Form<Player>("newPlayerForm", new CompoundPropertyModel<Player>(new Player()))
        {
            @Override
            protected void onSubmit()
            {
                dao.save(getModelObject());
            }
        };
        add(newPlayerForm);

        newPlayerForm.add(new RequiredTextField("name"));
        newPlayerForm.add(new PasswordTextField("password").setRequired(true));
        newPlayerForm.add(new RequiredTextField("nick"));
        newPlayerForm.add(new RequiredTextField("email"));
    }
}
