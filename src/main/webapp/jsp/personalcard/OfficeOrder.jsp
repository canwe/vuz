<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Просмотр и редактирование приказа</title>
<head>
	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
	<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>
</head>
<salmon:page controller="com.itm.vuz.personalcard.controllers.OfficeOrderController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<!--<salmon:body/>-->
<salmon:form name="pageForm"> 
<!--Page Content Begin-->
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
<salmon:datasource name="dsOfficeOrder" type="MODEL" model="com.itm.vuz.personalcard.models.OfficeOrderModel">
</salmon:datasource>
<salmon:datasource name="dsStudentHistory" type="MODEL" model="com.itm.vuz.personalcard.models.StudentHistoryModel">
</salmon:datasource>

<salmon:displaybox name="OfficeOrderBox" caption="Реквизиты приказа" width="60%"><salmon:displayboxheader>
    <salmon:input type="submit" name="sbtSave" value="Сохранить приказ"></salmon:input>&nbsp;
	<salmon:input type="submit" name="sbtDelete" value="Удалить приказ"></salmon:input>&nbsp;
	<salmon:input type="submit" name="sbtPrintOfficeOrder" value="Напечатать"></salmon:input>
  </salmon:displayboxheader><salmon:displayboxcontents>
    <table width="100%">
	  <tr>
	    <td align="right">№ приказа</td>
		<td><salmon:input name="teExternalNumber" size="25" maxlength="25" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.externalNumber"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">Дата</td>
		<td><vuz:calendar name="clndYearStarting" datasource="dsOfficeOrder:OfficeOrder.dateOrder" /></td>
	  </tr>
	  <tr>
	    <td align="right">Вид приказа</td>
		<td>
		  <salmon:input type="select" name="ddlOrderCategory" size="16" multiple="False" datasource="dsOfficeOrder:OfficeOrder.orderCategory.orderCategoryId">
		    <salmon:option key="" display="" table="ORDER_CATEGORY" keycolumn="ORDER_CATEGORY_ID" displaycolumn="ORDER_CATEGORY" nulloption="False" />
		  </salmon:input>
		</td>
	  </tr>
	  <tr>
	    <td align="right">Вид события</td>
		<td>
		  <salmon:input type="select" name="ddlEventType" size="16" multiple="False">
		    <salmon:option key="" display="" table="STUDENT_EVENT" keycolumn="EVENT_ID" displaycolumn="NAME" nulloption="False" />
		  </salmon:input>
		</td>
	  </tr>
	  <tr>
	    <td align="right">Предмет</td>
		<td><salmon:input cols="70" name="teaSubject" rows="5" type="textarea" value="" wrap="Soft" datasource="dsOfficeOrder:OfficeOrder.subject"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">Основание</td>
		<td><salmon:input type="text" name="teReason" size="80" maxlength="512" datasource="dsOfficeOrder:OfficeOrder.reason"></salmon:input></td>
	  </tr>
	  <tr><td colspan="2"><i>Утверждаю:</i></td></tr>
	  <tr>
	    <td align="right">Должность</td>
		<td><salmon:input type="text" name="teSignerPosition" size="50" maxlength="50" datasource="dsOfficeOrder:OfficeOrder.signerPosition"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">ФИО</td>
		<td><salmon:input type="text" name="teSignerName" size="50" maxlength="100" datasource="dsOfficeOrder:OfficeOrder.signerName"></salmon:input></td>
	  </tr>
	  <tr>
	    <td colspan="2"><i>Проект вносит:</i></td>
	  </tr>
	  <tr>
	    <td align="right">Должность</td>
		<td><salmon:input name="teMakerPosition" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.makerPosition"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">ФИО</td>
		<td><salmon:input name="teMakerName" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.makerName"></salmon:input></td>
	  </tr>
	  <tr><td colspan="2"><i>Согласовано:</i></td></tr>
	  <tr>
	    <td align="right">Должность</td>
		<td><salmon:input name="teCoordinator1Position" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator1Position"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">ФИО</td>
		<td><salmon:input name="teCoordinator1Name" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator1Name"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">Должность</td>
		<td><salmon:input name="teCoordinator2Position" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator2Position"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">ФИО</td>
		<td><salmon:input name="teCoordinator2Name" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator2Name"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">Должность</td>
		<td><salmon:input name="teCoordinator3Position" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator3Position"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">ФИО</td>
		<td><salmon:input name="teCoordinator3Name" size="50" type="text" value="" datasource="dsOfficeOrder:OfficeOrder.coordinator3Name"></salmon:input></td>
	  </tr>
	  <tr>
	    <td align="right">Приказ утвержден</td>
	    <td><salmon:input name="chbIsSigned" type="checkbox" datasource="dsOfficeOrder:OfficeOrder.issigned"></salmon:input></td>
	  </tr>
	  <tr>
	    <td colspan="2">
		  <salmon:input type="submit" name="sbtRemoveSelectedStudents" value="Удалить отмеченных"></salmon:input>&nbsp;
		  <salmon:input type="submit" name="sbtAddStudentsFromBasket" value="Добавить из списка"></salmon:input>
  	      <salmon:datatable name="dtStudentHistory" width="100%" datasource="dsStudentHistory" ><salmon:datatableheader>
	        <salmon:tr>
			  <salmon:td width="*" align="Center" valign="Middle" clicksort="False">
			    <input type="checkbox" onClick="ChangeChecked(checked)" />
			  </salmon:td>
	          <salmon:td clicksort="False">
	            <salmon:text name="tGroupNumberCAP" text="Группа" font="TableHeadingFont" />
              </salmon:td>
	          <salmon:td clicksort="False">
	            <salmon:text name="tStudentFIOCAP" text="ФИО студента" font="TableHeadingFont" />
              </salmon:td>
	          <salmon:td clicksort="False">
	            <salmon:text name="teCommentCAP" text="Комментарий" font="TableHeadingFont" />
              </salmon:td>
            </salmon:tr>
          </salmon:datatableheader><salmon:datatablerows>
	        <salmon:tr>
			  <salmon:td align="Center" valign="Middle">
			    <salmon:input name="chbCheck" type="checkbox" datasource="dsStudentHistory:Check"></salmon:input>
			  </salmon:td>
	          <salmon:td>
	            <salmon:text name="tGroupNumber" text="750055" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.student.studentGroup.number"/>
              </salmon:td>
	          <salmon:td>
	            <salmon:text name="tStudentFIO" text="Иванов" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.student.familyName"/>&nbsp;
	            <salmon:text name="student.nameTXT5" text="Иван" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.student.name"/>&nbsp;
	            <salmon:text name="student.patronymicTXT6" text="Иванович" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.student.patronymic"/>
              </salmon:td>
	          <salmon:td>
	            <salmon:input type="text" name="teComment" size="16" maxlength="16" datasource="dsStudentHistory:StudentHistory.comment"></salmon:input>
              </salmon:td>
            </salmon:tr>
          </salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>
		</td>
	  </tr>
	</table>
  </salmon:displayboxcontents></salmon:displaybox>
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>