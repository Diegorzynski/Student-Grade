package org.grades;

import java.util.HashMap;
import java.util.Map;

public class Subject {

    private String subject;

    private Map<Integer, Double> semesterGrades = new HashMap<>();

    public Subject(String subject) {
        this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }

    public Map<Integer, Double> getSemesterGrades() {
        return semesterGrades;
    }
}
