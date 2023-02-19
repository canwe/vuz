<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.CardController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
<!--Page Content Begin-->
&nbsp;
<salmon:datasource name="datasource_qbe" type="QBE" model="com.itm.vuz.vocabularies.models.CardQBEBuilder">
</salmon:datasource>

<%--<salmon:datasource name="datasource_qbe" type="QBE">
    <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
</salmon:datasource>--%>

<!--Page Content End-->
<salmon:datasource name="HibernateCardDatasource" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateCardModel" autoretrieve="Never">
</salmon:datasource>

<salmon:box name="box1" width="50%">

    <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Карточек"
                                 buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                 cancelbuttonvisible="True" cancelbuttoncaption="Отмена"
                                 searchbuttoncaption="Искать" addbuttoncaption="Добавить"
                                 qbebuilder="datasource_qbe" cellspacing="0">
        <table border="0" width="100%">
            <tr>
                <td width="200"><salmon:text name="searchEditCAP" text="Курс" font="ColumnCaptionFont"/></td>
                <td><salmon:input type="text" name="searchEdit" size="16" maxlength="16"
                                  datasource="datasource_qbe:Card.course"></salmon:input></td>
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

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Карточки" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernateCardDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateCardDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="coursesCAP" text="Курс" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="semestersCAP" text="Семестр" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="yearsCAP" text="Год" font="TableHeadingFont"/>
                                    </salmon:td>
                                </salmon:tr>
                            </salmon:datatableheader>
                            <salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="courses" text="Card.course Goes Here" font="DefaultFont"
                                                 datasource="HibernateCardDatasource:Card.course"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="semesters" text="Card.semester Goes Here" font="DefaultFont"
                                                 datasource="HibernateCardDatasource:Card.semester"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="years" text="Card.year Goes Here" font="DefaultFont"
                                                 datasource="HibernateCardDatasource:Card.year"/>
                                </salmon:td>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
            <td valign="top" width="40%"> <!--Detail Form-->
                <salmon:box name="box4" width="100%">

                    <salmon:detailformdisplaybox name="d" caption="Карточка" width="100%" addbuttonvisible="True"
                                                 deletebuttonvisible="True"
                                                 savebuttonvisible="True" cancelbuttonvisible="True"
                                                 datasource="HibernateCardDatasource"
                                                 visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="courseCAP" text="Курс" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="courseSelect" size="3" multiple="False"
                                                  datasource="HibernateCardDatasource:Card.course"></salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="semesterCAP" text="Семестр" font="ColumnCaptionFont"/></td>
                                <td><salmon:input name="semestr" type="select" size="16" multiple="False"
                                                  datasource="HibernateCardDatasource:Card.semester">
                                </salmon:input></td>
                            </tr>
                            <tr>
                                <td><salmon:text name="yearCAP" text="Год" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="yearSelect" size="4" multiple="False"
                                                  datasource="HibernateCardDatasource:Card.year"></salmon:input></td>
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
