/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opevel.client.widgets;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.ModalUploadStatus;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

/**
 *
 * @author Administrator
 */
public class ProgressBarUploaderWidget extends Composite{

    private FlowPanel fp = new FlowPanel();

    // upload specific

    private Button submitBtn = new Button("Submit");
    //SingleUploader uploader = new SingleUploader(FileInputType.LABEL, new ModalUploadStatus(), submitBtn, uploadListingFormPanel);
    SingleUploader uploader = new SingleUploader(FileInputType.BROWSER_INPUT, new ModalUploadStatus());

    OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
        @Override
    public void onLoad(PreloadedImage img) {
      img.setWidth("75px");
      fp.add(img);
    }
  };

    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
        @Override
    public void onFinish(IUploader uploader) {
      if (uploader.getStatus() == Status.SUCCESS) {
        new PreloadedImage(uploader.fileUrl(), showImage);
      }
    }
  };

    public ProgressBarUploaderWidget() {

        initWidget(fp);
        fp.add(uploader);
    }


}
