package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XImage {
    public static Image getAppIcon(){
        URL url=XImage.class.getResource("img/fpt.png");
        return new ImageIcon(url).getImage();
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
