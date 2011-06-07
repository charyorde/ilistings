/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.domain;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author Kayode Odeyemi
 */
@SuppressWarnings("serial")
//@PersistenceCapable(identityType = IdentityType.APPLICATION, objectIdClass=UserEntity.class, detachable = "true")
//@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserEntity implements Serializable {

    /*@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;*/

    @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
  private String id;
    
    /*@Persistent(primaryKey="uid")
    private Integer uid;*/
    @Persistent
    private String userid;
    @Persistent
    private String nickname;
    @Persistent
    private String pass;
    @Persistent
    private String email;
    @Persistent
    private Date created;
    @NotPersistent
    private int access;
    @NotPersistent
    private int login;
    @NotPersistent
    private short status;
    @NotPersistent
    private String timezone;
    @NotPersistent
    private String role;
    @Persistent
    private String type;

    @Persistent
    private User user;

    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    /*public Key getKey() {
        return key;
    }*/

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }*/

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opevel.dao.Users[uid=" + uid + "]";
    }*/
}
