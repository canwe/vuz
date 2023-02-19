package com.itm.vuz.personalcard.controllers;

import com.itm.vuz.common.domain.Student;
import com.itm.vuz.personalcard.models.StudentModel;
import com.salmonllc.sql.DataStoreException;

/**
 * User: Naumov Artem
 * Date: 16.06.2006
 * Time: 14:39:47
 * "Виртуальный" список студентов.
 */
public class StudentBasket {

    public StudentBasket() {
    }

    /**
     * Используется для хранения виртуального списка студентов
     */
    private StudentModel _basket = new StudentModel();

    /**
     * @return Возвращает список студентов, которых навыбирал пользователь
     */
    public StudentModel getStudents() {
        return this._basket;
    }

    /**
     * Возвращает кол-во студентов, добавленных в виртуальный список
     * @return кол-во студентов в списке
     */
    public int getCount() {
        return this._basket.getRowCount();
    }

    private boolean findInBusket(Student s) throws DataStoreException {
        String findExp = "s.studentId == " + s.getStudentId().toString();
        this._basket.setFindExpression(findExp);
        return this._basket.findFirst();
    }

    /**
     * Добавляет студента в список, если этот студент еще не содержится в списке
     * @param s Добавляемый студент
     * @throws DataStoreException
     */
    public void AddStudent(Student s) throws DataStoreException {
        if (s != null) {
            if (!this.findInBusket(s)) {
                this._basket.insertRow(s);
            }
        }
    }

    /**
     * Удаляет студента из списка
     * @param s Удаляемый студент
     * @throws DataStoreException
     */
    public void RemoveStudent(Student s) throws DataStoreException {
        if (s != null) {
            if (this.findInBusket(s)) {
                //пользуемся следствием метода поиска, который устанавливает
                //текущей первую найденную строку
                this._basket.deleteRow();
            }
        }
    }

    /**
     * Очистка списка студентов
     */
    public void Clear() {
        this._basket.reset();
    }
}
