package ruspringcourse.config.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ruspringcourse.config.DAO.PersonDAO;
import ruspringcourse.config.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO person;

    public PeopleController(PersonDAO person) {
        this.person = person;
    }


    @GetMapping
    public String giveAllPeople(Model model) {

        model.addAttribute("people", person.getAllPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", person.getPersonByIndex(id));
        return "people/person";
    }
}
