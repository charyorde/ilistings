/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import java.util.List;
import javax.jdo.annotations.Column;
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
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Column(name = "name")
    private String categoryName;

    @Persistent
    private List<ListingsType> typeName;

    public Key getKey() {
        return key;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ListingsType> getTypeName() {
        return typeName;
    }

    public void setTypeName(List<ListingsType> typeName) {
        this.typeName = typeName;
    }
    
}
