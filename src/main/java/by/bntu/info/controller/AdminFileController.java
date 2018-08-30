package by.bntu.info.controller;

import by.bntu.info.model.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping(value = "/admin")
public class AdminFileController {

    private final String RESOURCES = "src/main/webapp/resources/";

    @GetMapping(value = "/files")
    public ModelAndView getFiles() {
        File folder = new File(RESOURCES);
        File[] files = folder.listFiles();
        return new ModelAndView("adminFiles", "files", files);
    }

    @GetMapping(value = "/files/{otherPath}/")
    public ModelAndView searchFiles(@PathVariable("otherPath") String path) {
        File file = new File(String.format("%s/%s", RESOURCES, path));
        ModelAndView modelAndView = new ModelAndView("adminFiles", "files", file.listFiles());
        modelAndView.addObject("currentFile", path);
        return modelAndView;
    }

    @GetMapping(value = "/files/{path}/{file}.{format}")
    @ResponseBody
    public ModelAndView check(@PathVariable String path, @PathVariable String file, @PathVariable String format) {
        if (format.endsWith("txt") || format.endsWith("js") || format.endsWith("css")) {
            file = String.format("%s.%s", file, format);
            ModelAndView modelAndView = new ModelAndView("adminReadFile", "filename", file);
            modelAndView.addObject("text", FileUtil.readFromFile(String.format("%s/%s/%s", RESOURCES, path, file)));
            return modelAndView;
        }
        return null;
    }

    @GetMapping(value = "/files/{otherPath}/**")
    public ModelAndView getAll(@PathVariable String otherPath, HttpServletRequest request){
        String url = request.getRequestURL().toString();
        String correctPath = url.split("/admin/files/")[1];
        return searchFiles(correctPath);
    }


}
