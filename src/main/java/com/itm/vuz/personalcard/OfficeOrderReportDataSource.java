package com.itm.vuz.personalcard;

import com.itm.vuz.common.models.JasperBeanDataSource;
import com.itm.vuz.personalcard.models.StudentHistoryModel;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreBuffer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Источник данных для печати приказа.
 * Содержит только табличную часть - записи из истории студентов.
 * User: artem1
 */
public class OfficeOrderReportDataSource extends JasperBeanDataSource {

    public OfficeOrderReportDataSource(StudentHistoryModel _ds) {
        super(_ds);
    }

    /**
     * Корректировка поведения базового класса. Он извлекает имя поля
     * из имени поля JasperReport-а, а тот не допускает имен полей с точкой.
     * В то же время в моделях HibernateXXXModel, таких как используемая
     * StudentHistoryModel имена типа "StudentHistory.student.studentGroup.number"
     * в порядке вещей. Поэтому в отчете реальное имя поле указывается в описании
     * и используется в этом методе
     * TODO: обсудить с коллегами возможность выноса строки кода
     * <code>String _name = jrField.getDescription();</code> в отдельный виртуальный
     * метод и переопределять в наследниках только его
     *
     * @param jrField Поле отчета
     * @return Значение поля
     * @throws JRException
     */
    public Object getFieldValue(JRField jrField) throws JRException {
        try {
            String name = jrField.getDescription();
            if (_ds.getColumnDataType(name) == DataStoreBuffer.DATATYPE_STRING) {
                return this.whenNullReturnEmpty(_ds.getString(name));
            }
            return _ds.getAny(name);
        }
        catch (DataStoreException ex) {
            throw new JRException(ex.getMessage(), ex);
        }
    }

    /**
     * Если переданный параметр null, возвращает пустую строку
     * @param s Строка для анализа
     * @return Переданная строка либо пустая, если параметр == null
     */
    private String whenNullReturnEmpty(String s) {
        return s == null ? "" : s;
    }

}
