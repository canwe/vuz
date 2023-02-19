<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<%@ page extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Просмотр приказов</title>
<head>
	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
	<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>
</head>
<salmon:page controller="com.itm.vuz.personalcard.controllers.OfficeOrderSearchController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<!--<salmon:body/>-->

<salmon:form name="pageForm"> 
<!--Page Content Begin-->
<salmon:datasource name="dsOfficeOrder" type="MODEL" model="com.itm.vuz.personalcard.models.OfficeOrderModel" autoretrieve="Never">
</salmon:datasource>

<salmon:datasource name="dsOfficeOrderQBE" type="QBE" model="com.itm.vuz.personalcard.models.OfficeOrderQBE">
</salmon:datasource>

<salmon:searchformdisplaybox name="sfOfficeOrder" caption="Просмотр приказов" headerfont="DisplayBoxHeadingFont" width="50%" addbuttonvisible="False" cancelbuttonvisible="False" qbebuilder="dsOfficeOrderQBE" listformdisplaybox="lfOfficeOrder" searchbuttonvisible="True" searchbuttoncaption="Найти"  >
  <table width="100%" >
    <tr>
      <td><salmon:text name="teExternalNumberCAP" text="№ приказа" font="ColumnCaptionFont" /></td>
      <td><salmon:input type="text" name="teExternalNumber" size="16" maxlength="16" datasource="dsOfficeOrderQBE:ExternalNumber"></salmon:input></td>
      <td><salmon:text name="teDateOrderCAP" text="Дата приказа" font="ColumnCaptionFont" /></td>
      <td><vuz:calendar name="clndDateOrder" datasource="dsOfficeOrderQBE:DateOrder" /></td>
    </tr>
    <tr>
      <td><salmon:text name="teIsSignedCAP" text="Подписан" font="ColumnCaptionFont" /></td>
      <td>
        <salmon:input type="select" name="ddlIsSigned" size="16" datasource="dsOfficeOrderQBE:IsSigned">
		  <salmon:option key="" display="" nulloption="True" />
		  <salmon:option key="0" display="Нет"/>
		  <salmon:option key="1" display="Да"/>
		</salmon:input>
      </td>
      <td><salmon:text name="teOrderCategoryCAP" text="Вид приказа" font="ColumnCaptionFont" /></td>
      <td>
	    <salmon:input type="select" name="ddlOrderCategory" size="16" datasource="dsOfficeOrderQBE:OrderCategory">
		  <salmon:option key="" display="" table="ORDER_CATEGORY" keycolumn="ORDER_CATEGORY_ID" displaycolumn="ORDER_CATEGORY" nulloption="True" />
		</salmon:input>
	  </td>
    </tr>
  </table>
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

<salmon:listformdisplaybox name="lfOfficeOrder" caption="Результат поиска" mode="Display_single_page" width="100%" addbuttonvisible="False" savebuttonvisible="False" datasource="dsOfficeOrder" searchformdisplaybox="sfOfficeOrder" autocreatelink="False"  >
  <salmon:datatable name="datatable1" width="100%" datasource="dsOfficeOrder" ><salmon:datatableheader>
      <salmon:tr>
        <salmon:td align="Center" valign="Middle" clicksort="False" width="5%">
		  <input type="checkbox" onClick="ChangeChecked(checked)" />
		</salmon:td>
		<salmon:td width="35%" clicksort="False"><salmon:text name="teAttributesCAP" font="TableHeadingFont" text="Реквизиты приказа" /></salmon:td>
		<salmon:td clicksort="False"><salmon:text name="teSubjectCAP" font="TableHeadingFont" text="Предмет" /></salmon:td>
      </salmon:tr>
    </salmon:datatableheader><salmon:datatablerows>
      <salmon:tr>
        <salmon:td align="Center" valign="Middle">
		  <salmon:input name="chbCheck" type="checkbox" datasource="dsOfficeOrder:Check"></salmon:input>
		</salmon:td>
		<salmon:td>
		  <salmon:text name="teNumberCAP" font="TableHeadingFont" text="№ приказа:"/>&nbsp;
		  <salmon:a name="hlOfficeOrderLink" href="detail" target="_blank" datasource="dsOfficeOrder:'OfficeOrder.jsp?officeOrderId=' + OfficeOrder.officeOrderId">
		    <salmon:text name="teNumber" font="DefaultFont" text="458-98" datasource="dsOfficeOrder:OfficeOrder.externalNumber"/>
		  </salmon:a>
		  <br />
		  <salmon:text name="teDateCAP" font="TableHeadingFont" text="Дата:"/>&nbsp;
		  <salmon:text name="teDate" text="05.01.2006" font="DefaultFont" datasource="dsOfficeOrder:OfficeOrder.dateOrder" displayformat="dd.MM.yy"/>
		  <br />
		  <salmon:text name="teCategoryCAP" font="TableHeadingFont" text="Вид приказа:"/>
		  <salmon:text name="teCategory" text="Стипендия" font="DefaultFont" datasource="dsOfficeOrder:OfficeOrder.orderCategory.orderCategory"/>
		</salmon:td>
		<salmon:td>
		  <salmon:text name="teSubject" font="TextEditFont" text="Здесь должен быть большой текст приказа" datasource="dsOfficeOrder:OfficeOrder.subject"/>
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