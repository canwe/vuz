<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.BaseDisciplineController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateBaseDisciplineDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateBaseDisciplineModel" autoretrieve="Never">
    </salmon:datasource>
    <salmon:datasource name="HibernateSubfacultyModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateSubfacultyModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Базовых Дисциплин"
                                     buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                     addbuttoncaption="Добавить" searchbuttoncaption="Искать"
                                     cancelbuttonvisible="True" qbebuilder="datasource_qbe" cellspacing="0">
            <table border="0" width="100%">
                <tr>
                    <td width="200"><salmon:text name="searchEditCAP" text="Название базовой дисциплины" font="ColumnCaptionFont"/></td>
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

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Базовые Дисциплины" width="100%"
                                               addbuttonvisible="True"  savebuttonvisible="True"
                                               datasource="HibernateBaseDisciplineDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateBaseDisciplineDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="baseDisciplinesCAP" text="Название" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="subfacultiesCAP" text="Специальность" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="baseDisciplines" text="BaseDiscipline.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateBaseDisciplineDatasource:BaseDiscipline.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="subfaculties" text="BaseDiscipline.subfaculty.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateBaseDisciplineDatasource:BaseDiscipline.subfaculty.name"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Базовая Дисциплина" width="100%"
                                                     addbuttonvisible="True" deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateBaseDisciplineDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="baseDisciplineCAP" text="Название" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="baseDiscipline" size="16" maxlength="50"
                                                  datasource="HibernateBaseDisciplineDatasource:BaseDiscipline.name"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="subfacultyCAP" text="Специальность" font="ColumnCaptionFont"/>

</td>
                                <td><salmon:input type="select" name="subfacultySelect" size="16" multiple="False"
 										datasource="HibernateBaseDisciplineDatasource:BaseDiscipline.subfaculty.subfacultyId">
 									</salmon:input>
								</td>
                            </tr>
                        </table>
                        </salmon:detailformdisplaybox>
                    </salmon:box></td>
            </tr>
        </table>
    </salmon:box>

<salmon:input type="hidden" name="refreshFlag" value="false"></salmon:input>
</salmon:form>
<jsp:include page="../TempReturnVocabularies.jsp" flush="true"/>
<jsp:include page="../TemplateAfter.jsp" flush="true"/>
<salmon:endpage/>