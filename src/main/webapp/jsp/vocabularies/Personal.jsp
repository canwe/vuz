<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.PersonalController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernatePersonalDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernatePersonalModel" autoretrieve="Never">
    </salmon:datasource>
	<salmon:datasource name="HibernatePositionModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernatePositionModel" autoretrieve="Never">
    </salmon:datasource>
    <salmon:datasource name="HibernateDepartamentModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateDepartamentModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Личных данных"
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

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Личные данные" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernatePersonalDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernatePersonalDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                   <salmon:td>
                                        <salmon:text name="familyNamesCAP" text="Фамилия" font="TableHeadingFont"/>
                                   </salmon:td>
                                   <salmon:td>
                                        <salmon:text name="personalsCAP" text="Имя" font="TableHeadingFont"/>
                                   </salmon:td>
                                   <salmon:td>
                                        <salmon:text name="patronymicsCAP" text="Отчество" font="TableHeadingFont"/>
                                    </salmon:td>
									<salmon:td>
                                        <salmon:text name="positionsCAP" text="Должность" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="departamentsCAP" text="Отдел" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="familyNames" text="Personal.familyName Goes Here" font="DefaultFont"
                                                 datasource="HibernatePersonalDatasource:Personal.familyName"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="personals" text="Personal.name Goes Here" font="DefaultFont"
                                                 datasource="HibernatePersonalDatasource:Personal.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="patronymics" text="Personal.patronymic Goes Here" font="DefaultFont"
                                                 datasource="HibernatePersonalDatasource:Personal.patronymic"/>
                                </salmon:td>
								<salmon:td>
                                    <salmon:text name="positions" text="Personal.position.name Goes Here" font="DefaultFont"
                                                 datasource="HibernatePersonalDatasource:Personal.position.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="departaments" text="Personal.departament.name Goes Here" font="DefaultFont"
                                                 datasource="HibernatePersonalDatasource:Personal.departament.name"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Личные данные" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernatePersonalDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="personalCAP" text="Имя" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="personal" size="16" maxlength="25"
                                                  datasource="HibernatePersonalDatasource:Personal.name"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="patronymicCAP" text="Отчество" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="patronymic" size="16" maxlength="25"
                                                  datasource="HibernatePersonalDatasource:Personal.patronymic"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="familyNameCAP" text="Фамилия" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="familyName" size="16" maxlength="50"
                                                  datasource="HibernatePersonalDatasource:Personal.familyName"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="positionCAP" text="Должность" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="positionSelect" size="16" multiple="False"
 										datasource="HibernatePersonalDatasource:Personal.position.positionId">
 									</salmon:input>
								</td>
                            </tr>
                            <tr>
                                <td><salmon:text name="departamentCAP" text="Отдел" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="departamentSelect" size="16" multiple="False"
 										datasource="HibernatePersonalDatasource:Personal.departament.departmanentId">
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