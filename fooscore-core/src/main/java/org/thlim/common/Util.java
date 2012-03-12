package org.thlim.common;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/28/12
 * Time: 5:00 PM
 *
 */
public class Util
{
    static public Component modifyClassOnTargetError(final String clazzName, final Component target, final Component source)
    {
        target.add(new AttributeAppender("class", new Model<String>()
        {
            @Override
            public String getObject()
            {
                if (source.hasErrorMessage())
                {
                    return " " + clazzName;
                }
                return "";
            }
        }));
        return target;
    }
}
