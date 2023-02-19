<%@ taglib uri="/taglib.tld" prefix="salmon" %>
<%@ page errorPage="ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>

<salmon:page controller="com.itm.vuz.common.controllers.BaseVUZController" />
<jsp:include page="TemplateBefore.jsp" flush="true"/>
<salmon:form name="pageForm">
   <!--ListForm-->
   <salmon:box name="box2" width="50%">
        <table width="100%" bgcolor="#cccccc" border="0">
            <tr>
                <td valign="top" width="100%">
              <salmon:box name="box3" width="100%">
                    <salmon:listformdisplaybox name="l" mode="Display_single_page" caption="Справочники" width="100%">
                    <table width="100%" cellspacing="0" cellpadding="5" align="left">

                    <tr>
                     <td>
                     <salmon:text name="VocabulariesName" text="Наименование" font="TableHeadingFont"/>
                     </td></tr>

                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Address.jsp"><tt>Адреса</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/BaseDiscipline.jsp"><tt>Базовые дисциплины</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/StudentGroup.jsp"><tt>Группы</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Discipline.jsp"><tt>Дисциплины</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/DisciplineTeacher.jsp"><tt>Дисциплины и преподаватели</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Position.jsp"><tt>Должность</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Card.jsp"><tt>Карточки</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/OrderCategory.jsp"><tt>Категории приказов</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Subfaculty.jsp"><tt>Кафедры</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/FacultyGroupCode.jsp"><tt>Коды групп факультетов</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Personal.jsp"><tt>Личные данные</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Benefit.jsp"><tt>Льготы</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Settlement.jsp"><tt>Месторасположения</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Area.jsp"><tt>Область</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Hospice.jsp"><tt>Общежития</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Departament.jsp"><tt>Отделы</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/User.jsp"><tt>Пользователи</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Teacher.jsp"><tt>Преподаватели</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Region.jsp"><tt>Регионы</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/UserRole.jsp"><tt>Роли пользователей</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/StudentEvent.jsp"><tt>События</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Speciality.jsp"><tt>Специальности</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/StudentStatus.jsp"><tt>Статусы студентов</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Country.jsp"><tt>Страны</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/SettlementDefinition.jsp"><tt>Типы месторасположений</tt></a></td>
                    </tr>
                    <tr bgcolor="#eeeeee">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Faculty.jsp"><tt>Факультеты</tt></a></td>
                    </tr>
                    <tr bgcolor="#dddddd">
                    <td align="left">&nbsp;&nbsp;
                    <a href="/jsp/vocabularies/Language.jsp"><tt>Языки</tt></a></td>
                    </tr>
                    </table>
                    </salmon:listformdisplaybox>
                </salmon:box></td>
            </tr>
        </table>
    </salmon:box>
</salmon:form>
<jsp:include page="TemplateAfter.jsp" flush="true"/>
<salmon:endpage/>