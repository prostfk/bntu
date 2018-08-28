package by.bntu.info.controller;

import by.bntu.info.model.entity.Faculty;
import by.bntu.info.model.entity.News;
import by.bntu.info.model.entity.Specialty;
import by.bntu.info.repository.FacultyRepository;
import by.bntu.info.repository.NewsRepository;
import by.bntu.info.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping(value = "/faculty/{id}/news")
    public ModelAndView getFacultyNews(@PathVariable Long id){
        List<News> newsByFacultyId = newsRepository.findNewsByFacultyId(id);
        return new ModelAndView("facultyNews", "newsList", newsByFacultyId);
    }

    @GetMapping(value = "/faculty/{id}")
    public ModelAndView getFacultyPage(@PathVariable Long id){
        return new ModelAndView("facultyPage", "path",String.format("/resources/files/facultyLogo/%d.jpg", id));
    }

    @GetMapping(value = "/faculty/specialities/{id}")
    public ModelAndView getSpecialties(@PathVariable Long id){
        List<Specialty> specialtiesByFacultyId = specialtyRepository.findSpecialtiesByFacultyId(id);
        return new ModelAndView("specialties", "specialties", specialtiesByFacultyId);
    }

    @GetMapping(value = "/faculty/{id}/getTimeTable/{filename}")
    public void download(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        String filepath = String.format("/resources/files/timetables/%s/%s", id,filename);
        String dataDirectory = request.getServletContext().getRealPath(filepath);
        Path file = Paths.get(dataDirectory, filename);
        if (Files.exists(file)) {
            response.setContentType(URLConnection.guessContentTypeFromName(filename));
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
