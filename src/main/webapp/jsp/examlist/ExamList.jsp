<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<SCRIPT language="javascript">
function doDelete(examListId) {
	document.forms['pageForm'].htmlPageTopContainer_pageForm_examListIdHidden.value = examListId;
	document.forms['pageForm'].submit();
}
</SCRIPT>
<!-- BaseController child class shoudl be used as page Controller to handle the shared GUI elements -->
<salmon:page  controller="com.itm.vuz.examlist.controllers.ExamListController" contenttype="text/html; charset=UTF-8"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<salmon:datasource name="examListModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateExamListModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="facultyModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateFacultyModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="disciplineModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateDisciplineModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="examMarkModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateExamMarkModel" autoretrieve="Never">
</salmon:datasource>

<salmon:datasource name="examListQBE" type="QBE">
	<salmon:qbecriteria name="allColumns" type="COMPLEX" columns="*"/>
</salmon:datasource>
<salmon:form name="pageForm">
<salmon:input name="examListIdHidden" type="hidden"/>
<!--Page Content Begin-->
 &nbsp;
<salmon:box name="searchBox">
<table cellpadding="0" cellspacing="2" border="0">
<tr>
	<td colspan="2"><salmon:text name="searchTitle" text="Search Exam List" font="TableHeadingFont"/></td>
</tr>
<tr>
	<td><salmon:text name="facultySearchLabel" text="Faculty" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="facultySearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="specialitySearchLabel" text="Speciality" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="specialitySearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="studentGroupSearchLabel" text="Student Group" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="studentGroupSearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="educationPlanSearchLabel" text="Education Plan" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="educationPlanSearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="subFacultySearchLabel" text="Sub Faculty" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="subFacultySearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="baseDisciplineSearchLabel" text="Base Discipline" font="TableHeadingFont"/></td>
	<td><salmon:input type="select" name="baseDisciplineSearchSelect" size="16" multiple="False"
			onchange="javascript: document.pageForm.submit();"></salmon:input></td>
</tr>
<tr>
	<td><salmon:text name="numberSearchLabel" text="Number Exam List" font="TableHeadingFont"/></td>
	<td><salmon:input type="text" name="numberSearchTextEdit" size="16" maxlength="255"></salmon:input></td>
</tr>
<tr>
	<td colspan="2"><salmon:input type="submit" name="searchButton" value="Search"></salmon:input></td>
</tr>
</table>
</salmon:box>
<br>
<salmon:box name="resultBox">
<salmon:datatable name="datatableExamList" width="100%" datasource="examListModel" ><salmon:datatableheader>
    <salmon:tr>
	    <salmon:td><salmon:text name="examListIdCap" text="" font="TableHeadingFont"/></salmon:td>
        <salmon:td><salmon:text name="numberCap" text="Number" font="TableHeadingFont"/></salmon:td>
		<salmon:td><salmon:text name="dateCap" text="Date" font="TableHeadingFont"/></salmon:td>
	</salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
    <salmon:tr>
	    <salmon:td><salmon:input type="checkbox" name="deleteCheckBox" checkedtruevalue="1"></salmon:input></salmon:td>
        <salmon:td>
			<salmon:a href="examListDetail" name="examListDetailLink" datasource="examListModel:'%ExamListDetails?examListId='+ExamList.examListId">
			<salmon:text name="number" text="number Goes Here" font="DefaultFont" datasource="examListModel:ExamList.number"/>
			</salmon:a>
		</salmon:td>
		<salmon:td><salmon:text name="dateCreation" text="dateCreation Goes Here" font="DefaultFont"
 			datasource="examListModel:ExamList.dateCreation" displayformat="dd/MM/yyyy" /></salmon:td>
    </salmon:tr>
    </salmon:datatablerows></salmon:datatable>
</salmon:box>
<br>
<salmon:input name="addExamListButton" type="submit" caption="Add"></salmon:input>
<salmon:input name="deleteExamListButton" type="submit" caption="Delete"></salmon:input>
<!--Page Content End-->
</salmon:form>

<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>