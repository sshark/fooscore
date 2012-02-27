package org.thlim.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 2:11 PM
 *
 */

@Entity
public class Player implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String nick;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    private String description;

    public String getDescription()
    {
        return description;
    }

    public Player setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public Long getId()
    {
        return id;
    }

    public Player setId(Long id)
    {
        this.id = id;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public Player setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getNick()
    {
        return nick;
    }

    public Player setNick(String nick)
    {
        this.nick = nick;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public Player setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public Player setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public Player setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }
}
