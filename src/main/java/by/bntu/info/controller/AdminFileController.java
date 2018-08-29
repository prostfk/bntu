package by.bntu.info.controller;

import by.bntu.info.model.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
@RequestMapping(value = "/admin")
public class AdminFileController {

    private final String RESOURCES = "src/main/webapp/resources/";

    @GetMapping(value = "/files")
    public ModelAndView getFiles(){
        File folder = new File(RESOURCES);
        File[] files = folder.listFiles();
        return new ModelAndView("adminFiles", "files", files);
    }

    @GetMapping(value = "/files/{otherPath}")
    public ModelAndView searchFiles(@PathVariable("otherPath") String path){
        File file = new File(String.format("%s/%s", RESOURCES, path));
        if (file.isDirectory()){
            ModelAndView modelAndView = new ModelAndView("adminFiles", "files", file.listFiles());
            modelAndView.addObject("currentFile", path);
            return modelAndView;
        }else{
            if (file.getName().endsWith("txt")||file.getName().endsWith("js")||file.getName().endsWith("css")){
                ModelAndView modelAndView = new ModelAndView("adminReadFile", "filename", file.getName());
                modelAndView.addObject("text", FileUtil.readFromFile(String.format("%s/%s", RESOURCES, path)));
                return modelAndView;
            }
            return null;
        }
    }

}
