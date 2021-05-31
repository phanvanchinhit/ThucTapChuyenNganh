/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.helper;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class ShareHelper {
    public static final Image APP_ICON;
    static{
    // Tải biểu tượng ứng dụng
    String file = "/image/fpt.png";
    APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
    }
    public static boolean saveLogo(File file){
    File dir = new File("src/poly/com/image");
    // Tạo thư mục nếu chưa tồn tại
    if(!dir.exists()){
    dir.mkdirs();
    }
    File newFile = new File(dir, file.getName());
    try {
    // Copy vào thư mục logos (đè nếu đã tồn tại)
    Path source = Paths.get(file.getAbsolutePath());
    Path destination = Paths.get(newFile.getAbsolutePath());
    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    return true;
    }
    catch (Exception ex) {
    return false;
    }
    }
    public static ImageIcon readLogo(String fileName){
    File path = new File("src/poly/com/image/", fileName);
    return new ImageIcon(path.getAbsolutePath());
    }
  
    
}
