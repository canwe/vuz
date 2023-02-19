<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html>
    	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
		<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>

<salmon:page controller="com.itm.vuz.edplans.controllers.EducationPlanDetailsController" />


<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<salmon:form name="pageForm">
<salmon:datasource name="educationPlanModel" type="MODEL"
model="com.itm.vuz.edplans.models.HibernateEducationPlansModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="facultyModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateFacultyModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="disciplinesModel" type="MODEL"
	model="com.itm.vuz.edplans.models.HibernateDisciplinesModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="edPlanDisciplinesQBE" type="QBE" model="com.itm.vuz.edplans.models.EdPlanDisciplinesQBEBuilder">
</salmon:datasource>


<salmon:detailformdisplaybox name="detailformdisplaybox1" caption="�������������� �������� �����" width="100%"  datasource="educationPlanModel"
 listformdisplaybox="educationPlansDisplay" addbuttoncaption="��������" deletebuttoncaption="�������" savebuttoncaption="���������" cancelbuttoncaption="��������">

  <salmon:table name="facultySearch" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="facultySearchLabel" text="���������" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="facultySearchSelect" size="16" multiple="False" onchange="javascript: document.pageForm.submit();">
        </salmon:input>
      </salmon:td>
    </salmon:tr>
    <salmon:tr>
      <salmon:td>
        <salmon:text name="specialitySearchLabel" text="�������������" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="specialitySearchSelect" size="16" multiple="False"
			datasource="educationPlanModel:EducationPlan.speciality.specialityId"></salmon:input>
      </salmon:td>
    </salmon:tr>
  </salmon:table>


  <salmon:table name="EducationPlanTable" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="edDataStartCAP" text="���� ������" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
	    <salmon:input readonly="True" size="16" maxlength="8" name="edDataStart" type="text"></salmon:input>
		<salmon:input type="hidden" name="edDataStartHidden" datasource="educationPlanModel:EducationPlan.dataStart"></salmon:input>
		<salmon:input type="button" name="btShowCalendar" value="..."></salmon:input>
		<salmon:input type="button" name="btClearDate" value=" X " onclick="dateClear();"></salmon:input>
		<script type="text/javascript">
		  function dateClear() {
		    pageForm.htmlPageTopContainer_pageForm_detailformdisplaybox1_EducationPlanTable_EducationPlanTableTRRow0_EducationPlanTableTDRow1_edDataStartHidden.value = "";
			pageForm.htmlPageTopContainer_pageForm_detailformdisplaybox1_EducationPlanTable_EducationPlanTableTRRow0_EducationPlanTableTDRow1_edDataStart.value = "";
		  };
		  function dateChanged(calendar) {
		    pageForm.htmlPageTopContainer_pageForm_detailformdisplaybox1_EducationPlanTable_EducationPlanTableTRRow0_EducationPlanTableTDRow1_edDataStartHidden.value = calendar.date.print("%Y-%m-%d");
		  }

          Calendar.setup({
            inputField : "htmlPageTopContainer_pageForm_detailformdisplaybox1_EducationPlanTable_EducationPlanTableTRRow0_EducationPlanTableTDRow1_edDataStart", // ID of the input field
            ifFormat   : "%d.%m.%Y", //format of the displayed date
            button     : "htmlPageTopContainer_pageForm_detailformdisplaybox1_EducationPlanTable_EducationPlanTableTRRow0_EducationPlanTableTDRow1_btShowCalendar", //element, that fired the Calendar to show
			range      : [1970, 2020], //available year's range
			onUpdate   : dateChanged
                        });
		</script>


      </salmon:td>
      <salmon:td>
        <salmon:text name="edDataEndCAP" text="���� ���������" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:lookup name="edDataEnd" lookupurl="/jsp/personalcard/CalendarLookup.jsp" browseimage="../images/Browse.gif" datasource="educationPlanModel:EducationPlan.dataEnd" displayformat="dd/MM/yyyy" usepopup="True" popupwidth="240" popupheight="172" usemodal="False" popupposition="relative" usediv="True"/></td>
      </salmon:td>
    </salmon:tr>
  </salmon:table>


</salmon:detailformdisplaybox>

<salmon:input name="edPlanId" type="text" visible="false" datasource="edPlanDisciplinesQBE:Discipline.educationPlan.educationPlanId"/>

<salmon:displaybox name="educationPlanDisciplinesDisplaybox" caption="����������" width="100%">
  <salmon:displayboxheader>
    <salmon:input type="submit" name="add" value="��������"></salmon:input>
    <salmon:input type="submit" name="save" value="���������"></salmon:input>
  </salmon:displayboxheader>
  <salmon:displayboxcontents>
  <salmon:datatable name="datatableDisciplines" width="100%" align="Left" datasource="disciplinesModel" rowsperpage="5" rowsperpageselector="False">
    <salmon:datatableheader>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="disciplineNameCap" text="����������" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="hoursCap" text="����" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="semesterCap" text="�������" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="courseCap" text="����" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="yearCap" text="���" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="checkCap" text="����� ��������" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="deleteDisciplineCap" text="�������" font="TableHeadingFont"/>
        </salmon:td>
      </salmon:tr>
  </salmon:datatableheader>
  <salmon:datatablerows>
      <salmon:tr>
        <salmon:td>
          <salmon:lookup name="disciplineName" size="40" lookupurl="BaseDisciplinesLookup.jsp" browseimage="../images/Browse.gif" datasource="disciplinesModel:Discipline.baseDiscipline.baseDisciplineId" descriptiondatasource="disciplinesModel:Discipline.baseDiscipline.name" editdescription="true" usepopup="True" popupwidth="320" popupheight="300" usemodal="False" popupposition="relative" usediv="True"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" name="hours" value="Hours Goes Here" font="DefaultFont" datasource="disciplinesModel:Discipline.hours"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="select" name="semestr" size="4" multiple="False" datasource="disciplinesModel:Discipline.semester">
            <salmon:option key="false" display="1"/>
            <salmon:option key="true" display="2"/>
          </salmon:input>
        </salmon:td>
        <salmon:td>
          <salmon:input type="select" name="course" size="4" multiple="False" datasource="disciplinesModel:Discipline.course">
            <salmon:option key="1" display="1"/>
            <salmon:option key="2" display="2"/>
            <salmon:option key="3" display="3"/>
            <salmon:option key="4" display="4"/>
            <salmon:option key="5" display="5"/>
            <salmon:option key="6" display="6"/>
          </salmon:input>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" name="year" size="6" value="Year Goes Here" font="DefaultFont" datasource="disciplinesModel:Discipline.year"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="select" name="check" size="20" multiple="False" datasource="disciplinesModel:Discipline.checkingForm">
            <salmon:option key="0" display="�����"/>
            <salmon:option key="1" display="�������"/>
          </salmon:input>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" type="checkbox" name="deleteDiscipline" checkedtruevalue="1"></salmon:input>
        </salmon:td>
      </salmon:tr>
</salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>
</salmon:displayboxcontents></salmon:displaybox>


</salmon:form>

<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>
