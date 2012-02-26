package org.thlim.dao;

import java.util.List;

import org.thlim.model.Player;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 3:30 PM
 *
 */
public interface PlayerDao extends Dao<Player>
{
    Player findByName(String name);
    Player findByNick(String nick);
    Player findByEmail(String email);
    List<Player> findByNameNickOrEmail(String name, String nick, String email);
}
