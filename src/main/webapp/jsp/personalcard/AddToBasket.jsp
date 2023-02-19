<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Добавление в список</title>
<salmon:page controller="com.itm.vuz.personalcard.controllers.AddToBasketController"/>
<!--<salmon:body/>-->
<salmon:form name="pageForm"> 
<!--Page Content Begin-->
<salmon:text name="teErrorMsg" text="Неизвестная ошибка!" font="ErrorFont"/>
<salmon:text name="teInfoMsg" text="Студент добавлен в список" font="DefaultFont"/>
<br />
<salmon:text name="teCountCaption" text="Студентов в списке:" font="DefaultFont"/>&nbsp;
<salmon:text name="teCount" text="0" font="DefaultFont"/>
<br />
<salmon:a name="hlShowBasket" href="StudentBasket.jsp">Открыть список</salmon:a>&nbsp;
<salmon:a name="hlClose" href="" onclick="window.close()">Закрыть</salmon:a>
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<salmon:endpage/>
</html>