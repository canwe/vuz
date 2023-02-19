<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet"  pageEncoding="UTF-8"%>
<html>
<salmon:page controller="com.itm.vuz.edplans.controllers.lookup.BaseDisciplinesLookupController"/><salmon:body/>
<salmon:form name="pageForm1" >

<!--Page Content Begin-->
<salmon:datasource name="baseDisciplinesModel" type="MODEL" model="com.itm.vuz.edplans.models.HibernateBaseDisciplinesModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="baseDisciplinesQBE" type="QBE" model="com.itm.vuz.edplans.models.BaseDisciplinesQBEBuilder">
</salmon:datasource>


<salmon:searchformdisplaybox name="baseDisciplinesSearch" caption="?????" visible="True" enabled="True" addbuttonvisible="False" searchbuttonvisible="True" cancelbuttonvisible="True" qbebuilder="baseDisciplinesQBE"  >
  <salmon:table name="baseDisciplinesSearchTable" width="100%" >
    <salmon:tr>
      <salmon:td>
        <salmon:text name="baseDisciplineNameQBECAP" text="???????? ??????????" font="ColumnCaptionFont" />
      </salmon:td>
      <salmon:td>
        <salmon:input type="text" name="baseDisciplineNameQBE" size="16" maxlength="16" datasource="baseDisciplinesQBE:BaseDiscipline.name"></salmon:input>
      </salmon:td>
      <salmon:td>
        <salmon:input type="hidden" name="baseDisciplineIdQBE" size="16" maxlength="16" datasource="baseDisciplinesQBE:BaseDiscipline.baseDisciplineId"></salmon:input>
      </salmon:td>
    </salmon:tr>
  </salmon:table>
</salmon:searchformdisplaybox>

<salmon:listformdisplaybox name="baseDisciplinesDisplay" mode="Display_single_page" caption="??????????" width="100%" addbuttonvisible="False" savebuttonvisible="False" datasource="baseDisciplinesModel" lookupreturnexp="BaseDiscipline.baseDisciplineId" lookupdescreturnexp="BaseDiscipline.name" searchformdisplaybox="baseDisciplinesSearch"  >
  <salmon:datatable name="datatable1" width="100%" datasource="baseDisciplinesModel" pageselectortype="ScrollBar" theme="FixedSmall"><salmon:datatableheader>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="baseDisciplineNameCAP" text="???????? ??????????" font="TableHeadingFont" />
        </salmon:td>
      </salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
      <salmon:tr>
        <salmon:td>
          <salmon:text name="baseDisciplineName" text="BaseDiscipline.name Goes Here" font="DefaultFont" datasource="baseDisciplinesModel:BaseDiscipline.name"/>
        </salmon:td>
      </salmon:tr>
    </salmon:datatablerows></salmon:datatable>
</salmon:listformdisplaybox>

<!--Page Content End-->
</salmon:form>
<salmon:endbody/>
<salmon:endpage/>
</html>