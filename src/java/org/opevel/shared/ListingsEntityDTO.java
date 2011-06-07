/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.shared;

import java.io.Serializable;

import org.opevel.server.domain.ListingFile;

/**
 *
 * @author Administrator
 */
public class ListingsEntityDTO implements Serializable {

    private Long id;

    private String title;
    private String description;
    private Double price;
    private String managedby;

    private Boolean status;
    private String address;
    private Float latitude;
    private Float longitude;
    /*private ListingFile listingfile;*/

    public ListingsEntityDTO() {
    }

    public ListingsEntityDTO(Long id, String title, String description, Double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
/*
    public ListingFile getListingfile() {
        return listingfile;
    }

    public void setListingfile(ListingFile listingfile) {
        this.listingfile = listingfile;
    }
*/
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getManagedby() {
        return managedby;
    }

    public void setManagedby(String managedby) {
        this.managedby = managedby;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
