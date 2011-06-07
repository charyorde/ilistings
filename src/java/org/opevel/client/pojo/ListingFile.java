/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.pojo;


import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ListingFile implements IsSerializable {

  private String key;

  private String content_type;

  private Date creation;

  private String filename;

  private long size;

  /**
   * init contructor - nothing to do here.
   */
  public ListingFile() {
  }

  public void setKey(String name) {
    this.key = name;
  }

  public String getKey() {
    return key;
  }

  public void setContentType(String content_type) {
    this.content_type = content_type;
  }

  public String getContentType() {
    return content_type;
  }

  public void setCreation(Date creation) {
    this.creation = creation;
  }

  public Date getCreation() {
    return creation;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getSize() {
    return size;
  }

}
