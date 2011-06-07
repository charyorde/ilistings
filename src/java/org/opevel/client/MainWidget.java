package org.opevel.client;

import com.google.gwt.dom.client.Style.Unit;
import org.opevel.client.widgets.HeaderWidget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Logger;
import org.opevel.client.place.AgentProfilePlace;
import org.opevel.client.place.UploadListingPlace;
import org.opevel.client.resources.ListingsResources;
import org.opevel.client.view.AgentProfileView;
import org.opevel.client.view.AgentProfileViewImpl;
import org.opevel.client.view.HomeView;
import org.opevel.client.widgets.ExploreTreeWidget;
import org.opevel.client.widgets.FooterWidget;
import org.opevel.client.widgets.IntroVideoWidget;
import org.opevel.client.widgets.SearchWidget;
import org.opevel.client.widgets.WelcomeWidget;

/**
 * 
 * @author dreyemi@gmail.com (Kayode Odeyemi)
 * 
 */
public final class MainWidget extends Composite implements HomeView {

    private Logger logger = Logger.getLogger(MainWidget.class.getName());

    private final DockLayoutPanel frontdlptop = new DockLayoutPanel(Unit.EM);
    private final DockLayoutPanel frontdlpcontent = new DockLayoutPanel(Unit.EM);
    private final FlowPanel panel = new FlowPanel();

    private Widget content;
    
    private AgentProfileView agentProfileView = new AgentProfileViewImpl();
    
    private HeaderWidget headerWidget = new HeaderWidget();
    private IntroVideoWidget introwidget = new IntroVideoWidget();
    private MainSignup frontsignupWidget = new MainSignup();
    private ExploreTreeWidget exploreTreeWidget = new ExploreTreeWidget();

    private ClientFactory clientFactory = new ClientFactoryImpl();

    private Presenter presenter;

    private AgentProfilePlace agentprofileplace = new AgentProfilePlace("profile");

    private String historyToken = clientFactory.getHistoryMapper().getToken(agentprofileplace);

    private Hyperlink agentProfilelink = new Hyperlink("View Profile", historyToken);

    public MainWidget() {

        initWidget(panel);
        panel.setStyleName("gwt-innerContainer");
        panel.add(headerWidget);
        frontdlptop.setStyleName("gwt-frontdlptop");
        frontdlptop.addWest(introwidget, 33);
        frontdlptop.addEast(frontsignupWidget, 25);
        frontdlptop.add(new WelcomeWidget());
        /*frontdlptop.setWidth("947px");*/
        frontdlptop.setHeight("300px");
        panel.add(frontdlptop);
        //panel.add(introwidget);
        //panel.add(frontsignupWidget);
        panel.add(exploreTreeWidget);
        panel.add(new FrontContentPanel());
        panel.add(new HTML("<div class='clear'></div>"));
        panel.add(new FooterWidget());
        /*frontdlpcontent.setStyleName("gwt-frontdlpcontent");
        frontdlpcontent.addWest(exploreTreeWidget, 20);
        frontdlpcontent.add(new FrontContentArea());
        frontdlpcontent.setHeight("300px");
        panel.add(frontdlpcontent);*/
        //setStyleName("main-wrapper");

        showAgentProfileView().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent ce) {
                logger.info(clientFactory.getHistoryMapper().getToken(agentprofileplace));
                //String historyToken = clientFactory.getHistoryMapper().getToken(agentprofileplace);
                clientFactory.getPlaceController().goTo(new AgentProfilePlace(historyToken));
            }
        });
        doSearch().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent ce) {
                Window.alert("search clicked");
            }
        });
    }

    @Override
    public void setToken(String historyToken) {
        this.historyToken = historyToken;
    }

    @Override
    public HasClickHandlers showAgentProfileView() {
        return agentProfilelink;
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
    }

    @Override
    public HasClickHandlers doSearch() {
        return new SearchWidget().getButton();
    }

    class FrontContentPanel extends Composite implements DoFrontContentAction {

        FlowPanel frontcontentpanel = new FlowPanel();
        //FlowPanel contentWrapper = new FlowPanel();
        FlexTable featureGrid = new FlexTable();
        //HTML slider = new HTML();

        String featureSliderfile = ListingsResources.INSTANCE.getFeatureSliderContent().getText();
        String uploadhtmlfile = ListingsResources.INSTANCE.getUploadFeatureContent().getText();
        String findhtmlfile = ListingsResources.INSTANCE.getFindFeatureContent().getText();
        String buyhtmlfile = ListingsResources.INSTANCE.getBuyFeatureContent().getText();
        String socializehtmlfile = ListingsResources.INSTANCE.getSocializeFeatureContent().getText();

        Button upload = new Button("Upload");

        final UploadListingPlace uploadListingPlace = new UploadListingPlace("uploadlisting");

        String uploadListingToken = clientFactory.getHistoryMapper().getToken(uploadListingPlace);
       
        public FrontContentPanel() {

            frontcontentpanel.setStyleName("gwt-FrontContentArea");
            frontcontentpanel.add(upload);

            frontcontentpanel.add(new HTML(featureSliderfile));
            featureGrid.setBorderWidth(1);
            featureGrid.setCellPadding(3);
            featureGrid.setCellSpacing(3);
            featureGrid.addStyleName("gwt-featureGrid");
            frontcontentpanel.add(featureGrid);

            //featureGrid.rem
            featureGrid.setHTML(0, 0, uploadhtmlfile);
            featureGrid.setHTML(0, 1, findhtmlfile);
             featureGrid.setWidget(1, 0, upload);
            featureGrid.setHTML(2, 0, buyhtmlfile);
            featureGrid.setHTML(2, 1, socializehtmlfile);

            
            int rowAtZero = featureGrid.getCellCount(0);
            //Window.alert(java.lang.Integer.toString(rowAtZero));
            //store rowAtZero into an int array
            //featureGrid.insertCells(0, 0, 0) ;
            //rowAtZero[0] = the cell I want. get the first cell in the row
            //if rowAtZero[0].length < -1
            /*if() {(String)
                featureGrid.insertCells(0, 0, 0) 
                featureGrid.add(upload);
            }*/
            
            FlexTable.FlexCellFormatter cf = featureGrid.getFlexCellFormatter();
            //cf.setRowSpan(0, 0, 1);
            //cf.addStyleName(0, 0, "sometablestylesheet");
            //featureGrid.insertCells(1, 1, 4);
            
            callUploadView().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent ce) {
                    logger.info("upload button called");
                    Window.alert("upload button called");
                    clientFactory.getPlaceController().goTo(new UploadListingPlace(uploadListingToken));


                }
            });

            initWidget(frontcontentpanel);
        }

        @Override
        public HasClickHandlers callUploadView() {
            return upload;
        }
    }
    
    /*class FeaturesCell extends HTMLTable {
        
        Grid featureGrid = new Grid(2, 2);

        public FeaturesCell() {
            
            featureGrid.setBorderWidth(1);
            featureGrid.setCellPadding(3);
            featureGrid.setCellSpacing(3);
            featureGrid.addStyleName("gwt-featureGrid");
            //frontcontentpanel.add(featureGrid);

            featureGrid.rem
            featureGrid.setHTML(0, 0, uploadhtmlfile);
            featureGrid.setHTML(0, 1, findhtmlfile);
            featureGrid.setHTML(1, 0, buyhtmlfile);
            featureGrid.setHTML(1, 1, socializehtmlfile);
            
            featureGrid.getCellCount(0);
            if() {
                featureGrid.add(upload);
                featureGrid.
            }
            
            //featureGrid.insertCells(1, 1, 4);

            frontcontentpanel.add(new HTML(uploadhtmlfile));
            frontcontentpanel.add(new HTML(findhtmlfile));
            frontcontentpanel.add(new HTML(buyhtmlfile));
            frontcontentpanel.add(new HTML(socializehtmlfile));
        }

        @Override
        public boolean remove(Widget widget) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Iterator<Widget> iterator() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getCellCount(int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getRowCount() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        protected void prepareCell(int i, int i1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        protected void prepareRow(int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        @Override
        protected void setCellFormatter(HTMLTable.CellFormatter cellFormatter) {
            
        }
        
    }*/
    /*@Override
	public void removeContent() {
        
		if (content != null) {
			panel.remove(content);
		}
	}

    @Override
	public void addContent(org.enunes.gwt.mvp.client.view.Display display) {
		removeContent();
		content = display.asWidget();
		panel.add(display.asWidget());
	}

    @Override
	public void addWidget(org.enunes.gwt.mvp.client.view.Display display) {
		panel.add(display.asWidget());
	}

    @Override
	public Widget asWidget() {
		return this;
	}

    @Override
    public AgentProfileView getAgentProfileView() {
        return agentProfileView;
    }*/


    /*@Override
    public void goTo(Place place) {
        this.place = place;
    }*/

}
