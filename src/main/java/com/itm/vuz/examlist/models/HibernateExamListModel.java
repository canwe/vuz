package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.ExamList;

/**
 * User: avb
 * Date: 25.05.2006
 * Time: 12:47:15
 */
public class HibernateExamListModel extends HibernateDataStore {

	public static final String NUMBER = "ExamList.number";
	public static final String EXAMLIST_ID = "ExamList.examListId";
	public static final String DATE_CREATION = "ExamList.dateCreation";
	public static final String EXAM_MARKS_EMPTY = "ExamList.examMarks.empty";

	public HibernateExamListModel() {
        addBeanDefinition(ExamList.class, "ExamList");
    }
}
