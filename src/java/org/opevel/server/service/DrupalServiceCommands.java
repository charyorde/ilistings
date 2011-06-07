/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.server.service;

/**
 *
 * @author Administrator
 */
public enum DrupalServiceCommands {
    NodeSave {
            @Override
            public String toString() {
                return "node.save";
            }
        },
        NodeGet {
            @Override
            public String toString() {
                return "node.get";
            }
        },
        SystemConnect {
            @Override
            public String toString() {
                return "system.connect";
            }
        },
        UserLogout {
            @Override
            public String toString() {
                return "user.logout";
            }
        },
        UserLogin {
            @Override
            public String toString() {
                return "user.login";
            }
        },
        SaveUser {
            @Override
            public String toString() {
                return "user.save";
            }
        },
        FileSave {
            @Override
            public String toString() {
                return "file.save";
            }
        },
        NewComments {
            @Override
            public String toString() {
                return "test.count";
            }
        },
        ViewsGet {
            @Override
            public String toString() {
                return "views.get";
            }
        }

}
