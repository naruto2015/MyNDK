package com.gds.materialdesign.model;

import com.gds.materialdesign.modelimpl.ImageModelImpl;

/**
 */
public interface ImageModel {
    void loadImageList(ImageModelImpl.OnLoadImageListListener listener);
}
