/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.shared;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class UserEntityDTO implements Serializable {

    private Long id;

    private Integer uid;
    private String pass;
    private String email;
    private int created;
    private int access;
    private int login;
    private short status;
    private String timezone;
    private String role;
    private String type;
    private String userid;

    public UserEntityDTO() {
    }

    public UserEntityDTO(Long id, Integer uid, String pass, String email, String role, String type, String userid) {
        this.id = id;
        this.uid = uid;
        this.pass = pass;
        this.email = email;
        this.role = role;
        this.type = type;
        this.userid = userid;
    }
    

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
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

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
}
