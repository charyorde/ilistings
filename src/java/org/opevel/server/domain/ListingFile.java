/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.Extension;
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
public class ListingFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    private Long id;

    private String keystring;

    @Persistent
    private String title;

    @Persistent
    //@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
    private String imageType;

    @Persistent
    private Blob image;
    
    @Persistent
    private Long size;

    @Persistent
    private String content_type;

    @Persistent
    private Date creation;

    @Persistent
    private String filename;

    /*@Persistent
    private List<ListingFile> listingfile;

    public ListingFile() {
    }

    public List<ListingFile> getListingfile() {
        return listingfile;
    }

    public void setListingfile(List<ListingFile> listingfile) {
        this.listingfile = listingfile;
    }*/
    
    public Long getId() {
        return key.getId();
    }

    public Key getKey() {
        return key;
    }

    public void setKeyString(String key) {
        this.keystring = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        if (image == null) {
            return null;
        }

        return image.getBytes();
    }

    public void setImage(byte[] bytes) {
        this.image = new Blob(bytes);
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentTpe() {
        return content_type;
    }

    public void setContentType(String content_type) {
        this.content_type = content_type;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
