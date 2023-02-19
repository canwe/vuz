<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.StudentGroupController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>

<salmon:datasource name="HibernateStudentGroupDatasource" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateStudentGroupModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateSpecialityModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateSpecialityModel" autoretrieve="Never">
</salmon:datasource>

    <salmon:datasource name="HibernatePersonalModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernatePersonalModel" autoretrieve="Never">
    </salmon:datasource>

<salmon:box name="box1" width="50%">
    <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Групп"
                                 buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                 addbuttoncaption="Добавить" searchbuttoncaption="Искать"
                                 cancelbuttonvisible="True" qbebuilder="datasource_qbe" cellspacing="0">
        <table border="0" width="100%">
            <tr>
                <td width="200"><salmon:text name="searchEditCAP" text="Поиск по всем столбцам" font="ColumnCaptionFont"/></td>
                <td><salmon:input type="text" name="searchEdit" size="16" maxlength="16"
                                  datasource="datasource_qbe:AllColumns"></salmon:input></td>
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

                      <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Группы" width="100%"
                                                 addbuttonvisible="True" savebuttonvisible="True"
                                                 datasource="HibernateStudentGroupDatasource"
                                                 searchformdisplaybox="searchformdisplaybox1" visible="false">
                          <salmon:datatable name="datatable1" width="100%" datasource="HibernateStudentGroupDatasource">
                              <salmon:datatableheader>
                                  <salmon:tr>
                                      <salmon:td>
                                          <salmon:text name="curatorsCAP" text="Куратор" font="TableHeadingFont"/>
                                      </salmon:td>
  									<salmon:td>
                                          <salmon:text name="specialitiesCAP" text="Специальность" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                          <salmon:text name="coursesCAP" text="Курс" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                          <salmon:text name="educationdurationsCAP" text="Длительность обучения" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                          <salmon:text name="numbersCAP" text="Номер" font="TableHeadingFont"/>
                                    </salmon:td>
                                  </salmon:tr>
                              </salmon:datatableheader><salmon:datatablerows>
                              <salmon:tr>
                                  <salmon:td>
                                      <salmon:text name="curators" text="StudentGroup.personal Goes Here" font="DefaultFont"
                                                   datasource="HibernateStudentGroupDatasource:StudentGroup.personal.familyName+' '+StudentGroup.personal.name+' '+StudentGroup.personal.patronymic"/>
                                  </salmon:td>
  								  <salmon:td>
                                      <salmon:text name="specialities" text="StudentGroup.speciality.name Goes Here" font="DefaultFont"
                                                   datasource="HibernateStudentGroupDatasource:StudentGroup.speciality.name"/>
                                  </salmon:td>
                                  <salmon:td>
                                      <salmon:text name="courses" text="StudentGroup.course Goes Here" font="DefaultFont"
                                                   datasource="HibernateStudentGroupDatasource:StudentGroup.course"/>
                                  </salmon:td>
                                  <salmon:td>
                                      <salmon:text name="educationdurations" text="StudentGroup.educationDuration Goes Here" font="DefaultFont"
                                                   datasource="HibernateStudentGroupDatasource:StudentGroup.educationDuration"/>
                                  </salmon:td>
                                  <salmon:td>
                                      <salmon:text name="numbers" text="StudentGroup.number Goes Here" font="DefaultFont"
                                                   datasource="HibernateStudentGroupDatasource:StudentGroup.number"/>
                                  </salmon:td>
                              </salmon:tr>
                          </salmon:datatablerows></salmon:datatable>
                      </salmon:listformdisplaybox>
                  </salmon:box></td>
                  <td valign="top" width="50%"> <!--Detail Form-->
                      <salmon:box name="box4" width="100%">

                          <salmon:detailformdisplaybox name="d" caption="Группа" width="100%" addbuttonvisible="True"
                                                       deletebuttonvisible="True"
                                                       savebuttonvisible="True" cancelbuttonvisible="True"
                                                       datasource="HibernateStudentGroupDatasource"
                                                       visible="false">


                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                  <td><salmon:text name="curatorCAP" text="Куратор" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="curatorSelect" size="16" multiple="False"
                                                    datasource="HibernateStudentGroupDatasource:StudentGroup.personal.personalId"></salmon:input></td>
                              </tr>
  							<tr>
                                  <td><salmon:text name="specialityCAP" text="Специальность" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="specialitySelect" size="16" multiple="False"
   										datasource="HibernateStudentGroupDatasource:StudentGroup.speciality.specialityId">
   									</salmon:input>
  								</td>
                              </tr>
                           <tr>
                                  <td><salmon:text name="courseCAP" text="Курс" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="courseSelect" size="16" multiple="False"
                                      datasource="HibernateStudentGroupDatasource:StudentGroup.course">
                            </salmon:input>
                            </td>
                          </tr>
                           <tr>
                                  <td><salmon:text name="educationdurationCAP" text="Длительность обучения" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="select" name="educationduration" size="8" multiple="False"
                                       datasource="HibernateStudentGroupDatasource:StudentGroup.educationDuration">
                                       <salmon:option key="true" display="Да"/>
                                       <salmon:option key="false" display="Нет"/>
                                        </salmon:input>
                                    </td>
                           </tr>
                               <tr>
                                  <td><salmon:text name="numberCAP" text="Номер" font="ColumnCaptionFont"/></td>
                                  <td><salmon:input type="text" name="number" size="8" maxlength="7"
                                       datasource="HibernateStudentGroupDatasource:StudentGroup.number"></salmon:input></td>
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
