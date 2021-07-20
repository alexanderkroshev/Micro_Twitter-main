package all.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


import java.util.List;

@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String findAll(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
        return "person/person-list";
    }

    @GetMapping("person-create")
    public String createPersonForm(Person person) {
       return "person/person-create";
    }

    @PostMapping("/person-create")
    public String createPerson(Person person){
        personService.savePerson(person);
        return "redirect:/persons";
    }

    @GetMapping("person-delete/{id}")
    public String deleteTweet(@PathVariable("id") Long id){
        personService.deleteById(id);
        return "redirect:/persons";
    }

}

