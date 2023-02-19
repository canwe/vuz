<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.AreaController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateAreaDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateAreaModel" autoretrieve="Never">
    </salmon:datasource>
    <salmon:datasource name="HibernateRegionModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateRegionModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Областей"
                                     buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                     cancelbuttonvisible="True" cancelbuttoncaption="Отмена"
                                     searchbuttoncaption="Искать" addbuttoncaption="Добавить"
                                     qbebuilder="datasource_qbe" cellspacing="0">
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

                        <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Области" width="100%"
                                                   addbuttonvisible="True" savebuttonvisible="True"
                                                   datasource="HibernateAreaDatasource"
                                                   searchformdisplaybox="searchformdisplaybox1" visible="false">
                            <salmon:datatable name="datatable1" width="100%" datasource="HibernateAreaDatasource"><salmon:datatableheader>
                                    <salmon:tr>
                                        <salmon:td>
                                            <salmon:text name="areasCAP" text="Область" font="TableHeadingFont"/>
                                        </salmon:td>
                                        <salmon:td>
                                            <salmon:text name="regionsCAP" text="Регион" font="TableHeadingFont"/>
                                        </salmon:td>
                                    </salmon:tr>
                                </salmon:datatableheader><salmon:datatablerows>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="areas" text="Area.area Goes Here" font="DefaultFont"
                                                     datasource="HibernateAreaDatasource:Area.name"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="regions" text="Area.region.name Goes Here" font="DefaultFont"
                                                     datasource="HibernateAreaDatasource:Area.region.name"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatablerows></salmon:datatable>
                        </salmon:listformdisplaybox>
                    </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Область" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateAreaDatasource"
                                                     visible="false">


                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td><salmon:text name="areaCAP" text="Область" font="ColumnCaptionFont"/></td>
                                    <td><salmon:input type="text" name="area" size="16" maxlength="50"
                                                      datasource="HibernateAreaDatasource:Area.name"></salmon:input></td>
                                </tr>
                                <tr>
                                    <td><salmon:text name="regionCAP" text="Регион" font="ColumnCaptionFont"/></td>
                                    <td><salmon:input type="select" name="regionSelect" size="16" multiple="False"
                                                      datasource="HibernateAreaDatasource:Area.region.regionId">
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