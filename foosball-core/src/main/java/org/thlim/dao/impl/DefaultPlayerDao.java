package org.thlim.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.thlim.dao.PlayerDao;
import org.thlim.model.Player;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/23/12
 * Time: 3:30 PM
 *
 */
public class DefaultPlayerDao extends AbstractHibernateDaoImpl<Player> implements PlayerDao
{
    @Override
    @Transactional
    public Player findByName(String name)
    {
        return (Player) getCurrentSession().createCriteria(Player.class)
            .add(Restrictions.eq("name", name))
            .uniqueResult();
    }

    @Override
    @Transactional
    public Player findByNick(String nick)
    {
        return (Player) getCurrentSession().createCriteria(Player.class)
            .add(Restrictions.eq("nick", nick))
            .uniqueResult();
    }

    @Override
    @Transactional
    public Player findByEmail(String email)
    {
        return (Player) getCurrentSession().createCriteria(Player.class)
            .add(Restrictions.eq("email", email))
            .uniqueResult();
    }

    @Override
    @Transactional
    public List<Player> findByNameNickOrEmail(String name, String nick, String email)
    {
        return (List<Player>) getCurrentSession().createCriteria(Player.class)
            .add(Restrictions.or(
                Restrictions.or(
                    Restrictions.eq("name", name), Restrictions.eq("email", email)),
                Restrictions.eq("nick", nick)))
            .list();
    }
}
