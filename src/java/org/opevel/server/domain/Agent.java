/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.opevel.shared.AgentRegisterDTO;

/**
 *
 * @author Administrator
 */
@Entity
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String businessName;
    private String address;
    private String aboutCompany;
    private Long yearofestablishment;

    public Agent() {
    }

    public Agent(AgentRegisterDTO agentregisterDTO) {
        setId(agentregisterDTO.getId());
        setAboutCompany(agentregisterDTO.getAboutCompany());
        setAddress(agentregisterDTO.getAddress());
        setBusinessName(agentregisterDTO.getBusinessName());
        setYearofestablishment(agentregisterDTO.getYearofestablishment());
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opevel.server.domain.Agent[id=" + id + "]";
    }

}
