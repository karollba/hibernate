package pl.coderslab.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.person.Person;
import pl.coderslab.person.PersonDao;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    // dodawanie
    @GetMapping("/add/{firstname}/{lastname}")
    public String add(@PathVariable String login, @PathVariable String password) {
        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        personDao.savePerson(person);
        return "ok";
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id) {
        Person person = personDao.findById(id);
        if (person == null) {
            return "Nie znaleziono person o id " + id;
        }
        return person.toString();
    }


    // edycja po id
    @GetMapping("/update/{id}/{firstname}/{lastname}")
    public String update(@PathVariable Long id, @PathVariable String login, @PathVariable String password) {
        Person person = personDao.findById(id);
        if (person == null) {
            return "Nie znaleziono person o id " + id;
        }
        person.setLogin(login);
        person.setPassword(password);
        personDao.update(person);

        return "Zaktualizowano: " + person.getLogin();
    }

    // usuwanie po id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Person person = personDao.findById(id);
        if (person == null) {
            return "Nie znaleziono autora o id " + id;
        }

        personDao.delete(person);
        return "Usunięto autora o id: " + id;
    }

}

