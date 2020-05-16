package com.wl.kmail.config.upload;

import com.wl.kmail.config.exception.MyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("upload")
@CrossOrigin
public class UploadController {

    @Value("${app.filePath}")
    private String filePath;

    @PostMapping("/addFile")
    @ResponseBody
    public Object addImage(@RequestParam(name = "headImg", required = false) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        //文件上传
        if (!file.isEmpty()) {
            try {
                //图片命名
//                String newCompanyImageName = "newPIC";
                String newCompanyImagepath = filePath + "img/" + file.getOriginalFilename();
                File newFile = new File(newCompanyImagepath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return MyResponse.error().msg("上传失败");
            }
        }
        return MyResponse.success(fileName).msg("上传成功");
    }



}
