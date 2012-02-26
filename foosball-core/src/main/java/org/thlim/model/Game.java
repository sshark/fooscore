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
public class Game implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;

    private Team white;
    private Team black;
    
    private long durationMillis;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    public Game()
    {
    }

    public Game(Team white, Team black)
    {
        this.white = white;
        this.black = black;
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

    public void setDurationMillis(long durationMillis)
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
}
