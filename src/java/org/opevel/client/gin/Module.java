package org.opevel.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import org.opevel.client.MainSignup;
import org.opevel.client.MainWidget;
import org.opevel.client.presenter.AgentRegisterPresenter;
import org.opevel.client.presenter.AgentRegisterPresenterImpl;
import org.opevel.client.presenter.MainPresenter;
import org.opevel.client.presenter.MainPresenterImpl;
import org.opevel.client.presenter.MainSignupPresenter;
import org.opevel.client.presenter.MainSignupPresenterImpl;
import org.opevel.client.view.AgentRegisterView;

/**
 * 
 * @author dreyemi@gmail.com (Kayode Odeyemi)
 * 
 */
public class Module extends AbstractGinModule {

	@Override
	protected void configure() {

		install(new org.enunes.gwt.mvp.client.gin.Module());

		/*bind(IssueDisplayPresenter.class).to(IssueDisplayPresenterImpl.class);
		bind(IssueDisplayPresenter.Display.class).to(IssueDisplayWidget.class);

		bind(IssueEditPresenter.class).to(IssueEditPresenterImpl.class);
		bind(IssueEditPresenter.Display.class).to(IssueEditWidget.class);*/

		bind(MainSignupPresenter.class).to(MainSignupPresenterImpl.class);
		bind(MainSignupPresenter.Display.class).to(MainSignup.class);

		bind(MainPresenter.class).to(MainPresenterImpl.class);
		//bind(MainPresenter.Display.class).to(MainWidget.class);

                bind(AgentRegisterPresenter.class).to(AgentRegisterPresenterImpl.class);
                bind(AgentRegisterPresenter.Display.class).to(AgentRegisterView.class);

	}

}
