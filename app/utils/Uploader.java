package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import play.libs.Codec;
import play.libs.Files;
import sun.misc.BASE64Decoder;
import utils.enumvalue.FileUploadState;

/**
 * 文件上传辅助类
 *
 * @author willing
 */
public class Uploader {
	
	private String baseUrl = "";
    // 输出文件地址
    private String url = "";
    // 状态
    private String state = "";
    // 文件类型
    private String fileName="";
    private String type = "";
    // 原始文件名
    private String originalName = "";
    // 文件大小
//    private String size = "";
    private String title = "";
    // 保存路径
    private String savePath = "";
    // 文件允许格式
    private String[] allowFiles = {".rar", ".doc", "docx", ".ppt", "pptx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp", ".mp4", ".avi", ".flv"};
    // 文件大小限制，单位KB
//    private int maxSize = 10000;

    public Uploader(String baseUrl, String savePath) {
        this.baseUrl = baseUrl;
        this.savePath = savePath;
    }

    /**
     * 普通文件上传
     *
     * @param file
     */
    public void upload(File file) {
        if (file == null) {
            this.state = FileUploadState.NOFILE;
            return;
        }
        originalName = file.getName();
        this.type=getFileExt(originalName);
        if (!checkFileType(originalName)) {
            this.state = FileUploadState.TYPE;
            return ;
        }
        System.out.println();
        String uuid = Codec.UUID();
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.fileName=uuid + type;
        File saveFile = new File(savePath + uuid + type);
        this.state = FileUploadState.SUCCESS;//默认上传成功
        this.url = baseUrl + savePath + uuid + type;
        
        try {
            Files.copy(file, saveFile);
        } catch (Exception e) {
            this.state = FileUploadState.REQUEST;//文件保存失败
        }
    }
    
    /**
     * 接受并保存以base64格式上传的文件
     * @param content
     * @param sufix 
     */
     public void uploadBase64(String content,String sufix) {
         if (content == null) {
             this.state = FileUploadState.NOFILE;
         }
         this.type=sufix;
         BASE64Decoder decoder = new BASE64Decoder();
         String uuid = Codec.UUID();
         this.state = FileUploadState.SUCCESS;//默认保存成功
         this.url = baseUrl + uuid + type;
         try {
             File dir = new File(savePath);
             if (!dir.exists()) {
                 dir.mkdirs();
             }
             File outFile = new File(savePath + uuid + type);
             OutputStream ro = new FileOutputStream(outFile);
             byte[] b = decoder.decodeBuffer(content);
             for (int i = 0; i < b.length; ++i) {
                 if (b[i] < 0) {
                     b[i] += 256;
                 }
             }
             ro.write(b);
             ro.flush();
             ro.close();
         } catch (Exception e) {
             this.state = FileUploadState.REQUEST;//文件保存失败
         }
     }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String getUrl() {
        if(this.url.charAt(1)=='/') this.url=this.url.substring(1);
        return this.url;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }
    public String getType() {
        return this.type;
    }
    public String getOriginalName() {
        return this.originalName;
    }
    public String getFileName(){
        return this.fileName;
    }
}
