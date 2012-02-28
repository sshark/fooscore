package org.thlim.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.thlim.EmptyPage;
import org.thlim.dao.GameDao;
import org.thlim.dao.PlayerDao;
import org.thlim.dao.TeamDao;
import org.thlim.model.Game;
import org.thlim.model.Player;
import org.thlim.model.Team;
import org.thlim.util.CommFunc;

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

    @SpringBean
    private TeamDao teamDao;

    @SpringBean
    private GameDao gameDao;

    public RecordGamePage()
    {
        getBody().setMarkupId("record-a-game");
        add(new FeedbackPanel("feedback"));

        final List<Integer> scoresModel = Arrays.asList(new Integer[]
        {
            0, 1, 2 ,3, 4, 5
        });

        final IModel<Integer> durarionMinsModel = new Model<Integer>(0);
        final IModel<Integer> durarionSecondsModel = new Model<Integer>(0);

        final ListModel<Player> playersModel = new ListModel<Player>(playerDao.findAll());

        final ChoiceRenderer<Player> playerNameRender = new ChoiceRenderer<Player>("name");

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

        final DropDownChoice<Integer> whiteScore = new DropDownChoice<Integer>("whiteScore", new Model<Integer>(0), scoresModel);
        final DropDownChoice<Integer> blackScore = new DropDownChoice<Integer>("blackScore", new Model<Integer>(0), scoresModel);

        Form<Game> recordGameForm = new Form<Game>("recordGameForm", new CompoundPropertyModel<Game>(new Game()))
        {
            @Override
            protected void onSubmit()
            {
                if (whiteScore.getModelObject() == blackScore.getModelObject())
                {
                    error("The team scores cannot be the same");
                    return;
                }

                Game game = getModelObject();
                Team whiteTeam = new Team(Team.TeamColor.WHITE);
                Team blackTeam = new Team(Team.TeamColor.BLACK);

                if (Game.PlayerConfiguration.FourPlayers == game.getPlayersConf())
                {
                    whiteTeam.setDefender(whiteDefender.getModelObject());
                    blackTeam.setDefender(blackDefender.getModelObject());
                }
                whiteTeam.setAttacker(whiteAttacker.getModelObject());
                blackTeam.setAttacker(blackAttacker.getModelObject());

                if (whiteScore.getModelObject() > blackScore.getModelObject())
                {
                    whiteTeam.setWinner(true);
                }
                else
                {
                    blackTeam.setWinner(true);
                }

                teamDao.save(whiteTeam);
                teamDao.save(blackTeam);

                game.setWhite(whiteTeam);
                game.setBlack(blackTeam);

                game.setDuration(durarionMinsModel.getObject() * 60 + durarionSecondsModel.getObject());
                gameDao.save(game);

                setModelObject(new Game());
                whiteAttacker.setModelObject(null);
                blackAttacker.setModelObject(null);
                whiteDefender.setModelObject(null);
                blackDefender.setModelObject(null);
                whiteScore.setModelObject(0);
                blackScore.setModelObject(0);
                durarionMinsModel.setObject(0);
                durarionSecondsModel.setObject(0);
            }
        };
        add(recordGameForm);

        recordGameForm.add(whiteScore);
        recordGameForm.add(blackScore);

        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("whiteAttackerLabel", "Attacker"), whiteAttacker));
        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("whiteDefenderLabel", "Defender"), whiteDefender));
        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("blackAttackerLabel", "Attacker"), blackAttacker));
        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("blackDefenderLabel", "Defender"), blackDefender));

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

        recordGameForm.add(
            addLabelFor((FormComponent) whiteAttacker.setRequired(true).setOutputMarkupId(true), "White Team Attacker"));
        recordGameForm.add(
            addLabelFor((FormComponent) whiteDefender.setRequired(true).setOutputMarkupId(true), "White Team Defender"));
        recordGameForm.add(
            addLabelFor((FormComponent) blackAttacker.setRequired(true).setOutputMarkupId(true), "Black Team Attacker"));
        recordGameForm.add(
            addLabelFor((FormComponent) blackDefender.setRequired(true).setOutputMarkupId(true), "Black Team Defender"));

        final List<Game.PlayerConfiguration> playerConfigurations = new ArrayList<Game.PlayerConfiguration>();
        playerConfigurations.add(Game.PlayerConfiguration.FourPlayers);
        playerConfigurations.add(Game.PlayerConfiguration.TwoPlayers);

        final FormComponent<Game.PlayerConfiguration> playersConf =
            new RadioChoice<Game.PlayerConfiguration>("playersConf",
                playerConfigurations,
                new ChoiceRenderer<Game.PlayerConfiguration>("description"))
            {
                @Override
                protected boolean wantOnSelectionChangedNotifications()
                {
                    return true;
                }

                @Override
                protected void onSelectionChanged(Object newSelection)
                {
                    if (((Game.PlayerConfiguration) newSelection).equals(Game.PlayerConfiguration.TwoPlayers))
                    {
                        whiteDefender.setEnabled(false);
                        blackDefender.setEnabled(false);
                    }
                    else
                    {
                        whiteDefender.setEnabled(true);
                        blackDefender.setEnabled(true);
                    }
                }
            }
            .setRequired(true)
            .setLabel(new Model<String>("Players Configuration"));

        recordGameForm.add(playersConf);

        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts",
            new Label("playersConfLabel",
                "Players Configuration"),
            playersConf));

        final FormComponent<Integer> durationMins = new TextField<Integer>("durationMins", durarionMinsModel)
            .setType(Integer.class)
            .add(new MinimumValidator(0))
            .setLabel(new Model<String>("Minutes"));

        final FormComponent<Integer> durationSeconds = new TextField<Integer>("durationSeconds", durarionSecondsModel)
            .add(new RangeValidator<Integer>(0, 59))
            .setType(Integer.class)
            .setLabel(new Model<String>("Seconds"));
        recordGameForm.add(durationMins);
        recordGameForm.add(durationSeconds);
        recordGameForm.add(CommFunc.modifyClassOnTargetError("alerts", new Label("durationLabel", "Duration (mm:ss)"), durationMins));
        CommFunc.modifyClassOnTargetError("alerts", new Label("durationLabel", "Duration"), durationSeconds);

    }

    private FormComponent addLabelFor(FormComponent component, String label)
    {
        return component.setLabel(new Model<String>(label));
    }


}
