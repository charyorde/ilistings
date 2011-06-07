/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PostalAddress;

import java.io.Serializable;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author Administrator
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class ListingsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String title;
    @Persistent
    private String description;
    @Persistent
    private Double price;

    @Persistent
    private String managedby;

    @Persistent
    private Boolean status;

    @Persistent
    private String address;

    @Persistent
    private Float latitude;

    @Persistent
    private Float longitude;

    @Persistent
    private BlobInfo blobinfo;

    @Persistent
    private Integer version = 0;

    @Persistent
    private String blobKeyString;
    //@Persistent
    //private List<ListingFile> listingfileList;
/*
    @Persistent
    private ListingFile listingfile;
*/
    public Key getkey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    
    public String getTitle() {
            return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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


    /*public BlobInfo getBlobinfo() {
        return .getBlobKey();
    }*/

    public void setBlobinfo(BlobInfo blobinfo) {
        this.blobinfo = blobinfo;
    }


    /*public List<ListingFile> getListingfileList() {
        return listingfileList;
    }

    public void setListingfile(List<ListingFile> listingfileList) {
        this.listingfileList = listingfileList;
    }*/
/*
    public ListingFile getListingfile() {
        return listingfile;
    }

    public void setListingfile(ListingFile listingfile) {
        this.listingfile = listingfile;
    }
*/
    public Integer getVersion()
	{
		return version++;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

    public String getBlobKeyString() {
        return blobKeyString;
    }

    public void setBlobKeyString(String blobKeyString) {
        this.blobKeyString = blobKeyString;
    }

    
}
