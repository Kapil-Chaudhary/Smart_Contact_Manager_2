package com.scm.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {

        // code likhna hai jo image ko upload kar rha ho

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data); // hamara data data main aa jayega
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", filename));
            return this.getUrlFromPublicId(filename);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // return kar rha hoga URL

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url()
                .transformation(
                        new Transformation<>()
                                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                                .crop(AppConstants.CONTACT_IMAGE_CROP)
                )
                .generate(publicId);
    }


}
