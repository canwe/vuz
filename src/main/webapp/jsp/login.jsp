<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>

<salmon:page controller="com.itm.vuz.common.controllers.LoginController"/>

<salmon:form name="pageForm">
<salmon:datasource name="loginQBE" type="QBE" model="com.itm.vuz.common.models.LoginQBEBuilder">
</salmon:datasource>
<salmon:datasource name="usersModel" type="MODEL"
	model="com.itm.vuz.common.models.HibernateUsersModel" autoretrieve="Never">
</salmon:datasource>


<center>
<table width="550" height="100%" border="0" cellspacing="5" cellpadding="5">
  <tr valign="center">
    <td width="336"><p><img src="images/asu.gif" width="192" height="191" align="left"> <span style="font-size: 12px">      АСУ «<em>УЧЕБНЫЙ ПРОЦЕСС</em>»(АСУ ВУЗ) - интегрированный комплекс подсистем, обеспечивающий информационную поддержку процессу управления образовательной деятельностью университета и его филиалов. </span></p></td>
    <td width="220"><p align="left">
          <span style="font-size: 12px"><salmon:validator name="validator" breaksbefore="1" breaksafter="2" /></span>
          <span style="font-size: 12px">Пользователь:</span>
          <br/>
          <salmon:input maxlength="20" name="login" type="text" datasource="loginQBE:User.login"/>
          <br/>
          <span class="style1">Пароль</span>:
          <br/>
          <salmon:input  maxlength="20" name="password" type="password" datasource="loginQBE:User.password"/>
          <br/><br/>
          <salmon:input name="ok" value=" Вход " type="submit"/>
    </td>
  </tr>
</table>
</center>
</salmon:form>
<salmon:endpage/>

