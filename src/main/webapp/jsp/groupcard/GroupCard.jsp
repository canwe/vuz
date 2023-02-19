<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<style type="text/css">
<!--
  .disciplineStyle {
    writing-mode: tb-rl;
    color: black;
    text-decoration: underline;
    font-family:Arial;
    }
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
    	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
		<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>

<salmon:page controller="com.itm.vuz.groupcard.controllers.GroupCardController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"></jsp:include>
<salmon:form name="pageForm">
<!--Page Content Begin-->
<salmon:table name="tabBody" width="100%" border="0">
  <salmon:tr>
    <salmon:td valign="Top">
	  <salmon:table name="tabMenu" width="100%" height="100%" border="0" cellspacing="0" cellpadding="4">

          <salmon:td classname="MenuCell" width="20px">&nbsp;</salmon:td>

          <salmon:td classname="SelectedMenuCell" width="200px" height="20px">
		  <salmon:input type="submit" name="sbtGroupCard" value="Просмотр"></salmon:input><br>
		  <salmon:text name="teDesc1" text="Просмотр учебной карточки" font="DDLBFont"/>
		  </salmon:td>

	      <salmon:td classname="MenuCell" width="250px" height="20px">
		  <salmon:input type="submit" name="sbtGroupCardEdit" value="Редактирование"></salmon:input><br>
		  <salmon:text name="teDesc2" text="Редактирование учебной карточки" font="DDLBFont"/>
		  </salmon:td>

          <salmon:td classname="MenuCell" width="500px">&nbsp;</salmon:td>
   	  </salmon:table>
	</salmon:td></salmon:tr>

    <salmon:tr><salmon:td width="100%" valign="top">
	  <salmon:container name="ctGroupCard" visible="True">
        <vuz:groupcard name="mainGroupCard" search="true"/>
	  </salmon:container>
	  <salmon:container name="ctGroupCardEdit" visible="False">
        <vuz:groupcard name="mainGroupCardEdit" search="true" editnotes="true"/>
	  </salmon:container>
	</salmon:td>
  </salmon:tr>
</salmon:table>



<!--Page Content End-->
</salmon:form>
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>