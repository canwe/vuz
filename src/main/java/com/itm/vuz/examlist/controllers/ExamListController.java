package com.itm.vuz.examlist.controllers;

import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.html.OptionsSort;
import com.salmonllc.util.MessageLog;
import com.salmonllc.sql.DataStore;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.controllers.BaseVUZController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRSaver;
import org.hibernate.Session;

import javax.servlet.ServletException;

public class ExamListController extends BaseVUZController
		implements SubmitListener, PageListener, ValueChangedListener {

	//Visual Components
	public com.salmonllc.html.HtmlDropDownList _baseDisciplineSearchSelect;
	public com.salmonllc.html.HtmlDropDownList _educationPlanSearchSelect;
	public com.salmonllc.html.HtmlDropDownList _facultySearchSelect;
	public com.salmonllc.html.HtmlDropDownList _specialitySearchSelect;
	public com.salmonllc.html.HtmlDropDownList _studentGroupSearchSelect;
	public com.salmonllc.html.HtmlDropDownList _subFacultySearchSelect;
	public com.salmonllc.html.HtmlTextEdit _numberSearchTextEdit;
	public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
	public com.salmonllc.html.HtmlSubmitButton _searchButton;
	public com.salmonllc.html.HtmlSubmitButton _addExamListButton;
	public com.salmonllc.html.HtmlSubmitButton _deleteExamListButton;
	public com.salmonllc.html.HtmlCheckBox _deleteCheckBox;
	public HtmlHiddenField _examListIdHidden;
	//public com.salmonllc.html.HtmlText _examListId;
	public com.salmonllc.html.HtmlText _examListIdCap;
	public com.salmonllc.html.HtmlText _number;
	public com.salmonllc.html.HtmlText _numberCap;
	public com.salmonllc.html.HtmlText _dateCap;
	public com.salmonllc.html.HtmlText _facultySearchLabel;
	public com.salmonllc.html.HtmlText _numberSearchLabel;
	public com.salmonllc.html.HtmlText _specialitySearchLabel;
	public com.salmonllc.html.HtmlText _studentGroupSearchLabel;
	public com.salmonllc.html.HtmlText _educationPlanSearchLabel;
	public com.salmonllc.html.HtmlText _subFacultySearchLabel;
	public com.salmonllc.html.HtmlText _baseDisciplineSearchLabel;
	public com.salmonllc.html.HtmlText _searchTitle;
	public JspBox resultBox;
	public JspContainer _noCache;
	public JspDataTable _datatableExamList;
	public JspForm _pageForm;
	public JspTableCell _datatableExamListTDHeader0;
	public JspTableCell _datatableExamListTDHeader1;
	public JspTableCell _datatableExamListTDRow0;
	public JspTableCell _datatableExamListTDRow1;
	public JspTableRow _datatableExamListTRHeader0;
	public JspTableRow _datatableExamListTRRow0;

//DataSources
	//public com.itm.vuz.examlist.models.HibernateBaseDisciplineModel _baseDisciplineModel;
	//public com.itm.vuz.examlist.models.HibernateEducationPlanModel _educationPlanModel;
	public com.itm.vuz.examlist.models.HibernateExamListModel _examListModel;
	public com.itm.vuz.examlist.models.HibernateFacultyModel _facultyModel;
	//public com.itm.vuz.examlist.models.HibernateSpecialityModel _specialityModel;
	//public com.itm.vuz.examlist.models.HibernateSubFacultyModel _subFacultyModel;
	public com.itm.vuz.examlist.models.HibernateDisciplineModel _disciplineModel;
	public com.itm.vuz.examlist.models.HibernateExamMarkModel _examMarkModel;
	public com.salmonllc.sql.QBEBuilder _examListQBE;

//DataSource Column Constants
	public static final String EXAMLISTQBE_ALLCOLUMNS = "allColumns";

	private String DELETE_FLAG = "DELETE_FLAG";

    public HtmlHiddenField _isAdditional;
    String isAdditional;

	/**
	 * Initialize the page. Set up listeners and perform other initialization activities.
	 */
	public void initialize() {
        super.initialize();
		addPageListener(this);
		_searchButton.addSubmitListener(this);
		_addExamListButton.addSubmitListener(this);
		_deleteExamListButton.addSubmitListener(this);

		_searchTitle.setText("Поиск Экзаменационных ведомостей");
		_numberSearchLabel.setText("Номер ведомости");
		_facultySearchLabel.setText("Факультет");
		_specialitySearchLabel.setText("Специальность");
		_studentGroupSearchLabel.setText("Группа");
		_educationPlanSearchLabel.setText("Учебный План");
		_subFacultySearchLabel.setText("Кафедра");
		_baseDisciplineSearchLabel.setText("Дисциплина");
		_searchButton.setDisplayName("Поиск");
		_numberCap.setText("Номер ведомости");
		_dateCap.setText("Дата создания");
		_addExamListButton.setDisplayName("Добавить ведомость");
		_deleteExamListButton.setDisplayName("Удалить");
		_examListIdCap.setText("Удалить");

		_examListModel.addBucket(DELETE_FLAG, DataStore.DATATYPE_INT);
		_deleteCheckBox.setColumn(_examListModel, DELETE_FLAG);
		_deleteCheckBox.setFalseValue(null);
	}

	/**
	 * Process the given submit event
	 *
	 * @param event the submit event to be processed
	 * @return true to continue processing events, false to stop processing events
	 */
	public boolean submitPerformed(SubmitEvent event) {
		MessageLog.writeDebugMessage(
				"submitPerformed. button: " + event.getComponent().getName(),
				this);
		if (event.getComponent() == _addExamListButton) {
			try {
                MessageLog.writeDebugMessage("baseDisciplineSearchSelect = [" + _baseDisciplineSearchSelect.getValue() + "]", this);
                MessageLog.writeDebugMessage("studentGroupSearchSelect = [" + _studentGroupSearchSelect.getValue() + "]", this);
                MessageLog.writeDebugMessage("educationPlanSearchSelect = [" + _educationPlanSearchSelect.getValue() + "]", this);
				gotoSiteMapPage("ExamListDetails", "?baseDisciplineId="
						+ _baseDisciplineSearchSelect.getValue()
						+ "&studentGroupId="
						+ _studentGroupSearchSelect.getValue()
						+ "&educationPlanId="
						+ _educationPlanSearchSelect.getValue()
                        + "&isAdditional=" + isAdditional);
			} catch (Exception e) {
				MessageLog.writeErrorMessage("Error in submitPerformed", e, this);
			}
		} else if (event.getComponent() == _deleteExamListButton) {
			//delete exam list
			try {
				Session session = HibernateSessionFactory.getSession();
				int count = _examListModel.getRowCount() - 1;
				for (int i = count; i >= 0; i--) {
					if (_examListModel.getRowStatus() == DataStore.STATUS_NEW
							|| _examListModel.getInt(i, DELETE_FLAG) == 1) {
						ExamList examList = (ExamList) _examListModel.getDataRow(i).getBean();
						if (examList != null) {
							Iterator iterator = examList.getExamMarks().iterator();
							while (iterator.hasNext()) {
								ExamMark examMark = (ExamMark) iterator.next();

								session.delete(examMark);
							}
						}

						_examListModel.deleteRow(i);
					}
				}

				//update the datastore
				_examListModel.update(true);
			} catch (Exception e) {
				MessageLog.writeErrorMessage("Error in submitPerformed", e, this);
			}
		}
		return true;
	}


	private void retrieveData() {
		try {
			_examListModel.reset();

			Faculty faculty = initializeFacultySearchSelect();
			Speciality speciality = initializeSpecialitySearchSelect(faculty);
            EducationPlan educationPlan =
                    initializeEducationPlanSearchSelect(speciality);
            StudentGroup studentGroup = null;
            if(!isAdditional.equals("true")){
                studentGroup = initializeStudentGroupSearchSelect(speciality);
            }
			Subfaculty subfaculty = initializeSubFacultySearchSelect(faculty);
			BaseDiscipline baseDiscipline =
					initializeBaseDisciplineSearchSelect(educationPlan);

			searchExamList(studentGroup, educationPlan, baseDiscipline);
			printExamListModel();
		} catch (Throwable e) {
			MessageLog.writeErrorMessage("Error in retrieveData", e, this);
		}
	}

	private StudentGroup initializeStudentGroupSearchSelect(Speciality speciality) {
		String selectedValue = _studentGroupSearchSelect.getValue();
		StudentGroup selectedStudentGroup = null;
		_studentGroupSearchSelect.resetOptions();
		if (speciality != null) {
			Set studentGroups = speciality.getStudentGroups();
			Iterator iterator = studentGroups.iterator();
			while (iterator.hasNext()) {
				StudentGroup studentGroup = (StudentGroup) iterator.next();
				if (studentGroup != null) {
					_studentGroupSearchSelect.addOption(studentGroup.getStudentGroupId().toString(),
							studentGroup.getNumber());
					if (studentGroup.getStudentGroupId().toString().equals(selectedValue)) {
						selectedStudentGroup = studentGroup;
						_studentGroupSearchSelect.setValue(selectedValue);
					}
				}
			}
			if (selectedStudentGroup == null
					&& studentGroups.size() > 0
					&& _studentGroupSearchSelect.getOptionCount() > 0) {
				iterator = studentGroups.iterator();
				if (iterator.hasNext()) {
					selectedStudentGroup = (StudentGroup) iterator.next();
					_studentGroupSearchSelect.setValue(selectedStudentGroup.getStudentGroupId().toString());
				}
			}
		}
		return selectedStudentGroup;
	}

	private void searchExamList(StudentGroup studentGroup, EducationPlan educationPlan,
								BaseDiscipline baseDiscipline)
			throws Exception {
		if (educationPlan == null
				|| baseDiscipline == null) {
			return;
		}
		Discipline discipline = getDiscipline(baseDiscipline, educationPlan);
		if (discipline != null) {
			Set examMarks = getExamMarks(discipline, studentGroup);
			Iterator iterator = getExamList(examMarks).iterator();
			while (iterator.hasNext()) {
				_examListModel.insertRow(iterator.next());
			}
		}
	}

	private Set getExamList(Set examMarks) {
		Set examList = new HashSet();
		Iterator iterator = examMarks.iterator();
		while (iterator.hasNext()) {
			ExamMark examMark = (ExamMark) iterator.next();
            ExamList examList0 = examMark.getExamList();
            // check exam list type
            if(isAdditional.equals("true")){
                if(examList0.getType()!=null && examList0.getType().intValue() == 2){
                    examList.add(examList0);
                }
            }if(isAdditional.equals("false")){
                if(examList0.getType()!=null && (examList0.getType().intValue() == 0 || examList0.getType().intValue() == 1)){
                    examList.add(examList0);
                }
            }

		}
		return examList;
	}

	private Set getExamMarks(Discipline discipline, StudentGroup studentGroup) {
		Set filteredExamMarks = new HashSet();
		Iterator examIterator = discipline.getExamMarks().iterator();
		while (examIterator.hasNext()) {
			ExamMark examMark = (ExamMark) examIterator.next();
            if(!isAdditional.equals("true")){
                // filter by student group
                if (examMark.getStudentGroup().getStudentGroupId().equals(studentGroup.getStudentGroupId())) {
                    filteredExamMarks.add(examMark);
                }
            }else{
                filteredExamMarks.add(examMark);
            }
		}
		return filteredExamMarks;
	}

	private Discipline getDiscipline(BaseDiscipline baseDiscipline,
									 EducationPlan educationPlan)
			throws Exception {
		Iterator iterator = baseDiscipline.getDisciplines().iterator();
		Long educationPlanId = educationPlan.getEducationPlanId();
		while (iterator.hasNext()) {
			Discipline discipline = (Discipline) iterator.next();
			if (discipline.getEducationPlan().getEducationPlanId().equals(educationPlanId)) {
				return discipline;
			}
		}
		return null;
	}

	private BaseDiscipline initializeBaseDisciplineSearchSelect(EducationPlan edPlan) {
		String selectedValue = _baseDisciplineSearchSelect.getValue();
		BaseDiscipline selectedBaseDiscipline = null;
		_baseDisciplineSearchSelect.resetOptions();
		if (edPlan != null) {
			Set baseDisciplineSet = edPlan.getDisciplines();
			Iterator iterator = baseDisciplineSet.iterator();
			while (iterator.hasNext()) {
				BaseDiscipline baseDiscipline = ((Discipline) iterator.next()).getBaseDiscipline();
				if (baseDiscipline != null) {
					_baseDisciplineSearchSelect.addOption(baseDiscipline.getBaseDisciplineId().toString(),
							baseDiscipline.getName());
					if (baseDiscipline.getBaseDisciplineId().toString().equals(selectedValue)) {
						selectedBaseDiscipline = baseDiscipline;
						_baseDisciplineSearchSelect.setValue(selectedValue);
					}
				}
			}
			if (selectedBaseDiscipline == null
					&& baseDisciplineSet.size() > 0
					&& _baseDisciplineSearchSelect.getOptionCount() > 0) {
				iterator = baseDisciplineSet.iterator();
				if (iterator.hasNext()) {
					selectedBaseDiscipline = ((Discipline) iterator.next()).getBaseDiscipline();
					_baseDisciplineSearchSelect.setValue(selectedBaseDiscipline.getBaseDisciplineId().toString());
				}
			}
		}
		return selectedBaseDiscipline;
	}

	private Subfaculty initializeSubFacultySearchSelect(Faculty faculty) {
		String selectedValue = _subFacultySearchSelect.getValue();
		Subfaculty selectedSubfaculty = null;
		_subFacultySearchSelect.resetOptions();
		if (faculty != null) {
			Set subFactories = faculty.getSubfaculties();
			Iterator iterator = subFactories.iterator();
			while (iterator.hasNext()) {
				Subfaculty subfaculty =
						(Subfaculty) iterator.next();
				if (subfaculty != null) {
					_subFacultySearchSelect.addOption(subfaculty.getSubfacultyId().toString(),
							subfaculty.getName());
					if (subfaculty.getSubfacultyId().toString().equals(selectedValue)) {
						selectedSubfaculty = subfaculty;
						_subFacultySearchSelect.setValue(selectedValue);
					}
				}
			}
			if (selectedSubfaculty == null
					&& subFactories.size() > 0
					&& _subFacultySearchSelect.getOptionCount() > 0) {
				iterator = subFactories.iterator();
				if (iterator.hasNext()) {
					selectedSubfaculty = (Subfaculty) iterator.next();
					_subFacultySearchSelect.setValue(selectedSubfaculty.getSubfacultyId().toString());
				}
			}
		}
		return selectedSubfaculty;
	}

	private EducationPlan initializeEducationPlanSearchSelect(Speciality speciality) {
		String selectedValue = _educationPlanSearchSelect.getValue();
		EducationPlan selectedEducationPlan = null;
		_educationPlanSearchSelect.resetOptions();
		if (speciality != null) {
			Set educationPlans = speciality.getEducationPlans();
			Iterator iterator = educationPlans.iterator();
			while (iterator.hasNext()) {
				EducationPlan educationPlan = (EducationPlan) iterator.next();
				if (educationPlan != null) {
					_educationPlanSearchSelect.addOption(educationPlan.getEducationPlanId().toString(),
							getEducationPlanName(educationPlan));
					if (educationPlan.getEducationPlanId().toString().equals(selectedValue)) {
						selectedEducationPlan = educationPlan;
						_educationPlanSearchSelect.setValue(selectedValue);
					}
				}
			}
			if (selectedEducationPlan == null
					&& educationPlans.size() > 0
					&& _educationPlanSearchSelect.getOptionCount() > 0) {
				iterator = educationPlans.iterator();
				if (iterator.hasNext()) {
					selectedEducationPlan = (EducationPlan) iterator.next();
					_educationPlanSearchSelect.setValue(selectedEducationPlan.getEducationPlanId().toString());
				}
			}
		}
		return selectedEducationPlan;
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/yyyy");

	private String getEducationPlanName(EducationPlan educationPlan) {
		String start = DATE_FORMAT.format(educationPlan.getDataStart());
		String end = DATE_FORMAT.format(educationPlan.getDataEnd());
		return start + " - " + end;
	}

	private Speciality initializeSpecialitySearchSelect(Faculty faculty) {
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

	private Faculty initializeFacultySearchSelect() throws Exception {
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

	private void printExamListModel() {
		MessageLog.writeDebugMessage("exam list size: "
				+ _examListModel.getRowCount(), this);
		if (_examListModel.getRowCount() > 0) {
			for (int i = 0; i < _examListModel.getRowCount(); i++) {
				ExamList examList =
						(ExamList) _examListModel.getDataRow(i).getBean();
				if (examList != null) {
					MessageLog.writeDebugMessage(i + " id: "
							+ examList.getExamListId() + ", number: "
							+ examList.getNumber(), this);
				}
			}

		}
	}

	/**
	 * Process the page requested event
	 *
	 * @param event the page event to be processed
	 */
	public void pageRequested(PageEvent event) throws Exception{
        super.pageRequested(event);
        if(login!=null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)){
            _addExamListButton.setEnabled(true);
            _deleteExamListButton.setEnabled(true);
        }else{
            _addExamListButton.setEnabled(false);
            _deleteExamListButton.setEnabled(false);
        }

        isAdditional = this.getCurrentRequest().getParameter("isAdditional");
		MessageLog.writeDebugMessage("pageRequested with isAdditional = [" + isAdditional + "]", this);
        if(isAdditional.equals("true")){
            // hide student group criteria
            _studentGroupSearchLabel.setVisible(false);
            _studentGroupSearchSelect.setVisible(false);
        }else{
            _studentGroupSearchLabel.setVisible(true);
            _studentGroupSearchSelect.setVisible(true);
        }
		retrieveData();
        _baseDisciplineSearchSelect.sort(OptionsSort.SORT_ASC);
        _educationPlanSearchSelect.sort(OptionsSort.SORT_ASC);;
        _facultySearchSelect.sort(OptionsSort.SORT_ASC);;
        _specialitySearchSelect.sort(OptionsSort.SORT_ASC);;
        _studentGroupSearchSelect.sort(OptionsSort.SORT_ASC);;
        _subFacultySearchSelect.sort(OptionsSort.SORT_ASC);;

	}

	/**
	 * Process the page request end event
	 *
	 * @param event the page event to be processed
	 */
	public void pageRequestEnd(PageEvent event) {
	}

	/**
	 * Process the page submit end event
	 *
	 * @param event the page event to be processed
	 */
	public void pageSubmitEnd(PageEvent event) {
	}

	/**
	 * Process the page submit event
	 *
	 * @param event the page event to be processed
	 */
	public void pageSubmitted(PageEvent event) {
	}

	public boolean valueChanged(ValueChangedEvent valueChangedEvent) throws Exception {
		new SpecialityChanged().valueChanged(valueChangedEvent);
		return true;
	}

	class SpecialityChanged implements ValueChangedListener {

		public boolean valueChanged(ValueChangedEvent valueChangedEvent) throws Exception {
			MessageLog.writeDebugMessage("SpecialityChanged: index: "
					+ _specialitySearchSelect.getSelectedIndex(), this);
			retrieveData();
			return true;
		}
	}
}
