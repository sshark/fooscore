package org.thlim.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    enum TeamColor {WHITE, BLACK}

    @Id
    @GeneratedValue
    private Long id;

    private Player attacker;
    private Player defender;
    private Boolean winner;
    private TeamColor color;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    public Team()
    {
    }

    public Team(Long id, Player attacker, Player defender, TeamColor color)
    {
        this(id, attacker, defender, color, false);
    }

    public Team(Long id, Player attacker, Player defender, TeamColor color, Boolean winner)
    {
        this.id = id;
        this.attacker = attacker;
        this.defender = defender;
        this.color = color;
        this.winner = winner;
    }

    public TeamColor getColor()
    {
        return color;
    }

    public void setColor(TeamColor color)
    {
        this.color = color;
    }

    public boolean isWinner()
    {
        return (winner == null) ? false : winner;
    }

    public void setWinner(boolean winner)
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
