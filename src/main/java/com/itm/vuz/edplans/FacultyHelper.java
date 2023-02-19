package com.itm.vuz.edplans;

import com.itm.vuz.common.domain.Faculty;
import com.itm.vuz.common.domain.Speciality;
import com.salmonllc.html.HtmlDropDownList;
import com.salmonllc.hibernate.HibernateDataStore;

import java.util.Set;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 02.07.2006
 * Time: 13:16:55
 */
public class FacultyHelper {

    public Faculty initializeFacultySearchSelect(HtmlDropDownList _facultySearchSelect, HibernateDataStore _facultyModel) throws Exception {
        String selectedValue = _facultySearchSelect.getValue();
        _facultySearchSelect.resetOptions();
        _facultyModel.retrieve();
        Faculty selectedFaculty = null;
        for (int i = 0; i < _facultyModel.getRowCount(); i++) {
            Faculty faculty = (Faculty) _facultyModel.getDataRow(i).getBean();
            if (faculty != null) {
                _facultySearchSelect.addOption(faculty.getFacultyId().toString(), faculty.getName());
                if (faculty.getFacultyId().toString().equals(selectedValue)) {
                    selectedFaculty = faculty;
                    _facultySearchSelect.setValue(selectedValue);
                }
            }
        }
        if (selectedFaculty == null
                && _facultyModel.getRowCount() > 0
                && _facultySearchSelect.getOptionCount() > 0) {
            selectedFaculty = (Faculty) _facultyModel.getDataRow(0).getBean();
            _facultySearchSelect.setValue(selectedFaculty.getFacultyId().toString());
        }
        return selectedFaculty;
    }

    public Speciality initializeSpecialitySearchSelect(Faculty faculty, HtmlDropDownList _specialitySearchSelect) {
        String selectedValue = _specialitySearchSelect.getValue();
        Speciality selectedSpeciality = null;
        _specialitySearchSelect.resetOptions();
        if (faculty != null) {
            Set specialities = faculty.getSpecialities();
            Iterator iterator = specialities.iterator();
            while (iterator.hasNext()) {
                Speciality speciality = (Speciality) iterator.next();
                if (speciality != null) {
                    _specialitySearchSelect.addOption(speciality.getSpecialityId().toString(),
                            speciality.getName());
                    if (speciality.getSpecialityId().toString().equals(selectedValue)) {
                        selectedSpeciality = speciality;
                        _specialitySearchSelect.setValue(selectedValue);
                    }
                }
            }
            if (selectedSpeciality == null
                    && specialities.size() > 0
                    && _specialitySearchSelect.getOptionCount() > 0) {
                iterator = specialities.iterator();
                if (iterator.hasNext()) {
                    selectedSpeciality = (Speciality) iterator.next();
                    _specialitySearchSelect.setValue(selectedSpeciality.getSpecialityId().toString());
                }
            }
        }
        return selectedSpeciality;
    }


}
