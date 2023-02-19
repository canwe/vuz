package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Student;

/**
 * User: Alexei
 * Date: 03.06.2006
 * Time: 18:25:20
 */
public class HibernateStudentModel extends HibernateDataStore {

	public HibernateStudentModel() {
		addBeanDefinition(Student.class, "Student");
	}
}
