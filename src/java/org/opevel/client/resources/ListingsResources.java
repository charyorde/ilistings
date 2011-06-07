/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

/**
 *
 * @author Administrator
 */
public interface ListingsResources extends ClientBundle {

    public static final ListingsResources INSTANCE =  GWT.create(ListingsResources.class);

    @Source("gwt.css")
    public CssResource css();

    @Source("upload.xhtml")
    public TextResource getUploadFeatureContent();

    @Source("find.xhtml")
    public TextResource getFindFeatureContent();

    @Source("buy.xhtml")
    public TextResource getBuyFeatureContent();

    @Source("socialize.xhtml")
    public TextResource getSocializeFeatureContent();

    @Source("featureSlider.xhtml")
    public TextResource getFeatureSliderContent();

    @Source("welcome.xhtml")
    public TextResource getWelcomeContent();

}
