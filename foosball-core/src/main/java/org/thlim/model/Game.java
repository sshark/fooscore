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
public class Game implements Serializable
{
    public enum PlayerConfiguration {
        TwoPlayers(2, "2 Players"), FourPlayers(4, "4 Players");

        private int quantity;
        private String description;

        PlayerConfiguration(int quantity, String description)
        {
            this.quantity = quantity;
            this.description = description;
        }

        @Override
        public String toString()
        {
            return description;
        }

        public String getDescription()
        {
            return description;
        }

        public int getQuantity()
        {
            return quantity;
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Team white;

    @OneToOne
    private Team black;

    private long durationMillis;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Enumerated(EnumType.ORDINAL)
    private PlayerConfiguration playersConf = PlayerConfiguration.FourPlayers;

    public Game()
    {
    }

    public Game(Team white, Team black)
    {
        this.white = white;
        this.black = black;
    }

    public PlayerConfiguration getPlayersConf()
    {
        return playersConf;
    }

    public void setPlayersConf(PlayerConfiguration playersConf)
    {
        this.playersConf = playersConf;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Team getWhite()
    {
        return white;
    }

    public void setWhite(Team white)
    {
        this.white = white;
    }

    public Team getBlack()
    {
        return black;
    }

    public void setBlack(Team black)
    {
        this.black = black;
    }

    public long getDurationMillis()
    {
        return durationMillis;
    }

    public void setDuration(long durationMillis)
    {
        this.durationMillis = durationMillis;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game)o;

        if (id != null ? !id.equals(game.id) : game.id != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
