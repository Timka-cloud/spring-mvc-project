package kz.timka.controllers;

import kz.timka.dao.PersonDAO;
import kz.timka.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        List<Person> people = personDAO.findAll();
        model.addAttribute("people", people);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Person person = personDAO.getById(id);
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("createPersonObj", new Person()); // отдаем нового персона чтобы его наполнили на форме
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        // автоматно создасть обьект Person заполнить его поля сеттерами из html формы, также добавить его сразу в модель
//        Person p = new Person();
//        p.setId(1L);
//        p.setName("some name");
//        model.addAttribute("person", p); это все сделает за нас аннотация
        personDAO.save(person);
        return "redirect:/people"; // указали путь куда редирект сделать

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Person person = personDAO.getById(id);
        model.addAttribute("personEdit", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable Long id) {
        personDAO.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    @ModelAttribute("headerMessage") // если вешаем на метод аннотацию:
    //В модель в каждом методе текущего контроллера добавляет клюл-значение
    //Используются для добавления тех пар ключ-значение, которые нужны во всех моделях этого контроллера
    // Любая модель из этого контроллера по умолчанию будет иметь значение с ключом headerMessage
    // можно добавлять в модель и обьекты тоже
    public String populateHeaderMessage() {
        return "Welcome to our website!";
    }


}
