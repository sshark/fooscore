package org.thlim.util;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

/**
 *
 * User: Lim, Teck Hooi
 * Date: 2/28/12
 * Time: 5:00 PM
 *
 */
public class CommFunc
{
    static public Component modifyClassOnTargetError(final String clazzName, final Component source, final Component target)
    {
        source.add(new AttributeModifier("class", new Model<String>()
        {
            @Override
            public String getObject()
            {
                if (target.hasErrorMessage())
                {
                    return clazzName;
                }
                return "";
            }
        }));
        return source;
    }
}
