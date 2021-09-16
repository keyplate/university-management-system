package com.ums.service.entity;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.course.Course;
import com.ums.model.request.CourseRequest;
import com.ums.model.response.CourseResponse;
import com.ums.repository.CourseRepository;
import com.ums.repository.LecturerRepository;
import com.ums.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private LecturerRepository lecturerRepository;
    private SubjectRepository subjectRepository;

    public CourseService(CourseRepository courseRepository, LecturerRepository lecturerRepository, SubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public CourseResponse create(CourseRequest courseRequest) {
        validate(courseRequest);
        Course course = new Course();
        course.setLecturer(lecturerRepository.getById(courseRequest.getLecturerId()));
        course.setSubject(subjectRepository.getById(courseRequest.getSubjectId()));
        courseRepository.save(course);
        return CourseResponse.courseResponse(course);
    }

    @Transactional
    public void delete(long id) {
        if(!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course with this id doesn't exist " + id);
        }
        courseRepository.deleteById(id);
    }

    private void validate(CourseRequest courseRequest) {
        if(courseRepository.existsCourseByLecturerAndSubject(lecturerRepository.getById(courseRequest.getLecturerId()),
                subjectRepository.getById(courseRequest.getSubjectId()))) {
            throw new DuplicateEntityException("Course already exists");
        }
    }
}
