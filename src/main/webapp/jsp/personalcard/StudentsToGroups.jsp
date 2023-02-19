<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Распределение студентов по группам</title>
<salmon:page controller="com.itm.vuz.personalcard.controllers.StudentsToGroupsController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<!--<salmon:body/>-->
<salmon:form name="pageForm"> 
<!--Page Content Begin-->

<script language="javascript" type="text/javascript">
  function ChangeFreeChecked(chk) {
    for (i = 0; i < document.forms['pageForm'].elements.length; i++) {
	  var element = document.forms['pageForm'].elements[i];
	  if (element.name.indexOf('chbFreeCheck') != -1) {
	    element.checked = chk;
	  };
	}
  }

  function ChangeGroupChecked(chk) {
    for (i = 0; i < document.forms['pageForm'].elements.length; i++) {
	  var element = document.forms['pageForm'].elements[i];
	  if (element.name.indexOf('chbGroupCheck') != -1) {
	    element.checked = chk;
	  };
	}
  }  
</script>

<salmon:datasource name="dsFreeStudent" type="MODEL" model="com.itm.vuz.personalcard.models.StudentModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="dsStudentInGroup" type="MODEL" model="com.itm.vuz.personalcard.models.StudentModel" autoretrieve="Never">
</salmon:datasource>


  <table width="100%" cellpadding="4" cellspacing="0">
    <tr>
	  <td align="center" valign="top" width="50%">
		<salmon:displaybox name="dbSearchFreeStudents" caption="Выбор студентов без групп" width="100%"><salmon:displayboxcontents>
			<table width="100%" >
			  <tr>
				<td nowrap="nowrap"><salmon:text name="teLanguageCAP" text="Язык" font="ColumnCaptionFont" /></td>
				<td nowrap="nowrap">
				  <salmon:input type="select" name="ddlLanguage" size="16">
					<salmon:option key="" display="" table="LANGUAGES" keycolumn="LANGUAGE_ID" displaycolumn="NAME" nulloption="False" />
				  </salmon:input>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="2">
				  <salmon:input name="sbtSearchStudents" type="submit" value="Выбрать"></salmon:input>&nbsp;
				  <salmon:input name="sbtAddToGroup" type="submit" value="Добавить в группу >>"></salmon:input>
				</td>
			  </tr>
			  <tr>
			    <td colspan="2">
				  <salmon:datatable name="dtFreeStudents" width="100%" datasource="dsFreeStudent" ><salmon:datatableheader>
				      <salmon:tr>
				        <salmon:td width="*" valign="Middle" align="Center" clicksort="False">
						  <input type="checkbox" onClick="ChangeFreeChecked(checked)" />
						</salmon:td>
				        <salmon:td><salmon:text name="teFreeNameCAP" text="ФИО студента" font="TableHeadingFont" /></salmon:td>
				      </salmon:tr>
				      </salmon:datatableheader><salmon:datatablerows>
				      <salmon:tr>
				        <salmon:td valign="Middle" align="Center">
				          <salmon:input type="checkbox" name="chbFreeCheck" datasource="dsFreeStudent:Check"></salmon:input>
				          </salmon:td>
				        <salmon:td>
						  <salmon:text name="teFreeFamilyNameTXT" text="s.familyName Goes Here" font="DefaultFont" datasource="dsFreeStudent:s.familyName"/>&nbsp;
				          <salmon:text name="teFreeNameTXT" text="s.name Goes Here" font="DefaultFont" datasource="dsFreeStudent:s.name"/>&nbsp;
						  <salmon:text name="teFreePatronymicTXT" text="s.patronymic Goes Here" font="DefaultFont" datasource="dsFreeStudent:s.patronymic"/>
				        </salmon:td>
				      </salmon:tr>
				    </salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>
				  </td>
			  </tr>
			</table>
		  </salmon:displayboxcontents></salmon:displaybox>
    </td>
	<td align="center" valign="top">
	  <salmon:displaybox name="dbStudentsInGroup" caption="Состав группы" width="100%"><salmon:displayboxcontents>
			<table width="100%" >
			  <tr>
				<td nowrap="nowrap"><salmon:text name="teStudentGroupCAP" text="Выберите группу" font="ColumnCaptionFont" /></td>
				<td nowrap="nowrap">
				  <salmon:input type="select" name="ddlStudentGroups" size="16">
					<salmon:option key="" display="" table="STUDENT_GROUP" keycolumn="STUDENT_GROUP_ID" displaycolumn="NUMBER" nulloption="False" />
				  </salmon:input>

				</td>
			  </tr>
			  <tr>
			    <td colspan="2">
				  <salmon:input name="sbtShowGroupStudents" type="submit" value="Показать"></salmon:input>&nbsp;
				  <salmon:input name="sbtRemoveFromGroup" type="submit" value="<< Удалить из группы"></salmon:input>
				</td>
			  </tr>
			  <tr>
			    <td colspan="2">
				  <salmon:datatable name="dtStudentsInGroup" width="100%" datasource="dsStudentInGroup" ><salmon:datatableheader>
				      <salmon:tr>
				        <salmon:td width="*" valign="Middle" align="Center" clicksort="False">
						  <input type="checkbox" onClick="ChangeGroupChecked(checked)" />
						</salmon:td>
				        <salmon:td><salmon:text name="teNameCAP" text="ФИО студента" font="TableHeadingFont" /></salmon:td>
				      </salmon:tr>
				      </salmon:datatableheader><salmon:datatablerows>
				      <salmon:tr>
				        <salmon:td valign="Middle" align="Center">
				          <salmon:input type="checkbox" name="chbGroupCheck" datasource="dsStudentInGroup:Check"></salmon:input>
				          </salmon:td>
				        <salmon:td>
						  <salmon:text name="teFamilyNameTXT" text="s.familyName Goes Here" font="DefaultFont" datasource="dsStudentInGroup:s.familyName"/>&nbsp;
				          <salmon:text name="teNameTXT" text="s.name Goes Here" font="DefaultFont" datasource="dsStudentInGroup:s.name"/>&nbsp;
						  <salmon:text name="tePatronymicTXT" text="s.patronymic Goes Here" font="DefaultFont" datasource="dsStudentInGroup:s.patronymic"/>
				        </salmon:td>
				      </salmon:tr>
				    </salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>
				  </td>
			  </tr>			  
			</table>		
		</salmon:displayboxcontents></salmon:displaybox>
	</td>
  </tr>
</table>

<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>
</html>