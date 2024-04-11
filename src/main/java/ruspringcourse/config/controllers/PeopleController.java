package ruspringcourse.config.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruspringcourse.config.DAO.PersonDAO;
import ruspringcourse.config.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO person) {
        this.personDAO = person;
    }


    @GetMapping
    public String giveAllPeople(Model model) {

        model.addAttribute("people", personDAO.getAllPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.getPersonByIndex(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String newPerson(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

/*    @GetMapping("/{id}/edit")
public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonByIndex(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }*/

}
