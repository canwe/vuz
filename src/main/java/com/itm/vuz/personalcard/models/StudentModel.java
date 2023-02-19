package com.itm.vuz.personalcard.models;

import com.salmonllc.sql.DataStoreException;

import com.itm.vuz.common.domain.*;


/**
 * User: artem1
 * Date: 20.04.2006
 * Time: 21:16:47
 */
public class StudentModel extends BaseCheckableModel {

    public static final String ENTITY = "s";
    public static final String STUDENT_ID = ENTITY + ".studentId";
    public static final String NAME = ENTITY + ".name";
    public static final String FAMILY_NAME = ENTITY + ".familyName";
    public static final String PATRONYMIC = ENTITY + ".partonymic";
    public static final String BENEFIT = ENTITY + ".benefit";
    public static final String CONTRACT = ENTITY + ".contract";
    public static final String STUDENT_NUMBER = ENTITY + ".studentNumber";
    public static final String HOSPICE_NEEDED = ENTITY + ".hospiceNeeded";
    public static final String YEAR_STARTING = ENTITY + ".yearStarting";
    public static final String STUDENT_GROUP_NUMBER = ENTITY + ".studentGroup.number";
    public static final String STUDENT_GROUP_ID = ENTITY + ".studentGroup.studentGroupId";
    public static final String FACULTY_SHORTNAME = ENTITY + ".studentGroup.speciality.faculty.shortName";
    public static final String SPECIALITY_SHORTNAME = ENTITY + ".studentGroup.speciality.shortName";
    public static final String LANGUALE_ID = ENTITY + "language.languageId";

    public StudentModel() {
        super();
        this.addBeanDefinition(Student.class, ENTITY);
    }

    /**
     * Возвращает экземпляр класса Student из текущей строки модели
     * @return Текущий студент в модели
     * @throws DataStoreException
     */
    public Student getStudent() throws DataStoreException {
        return this.getStudent(this.getRow());
    }

    /**
     * Возвращает экземпляр класса Student из заданной строки модели
     * @param row Индекс заданной строки
     * @return Студент из строки модели, заданной индексов
     * @throws DataStoreException
     */
    public Student getStudent(int row) throws DataStoreException {
        return (Student)this.getBean(row);
    }
}
