<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
    	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
		<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>

<!-- BaseController child class shoudl be used as page Controller to handle the shared GUI elements -->
<salmon:page  controller="com.itm.vuz.examlist.controllers.ExamListDetailsController" contenttype="text/html; charset=UTF-8"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>

<salmon:datasource name="examMarkModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateExamMarkModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="studentModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateStudentModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="disciplineModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateDisciplineModel" autoretrieve="Never">
</salmon:datasource>

<salmon:datasource name="studentGroupModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateStudentGroupModel" autoretrieve="Never">
</salmon:datasource>

<br>
<salmon:form name="pageForm">
<salmon:input type="hidden" name="disciplineIdHiddenField"></salmon:input>
<salmon:input type="hidden" name="examListIdHiddenField"></salmon:input>
<salmon:input type="hidden" name="studentGroupIdHiddenField"></salmon:input>
<salmon:input type="hidden" name="baseDisciplineIdHiddenField"></salmon:input>
<salmon:input type="hidden" name="educationPlanIdHiddenField"></salmon:input>
<salmon:input type="hidden" name="actionType" value="view" ></salmon:input>
<!--Page Content Begin-->
<salmon:box name="headerBox">
<table cellpadding="0" cellspacing="2" border="0">
<tr>
	<td><salmon:text name="numberLabel" text="Number Exam List" font="TableHeadingFont"/></td>
	<td><salmon:input type="text" name="numberTextEdit" size="16" maxlength="255"></salmon:input></td>
</tr>
<tr>
	<td colspan="2"><salmon:text name="semestrLabel" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="semestrLabelValue" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="yearLabelValue" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="yearLabel" text="Semestr" font="TableHeadingFont"/></td>
</tr>
<tr>
	<td colspan="2"><salmon:text name="checkingFormLabel" text="CheckingForm" font="TableHeadingFont"/>
	<salmon:text name="checkingFormLabelValue" text="exam" font="TableHeadingFont" />
	<salmon:text name="underlineCheckingFormLabel" text="underlineCheckingForm" font="TableHeadingFont"/></td>
</tr>
<tr>
	<td colspan="2"><salmon:text name="specialityLabel" text="Speciality" font="TableHeadingFont"/>
	<salmon:text name="specialityLabelValue" text="Semestr" font="TableHeadingFont" />
	<salmon:text name="studentGroupLabel" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="studentGroupLabelValue" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="courseLabel" text="Semestr" font="TableHeadingFont"/>
	<salmon:text name="courseLabelValue" text="Semestr" font="TableHeadingFont"/></td>
</tr>
<tr>
	<td colspan="2"><salmon:text name="disciplineLabel" text="Discipline" font="TableHeadingFont"/>
	<salmon:text name="disciplineLabelValue" text="Semestr" font="TableHeadingFont"/>
	</td>
</tr>
<tr>
	<td colspan="2"><salmon:text name="fioLabel" text="FIO" font="TableHeadingFont"/>
	<salmon:text name="fioLabelValue" text="Semestr" font="TableHeadingFont"/>
	</td>
</tr>
<tr>
	<td><salmon:text name="dateCreationLabel" text="Number Exam List" font="TableHeadingFont" /></td>
	<td><vuz:calendar name="dateCreationTextEdit" ></vuz:calendar></td>
</tr>
<tr>
	<td><salmon:text name="typeExamLabel" text="Number Exam List" font="TableHeadingFont" /></td>
	<td>
		<salmon:input type="select" name="typeExamSelect" size="16" multiple="False"/>
	</td>
</tr>
</table>
</salmon:box>
<br>
<salmon:box name="examListBox">
<salmon:datatable name="datatableExamList" width="100%" datasource="examMarkModel" ><salmon:datatableheader>
    <salmon:tr>
	    <salmon:td><salmon:text name="studentNumberCap" text="" font="TableHeadingFont"/></salmon:td>
        <salmon:td><salmon:text name="studentGroupCap" text="Group" font="TableHeadingFont"/></salmon:td>
        <salmon:td><salmon:text name="studentFIOCap" text="FIO" font="TableHeadingFont"/></salmon:td>
		<salmon:td><salmon:text name="bookNumberCap" text="bookNumber" font="TableHeadingFont"/></salmon:td>
		<salmon:td><salmon:text name="markCap" text="mark" font="TableHeadingFont"/></salmon:td>
		<salmon:td>
            <salmon:text name="signCap" text="mark" font="TableHeadingFont"/>
        </salmon:td>
	</salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
    <salmon:tr>
	    <salmon:td><salmon:input type="checkbox" name="deleteCheckBox" checkedtruevalue="1"></salmon:input></salmon:td>
        <salmon:td><salmon:text name="studentGroup" text="studentGroup Goes Here" font="DefaultFont" datasource="examMarkModel:ExamMark.student.studentGroup.number"/></salmon:td>
        <salmon:td><salmon:text name="studentFIO" text="studentFIO Goes Here" font="DefaultFont" datasource="examMarkModel:ExamMark.student.familyName + ', ' + ExamMark.student.name + ' ' + ExamMark.student.patronymic"/></salmon:td>
		<salmon:td><salmon:text name="bookNumber" text="bookNumber Goes Here" font="DefaultFont" datasource="examMarkModel:ExamMark.student.studentNumber"/></salmon:td>
		<salmon:td>
            <salmon:input type="select" name="markTextEdit"  value="marks" multiple="False" datasource="examMarkModel:ExamMark.value"/>                    
        </salmon:td>
		<salmon:td><salmon:text name="sign" text="прописью" font="DefaultFont" /></salmon:td>
    </salmon:tr>
    </salmon:datatablerows></salmon:datatable>
</salmon:box>
 &nbsp;
<br>
<salmon:input type="submit" name="Save" caption="Save" ></salmon:input>
<salmon:input type="button" name="Delete" caption="Delete"
 	onclick="javascript: document.pageForm.htmlPageTopContainer_pageForm_actionType.value = 'delete'; document.pageForm.submit();" ></salmon:input>
<salmon:input type="submit" name="exit" caption="Exit" ></salmon:input>
<salmon:input type="button" name="editStudentList" caption="Edit Strudent List" visible="false"
    onclick="window.open('/jsp/personalcard/StudentSearch.jsp');"></salmon:input>
<salmon:input type="submit" name="insertStudentList" caption="Insert Student List" visible="false" ></salmon:input>
<br>
<salmon:input name="reportButton" type="submit" caption="Report"></salmon:input>
<!--Page Content End-->
</salmon:form>

<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>