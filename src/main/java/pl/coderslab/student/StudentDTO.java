package pl.coderslab.student;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.student.Student;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String indexNumber;
    private double averageGrade;

    public StudentDTO(){

    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.indexNumber = student.getIndexNumber();
        this.averageGrade = student.getAverageGrade();
    }
}
