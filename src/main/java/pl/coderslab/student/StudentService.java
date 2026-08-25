package pl.coderslab.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return new StudentDTO(student);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void update(Long id, String firstName, String lastName, double averageGrade) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null ) {
            return;
        }
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAverageGrade(averageGrade);
        studentRepository.save(student);
    }

    public boolean delete(Long id) {
        if (!studentRepository.existsById(id) ) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
}
