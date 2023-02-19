<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>
<style type="text/css">
<!--
.disciplineStyle {writing-mode: tb-rl}
-->
</style>

<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
    	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
		<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>

<salmon:page controller="com.itm.vuz.common.controllers.BaseController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"></jsp:include>
<salmon:form name="pageForm">
<!--Page Content Begin-->

<vuz:groupcard name="mainGroupCard" semester="1" course="1" year="2005" group="750504" search="true" editnotes="true"/>

<!--Page Content End-->
</salmon:form>
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>