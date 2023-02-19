package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.ExamList;

/**
 * Created by IntelliJ IDEA.
 * User: avb
 * Date: 25.05.2006
 * Time: 12:47:15
 * To change this template use File | Settings | File Templates.
 */
public class ExamListModel extends HibernateDataStore {

    public ExamListModel() {
        addBeanDefinition(ExamList.class, "ExamList");
    }
}
