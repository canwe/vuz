<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.vocabularies.controllers.UserController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true"/>

<salmon:form name="pageForm">
    <!--Page Content Begin-->
    &nbsp;
    <salmon:datasource name="datasource_qbe" type="QBE">
        <salmon:qbecriteria name="AllColumns" type="complex" columns="*"/>
    </salmon:datasource>
    <!--Page Content End-->
    <salmon:datasource name="HibernateUserDatasource" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateUserModel" autoretrieve="Never">
    </salmon:datasource>
	<salmon:datasource name="HibernateUserRoleModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernateUserRoleModel" autoretrieve="Never">
    </salmon:datasource>
    <salmon:datasource name="HibernatePersonalModel" type="MODEL"
                       model="com.itm.vuz.vocabularies.models.HibernatePersonalModel" autoretrieve="Never">
    </salmon:datasource>

    <salmon:box name="box1" width="50%">

        <salmon:searchformdisplaybox width="100%" name="searchformdisplaybox1" caption="Поиск Пользователя"
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

                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Пользователи" width="100%"
                                               addbuttonvisible="True" savebuttonvisible="True"
                                               datasource="HibernateUserDatasource"
                                               searchformdisplaybox="searchformdisplaybox1" visible="false">
                        <salmon:datatable name="datatable1" width="100%" datasource="HibernateUserDatasource">
                            <salmon:datatableheader>
                                <salmon:tr>
                                    <salmon:td>
                                        <salmon:text name="personalsCAP" text="ФИО" font="TableHeadingFont"/>
                                    </salmon:td>
									<salmon:td>
                                        <salmon:text name="userrolesCAP" text="Роль пользователя" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <salmon:td>
                                        <salmon:text name="loginsCAP" text="Логин" font="TableHeadingFont"/>
                                    </salmon:td>
                                    <%--salmon:td>
                                        <salmon:text name="passwordsCAP" text="Пароль" font="TableHeadingFont"/>
                                    </salmon:td--%>
                                </salmon:tr>
                            </salmon:datatableheader><salmon:datatablerows>
                            <salmon:tr>
                                <salmon:td>
                                    <salmon:text name="personals" text="User.personal Goes Here" font="DefaultFont"
                                                   datasource="HibernateUserDatasource:User.personal.familyName+' '+User.personal.name+' '+User.personal.patronymic"/>
                                </salmon:td>
								<salmon:td>
                                    <salmon:text name="userroles" text="User.UserRole.name Goes Here" font="DefaultFont"
                                                 datasource="HibernateUserDatasource:User.UserRole.name"/>
                                </salmon:td>
                                <salmon:td>
                                    <salmon:text name="logins" text="User.login Goes Here" font="DefaultFont"
                                                 datasource="HibernateUserDatasource:User.login"/>
                                </salmon:td>
                                <%--salmon:td>
                                    <salmon:text name="passwords" text="User.password Goes Here" font="DefaultFont"
                                                 datasource="HibernateUserDatasource:User.password"/>
                                </salmon:td--%>
                            </salmon:tr>
                        </salmon:datatablerows></salmon:datatable>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
                <td valign="top" width="50%"> <!--Detail Form-->
                    <salmon:box name="box4" width="100%">

                        <salmon:detailformdisplaybox name="d" caption="Пользователь" width="100%" addbuttonvisible="True"
                                                     deletebuttonvisible="True"
                                                     savebuttonvisible="True" cancelbuttonvisible="True"
                                                     datasource="HibernateUserDatasource"
                                                     visible="false">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><salmon:text name="personalCAP" text="ФИО" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="personalSelect" size="16" multiple="False"
                                                  datasource="HibernateUserDatasource:User.personal.personalId"></salmon:input></td>
                            </tr>
							<tr>
                                <td><salmon:text name="userroleCAP" text="Роль пользователя" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="select" name="userroleSelect" size="16" multiple="False"
 										datasource="HibernateUserDatasource:User.userRole.userRoleId">
 									</salmon:input>
								</td>
                            </tr>
                            <tr>
                                <td><salmon:text name="loginCAP" text="Логин" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="text" name="loginText" size="16" maxlength="15"
 										datasource="HibernateUserDatasource:User.login">
 									</salmon:input>
								</td>
                            </tr>
                            <tr>
                                <td><salmon:text name="passwordCAP" text="Пароль" font="ColumnCaptionFont"/></td>
                                <td><salmon:input type="password" name="passwordText" maxlength="20"
 										datasource="HibernateUserDatasource:User.password">
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
