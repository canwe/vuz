package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.models.BaseHibernateModel;
import com.salmonllc.sql.DataStoreException;

import java.util.List;
import java.util.ArrayList;

/**
 * Базовая модель для моделей, поддерживающих отметку своих записей
 * User: artem1
 * Date: 09.08.2006
 * Time: 15:36:38
 */
public class BaseCheckableModel extends BaseHibernateModel {

    public BaseCheckableModel() {
        super();
        this.addBucket("Check", DATATYPE_SHORT);
    }

    /**
     * @return true, если текущая строка отмечена
     */
    public boolean isChecked() throws DataStoreException {
        return this.isChecked(this.getRow());
    }

    /**
     * @return true, если заданная строка отмечена. Для значений индекса строки,
     * выходящих за диапазон допустимых значений, возвращает false
     */
    public boolean isChecked(int row) throws DataStoreException {
        if ((0 <= row) && (row < this.getRowCount())) {
            return (this.getShort(row, "Check") == 1);
        }
        return false;
    }

    /**
     * Возвращает список бинов для строк модели
     * @return Список отмеченны бинов
     * @throws DataStoreException
     */
    public List getCheckedItems() throws DataStoreException {
        ArrayList result = new ArrayList();
        for(int rowIndex = 0; rowIndex < this.getRowCount(); rowIndex++) {
            if (this.isChecked(rowIndex)) {
                result.add(this.getBean(rowIndex));
            }
        }
        return result;
    }

}
