<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.DepartamentController"/> <!---->
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateDepartamentDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateDepartamentModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Отделов"
                                     buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                     cancelbuttonvisible="True" cancelbuttoncaption="Отмена"
                                     searchbuttoncaption="Искать" addbuttoncaption="Добавить"
                                     qbebuilder="datasource_qbe" cellspacing="0">
            <table border="0" width="100%">
                <tr>
                    <td width="200"><salmon:text name="searchEditCAP" text="Название отдела" font="ColumnCaptionFont"/></td>
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
                <td valign="top" width="60%">
                    <salmon:box name="box3" width="100%">

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Отделы" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernateDepartamentDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateDepartamentDatasource"><salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="departamentsCAP" text="Название" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="departaments" text="Departament.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateDepartamentDatasource:Departament.name"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="40%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Отдел" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateDepartamentDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="departamentCAP" text="Название" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="departament" size="16" maxlength="50"
                                                  datasource="HibernateDepartamentDatasource:Departament.name"></salmon:input></td>
                            </tr>
                        </table>
                        </salmon:detailformdisplaybox>
                    </salmon:box></td>
            </tr>
        </table>
    </salmon:box>

</salmon:form>
<jsp:include page="../TempReturnVocabularies.jsp" flush="true"/>
<jsp:include page="../TemplateAfter.jsp" flush="true"/>
<salmon:endpage/>