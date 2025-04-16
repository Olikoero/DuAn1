package org.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XImage {
    public static ImageIcon loadImage(String imagePath, JComponent component) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        int width = (int) (component.getWidth() * 0.6);
        int height =(int) (component.getHeight()*0.8);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public static Image getAppIcon(String file){
        URL url = XImage.class.getResource(file);
        if (url == null) {
            System.out.println("Không tìm thấy ảnh: " + file);
            return null;
        }
        return new ImageIcon(url).getImage();
    }
    public static ImageIcon loadIcon(String pathInJar) {
        URL url = XImage.class.getResource(pathInJar);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.out.println("Không tìm thấy ảnh: " + pathInJar);
            return null;
        }
    }
    public static void save(File src){
        File dst=new File("IMG",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdir();
        }
        try {
            Path from= Paths.get(src.getAbsolutePath());
            Path to= Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon read(String fileName){
        File path= new File("IMG",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
