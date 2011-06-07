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
public class UsersDTO implements Serializable {
    private String type;
    private Integer uid;
    private String pass;
    private String email;
    private int created;
    private int access;
    private int login;
    private String role;
    private short status;
    private String timezone;

    public UsersDTO() {

    }

    public UsersDTO(Integer uid, String pass, int created, int access, int login, short status, String role, String type) {
        this.uid = uid;
        this.pass = pass;
        this.created = created;
        this.access = access;
        this.login = login;
        this.status = status;
        this.role = role;
        this.type = type;
    }

    public UsersDTO(String email, String password) {
        this();
        this.email = email;
        this.pass = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
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
}
