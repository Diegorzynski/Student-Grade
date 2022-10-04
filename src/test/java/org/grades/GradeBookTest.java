package org.grades;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GradeBookTest {


    @Test
    public void addingNewSubject(){
        //Given
        GradeBook gradeBook= new GradeBook("Mario");
        //Then
        assertTrue(gradeBook.addSubject("History"));
        }

    @Test
    public void addingExistingSubject(){
        //Given
        GradeBook gradeBook= new GradeBook("Mario");
        //When
        gradeBook.addSubject("History");
        //Then
        assertFalse(gradeBook.addSubject("History"));
    }

    @Test
    public void getSubjectWhenPresent(){
        //Given
        GradeBook gradeBook= new GradeBook("Mario");
        //When
        gradeBook.addSubject("History");
        Subject expected = gradeBook.getSubject("History");
        //Then
        assertTrue(expected instanceof Subject);

    }

    @Test
    public void getSubjectWhenNotPresent(){
        //Given
        GradeBook gradeBook= new GradeBook("Mario");
        //When
        gradeBook.addSubject("History");
        //Then
        assertNull(gradeBook.getSubject("Geography"));

    }

    @Test
    public void addGradeNew(){
        //Given
        String subject  = "History";
        GradeBook gradeBook= new GradeBook("Mario");
        gradeBook.addSubject(subject);
        double before = gradeBook.getGrade(subject,1);
        //When
        gradeBook.addGrade(subject, 1,4.3);
        double after = gradeBook.getGrade(subject,1);
        //Then
        assertTrue(0.0 == before);
        assertTrue(4.3 == after);

    }
    @Test
    public void addGradeWhenExist(){
        //Given
        String subject  = "History";
        GradeBook gradeBook= new GradeBook("Mario");
        gradeBook.addSubject(subject);
        //When
        gradeBook.addGrade(subject, 1,4.3);
        //Then
        assertFalse(gradeBook.addGrade(subject,1, 4.3));
    }

    @Test
    public void getSubjectAverage(){
        //Given
        String subject = "History";
        GradeBook gradeBook= new GradeBook("Mario");
        gradeBook.addSubject(subject);
        double before = gradeBook.getSubjectAverage(subject);
        //When
        gradeBook.addGrade(subject, 1,4.0);
        gradeBook.addGrade(subject, 2,2.0);
        double average = gradeBook.getSubjectAverage(subject);
        //Then
        assertTrue(0.0 == before);
        assertTrue(average == 3.0);

    }

    @Test
    public void getStudentAverage(){
        //Given
        GradeBook gradeBook= new GradeBook("Mario");
        String subject1 = "History";
        String subject2 = "Geography";
        String subject3 = "Mathematics";
        gradeBook.addSubject(subject1);
        gradeBook.addSubject(subject2);
        gradeBook.addSubject(subject3);
        //When
        gradeBook.addGrade(subject1, 1,4.0);
        gradeBook.addGrade(subject2, 1,2.0);
        gradeBook.addGrade(subject3, 1,3.0);
        //Then
        assertTrue(3.0 == gradeBook.getStudentAverage());
    }

}