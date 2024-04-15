package ruspringcourse.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruspringcourse.DAO.PersonDAO;
import ruspringcourse.DAO.PersonDAOArrayList;
import ruspringcourse.DAO.PersonDAOHibernate;
import ruspringcourse.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController( @Qualifier("personDAOHibernate") PersonDAO person) {
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
    public String newPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonByIndex(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id ) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
