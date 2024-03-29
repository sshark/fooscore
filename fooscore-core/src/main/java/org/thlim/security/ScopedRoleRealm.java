/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.thlim.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thlim.dao.PlayerDao;
import org.thlim.model.Player;

/**
 * The Spring/Hibernate sample application's one and only configured Apache Shiro Realm.
 *
 * <p>
 * Because a Realm is really just a security-specific DAO, we could have just made Hibernate calls
 * directly in the implementation and named it a 'HibernateRealm' or something similar.
 * </p>
 *
 * <p>
 * But we've decided to make the calls to the database using a UserDAO, since a DAO would be used in
 * other areas of a 'real' application in addition to here. We felt it better to use that same DAO
 * to show code re-use.
 * </p>
 */

@Component
public class ScopedRoleRealm extends AuthorizingRealm
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScopedRoleRealm.class);

    @Autowired
    private PlayerDao playerDao;

    public ScopedRoleRealm()
    {
        setName("ScopedRoleRealm"); // This name must match the name in the User class's getPrincipals()  method
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
        setCredentialsMatcher(matcher);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Player player = playerDao.findBy(token.getUsername(), new String(new Sha512Hash(token.getPassword()).toHex()));
        if (player != null)
        {
            return new SimpleAuthenticationInfo(player, player.getPassword(), getName());
        }
        return null;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        Long playerId = (Long) principals.fromRealm(getName()).iterator().next();
        Player player = playerDao.load(playerId);
        if (player != null)
        {
            LOGGER.info("Found user " + player.getEmail() + ", loading user's permissions.");
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            /*
            info.addRoles(player.getGrantedRoles());
            info.addStringPermissions(player.getGrantedPermissions());
            */
            return info;
        }
        return null;
    }
}
