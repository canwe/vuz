package com.itm.vuz.personalcard.models;

import com.salmonllc.sql.DataStoreException;

import com.itm.vuz.common.domain.StudentHistory;


/**
 * User: artem1
 * Date: 20.04.2006
 * Time: 21:16:47
 */
public class StudentHistoryModel extends BaseCheckableModel {

    public static final String ENTITY = "StudentHistory";
    public static final String OFFICE_ORDER_ID = ENTITY + ".officeOrder.officeOrderId";
    public static final String DATE_EVENT = ENTITY + ".dateEvent";
    public static final String STUDENT_EVENT_ID = ENTITY + ".studentEvent.eventId";
    public static final String COMMENT = ENTITY + ".comment";
    public static final String USER_ID = ENTITY + ".user.userId";

    public StudentHistoryModel() {
        super();
        this.addBeanDefinition(StudentHistory.class, ENTITY);
    }

    public StudentHistory getStudentHistory() throws DataStoreException {
        return this.getStudentHistory(this.getRow());
    }

    public StudentHistory getStudentHistory(int row) throws DataStoreException {
        return (StudentHistory)this.getBean(row);
    }
}