package pl.coderslab.student;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GetMapping("/add/{id}/{firstName}/{lastName}/{indexNumber}/{averageGrade}")
    public String add(@PathVariable String firstName,
                      @PathVariable Long id,
                      @PathVariable String lastName,
                      @PathVariable String averageGrade,
                      @PathVariable String indexNumber) {
        Student student = new Student();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAverageGrade(Double.parseDouble(averageGrade));
        student.setIndexNumber(indexNumber);
        studentRepository.save(student);
        return "ok ";
    }

    @GetMapping("/get/{id}")
    public StudentDTO get(@PathVariable Long id) {
        return studentService.findById(id);
    }
    @GetMapping("/all")
   public List<StudentDTO> all() {
        return studentService.findAll();
    }


    @GetMapping("/update/{id}/{firstName}/{lastName}/{averageGrade}")
    public String update(@PathVariable String firstName,
                     @PathVariable Long id,
                     @PathVariable String lastName,
                     @PathVariable double averageGrade) {
        studentService.update(id, firstName, lastName, averageGrade);
        return "zaktualizowano studenta o id: " + id;

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boolean deleted = studentService.delete(id);
        if (!deleted) {
            return "Nie znaleziono studenta o id: " + id;
        }
        studentService.delete(id);
        return "usunieto studenta o id: " + id;
    }

}
