<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8" %>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.DisciplineTeacherController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
<!--Page Content Begin-->
&nbsp;
<salmon:datasource name="datasource_qbe" type="QBE" model="com.itm.vuz.vocabularies.models.DisciplineTeacherQBEBuilder">
</salmon:datasource>
<!--Page Content End-->
<salmon:datasource name="HibernateDisciplineTeacherDatasource" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateDisciplineTeacherModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateTeacherModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateTeacherModel" autoretrieve="Never">
</salmon:datasource>
<salmon:datasource name="HibernateDisciplineModel" type="MODEL"
                   model="com.itm.vuz.vocabularies.models.HibernateDisciplineModel" autoretrieve="Never">
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
                                  datasource="datasource_qbe:DisciplineTeacher.discipline.baseDiscipline.name"></salmon:input></td>
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
                                   datasource="HibernateDisciplineTeacherDatasource"
                                   searchformdisplaybox="searchformdisplaybox1" visible="false">
            <salmon:datatable name="datatable1" width="100%" datasource="HibernateDisciplineTeacherDatasource"><salmon:datatableheader>
                    <salmon:tr>
                        <salmon:td>
                            <salmon:text name="disciplinesCAP" text="Дисциплина" font="TableHeadingFont"/>
                        </salmon:td>
                        <salmon:td>
                            <salmon:text name="teachersCAP" text="Преподаватель" font="TableHeadingFont"/>
                        </salmon:td>
                    </salmon:tr>
                </salmon:datatableheader><salmon:datatablerows>
                <salmon:tr>
                    <salmon:td>
                        <salmon:text name="disciplines" text="DisciplineTeacher.discipline.name Goes Here"
                                     font="DefaultFont"
                                     datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.discipline.baseDiscipline.name"/>
                    </salmon:td>
                    <salmon:td>
                        <salmon:text name="teacherss" text="DisciplineTeacher.teacher.personal.familyName Goes Here"
                                     font="DefaultFont"
                                     datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.teacher.personal.familyName"/>
                        <salmon:text name="teacherss1" text="DisciplineTeacher.teacher.personal.name Goes Here"
                                     font="DefaultFont"
                                     datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.teacher.personal.name"/>
                        <salmon:text name="teacherss2" text="DisciplineTeacher.teacher.personal.patronymic Goes Here"
                                     font="DefaultFont"
                                     datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.teacher.personal.patronymic"/>
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
                                     datasource="HibernateDisciplineTeacherDatasource"
                                     visible="false">


            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td><salmon:text name="disciplineCAP" text="Дисциплина" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="disciplineSelect" size="16" multiple="False"
                                      datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.discipline.disciplineId">
                    </salmon:input>
                    </td>
                </tr>
                <tr>
                    <td><salmon:text name="teacherCAP" text="Преподаватель" font="ColumnCaptionFont"/></td>
                    <td><salmon:input type="select" name="teacherSelect" size="16" multiple="False"
                                      datasource="HibernateDisciplineTeacherDatasource:DisciplineTeacher.teacher.teacherId">
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