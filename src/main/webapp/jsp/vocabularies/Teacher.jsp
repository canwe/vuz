<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.TeacherController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
<salmon:datasource name="datasource_qbe" type="QBE" model="com.itm.vuz.vocabularies.models.TeacherQBEBuilder">
    </salmon:datasource>

<salmon:datasource name="HibernateTeacherDatasource" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateTeacherModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateSubfacultyModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateSubfacultyModel" autoretrieve="Never">
</salmon:datasource>

    <salmon:datasource name="HibernatePersonalModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernatePersonalModel" autoretrieve="Never">
    </salmon:datasource>

<salmon:box name="box1" width="50%">
    <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Преподавателя"
                                 buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                 addbuttoncaption="Добавить" searchbuttoncaption="Поиск"
                                 cancelbuttonvisible="True" qbebuilder="datasource_qbe" cellspacing="0">
        <table border="0" width="100%">
            <tr>
                <td width="200"><salmon:text name="searchEditCAP" text="ФИО преподавателя" font="ColumnCaptionFont"/></td>
                <td><salmon:input type="text" name="searchEdit" size="16" maxlength="16"
                                  datasource="datasource_qbe:Teacher.personal.familyName"></salmon:input></td>
            </tr>
        </table>
    </salmon:searchformdisplaybox>
</salmon:box>

<br>

  <salmon:box name="box2" visible="False">
          <table width="100%" bgcolor="#cccccc" border="0">
              <tr>
                  <td valign="top" width="50%">
                      <salmon:box name="box3" width="100%">

                      <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Преподаватели" width="100%"
                                                 addbuttonvisible="True" savebuttonvisible="True"
                                                 datasource="HibernateTeacherDatasource"
                                                 searchformdisplaybox="searchformdisplaybox1" visible="false">
                          <salmon:datatable name="datatable1" width="100%" datasource="HibernateTeacherDatasource">
                              <salmon:datatableheader>
                                  <salmon:tr>
                                      <salmon:td>
                                          <salmon:text name="personalsCAP" text="ФИО" font="TableHeadingFont"/>
                                      </salmon:td>
  									<salmon:td>
                                          <salmon:text name="subfacultysCAP" text="Специальность" font="TableHeadingFont"/>
                                      </salmon:td>
                                  </salmon:tr>
                              </salmon:datatableheader><salmon:datatablerows>
                              <salmon:tr>
                                  <salmon:td>
                                      <salmon:text name="personals" text="Teacher.personal Goes Here" font="DefaultFont"
                                                   datasource="HibernateTeacherDatasource:Teacher.personal.familyName+' '+Teacher.personal.name+' '+Teacher.personal.patronymic"/>
                                     </salmon:td>
  								<salmon:td>
                                      <salmon:text name="subfacultys" text="Teacher.subfaculty.name Goes Here" font="DefaultFont"
                                                   datasource="HibernateTeacherDatasource:Teacher.subfaculty.name"/>
                                  </salmon:td>
                              </salmon:tr>
                          </salmon:datatablerows></salmon:datatable>
                      </salmon:listformdisplaybox>
                  </salmon:box></td>
                  <td valign="top" width="50%"> <!--Detail Form-->
                      <salmon:box name="box4" width="100%">

                          <salmon:detailformdisplaybox name="d" caption="Преподаватель" width="100%" addbuttonvisible="True"
                                                       deletebuttonvisible="True"
                                                       savebuttonvisible="True" cancelbuttonvisible="True"
                                                       datasource="HibernateTeacherDatasource"
                                                       visible="false">


                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                  <td><salmon:text name="personals1CAP" text="ФИО" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="personalSelect" size="16" multiple="False"
                                                    datasource="HibernateTeacherDatasource:Teacher.personal.personalId"></salmon:input></td>
                              </tr>
  							<tr>
                                  <td><salmon:text name="subfacultys1CAP" text="Кафедра" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="subfacultySelect" size="16" multiple="False"
   										datasource="HibernateTeacherDatasource:Teacher.subfaculty.subfacultyId">
   									</salmon:input>
  								</td>
                              </tr>
                          </table>
                          </salmon:detailformdisplaybox>
                      </salmon:box></td>
              </tr>
          </table>
      </salmon:box>
<salmon:input type="hidden" name="refreshFlag" value="false"/>
</salmon:form>

<jsp:include page="../TempReturnVocabularies.jsp" flush="true"/>
<jsp:include page="../TemplateAfter.jsp" flush="true"/>
<salmon:endpage/>
