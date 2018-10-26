package com.example.demo.controller;

import com.example.demo.form.PersonForm;
import com.example.demo.model.PersonModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static List<PersonModel> persons = new ArrayList();

    static {
        persons.add(new PersonModel("Yurii", "Creator"));
        persons.add(new PersonModel("Vysylii", "Pupkin"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("persons", persons);
        return "personList";
}


    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {
        String result;
        String name = personForm.getName();
        String surname = personForm.getSurname();

        if (name != null && name.length() > 0 //
                && surname != null && surname.length() > 0) {
            PersonModel newPerson = new PersonModel(name, surname);
            persons.add(newPerson);
            result = "redirect:/";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            result = "addPerson";
        }
        return result;
    }


}
