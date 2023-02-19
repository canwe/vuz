package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.domain.StudentEvent;
import com.itm.vuz.common.models.BaseHibernateModel;
import com.salmonllc.sql.DataStoreException;

/**
 * User: artem1
 * Date: 25.06.2006
 * Time: 14:13:58
 */
public class StudentEventModel extends BaseHibernateModel{

    public static final String ENTITY = "StudentEvent";
    public static final String EVENT_ID = ENTITY + ".eventId";

    public StudentEventModel() {
        super();
        this.addBeanDefinition(StudentEvent.class,  ENTITY);
    }

    /**
     * Возвращает экземпляр класса StudentEvent из заданной строки модели
     * @param row Индекс заданной строки
     * @return Событие из заданной строки модели
     * @throws DataStoreException
     */
    public StudentEvent getStudentEvent(int row) throws DataStoreException {
        return (StudentEvent)this.getBean(row);
    }

    /**
     * Возвращает экземпляр класса StudentEvent из текущей строки модели
     * @return Событие из текущей строки модели
     * @throws DataStoreException
     */
    public StudentEvent getStudentEvent() throws DataStoreException {
        return this.getStudentEvent(this.getRow());
    }
}
