package org.roo.inaction.model;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.transaction.annotation.Transactional;

@RooIntegrationTest(entity = Course.class)
public class CourseIntegrationTest {

	@Test
	public void testMarkerMethod() {
	}

	@Test
	@Transactional
	public void testAddAndFetchCourse() {
		Course c = new Course();
		c.setCourseType(CourseTypeEnum.CONTINUING_EDUCATION);
		c.setName("Stand-up Comedy");
		c.setDescription("You'll laugh, you'll cry, it will become a part of you.");
		c.setMaxiumumCapacity(10);
		c.setListPrice(BigDecimal.ONE);
		c.persist();

		c.flush();
		c.clear();

		Assert.assertNotNull(c.getId());

		Course c2 = Course.findCourse(c.getId());

		Assert.assertNotNull(c2);
		Assert.assertEquals(c.getName(), c2.getName());
		Assert.assertEquals(c2.getDescription(), c.getDescription());
		Assert.assertEquals(c.getMaxiumumCapacity(), c2.getMaxiumumCapacity());
		Assert.assertEquals(c.getCourseType(), c2.getCourseType());
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void testInvalidCourse() {
		Course c = new Course();
		c.persist();
	}

	@Test
	@Transactional
	public void testSpecificException() {
		Course c = new Course();
		c.setCourseType(CourseTypeEnum.CONTINUING_EDUCATION);
		c.setMaxiumumCapacity(10);
		c.setRunDate(new Date());
		c.setName(null);
		c.setDescription(null);
		try {
			c.persist();
		} catch (ConstraintViolationException cve) {
			Assert.assertEquals(2, cve.getConstraintViolations().size());
			Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations()
					.iterator();
			while (it.hasNext()) {
				ConstraintViolation<?> constraintViolation = it.next();
				ConstraintDescriptor<?> descriptor = constraintViolation
						.getConstraintDescriptor();
				Annotation annotation = descriptor.getAnnotation();
				if (!(annotation.annotationType().getName()
						.equals("javax.validation.constraints.NotNull")))
					Assert.fail("invalid error raised.  Should be 'not null'");
			}

			return;
		} catch (Exception e) {
			Assert.fail("Unexpected exception thrown " + e.getMessage());
			return;
		}
	}

	@Test
	@Transactional
	public void testInvalidPrice() {
		try {
			CourseDataOnDemand cDod = new CourseDataOnDemand();
			Course course = cDod.getNewTransientCourse(0);
			course.setListPrice(new BigDecimal("2234.01"));
			course.persist();
			course.flush();
			// return;
		} catch (ConstraintViolationException ve) {
			Iterator<ConstraintViolation<?>> violationsIterator = ve
					.getConstraintViolations().iterator();
			while (violationsIterator.hasNext()) {
				ConstraintViolation<?> violation = violationsIterator.next();
				String messageTemplate = violation.getMessageTemplate();
				if (messageTemplate.startsWith("Price is invalid")) {
					return;
				}
			}
		}
		Assert.fail("This test did not throw an invalid price exception.");
	}

	@Test
	@Transactional
	public void testFindByNameFinder() {
		Course c = new Course();
		c.setName("Basket Weaving");
		c.setCourseType(CourseTypeEnum.SEMINAR);
		c.setDescription("Weaving baskets is an essential skill.");
		c.setMaxiumumCapacity(100);
		c.setRunDate(new Date());
		c.setListPrice(BigDecimal.ONE);
		c.persist();
		c.flush();
		Course.entityManager().clear();
		List<Course> courses = Course.findCoursesByNameLike("Bas")
				.getResultList();
		Assert.assertEquals(1, courses.size());
	}

	@Test
	@Transactional
	@Ignore(value = "Ignore for not able to configure the right test")
	public void testFindCoursesByCourseTypeAndRunDateBetween() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date minRunDate = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		Date maxRunDate = calendar.getTime();

		CourseDataOnDemand courseDataOnDemand = new CourseDataOnDemand();
		Course course = courseDataOnDemand.getRandomCourse();
		CourseTypeEnum courseType = course.getCourseType();

		course.setRunDate(new Date());

		course.persist();
		course.flush();
		Course.entityManager().clear();

		List<Course> courses = Course.findCoursesByCourseTypeAndRunDateBetween(
				courseType, minRunDate, maxRunDate).getResultList();

		Assert.assertEquals(1, courses.size());

	}

	@Test
	public void testPersistTagsInCourses() {
		CourseDataOnDemand courseDod = new CourseDataOnDemand();
		Course course = courseDod.getRandomCourse();
		TagDataOnDemand tagDod = new TagDataOnDemand();
		Tag t1 = tagDod.getNewTransientTag(0);
		Tag t2 = tagDod.getNewTransientTag(1);
		course.getTags().add(t1);
		course.getTags().add(t2);
		t1.getCourses().add(course);
		t2.getCourses().add(course);
		course.flush();
		course.clear();
		Assert.assertEquals(2, Course.findCourse(course.getId()).getTags()
				.size());
		
	}
}
