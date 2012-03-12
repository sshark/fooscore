package org.thlim.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 26/2/12
 * Time: 10:40 PM
 *
 */

@Entity
public class Team implements Serializable
{
    public enum TeamColor {WHITE, BLACK}

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Player attacker;

    @OneToOne
    private Player defender;

    private Boolean winner;

    @Enumerated(EnumType.ORDINAL)
    private TeamColor teamColor;
    private int score;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    public Team() {}

    public Team(TeamColor teamColor)
    {
        this.teamColor = teamColor;
    }

    public Team(Player attacker, Player defender, TeamColor teamColor)
    {
        this(attacker, defender, teamColor, false);
    }

    public Team(Player attacker, Player defender, TeamColor teamColor, Boolean winner)
    {
        this.attacker = attacker;
        this.defender = defender;
        this.teamColor = teamColor;
        this.winner = winner;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public TeamColor getTeamColor()
    {
        return teamColor;
    }

    public void setTeamColor(TeamColor teamColor)
    {
        this.teamColor = teamColor;
    }

    public Boolean isWinner()
    {
        return (winner == null) ? Boolean.FALSE : winner;
    }

    public Boolean getWinner()
    {
        return winner;
    }

    public void setWinner(Boolean winner)
    {
        this.winner = winner;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Player getAttacker()
    {
        return attacker;
    }

    public void setAttacker(Player attacker)
    {
        this.attacker = attacker;
    }

    public Player getDefender()
    {
        return defender;
    }

    public void setDefender(Player defender)
    {
        this.defender = defender;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team)o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
