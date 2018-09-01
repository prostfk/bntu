package by.bntu.info.controller;

import by.bntu.info.model.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        ModelAndView modelAndView = new ModelAndView("adminReadFile", "filename", file);
        if (format.equals("txt") || format.equals("js") || format.equals("css") || format.equals("json")) {
            file = String.format("%s.%s", file, format);
            modelAndView.addObject("text", FileUtil.readFromFile(String.format("%s/%s/%s", RESOURCES, path, file)));
            return modelAndView;
        } else if (format.equals("png") || format.equals("jpg") || format.equals("jpeg")) {
            file = String.format("%s.%s", file, format);
            System.out.println(path);
            modelAndView.addObject("picture", String.format("%s/%s/%s", "/resources/", path, file));
            return modelAndView;
        }
        return null;
    }

    @GetMapping(value = "/files/{otherPath}/**")
    public ModelAndView getAll(@PathVariable String otherPath, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (FileUtil.isFile(url)) {
            String[] details = url.split("/");
            String filename = details[details.length - 1];
            StringBuilder stringBuilder = new StringBuilder();
            details = url.split("/");
            details[0] = "";
            for (int i = 5; i < details.length - 1; i++) {
                if (details[i].endsWith(filename)) {
                    break;
                }
                stringBuilder.append(String.format("%s/", details[i]));
            }
            String[] split = filename.split("\\.");
            return check(stringBuilder.toString(), split[0], split[1]);
        } else {
            String correctPath = url.split("/admin/files/")[1];
            return searchFiles(correctPath);
        }
    }

    @PostMapping(value = "/files/upload")
    public String saveFile(MultipartFile file, HttpServletRequest request) {
        String url = request.getHeader("referer");
//        String url = request.getRequestURL().toString();
        String path = url.split("/files/")[1];
        FileUtil.save(file, path);
        return "redirect:/admin/files/" + path;
    }

    @PostMapping(value = "/files/remove")
    public String removeFile(HttpServletRequest request, @RequestParam String path){
        String url = request.getHeader("referer");
        String oldPath = url.split("/files/")[1];
        FileUtil.remove(path);
        return "redirect:/admin/files/" + oldPath;
    }
}
