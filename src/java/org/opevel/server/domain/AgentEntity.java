/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import java.io.Serializable;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.Extension;
/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class AgentEntity implements Serializable {

    /**
     * The JDO persistence manager used for all calls.
     */
    private final PersistenceManager manager = null;

    /**
     * An auto-generated primary key for this object. This key will be a child
     * key of the owning surfaces key.
     */
    /*@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;*/

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
  private String id;

    //private Long id;
    
    @Persistent
    private String businessName;

    @Persistent
    private String address;

    @Persistent
    private String aboutCompany;

    @Persistent
    private Long yearofestablishment;

    public AgentEntity() {

    }
    
    /*public AgentEntity(AgentRegisterDTO agentregisterDTO) {
        //setId(agentregisterDTO.getId());
        setAboutCompany(agentregisterDTO.getAboutCompany());
        setAddress(agentregisterDTO.getAddress());
        setBusinessName(agentregisterDTO.getBusinessName());
        setYearofestablishment(agentregisterDTO.getYearofestablishment());
    }*/

    /*public Key getKey() {
        return key;
    }

    public Long getId() {
        return key.getId();
    */
    public String getId() {
    return id;
  }
    
    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getYearofestablishment() {
        return yearofestablishment;
    }

    public void setYearofestablishment(Long yearofestablishment) {
        this.yearofestablishment = yearofestablishment;
    }


}
