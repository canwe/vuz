<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Поиск студентов</title>
<head>
	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
	<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>
</head>
<salmon:page controller="com.itm.vuz.personalcard.controllers.StudentSearchController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<!--<salmon:body/>-->
<salmon:form name="pageForm">
<!--Page Content Begin-->
<salmon:datasource name="dsStudentQBE" type="QBE" model="com.itm.vuz.personalcard.models.StudentQBE">
</salmon:datasource>
<salmon:datasource name="dsStudent" type="MODEL" model="com.itm.vuz.personalcard.models.StudentModel" autoretrieve="Never">
</salmon:datasource>

<salmon:searchformdisplaybox name="sfStudentSearchForm" caption="Введите критерии поиска" headerfont="DisplayBoxHeadingFont" width="60%" addbuttonvisible="False" searchbuttonvisible="True" searchbuttoncaption="Найти" cancelbuttonvisible="False" qbebuilder="dsStudentQBE" listformdisplaybox="lfSearchResult"  >
  <table width="100%" >
    <tr>
      <td><salmon:text name="teFamilyCAP" text="Фамилия" font="ColumnCaptionFont" /></td>
      <td><salmon:input type="text" name="teFamily" size="16" maxlength="16" datasource="dsStudentQBE:FamilyName"></salmon:input></td>
      <td><salmon:text name="teNameCAP" text="Имя" font="ColumnCaptionFont" /></td>
      <td><salmon:input type="text" name="teName" size="16" maxlength="16" datasource="dsStudentQBE:Name"></salmon:input></td>
    </tr>
    <tr>
      <td><salmon:text name="tePatronymicCAP" text="Отчество" font="ColumnCaptionFont" /></td>
      <td><salmon:input type="text" name="tePatronymic" size="16" maxlength="16" datasource="dsStudentQBE:Patronymic"></salmon:input></td>
      <td><salmon:text name="ddlBenefitsCAP" text="Льгота" font="ColumnCaptionFont" /></td>
      <td>
	    <salmon:input type="select" name="ddlBenefits" size="16" datasource="dsStudentQBE:Benefit">
		  <salmon:option table="BENEFITS" key="" display="" keycolumn="BENEFIT_ID" displaycolumn="NAME" nulloption="True" />
		</salmon:input>
	  </td>
    </tr>
	<tr>
	  <td><salmon:text name="teContractCAP" text="Контрактник" font="ColumnCaptionFont" /></td>
	  <td>
		<salmon:input type="select" name="ddlContract" size="16" multiple="False" datasource="dsStudentQBE:Contract">
		  <salmon:option key="" display="" nulloption="True" />
		  <salmon:option key="0" display="Нет"/>
		  <salmon:option key="1" display="Да"/>
		</salmon:input>
	  </td>
	  <td><salmon:text name="teStudentNumberCAP" text="№ студенческого" font="ColumnCaptionFont"/></td>
	  <td><salmon:input type="text" name="teStudentNumber" size="16" maxlength="16" datasource="dsStudentQBE:StudentNumber"></salmon:input></td>
	</tr>
	<tr>
	  <td><salmon:text name="teHospiceNeededCAP" text="Общежитие" font="ColumnCaptionFont"/></td>
	  <td>
		<salmon:input type="select" name="ddlHospiceNeeded" size="16" multiple="False" datasource="dsStudentQBE:HospiceNeeded">
		  <salmon:option key="" display="" nulloption="True"/>
		  <salmon:option key="0" display="Нет"/>
		  <salmon:option key="1" display="Да"/>
		</salmon:input>
	  </td>
	  <td><salmon:text name="teYearStartingCAP" text="Дата начала обучения" font="ColumnCaptionFont"/></td>
	  <td nowrap="nowrap"><vuz:calendar name="clndYearStarting" datasource="dsStudentQBE:YearStarting" /></td>
	</tr>
	<tr>
	  <td><salmon:text name="teStudentGroupCAP" text="№ группы" font="ColumnCaptionFont" /></td>
	  <td><salmon:input name="teStudentGroup" size="16" type="text" value="" datasource="dsStudentQBE:StudentGroup"></salmon:input></td>
	  <td><salmon:text name="teFacultyShortNameCAP" text="Факультет (сокр)" font="ColumnCaptionFont" /></td>
	  <td><salmon:input name="teFacultyShortName" size="16" type="text" value="" datasource="dsStudentQBE:FacultyShortName"></salmon:input></td>
	</tr>
	<tr>
	  <td><salmon:text name="teSpecialityShortNameCAP" text="Специальность (сокр)" font="ColumnCaptionFont" /></td>
	  <td><salmon:input name="teSpecialityShortName" size="16" type="text" value="" datasource="dsStudentQBE:SpecialityShortName"></salmon:input></td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	</tr>
  </table>
  <salmon:a href="PersonalCard.jsp" target="_blank" name="hlCreateNewPersonalCard">Создать новое личное дело</salmon:a>&nbsp;
  <salmon:input type="submit" value="Добавить в список" name="sbtAddToStudentList"></salmon:input>&nbsp;
  <salmon:a href="StudentBasket.jsp" name="hlGoToStudentBasket">Перейти к списку</salmon:a>
</salmon:searchformdisplaybox>

<script language="javascript" type="text/javascript">
  function ChangeChecked(chk) {
    for (i = 0; i < document.forms['pageForm'].elements.length; i++) {
	  var element = document.forms['pageForm'].elements[i];
	  if (element.name.indexOf('chbCheck') != -1) {
	    element.checked = chk;
	  };
	}
  }
</script>

<salmon:listformdisplaybox name="lfSearchResult" caption="Результаты поиска" mode="Display_single_page" cellpadding="4" width="100%" addbuttonvisible="False" savebuttonvisible="False" datasource="dsStudent" searchformdisplaybox="sfStudentSearchForm">
  <salmon:datatable name="datatable1" width="100%" datasource="dsStudent"><salmon:datatableheader>
      <salmon:tr>
	  	<salmon:td width="*" valign="Middle" clicksort="False">
			<input type="checkbox" onClick="ChangeChecked(checked)" />
		</salmon:td>
        <salmon:td width="30%">
          <salmon:text name="familyNameCAP10" text="ФИО" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td width="25%">
          <salmon:text name="nameCAP11" text="Паспортные данные" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td width="25%">
          <salmon:text name="addressCAP12" text="Адрес" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td width="*">
          <salmon:text name="yearStartingCAP14" text="Год начала обучения" font="TableHeadingFont" />
        </salmon:td>
        <salmon:td width="5%">
          <salmon:text name="group.numberCAP" text="Группа" font="TableHeadingFont" />
        </salmon:td>
      </salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
      <salmon:tr>
	  	<salmon:td>
		  <salmon:input name="chbCheck" type="checkbox" datasource="dsStudent:Check"></salmon:input>
		</salmon:td>
        <salmon:td nowrap="True">
		  <salmon:a name="hlPersonalCardLink" href="detail" target="_blank" datasource="dsStudent:'PersonalCard.jsp?student_id=' + s.studentId">
            <salmon:text name="familyNameTXT4" text="s.familyName Goes Here" font="EmphasisFont" datasource="dsStudent:s.familyName"/>
		    <br />
		    <salmon:text name="nameTXT5" text="s.name Goes Here" font="EmphasisFont" datasource="dsStudent:s.name"/>
		    &nbsp;
		    <salmon:text name="patronymicTXT6" text="s.patronymic Goes Here" font="EmphasisFont" datasource="dsStudent:s.patronymic"/>
  		  </salmon:a>
		  <br />
		  <salmon:text name="studentStatus.nameTXT9" text="s.studentStatus.name Goes Here" font="DefaultFont" datasource="dsStudent:s.studentStatus.name"/>
		  &nbsp;
		  <salmon:a name="hlAddToBasketLink" href="add" target="_blank" 
		    datasource="dsStudent:'AddToBasket.jsp?studentId=' + s.studentId">
		    <salmon:text name="teToBasketText" text="в список" font="FixedSmallLinkFont"/>
		  </salmon:a>
        </salmon:td>
        <salmon:td>
          <salmon:text name="passportDataTXT8" text="passport Goes Here" font="DefaultFont" datasource="dsStudent:s.passportData"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="teCountry" text="country Goes Here" font="DefaultFont" datasource="dsStudent:s.address.settlement.area.region.country.name"/>,
          <br />
          <salmon:text name="teRegion" text="region Goes Here" font="DefaultFont" datasource="dsStudent:s.address.settlement.area.region.name"/>,&nbsp;
          <salmon:text name="teArea" text="area Goes Here" font="DefaultFont" datasource="dsStudent:s.address.settlement.area.name"/>
		  <br />
          <salmon:text name="reSettlementDef" text="settlementDef Goes Here" font="DefaultFont" datasource="dsStudent:s.address.settlement.settlementDefinition.name"/>&nbsp;
          <salmon:text name="teSettlement" text="settlement Goes Here" font="DefaultFont" datasource="dsStudent:s.address.settlement.name"/>,&nbsp;
		  <salmon:text name="teAddress" text="Address goes here" font="DefaultFont" datasource="dsStudent:s.address.address"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="yearStartingTXT8" font="DefaultFont" text="s.yearStarting Goes Here" datasource="dsStudent:s.yearStarting" displayformat="dd.MM.yyyy"/>
        </salmon:td>
        <salmon:td>
          <salmon:text name="studentGroup.numberTXT9" displayformat="dd/MM/yy" text="studentGroup.number Goes Here" font="DefaultFont" datasource="dsStudent:s.studentGroup.number"/>
        </salmon:td>
      </salmon:tr>
    </salmon:datatablerows></salmon:datatable>
</salmon:listformdisplaybox>
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>

</html>