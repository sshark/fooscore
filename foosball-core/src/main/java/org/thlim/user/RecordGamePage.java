package org.thlim.user;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.thlim.EmptyPage;
import org.thlim.dao.PlayerDao;
import org.thlim.model.Game;
import org.thlim.model.Player;

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
    @SpringBean
    private PlayerDao playerDao;

    public RecordGamePage()
    {
        getBody().setMarkupId("record-a-game");
        add(new FeedbackPanel("feedback"));

        final List<Integer> scoresModel = Arrays.asList(new Integer[]
        {
            0, 1, 2 ,3, 4, 5
        });

        Form recordGameForm = new Form<Game>("recordGameForm", new Model<Game>())
        {
            @Override
            protected void onSubmit()
            {

            }
        };
        add(recordGameForm);

        final ListModel<Player> playersModel = new ListModel<Player>(playerDao.findAll());


        final DropDownChoice<Integer> whiteScore = new DropDownChoice<Integer>("whiteScore", new Model<Integer>(0), scoresModel);
        final DropDownChoice<Integer> blackScore = new DropDownChoice<Integer>("blackScore", new Model<Integer>(0), scoresModel);

        recordGameForm.add(whiteScore);
        recordGameForm.add(blackScore);

        ChoiceRenderer<Player> playerNameRender = new ChoiceRenderer<Player>("name");

        final DropDownChoice<Player> whiteAttacker =
            new DropDownChoice<Player>("whiteAttacker",
                new Model<Player>(), playersModel, playerNameRender);
        final DropDownChoice<Player> blackAttacker =
            new DropDownChoice<Player>("blackAttacker",
                new Model<Player>(), playersModel, playerNameRender);
        final DropDownChoice<Player> whiteDefender =
            new DropDownChoice<Player>("whiteDefender",
                new Model<Player>(), playersModel, playerNameRender);
        final DropDownChoice<Player> blackDefender =
            new DropDownChoice<Player>("blackDefender",
                new Model<Player>(), playersModel, playerNameRender);

        recordGameForm.add(new IFormValidator()
        {
            @Override
            public FormComponent<?>[] getDependentFormComponents()
            {
                return new FormComponent<?>[]
                {
                    whiteAttacker, whiteDefender, blackAttacker, blackDefender
                };
            }

            @Override
            public void validate(Form<?> form)
            {

            }
        });

        recordGameForm.add(addLabelFor(whiteAttacker.setRequired(true), "White Team Attacker"));
        recordGameForm.add(addLabelFor(whiteDefender.setRequired(true), "White Team Defender"));
        recordGameForm.add(addLabelFor(blackAttacker.setRequired(true), "Black Team Attacker"));
        recordGameForm.add(addLabelFor(blackDefender.setRequired(true), "Black Team Defender"));
        recordGameForm.add(new DateTextField("duration", new Model<Date>(), new PatternDateConverter("mm:ss", false)));
    }

    private FormComponent addLabelFor(FormComponent component, String label)
    {
        return component.setLabel(new Model<String>(label));
    }
}
