<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Список студентов</title>
	<head>
		<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
		<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
		<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>	
	</head>
<salmon:page controller="com.itm.vuz.personalcard.controllers.StudentBasketController"/>
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

<salmon:datasource name="dsStudents" type="MODEL" model="com.itm.vuz.personalcard.models.StudentModel" autoretrieve="Never">
</salmon:datasource>


<salmon:displaybox name="dboxStudentsBox" caption="Список студентов" width="100%">
	 <salmon:displayboxheader>
		<div style="text-align:left;white-space:normal">
	          <salmon:input type="submit" name="sbtAddOrder" value="Зарегистрировать приказ"></salmon:input>
			  &nbsp;
			  <salmon:input type="submit" name="sbtRemoveSelected" value="Удалить отмеченных"></salmon:input>
			  &nbsp;
			  <salmon:input type="submit" name="sbtClearList" value="Удалить всех"></salmon:input>
			  <hr color="#999999" />
		      Дата:<vuz:calendar name="clndEventDate" />&nbsp;
			  Событие:
			  <salmon:input type="select" name="ddlStudentEvent" size="16" multiple="False">
                <salmon:option key="" display="" keycolumn="EVENT_ID" displaycolumn="NAME" table="STUDENT_EVENT" nulloption="False" />
              </salmon:input>
			  <br />
			  Комментарий:<salmon:input type="text" name="teEventComment" size="50" maxlength="250"></salmon:input>&nbsp;
			  <salmon:input type="submit" name="sbtAddEvent" value="Добавить событие"></salmon:input>
		</div>
	</salmon:displayboxheader><salmon:displayboxcontents>
					  
        <salmon:datatable name="dtStudents" width="100%" datasource="dsStudents" ><salmon:datatableheader>
            <salmon:tr>
			  <salmon:td width="*" align="Center" valign="Middle" clicksort="False">
			    <input type="checkbox" onClick="ChangeChecked(checked)" />
		      </salmon:td>
              <salmon:td>
                <salmon:text name="familyNameCAP4" text="Студент" font="TableHeadingFont" />
              </salmon:td>
            </salmon:tr>
          </salmon:datatableheader><salmon:datatablerows>
            <salmon:tr>
			  <salmon:td align="Center" valign="Middle">
			    <salmon:input name="chbCheck" type="checkbox" datasource="dsStudents:Check"></salmon:input>
			  </salmon:td>
              <salmon:td>
			    <salmon:a name="hlPersonalCardLink" href="detail" target="_blank" datasource="dsStudents:'PersonalCard.jsp?student_id=' + s.studentId">
                  <salmon:text name="familyNameTXT" text="s.familyName Goes Here" font="DefaultFont" datasource="dsStudents:s.familyName"/>&nbsp;
				  <salmon:text name="NameTXT3" text="s.name Goes Here" font="DefaultFont" datasource="dsStudents:s.name"/>&nbsp;
				  <salmon:text name="patronymicTXT" text="s.patrinymic Goes Here" font="DefaultFont" datasource="dsStudents:s.patronymic"/>
				</salmon:a>
              </salmon:td>
            </salmon:tr>
          </salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>  
        </salmon:displayboxcontents></salmon:displaybox>
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>