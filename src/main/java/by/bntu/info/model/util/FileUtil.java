package by.bntu.info.model.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    public static String readFromFile(String filename){
        try(var fis = new FileInputStream(filename)){
            StringBuilder sb = new StringBuilder();
            int i;
            while((i=fis.read())!=-1){
                sb.append((char)i);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "incorrect FileName";
    }

    public static boolean isFile(String path){
        for (String s : new String[]{".png", ".jpg", "jpeg", "css", "txt", "js", "json"}) {
            if (path.endsWith(s)){
                return true;
            }
        }
        return false;
    }

    public static void save(MultipartFile file,String path){
        String filePath = String.format("src/main/webapp/resources/%s/", path);
        File javaFile = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
            javaFile = new File(javaFile.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(javaFile));
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void remove(String path) {
        File file = new File(String.format("src/main/webapp/resources/%s", path));
        if (file.exists()||!file.isDirectory()){
            file.delete();
        }
    }
}
