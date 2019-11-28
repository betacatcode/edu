package com.lab.edu.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author ruin
 * @date 2019/7/23-13:47
 */
@Component
public class ImageUtil {

    public String saveImage( MultipartFile img,String userName, String account){
        String[]imgNames=img.getOriginalFilename().split("\\.");
        String imgName=account+"-"+userName+"."+imgNames[1];

        String path="E:\\edu\\";
        File file=new File(path);
        String  pathFile = path + File.separator + imgName;
        File newFile=new File(pathFile);

        if(!file.exists())
            file.mkdirs();
        else {
            try {
                if(img.getSize()!=0)
                    img.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgName;
    }
}
