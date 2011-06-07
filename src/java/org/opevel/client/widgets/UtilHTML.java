/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 * @author Administrator
 */
public class UtilHTML extends HTML {

    private String clearfix;
    
    public UtilHTML() {
        //initWidget(this);
    }
    
    public UtilHTML(String clearfix) {
        this.clearfix = clearfix;
    }

    public String getClearfix() {
        return clearfix;
    }

    public void setClearfix(String clearfix) {
        this.clearfix = clearfix;
    }
    
    public String setDivFloatFix() {
        return "<div class='clearfix'></div>";
    }

    
}
