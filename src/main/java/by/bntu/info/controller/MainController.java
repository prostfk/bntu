package by.bntu.info.controller;

import by.bntu.info.model.entity.Faculty;
import by.bntu.info.model.entity.Specialty;
import by.bntu.info.repository.FacultyRepository;
import by.bntu.info.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping(value = "/")
    public String getIndex(){
        return "index";
    }

    @GetMapping(value = "/faculties")
    public ModelAndView getFaculties(){
        List<Faculty> all = facultyRepository.findAll();
        return new ModelAndView("faculties", "faculties", all);
    }

    @GetMapping(value = "/faculty/{id}")
    public ModelAndView getSpecialties(@PathVariable Long id){
        List<Specialty> specialtiesByFacultyId = specialtyRepository.findSpecialtiesByFacultyId(id);
        return new ModelAndView("specialties", "specialties", specialtiesByFacultyId);
    }

}
