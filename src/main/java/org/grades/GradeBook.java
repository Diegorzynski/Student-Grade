package org.grades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GradeBook {

    private static Logger log = LogManager.getLogger(GradeBook.class);

    private String student;

    private List<Subject> subjectList = new ArrayList<>();

    public GradeBook(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public boolean addSubject(String subject){
      if(!containsSubject(subject)){
          subjectList.add(new Subject(subject));
          log.trace("{} has been added successfully.", subject);
          return true;
      }
      log.warn("Subject {} has already been added.", subject);
        return false;
    }

    public Subject getSubject(String subject){
        if(containsSubject(subject)){
            return subjectList.stream().filter(subj -> subj.getSubject()
                    .equals(subject)).findFirst().get();
        }
        log.warn("Subject {} was not found", subject);
        return null;
    }

    public boolean addGrade(String subject,int semester,  double grade){
      if(containsSemesterGrade(subject, semester)){
          log.warn("The grade for this semester has already been added");
          return false;
      } else {
          log.trace("Added grade {} for the subject {} {} semester",grade, subject, semester);
          getSubject(subject).getSemesterGrades().put(semester, grade);
          return true;
      }
    }


    public double getGrade(String subject, int semester){
        if(containsSubject(subject) && containsSemesterGrade(subject, semester)){
            return getSubject(subject).getSemesterGrades().get(semester);
        }
        log.warn("Subject {} wast not found", subject);
        return 0.0;
    }

    public double getSubjectAverage(String subject){
        double finalGrade = 0.0;
        if(containsSubject(subject)) {
            Map<Integer, Double> map = getSubject(subject).getSemesterGrades();
            double grade = 0.0;
            int semesters = 0;
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                grade += entry.getValue();
                semesters++;
            }
            finalGrade = (semesters == 0.0) ? 0.0 :  grade / semesters;
            log.trace("Student {} average for {} is {}",getStudent(), subject,finalGrade );
            return finalGrade;
        }
        log.warn("Subject {} was not found", subject);
        return finalGrade;
    }

    public double getStudentAverage(){
        double gradeAllsubjects = 0.0;
        int subjects = 0;
        for(Subject sub : subjectList){
           gradeAllsubjects += getSubjectAverage(sub.getSubject());
           subjects++;
        }
        return gradeAllsubjects / subjects;
    }

    private boolean containsSubject(String subject){
        return subjectList.stream().filter(subj -> subj.getSubject().toLowerCase().
                equals(subject.toLowerCase())).count() > 0;
    }

    private boolean containsSemesterGrade(String subject, int semester){
        return getSubject(subject).getSemesterGrades().containsKey(semester);
    }
}
