package pl.coderslab;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/first")
public class RandomController {
    @GetMapping("/random/{min}/{max}")
    public String random(@PathVariable int min, @PathVariable int max) {
        Random random = new Random();
        int next = random.nextInt(max - min + 1) + min;
        return "Użytkownik podał wartości min " + min + " oraz max: " + max + " Wylosowano liczbę: " + next;
    }

    @GetMapping("/hello/{firstName}/{lastName}")
    public String hello(@PathVariable String firstName, @PathVariable String lastName) {
        return "Witaj " + firstName + " " + lastName;
    }

    @PostMapping("/form")
    public String form(@RequestParam String paramName, @RequestParam LocalDate paramDate) {
        return paramName + " " + paramDate;
    }
}
