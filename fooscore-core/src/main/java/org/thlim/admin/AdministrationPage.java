package org.thlim.admin;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.thlim.BasePage;

/**
 * An example of a page that is secured via Shiro annotations.
 * This page can only be accessed by an authenticated user that has the "admin" role.
 */
@RequiresAuthentication
@RequiresRoles("admin")
public class AdministrationPage extends BasePage
{
    public AdministrationPage()
    {
        super();
    }
}
