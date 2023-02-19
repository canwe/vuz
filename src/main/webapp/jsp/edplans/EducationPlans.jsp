<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html>
<salmon:page  controller="com.itm.vuz.edplans.controllers.EducationPlansController"/>


<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<salmon:form name="pageForm">
<salmon:datasource name="facultyModel" type="MODEL"
	model="com.itm.vuz.examlist.models.HibernateFacultyModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="educationPlanModel" type="MODEL"
	model="com.itm.vuz.edplans.models.HibernateEducationPlansModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="educationPlanQBE" type="QBE" model="com.itm.vuz.edplans.models.EducationPlansQBEBuuilder">
</salmon:datasource>

<salmon:searchformdisplaybox name="edPlansSearch" caption="Поиск учебных планов" visible="True" enabled="True"
 addbuttonvisible="True" searchbuttonvisible="True" cancelbuttonvisible="True" addbuttoncaption="Добавить" searchbuttoncaption="Искать" qbebuilder="educationPlanQBE"  >
  <salmon:table name="groupSearch" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="facultySearchLabel" text="Факультет" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="facultySearchSelect" size="16" multiple="False" onchange="javascript: document.pageForm.submit();">
        </salmon:input>
      </salmon:td>
    </salmon:tr>
    <salmon:tr>
      <salmon:td>
        <salmon:text name="specialitySearchLabel" text="Специальность" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="specialitySearchSelect" size="16" multiple="False"
			datasource="educationPlanQBE:EducationPlan.speciality.specialityId"></salmon:input>
      </salmon:td>
    </salmon:tr>
    <salmon:tr>
      <salmon:td>
        <salmon:text name="disciplibeSearchLabel" text="Дисциплина" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="select" name="specialitySearchSelect" size="16" multiple="False"
			datasource="educationPlanQBE:EducationPlan.speciality.specialityId"></salmon:input>
      </salmon:td>
    </salmon:tr>
  </salmon:table>
</salmon:searchformdisplaybox>
<salmon:listformdisplaybox name="educationPlansDisplay" mode="Display_multi_page" caption="Список учебных планов" width="100%" addbuttonvisible="True" savebuttonvisible="True" datasource="educationPlanModel" searchformdisplaybox="edPlansSearch"
 detailpageurl="EducationPlanDetails.jsp" addbuttoncaption="Добавить">
  <salmon:datatable name="datatable1" width="100%" datasource="educationPlanModel" pageselectortype="ScrollBar" theme="FixedSmall"><salmon:datatableheader>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="identCap" text="Идентификатор" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="dateStartCap" text="Дата начала" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="dateEndCap" text="Дата окончания" font="TableHeadingFont" />
        </salmon:td>
      </salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="identStart" text="ID Goes Here" font="DefaultFont" datasource="educationPlanModel:EducationPlan.educationPlanId"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="dateStart" text="Date Start Goes Here" font="DefaultFont" displayformat="yyyy-MM-dd" datasource="educationPlanModel:EducationPlan.dataStart"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="dateEnd" text="Date End Goes Here" font="DefaultFont" displayformat="yyyy-MM-dd" datasource="educationPlanModel:EducationPlan.dataEnd"/>
        </salmon:td>
      </salmon:tr>
    </salmon:datatablerows></salmon:datatable>
</salmon:listformdisplaybox>
</salmon:form>

<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>