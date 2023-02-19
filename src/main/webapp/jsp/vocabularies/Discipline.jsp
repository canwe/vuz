<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.DisciplineController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
<!--Page Content Begin-->
&nbsp;
<salmon:datasource name="datasource_qbe" type="QBE" model="com.itm.vuz.vocabularies.models.DisciplinesQBEBuilder">
</salmon:datasource> 
<!--Page Content End-->
<salmon:datasource name="HibernateDisciplineDatasource" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateDisciplineModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateEducationPlanModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateEducationPlanModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateBaseDisciplineModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateBaseDisciplineModel" autoretrieve="Never">
</salmon:datasource>

<salmon:box name="box1" width="50%">

    <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Дисциплин"
                                 buttonbgcolor="#B0B0B0" addbuttonvisible="True" searchbuttonvisible="True"
                                 cancelbuttonvisible="True" cancelbuttoncaption="Отмена"
                                 searchbuttoncaption="Искать" addbuttoncaption="Добавить"
                                 qbebuilder="datasource_qbe" cellspacing="0">
        <table border="0" width="100%">
            <tr>
                <td width="200"><salmon:text name="searchEditCAP" text="Название базовой дисциплины" font="ColumnCaptionFont"/></td>
                <td><salmon:input type="text" name="searchEdit" size="16" maxlength="16"
                                  datasource="datasource_qbe:Discipline.baseDiscipline.name"></salmon:input></td>
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

        <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Дисциплины" width="100%"
                                   addbuttonvisible="True" savebuttonvisible="True"
                                   datasource="HibernateDisciplineDatasource"
                                   searchformdisplaybox="searchformdisplaybox1" visible="false">
            <salmon:datatable name="datatable1" width="100%" datasource="HibernateDisciplineDatasource">
                <salmon:datatableheader>
                    <salmon:tr>
                        <salmon:td>
                            <salmon:text name="baseDisciplinesCAP" text="Базовая дисциплина" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="educationPlansCAP" text="Учебный план" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="audiencehoursCAP" text="Аудиторные часы" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="independenthoursCAP" text="Самостоятельная работа" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="allhoursCAP" text="Общие часы" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="semestersCAP" text="Семестр" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="coursesCAP" text="Курс" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="yearsCAP" text="Год" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="checkingFormsCAP" text="Форма проверки" font="TableHeadingFont"/>
                        </salmon:td>
                    </salmon:tr>
                </salmon:datatableheader><salmon:datatablerows>
                <salmon:tr>
                    <salmon:td>
                        <salmon:text name="baseDisciplines" text="Discipline.baseDiscipline.name Goes Here"
                                     font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.baseDiscipline.name"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="educationPlans" text="Discipline.educationPlan.name Goes Here"
                                     font="DefaultFont" displayformat="dd/MM/yyyy"
                                     datasource="HibernateDisciplineDatasource:Discipline.educationPlan.dataStart+' - '+Discipline.educationPlan.dataEnd"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="audiencehourses" text="Discipline.audienceHours Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.audienceHours"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="independenthourses" text="Discipline.independentHours Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.independentHours"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="allhourses" text="Discipline.allHours Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.independentHours"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="semesters" text="Discipline.semester Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.semester"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="courses" text="Discipline.course Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.course"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="years" text="Discipline.year Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.year"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="checkingForms" text="Discipline.checkingForm Goes Here" font="DefaultFont"
                                     datasource="HibernateDisciplineDatasource:Discipline.checkingForm"/>
                    </salmon:td>
                </salmon:tr>
            </salmon:datatablerows></salmon:datatable>
        </salmon:listformdisplaybox>
    </salmon:box></td>
<td valign="top" width="50%"> <!--Detail Form-->
    <salmon:box name="box4" width="100%">

        <salmon:detailformdisplaybox name="d" caption="Дисциплина" width="100%" addbuttonvisible="True"
                                     deletebuttonvisible="True"
                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                     savebuttoncaption="Сохранить"
                                     datasource="HibernateDisciplineDatasource"
                                     visible="false">


            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td><salmon:text name="baseDisciplineCAP" text="Базовая Дисциплина" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="baseDisciplineSelect" size="16" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.baseDiscipline.baseDisciplineId">
                    </salmon:input>
                    </td>
                </tr>
                <tr>
                    <td><salmon:text name="educationPlanCAP" text="Учебный план" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="educationPlanSelect" size="16" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.educationPlan.educationPlanId">
                    </salmon:input>
                    </td>
                </tr>
                <tr>
                    <td><salmon:text name="audiencehourCAP" text="Аудиторные часы" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="text" name="audiencehours" size="5" maxlength="4" value=""
                                      datasource="HibernateDisciplineDatasource:Discipline.audienceHours"></salmon:input></td>
                </tr>
                <tr>
                    <td><salmon:text name="independenthourCAP" text="Самостоятельная работа" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="text" name="independenthours" size="5" maxlength="4" value=""
                                      datasource="HibernateDisciplineDatasource:Discipline.independentHours"></salmon:input></td>
                </tr>
                <tr>
                    <td><salmon:text name="semesterCAP" text="Семестр" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="semesterSelect" size="16" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.semester">
                    </salmon:input>
                    </td>
                </tr>
                <tr>
                    <td><salmon:text name="courseCAP" text="Курс" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="courseSelect" size="5" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.course"></salmon:input></td>
                </tr>
                <tr>
                    <td><salmon:text name="yearCAP" text="Год" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="yearSelect" size="5" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.year"></salmon:input></td>
                </tr>
                <tr>
                    <td><salmon:text name="checkingFormCAP" text="Форма проверки" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="checkingFormSelect" size="5" multiple="False"
                                      datasource="HibernateDisciplineDatasource:Discipline.checkingForm"></salmon:input></td>
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
