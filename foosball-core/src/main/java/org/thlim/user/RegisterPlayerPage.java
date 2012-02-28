package org.thlim.user;

import java.util.Date;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.thlim.EmptyPage;
import org.thlim.dao.PlayerDao;
import org.thlim.model.Player;
import org.thlim.util.CommFunc;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 2:05 PM
 *
 */
public class RegisterPlayerPage extends EmptyPage
{
    @SpringBean
    private PlayerDao dao;

    private String verifyPassword;

    public RegisterPlayerPage()
    {
        getBody().setMarkupId("register-new-player");
        add(new FeedbackPanel("feedback"));

        FormComponent password = new PasswordTextField("password").setRequired(true);
        FormComponent passwordVerification = new PasswordTextField("verifyPassword", new PropertyModel<String>(this, "verifyPassword")).setRequired(true);
        Form newPlayerForm = new Form<Player>("newPlayerForm", new CompoundPropertyModel<Player>(new Player()))
        {
            @Override
            protected void onSubmit()
            {
                Player player = getModelObject();
                if (isNickEmailAvailable(this, player))
                {
                    player.setPassword(new Sha512Hash(player.getPassword()).toString());
                    dao.save(player.setDateCreated(new Date()));
                    setModelObject(new Player());
                }
            }
        };
        add(newPlayerForm);

        newPlayerForm.add(new EqualPasswordInputValidator(password, passwordVerification));

        newPlayerForm.add(password.setLabel(new Model("Password")));
        newPlayerForm.add(passwordVerification.setLabel(new Model("Verify Password")));
        Component nick = new RequiredTextField("nick").setLabel(new Model("Nick"));
        newPlayerForm.add(nick);
        Component email = new EmailTextField("email").setRequired(true).setLabel(new Model("Email"));
        newPlayerForm.add(email);

        newPlayerForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("emailLabel", "Email"), email));
        newPlayerForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("nickLabel", "Nick"), nick));
        newPlayerForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("passwordLabel", "Password"), password));
        newPlayerForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("verifyPasswordLabel", "Verify Password"), passwordVerification));
    }

    private boolean isNickEmailAvailable(Form<Player> form, Player player)
    {
        boolean isAvailable = true;
        if (dao.findByEmail(player.getEmail()) != null)
        {
            isAvailable = false;
            form.error(player.getEmail() + " is taken");
        }

        if (dao.findByNick(player.getNick()) != null)
        {
            isAvailable = false;
            form.error(player.getNick() + " is taken");
        }
        return isAvailable;
    }

    public String getVerifyPassword()
    {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword)
    {
        this.verifyPassword = verifyPassword;
    }
}
