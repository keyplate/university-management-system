package com.ums.service;

import com.ums.model.entity.grade.Grade;
import com.ums.model.entity.student.Student;
import com.ums.model.request.GradeRequest;
import com.ums.model.request.UpdateGradeRequest;
import com.ums.model.response.GradeResponse;
import com.ums.repository.CourseRepository;
import com.ums.repository.GradeRepository;
import com.ums.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

@Service
public class GradeService {

    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public Page<GradeResponse> getStudentGrades(int studentId, Pageable pageable) {
        Student student = studentRepository.getById(studentId);
        return gradeRepository.findGradesByStudent(student, pageable).map(GradeResponse::gradeResponse);
    }

    @Transactional
    public GradeResponse create(GradeRequest gradeRequest) {
        Grade grade = new Grade();
        grade.setStudent(studentRepository.getById(gradeRequest.getStudentId()));
        grade.setCourse(courseRepository.getById(gradeRequest.getCourseId()));
        grade.setValue(gradeRequest.getValue());
        gradeRepository.save(grade);
        return GradeResponse.gradeResponse(grade);
    }

    @Transactional
    public GradeResponse update(long id, UpdateGradeRequest updateGradeResponse) {
        Grade grade = gradeRepository.getById(id);
        grade.setValue(updateGradeResponse.getValue());
        return GradeResponse.gradeResponse(grade);
    }

    @Transactional
    public void delete(long id) {
        if (!gradeRepository.existsById(id)) {
            throw new EntityNotFoundException("Grade with id " + id + "doesn't exist");
        }
        gradeRepository.getById(id);
    }
}
