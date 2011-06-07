/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.shared;

import java.io.Serializable;

/**
 *
 * @author Kayode Odeyemi
 */
@SuppressWarnings("serial")
public class AgentRegisterDTO implements Serializable {

    private Long id;

    private String businessName;
    private String address;
    private String aboutCompany;
    private Long yearofestablishment;

    public AgentRegisterDTO() {
    }

    public AgentRegisterDTO(Long id, String businessName, String address, String aboutCompany, Long yearofestablishment) {
        this.id = id;
        this.businessName = businessName;
        this.address = address;
        this.aboutCompany = aboutCompany;
        this.yearofestablishment = yearofestablishment;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYearofestablishment() {
        return yearofestablishment;
    }

    public void setYearofestablishment(Long yearofestablishment) {
        this.yearofestablishment = yearofestablishment;
    }

}
