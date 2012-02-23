package org.thlim.dao.impl;

import org.thlim.model.Player;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 3:30 PM
 *
 */
public class DefaultPlayerDao extends AbstractHibernateDaoImpl<Player>
{
    public DefaultPlayerDao()
    {
        super(Player.class);
    }
}
