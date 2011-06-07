/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import org.opevel.shared.exceptions.RESTfulWebServiceException;

/**
 *
 * @author Administrator
 */
@RemoteServiceRelativePath("searchservice")
public interface SearchService extends RemoteService {
    public String myMethod(String s);

    public ArrayList<Object> searchListingsService();

    public Boolean login(String username, String password);

    public String serviceLogin(String username, String password) throws RESTfulWebServiceException;
}
