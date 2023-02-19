<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet"  pageEncoding="UTF-8"%>
<html>
<salmon:page controller="com.itm.vuz.groupcard.lookup.StudentGroopLookupController" /><salmon:body/>
<salmon:form name="pageForm">

<!--Page Content Begin-->
<salmon:datasource name="studentGroupModel" type="MODEL" model="com.itm.vuz.groupcard.models.HibernateStudentGroupModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="studentGroupQBE" type="QBE" model="com.itm.vuz.groupcard.models.StudentGroupQBEBuilder">
</salmon:datasource>


<salmon:searchformdisplaybox name="studentGroupSearch" caption="Поиск" visible="True" enabled="True" addbuttonvisible="False" searchbuttonvisible="True" cancelbuttonvisible="True" qbebuilder="studentGroupQBE"  >
  <salmon:table name="groupSearch" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="groupNumberCAP" text="Номер группы" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="text" name="groupNumber" size="16" maxlength="16" datasource="studentGroupQBE:StudentGroup.number"></salmon:input>
      </salmon:td>
      <salmon:td>
        <salmon:text name="facultyNameCAP" text="Аббревиатура факультета" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="text" name="facultyShortName" size="16" maxlength="16" datasource="studentGroupQBE:StudentGroup.speciality.faculty.shortName"></salmon:input>
      </salmon:td>
    </salmon:tr>
  </salmon:table>
</salmon:searchformdisplaybox>
<salmon:listformdisplaybox name="studentGroupDisplay" mode="Display_single_page" caption="Группы" width="100%" addbuttonvisible="False" savebuttonvisible="False" datasource="studentGroupModel" lookupreturnexp="StudentGroup.number" searchformdisplaybox="studentGroupSearch"  >
  <salmon:datatable name="datatable1" width="100%" datasource="studentGroupModel" pageselectortype="ScrollBar" theme="FixedSmall">
    <salmon:datatableheader>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="specialityNumberCAP" text="Номер группы" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="facultyNameCAP" text="Факультет" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td>
          <salmon:text name="specialityNameCAP" text="Специальность" font="TableHeadingFont" />
        </salmon:td>
      </salmon:tr>
    </salmon:datatableheader>
    <salmon:datatablerows>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="specialityNumber" text="StudentGroup.number Goes Here" font="DefaultFont" datasource="studentGroupModel:StudentGroup.number"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="facultyName" text="StudentGroup.speciality.faculty.name Goes Here" font="DefaultFont" datasource="studentGroupModel:StudentGroup.speciality.faculty.name"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="specialityName" text="StudentGroup.speciality.name Goes Here" font="DefaultFont" datasource="studentGroupModel:StudentGroup.speciality.name"/>
        </salmon:td>
      </salmon:tr>
    </salmon:datatablerows>
  </salmon:datatable>
</salmon:listformdisplaybox>
<!--Page Content End-->
</salmon:form>
<salmon:endbody/>
<salmon:endpage/>
</html>