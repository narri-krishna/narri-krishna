package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.entity.Courses;
import com.springrest.springrest.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courserepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courserepository) {
		this.courserepository=courserepository;
	}

	@Override
	public List<Courses> getCourses() {
		return courserepository.findAll();
	}

	@Override
	public Courses getCourse(long courseId) {
		Optional<Courses> course = courserepository.findById(courseId);
		return course.orElse(null);
	}

	@Override
	public Courses addCourse(Courses courses) {
		courserepository.save(courses);
		return courses;
	}

	@Override
	public Courses updateCourse(Courses courses) {
		 Optional<Courses> existingCourse = courserepository.findById(courses.getId());
	        if (existingCourse.isPresent()) {
	            Courses updatedCourse = existingCourse.get();
	            updatedCourse.setTitle(courses.getTitle());
	            updatedCourse.setDescription(courses.getDescription());
	            return courserepository.save(updatedCourse);
	        } else {
	            // Handle the case where the course does not exist
	            return null;
	        }
	}

	@Override
	public void deleteCourse(long courseId) {
		Optional<Courses> entity = courserepository.findById(courseId);
		if (entity.isPresent()) {
			courserepository.delete(entity.get());
		}
	}
}