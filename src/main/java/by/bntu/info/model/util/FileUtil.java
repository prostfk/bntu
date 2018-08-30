package by.bntu.info.model.util;

import java.io.FileInputStream;

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
        for (String s : new String[]{".png", ".jpg", "jpeg", "css", "txt", "js"}) {
            if (path.endsWith(s)){
                return true;
            }
        }
        return false;
    }


}
