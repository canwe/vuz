<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<!-- BaseController child class shoudl be used as page Controller to handle the shared GUI elements -->
<salmon:page  controller="com.itm.vuz.common.controllers.BaseController" contenttype="text/html; charset=UTF-8"/>
<jsp:include page="TemplateBefore.jsp" flush="true" ></jsp:include>

<salmon:form name="pageForm">
<!--Page Content Begin-->

 &nbsp;
<salmon:text name="text1" text="Hello , it's JSP template for new JSP creation" font="PageHeadingFont"/>

<!--Page Content End-->
</salmon:form>

<jsp:include page="TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>