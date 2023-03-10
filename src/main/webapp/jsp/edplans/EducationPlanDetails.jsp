<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>
<style type="text/css">
<!--
.disciplineStyle {writing-mode: tb-rl}
-->
</style>

<style type="text/css">
<!--
  .MenuCell {
    border-bottom-color:Blue;
	border-bottom-style:solid;
	border-bottom-width: 1px;
	border-right: none;
	border-left: none;
	border-top: none;
	background-color:#eeeeee;
	}
  .SelectedMenuCell {
    border-bottom: none;
    border-right-color:Blue;
	border-right-style:solid;
	border-right-width: 1px;
    border-left-color:Blue;
	border-left-style:solid;
	border-left-width: 1px;
    border-top-color:Blue;
	border-top-style:solid;
	border-top-width: 1px;
	}
-->
</style>
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

<salmon:table name="tabBody" width="100%" border="0">
  <salmon:tr>
    <salmon:td valign="Top">
	  <salmon:table name="tabMenu" width="100%" height="100%" border="0" cellspacing="0" cellpadding="4">

          <salmon:td classname="MenuCell" width="20px">&nbsp;</salmon:td>

          <salmon:td classname="SelectedMenuCell" width="200px" height="20px">
		  <salmon:input type="submit" name="sbtEducPlan" value="????????????????"></salmon:input><br>
		  <salmon:text name="teDesc1" text="???????????????? ???????????????? ??????????" font="DDLBFont"/>
		  </salmon:td>

	      <salmon:td classname="MenuCell" width="250px" height="20px">
		  <salmon:input type="submit" name="sbtEducPlanEdit" value="????????????????????????????"></salmon:input><br>
		  <salmon:text name="teDesc2" text="???????????????????????????? ???????????????? ??????????" font="DDLBFont"/>
		  </salmon:td>

          <salmon:td classname="MenuCell" width="500px">&nbsp;</salmon:td>
   	  </salmon:table>
	</salmon:td></salmon:tr>

    <salmon:tr><salmon:td width="100%" valign="top">

<salmon:detailformdisplaybox name="detailformdisplaybox1" caption="???????????????? ???????????????? ??????????" width="100%"  datasource="educationPlanModel"
 listformdisplaybox="educationPlansDisplay" addbuttoncaption="????????????????" deletebuttoncaption="??????????????" savebuttoncaption="??????????????????" cancelbuttoncaption="????????????????">

  <salmon:table name="facultySearch" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="facultySearchLabel" text="??????????????????" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="facultySearchSelect" size="16" multiple="False" onchange="javascript: document.pageForm.submit();">
        </salmon:input>
      </salmon:td>
    </salmon:tr>
    <salmon:tr>
      <salmon:td>
        <salmon:text name="specialitySearchLabel" text="??????????????????????????" font="ColumnCaptionFont" />
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
        <salmon:text name="edDataStartCAP" text="???????? ????????????" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <vuz:calendar name="edDataStart" datasource="educationPlanModel:EducationPlan.dataStart"/>
      </salmon:td>
      <salmon:td>
        <salmon:text name="edDataEndCAP" text="???????? ??????????????????" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <vuz:calendar name="edDataEnd" datasource="educationPlanModel:EducationPlan.dataEnd"/>
      </salmon:td>
    </salmon:tr>
  </salmon:table>


</salmon:detailformdisplaybox>

<salmon:input name="edPlanId" type="text" visible="false" datasource="edPlanDisciplinesQBE:Discipline.educationPlan.educationPlanId"/>

<salmon:displaybox name="educationPlanDisciplinesDisplaybox" caption="????????????????????" width="100%">
  <salmon:displayboxheader>
    <salmon:input type="submit" name="add" value="????????????????" visible="false"></salmon:input>
    <salmon:input type="submit" name="save" value="??????????????????" visible="false"></salmon:input>
  </salmon:displayboxheader>
  <salmon:displayboxcontents>
  <salmon:datatable name="datatableDisciplines" width="100%" align="Left" datasource="disciplinesModel" rowsperpage="5" rowsperpageselector="true">
    <salmon:datatableheader>
      <salmon:tr>
        <salmon:td>
        </salmon:td>
        <salmon:td>
          <salmon:text name="disciplineNameCap" text="????????????????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="audiencehoursCap" text="???????????????????? ????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="independenthoursCap" text="?????????????????????????????? ????????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="hoursCap" text="?????????? ????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="semesterCap" text="??????????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="courseCap" text="????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="yearCap" text="??????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="checkCap" text="?????????? ????????????????" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="deleteDisciplineCap" text="??????????????" font="TableHeadingFont"/>
        </salmon:td>
      </salmon:tr>
  </salmon:datatableheader>
  <salmon:datatablerows>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="number"  text="1" font="DefaultFont"/>
        </salmon:td>
        <salmon:td>
          <salmon:lookup name="disciplineName" size="80" lookupurl="BaseDisciplinesLookup.jsp" browseimage="../images/Browse.gif" datasource="disciplinesModel:Discipline.baseDiscipline.baseDisciplineId" descriptiondatasource="disciplinesModel:Discipline.baseDiscipline.name" editdescription="true" usepopup="True" popupwidth="320" popupheight="360" usemodal="False" popupposition="relative" usediv="True"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" name="audiencehours" width="5px" size="3" maxlength="3" value="Hours Goes Here" font="DefaultFont" datasource="disciplinesModel:Discipline.audiencehours"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" name="independenthours" width="5px" size="3" maxlength="3" value="Hours Goes Here" font="DefaultFont" datasource="disciplinesModel:Discipline.independenthours"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="hours" text="Discipline.hours Goes Here"  font="DefaultFont" datasource="disciplinesModel:Discipline.independenthours"/>
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
          <salmon:input type="select" name="year" size="4" value="Year Goes Here" multiple="False" datasource="disciplinesModel:Discipline.year"/>
        </salmon:td>
        <salmon:td>
          <salmon:input type="select" name="check" size="20" multiple="False" datasource="disciplinesModel:Discipline.checkingForm">
            <salmon:option key="0" display="??????????"/>
            <salmon:option key="1" display="??????????????"/>
          </salmon:input>
        </salmon:td>
        <salmon:td>
          <salmon:input type="text" type="checkbox" name="deleteDiscipline" checkedtruevalue="1"></salmon:input>
        </salmon:td>
      </salmon:tr>
</salmon:datatablerows><salmon:datatablefooter>
</salmon:datatablefooter></salmon:datatable>
</salmon:displayboxcontents></salmon:displaybox>
<salmon:displaybox caption="????????" name="summary">
  <salmon:displayboxcontents>
        <salmon:text name="disciplinesCountCap" text="???????????????????? ??????????????????:" font="ColumnCaptionFont" />&nbsp;
        <salmon:text name="disciplinesCount" text="0" font="ColumnCaptionFont" />
        </br>
        <salmon:text name="hoursCountCap" text="???????????????????? ?????????? ?????????? ?? ?????????????? ??????????:" font="ColumnCaptionFont" />&nbsp;
        <salmon:text name="hoursCount" text="0" font="ColumnCaptionFont" />
  </salmon:displayboxcontents>
</salmon:displaybox>

	</salmon:td>
  </salmon:tr>
</salmon:table>

</salmon:form>

<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>
