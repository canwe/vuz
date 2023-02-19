<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.SpecialityController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateSpecialityDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateSpecialityModel" autoretrieve="Never">
    </salmon:datasource>
	<salmon:datasource name="HibernateFacultyModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateFacultyModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Специальности"
                                     buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                     addbuttoncaption="Добавить" searchbuttoncaption="Поиск"
                                     cancelbuttonvisible="True" qbebuilder="datasource_qbe" cellspacing="0">
            <table border="0" width="100%">
                <tr>
                    <td width="200"><salmon:text name="searchEditCAP" text="Поиск по всем столбцам" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="text" name="searchEdit" size="16" maxlength="16"
                                      datasource="datasource_qbe:AllColumns"></salmon:input></td>
                </tr>
            </table>
        </salmon:searchformdisplaybox>
    </salmon:box>  <br>

    <!--ListForm-->
    <salmon:box name="box2" visible="False">
        <table width="100%" bgcolor="#cccccc" border="0">
            <tr>
                <td valign="top" width="50%">
                    <salmon:box name="box3" width="100%">

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Специальности" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernateSpecialityDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateSpecialityDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="specialitiesCAP" text="Название" font="TableHeadingFont"/>
                                    </salmon:td>
									 <salmon:td>
                                        <salmon:text name="shortnamesCAP" text="Сокращенное название" font="TableHeadingFont"/>
                                    </salmon:td>                                   
									<salmon:td>
                                        <salmon:text name="facultiesCAP" text="Факультет" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="numbersCAP" text="Номер" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="specialities" text="Speciality.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateSpecialityDatasource:Speciality.name"/>
                                </salmon:td>
								<salmon:td>
                                    <salmon:text name="shortnames" text="Speciality.shortname Goes Here" font="DefaultFont"
                                                 datasource="HibernateSpecialityDatasource:Speciality.shortname"/>
                                </salmon:td>                                
								<salmon:td>
                                    <salmon:text name="faculties" text="Speciality.faculty.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateSpecialityDatasource:Speciality.faculty.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="specialityNumbers" text="Speciality.number Goes Here" font="DefaultFont"
                                                 datasource="HibernateSpecialityDatasource:Speciality.number"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Специальность" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateSpecialityDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="specialityCAP" text="Название" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="speciality" size="16" maxlength="75"
                                                  datasource="HibernateSpecialityDatasource:Speciality.name"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="shortnameCAP" text="Сокращенное название" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="shortname" size="16" maxlength="7"
                                                  datasource="HibernateSpecialityDatasource:Speciality.shortname"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="facultyCAP" text="Факультет" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="facultySelect" size="16" multiple="False"
 										datasource="HibernateSpecialityDatasource:Speciality.faculty.facultyId">
 									</salmon:input>
								</td>
                            </tr>
							   <tr>
                                <td><salmon:text name="specialityNumberCAP" text="Номер" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="number" size="16" maxlength="10"
                                                  datasource="HibernateSpecialityDatasource:Speciality.number"></salmon:input></td>
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