package pl.coderslab.math;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @ResponseBody
    @GetMapping("/add/{a}/{b}")
    public String add(@PathVariable int a, @PathVariable int b) {
        return String.valueOf(mathService.add(a, b));
    }

    @ResponseBody
    @GetMapping("/sub/{a}/{b}")
    public String subtract(@PathVariable int a, @PathVariable int b) {
        return String.valueOf(mathService.subtract(a, b));
    }

    @ResponseBody
    @GetMapping("/fact/{a}")
    public String fact(@PathVariable int a) {
        return String.valueOf(mathService.factorial(a));
    }


    @ResponseBody
    @GetMapping("/div/{a}/{b}")
    public String div(@PathVariable int a, @PathVariable int b) {
        return String.valueOf(mathService.div(a, b));
    }

    @ResponseBody
    @GetMapping("/mult/{a}/{b}")
    public String mult(@PathVariable int a, @PathVariable int b) {
        return String.valueOf(mathService.mult(a, b));
    }
}
