/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.threerings.gwt.ui.NumberTextBox;
import org.opevel.client.ClientFactory;
import org.opevel.client.service.UploadListingService;
import org.opevel.client.service.UploadListingServiceAsync;
import org.opevel.client.widgets.FileUploaderWidget;
import org.opevel.client.widgets.HeaderWidget;
import org.opevel.client.widgets.UtilHTML;
import org.opevel.shared.ListingsEntityDTO;
import org.opevel.shared.proxy.ListingsEntityProxy;
import org.opevel.shared.service.ListingsRequestFactory;
import org.opevel.shared.service.ListingsRequestFactory.ListingsEntityRequestContext;

/**
 *
 * @author Administrator
 */
public class UploadListingImpl extends Composite implements UploadListing, ValueChangeHandler<Object> {

    private FlowPanel uploadPanelWrapper = new FlowPanel();
    //Create form panel
    private FormPanel uploadListingFormPanel = new FormPanel();

    // Create a panel to hold all of the form widgets.
    private FlowPanel uploadListingPanelInner = new FlowPanel();
    
    private FlowPanel listingInfoPanel = new FlowPanel();
    private FlowPanel listingLocationPanel = new FlowPanel();

    private VerticalPanel listingInfoTableFormatter = new VerticalPanel();
    private VerticalPanel listingLocationTableFormatter = new VerticalPanel();

    private Label statusUpdatelbl = new Label();
    private Label historylbl = new Label();

    private TextBox title = new TextBox();
    private TextArea description = new TextArea();
    private NumberTextBox price = new NumberTextBox(true, 9, 9);
    private TextBox managedby = new TextBox();
    private RadioButton rentRadioButton = new RadioButton("ListingStatus", "Rent");
    private RadioButton saleRadioButton = new RadioButton("ListingStatus", "Sale", true);
    private TextBox address = new TextBox();
    private NumberTextBox latitude = new NumberTextBox(true, Integer.MAX_VALUE, Integer.SIZE);
    private NumberTextBox longitude = new NumberTextBox(true, Integer.MAX_VALUE, Integer.SIZE);
    private TextBox postalAddress = new TextBox();

    private Presenter presenter;
    private String uploadListingPlaceToken;
    public static UploadListingServiceAsync uploadListingService = GWT.create(UploadListingService.class);
    private ListingsEntityDTO listingsEntitydto;
    private ClientFactory clientFactory;

    /*ListingsRequestFactory rf = clientFactory.getRequestFactory();
    ListingsEntityRequestContext reqCtx = rf.listingsEntityRequest();
    ListingsEntityProxy newListings = reqCtx.create(ListingsEntityProxy.class);*/

    public UploadListingImpl() {
        initWidget(uploadPanelWrapper);

        uploadPanelWrapper.add(new HeaderWidget());
        uploadPanelWrapper.add(uploadListingFormPanel);
        //uploadPanelWrapper.add(new FileUploaderWidget());
        uploadPanelWrapper.setStyleName("gwt-uploadPanelWrapper");
        
        uploadListingFormPanel.setAction("/uploadListing");

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        uploadListingFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        uploadListingFormPanel.setMethod(FormPanel.METHOD_POST);

        uploadListingFormPanel.setWidget(uploadListingPanelInner);
        uploadListingFormPanel.setStyleName("gwt-uploadListingFormPanel");
        
        uploadListingPanelInner.add(new HTML("<h2 class='title'>Create New Listing</h2>"));
        uploadListingPanelInner.add(statusUpdatelbl);
        statusUpdatelbl.setVisible(false);
        statusUpdatelbl.setStyleName("uploadstatuslbl statuslbl");
        uploadListingPanelInner.setStyleName("gwt-uploadListingPanelInner");
        
        uploadListingPanelInner.add(listingInfoPanel);
        uploadListingPanelInner.add(listingLocationPanel);

        listingInfoPanel.add(listingInfoTableFormatter);

        listingInfoTableFormatter.add(new Label("Title"));
        listingInfoTableFormatter.add(title);
        listingInfoTableFormatter.add(new Label("Description"));
        listingInfoTableFormatter.add(description);
        listingInfoTableFormatter.add(new Label("Price"));
        listingInfoTableFormatter.add(price);
        listingInfoTableFormatter.add(new Label("Managed by"));
        listingInfoTableFormatter.add(managedby);
        listingInfoTableFormatter.add(new Label("Listing status"));
        listingInfoTableFormatter.add(rentRadioButton);
        listingInfoTableFormatter.add(saleRadioButton);
        
        listingInfoPanel.setStyleName("gwt-listingInfoPanel");

        listingLocationPanel.add(listingLocationTableFormatter);

        listingLocationTableFormatter.add(new Label("Address"));
        listingLocationTableFormatter.add(address);
        listingLocationTableFormatter.add(new Label("Latitude"));
        listingLocationTableFormatter.add(latitude);
        listingLocationTableFormatter.add(new Label("Longitude"));
        listingLocationTableFormatter.add(longitude);
        listingLocationTableFormatter.add(new Label("Postal Address"));
        listingLocationTableFormatter.add(new TextBox());

        listingLocationPanel.add(new FileUploaderWidget());
        
        listingLocationPanel.setStyleName("gwt-listingLocationPanel");

        uploadListingPanelInner.add(new HTML(new UtilHTML().setDivFloatFix()));

        /*uploadListingPanelInner.add(new HTML("<h2 class='title'>Create New Listing</h2>"));
        uploadListingPanelInner.add(statusUpdatelbl);
        uploadListingPanelInner.add(title);
        uploadListingPanelInner.add(description);
        uploadListingPanelInner.add(price);
        uploadListingPanelInner.setStyleName("gwt-uploadListingPanelInner");*/

        // Add a 'submit' button.
        uploadListingPanelInner.add(new Button("Submit", new ClickHandler() {
            @Override
          public void onClick(ClickEvent event) {
            uploadListingFormPanel.submit();
          }
        }));
        
        final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                //Window.alert("onSuccess succeeds");
                statusUpdatelbl.setVisible(true);
                statusUpdatelbl.setText("New Listing created successfully");
            }

            public void onFailure(Throwable caught) {
                //Window.alert("onFailure fails");
                statusUpdatelbl.setVisible(true);
                statusUpdatelbl.setText("Communication failed");
            }

        };

        // Add an event handler to the form.
        uploadListingFormPanel.addSubmitHandler(new FormPanel.SubmitHandler() {
          public void onSubmit(SubmitEvent event) {
            // This event is fired just before the form is submitted. We can take
            // this opportunity to perform validation.
              statusUpdatelbl.setVisible(true);
              statusUpdatelbl.setText("Uploading...");
              //uploadListingService.myMethod("I got it", callback);
              uploadListingService.createNewListing(setNewListingsData(), callback);
              //presenter.fireCreateNewListing(setNewListingsProxyData());

          }
        });
        uploadListingFormPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
          public void onSubmitComplete(SubmitCompleteEvent event) {
            // When the form submission is successfully completed, this event is
            // fired. Assuming the service returned a response of type text/html,
            // we can get the result text here (see the FormPanel documentation for
            // further explanation).
            //Window.alert(event.getResults());
          }
        });
    }


    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setUploadListingPlaceToken(String uploadListingPlaceToken) {
        this.uploadListingPlaceToken = uploadListingPlaceToken;
    }

    @Override
    public void onValueChange(ValueChangeEvent<Object> vce) {
        historylbl.setText("Current token is" + vce.getValue());
    }

    @Override
    public ListingsEntityDTO setNewListingsData() {
        listingsEntitydto = new ListingsEntityDTO();
        listingsEntitydto.setTitle(title.getValue());
        listingsEntitydto.setDescription(description.getValue());
        listingsEntitydto.setPrice((Double) price.getNumber());
        listingsEntitydto.setManagedby(managedby.getValue());
        listingsEntitydto.setAddress(address.getValue());
        listingsEntitydto.setLatitude(latitude.getNumber().floatValue());
        listingsEntitydto.setLongitude( longitude.getNumber().floatValue());
        return listingsEntitydto;
    }

    /*@Override
    public ListingsEntityProxy setNewListingsProxyData() {
        newListings.setTitle(title.getValue());
        newListings.setDescription(description.getValue());
        newListings.setPrice((Double) price.getNumber());
        newListings.setManagedby(managedby.getValue());
        newListings.setAddress(address.getValue());
        newListings.setLatitude((Float) latitude.getNumber());
        newListings.setLongitude((Float) longitude.getNumber());
        return newListings;
    }*/

}
