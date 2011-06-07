/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PhoneNumber;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class BuyerEntity {

    /**
     * An auto-generated primary key for this object. This key will be a child
     * key of the owning surfaces key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String userName;

    @Persistent
    private String fullnames;

    @Persistent
    private String address;

    @Persistent
    private String aboutYou;

    @Persistent
    private PhoneNumber phone;

    public BuyerEntity() {
    }

    public Key getKey() {
        return key;
    }
    
    public String getAboutYou() {
        return aboutYou;
    }

    public void setAboutYou(String aboutYou) {
        this.aboutYou = aboutYou;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
