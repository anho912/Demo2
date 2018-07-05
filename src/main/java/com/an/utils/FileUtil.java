package com.an.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件处理
 * </p>
 */
public class FileUtil {

    /**
     * 上传文件大小
     */
    public static final int MAX_SIZE = 20;
    /**
     * 上传文件目录
     */
    //本地
    public static final String SAVEUPLOAD = "D:\\STS workspace\\Demo\\src\\main\\webapp\\upload";

    /**
     * <p>
     * 上传图片
     * </p>
     *
     * @param file
     * @param type
     * @return
     */
    public static Results uploadImage(MultipartFile file, int type) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 判断参数
        if (request == null) {
            return Results.error("请求非法");
        }
        if (file == null) {
            return Results.error("文件为空");
        }
        if (Arith.div(file.getSize(), 1024 * 1024, 2) > MAX_SIZE) {
            return Results.error("上传文件最大为" + MAX_SIZE + "MB");
        }   
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        List<String> array = Arrays.asList("gif", "jpg", "jpeg", "png", "bmp", "ico");
        boolean flag = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equalsIgnoreCase(fileSuffix)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return Results.error("文件后缀不支持");
        }
        // 保存文件
        File file2=new File(SAVEUPLOAD);
        if(!file2.exists() && !file2.isDirectory()) {
        	try {
				file2.mkdir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        File targetFile;
        if (type == 1) {
            targetFile = new File(SAVEUPLOAD, UUID.randomUUID().toString() + "." + fileSuffix);
            try {
                file.transferTo(targetFile);
                /**
                 * 保存名称
                 */
                // 返回数据
                return Results.ok("上传成功").put("name", targetFile.getName());
            } catch (Exception e) {
                e.printStackTrace();
                return Results.error("上传失败");
            }
        } else {
            targetFile = new File(SAVEUPLOAD, UUID.randomUUID().toString() + "." + fileSuffix);
            try {
                file.transferTo(targetFile);
                /**
                 * 保存名称
                 */
                // 返回数据
                return Results.ok("上传成功").put("name", targetFile.getName());
            } catch (Exception e) {
                e.printStackTrace();
                return Results.error("上传失败");
            }
        }
    }

    /**
     * <p>
     * 获取文件类型
     * </p>
     *
     * @param path
     * @return
     */
    public static String getFileMime(String path) {
        if (path == null || "".equals(path)) {
            return null;
        }
        Path source = Paths.get(path);
        try {
            return Files.probeContentType(source);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
