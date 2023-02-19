<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<!-- BaseController child class should be used as page Controller to handle the shared GUI elements -->
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>

<salmon:page  controller="com.itm.vuz.common.controllers.HomePageController" contenttype="text/html; charset=UTF-8"/>
<jsp:include page="TemplateBefore.jsp" flush="true"></jsp:include>

<salmon:form name="pageForm">
<!--Page Content Begin-->

<table width="550" border="0" cellspacing="5" cellpadding="5">
  <tr>
    <td width="336"><p><img src="images/asu.gif" width="192" height="191" align="left">
        <span style="font-size: 12px">АСУ «<em>УЧЕБНЫЙ ПРОЦЕСС</em>»(АСУ ВУЗ) - интегрированный комплекс подсистем, обеспечивающий информационную поддержку процессу управления образовательной деятельностью университета и его филиалов. </span></p>
    </td>
    <td width="179">
    <p align="left"><span style="font-size: 12px">Пользователь:</span><br>
          <span style="font-size: 14px"><salmon:text name="userName" text="Administrator"/></span><br><br>
          <span style="font-size: 12px">Роль в системе:</span><br>
          <span style="font-size: 14px"><salmon:text name="userRole" text="Administrator"/></span><br>
    </p>
    </td>
  </tr>
  <tr>
    <td><p><img src="images/groupCard.gif" width="46" height="54" align="left"> <strong><br>
          <a href="groupcard/GroupCard.jsp">Возможности:</a></strong>
        </p>
      <ul>
        <li class="style1"><a href="groupcard/GroupCard.jsp">Просмотр и формирование в Excel Учебной Карты по группе </a></li>
        <li class="style1"><a href="examlist/ExamList.jsp?isAdditional=false"> Формирование экзаменационной ведомости/контрольного листа и ввод оценок с ведомости по группе </a></li>
        <li><a href="examlist/ExamList.jsp?isAdditional=true"><span class="style1"> Формирование дополнительной экзаменационной ведомости </span></a><br>
              <br>
          </li>
      </ul></td>
    <td><p><img src="images/personalCard.gif" width="50" height="50" align="left"> <strong><br>
          <a href="personalcard/PersonalCard.jsp">Возможности:</a></strong>
      <ul>
        <li class="style1"><a href="personalcard/PersonalCard.jsp">Ввод и редактирование Карточки Студента </a></li>
        <li class="style1"><a href="personalcard/StudentsToGroups.jsp"> Составление списков групп студентов </a></li>
        <li class="style1"><a href="personalcard/StudentSearch.jsp"> Формирование списков студентов по различным критериям </a></li>
        <li class="style1"><a href="personalcard/OfficeOrderSearch.jsp"> Формирование приказов по студентам </a></li>
      </ul></td>
  </tr>
  <tr>
<!--
    <td> <p><img src="images/graduate.gif" width="48" height="40" align="left"><strong>      <br>
          <a href="graduate/Graduate.jsp">Возможности:</a></strong>
      <ul>
        <li class="style1"><a href="graduate/Graduate.jsp">Формирование вкладыша к диплому </a></li>
        <li class="style1"><a href="graduate/Graduate.jsp"> Редактирование Карточки Выпускника </a></li>
      </ul></td>
-->
    <td> <p><img src="images/subFaculty.gif" width="49" height="55" align="left"><strong>      <br>
         <a href="edplans/EducationPlans.jsp">Возможности:</a></strong>
      <ul>
        <li class="style1"><a href="edplans/EducationPlans.jsp">Ведение учебных программ и планов </a></li>
        <li class="style1"><a href="VocabulariesList.jsp"> Ввод и редактирование справочников </a></li>
      </ul></td>
  </tr>
<!--
  <tr>
    <td><p><img src="images/security.gif" width="38" height="46"> <strong><a href="administration/Administration.jsp">Возможности:</a></strong> </p>
      <ul>
        <li class="style1"><a href="administration/Administration.jsp">Администрирование АСУ</a></li>
        <li class="style1"><a href="administration/Administration.jsp">Настройка прав пользователей</a></li>
        <li class="style1"><a href="administration/Administration.jsp"> Миграция данных из существующей АСУ Приемной комиссии в АСУ «ВУЗ» </a></li>
      </ul></td>
    <td>&nbsp;</td>
  </tr>
-->
</table>


<!--Page Content End-->
</salmon:form>

<jsp:include page="TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>