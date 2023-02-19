<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.SettlementController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateSettlementDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateSettlementModel" autoretrieve="Never">
    </salmon:datasource>
	<salmon:datasource name="HibernateSettlementDefinitionModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateSettlementDefinitionModel" autoretrieve="Never">
    </salmon:datasource>
    <salmon:datasource name="HibernateAreaModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateAreaModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Месторасположения"
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

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Месторасположения" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernateSettlementDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateSettlementDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="settlementsCAP" text="Название" font="TableHeadingFont"/>
                                    </salmon:td>
									<salmon:td>
                                        <salmon:text name="settlementDefinitionsCAP" text="Тип" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="areasCAP" text="Область" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="settlements" text="Settlement.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateSettlementDatasource:Settlement.name"/>
                                </salmon:td>
								<salmon:td>
                                    <salmon:text name="settlementDefinitions" text="Settlement.settlement.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateSettlementDatasource:Settlement.settlementDefinition.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="areas" text="Address.settlement.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateSettlementDatasource:Settlement.area.name"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Месторасположение" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateSettlementDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="settlementCAP" text="Название" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="address" size="16" maxlength="50"
                                                  datasource="HibernateSettlementDatasource:Settlement.name"></salmon:input></td>
                            </tr>
							<tr>
                                <td><salmon:text name="settlementDefinitionCAP" text="Тип" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="settlementDefinitionSelect" size="16" multiple="False"
 										datasource="HibernateSettlementDatasource:Settlement.settlementDefinition.settlementDefinitionId">
 									</salmon:input>
								</td>
                            </tr>
                            <tr>
                                <td><salmon:text name="areaCAP" text="Область" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="areaSelect" size="16" multiple="False"
 										datasource="HibernateSettlementDatasource:Settlement.area.areaId">
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
