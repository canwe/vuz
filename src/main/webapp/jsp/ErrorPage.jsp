<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.common.controllers.BaseController"/>
<jsp:include page="TemplateBefore.jsp" flush="true"></jsp:include>
<salmon:form name="pageform">
	<salmon:text name="text1" text="Welcome to VUZ!" font="PageHeadingFont"/>
	<br/>
    <salmon:a name="SOFIAHome" href="%VUZHome">
        <salmon:text name="text18" text="SOFIA Home Page" font="LinkFont"/>
    </salmon:a>
    <br/>
	<salmon:a name="homepagelink2" href="%SOFIAHome"/>
	<br/>
	<salmon:a name="downloadLink" href="%SOFIASourceForgePage">
	    <salmon:img name="sourceForgeLogoImage" src="/jsp/images/powered_by_sofia.gif" width="88" height="31" border="0" alt="Download SOFIA from SourceForge"/>
	</salmon:a>
</salmon:form>
<jsp:include page="TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>