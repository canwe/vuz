package com.itm.vuz.common.controllers;

import com.itm.vuz.common.Application;
import com.itm.vuz.common.utils.SessionManager;
import com.itm.vuz.common.utils.LinkHistory;
import com.itm.vuz.common.domain.User;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.*;
import com.salmonllc.jsp.*;
import com.salmonllc.properties.Props;
import com.salmonllc.util.MessageLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 23.07.2006
 * Time: 13:32:17
 */

/**
 * provides such common tasks as: login checking, access rights, etc.
 * all custom controllers in VUZ project should extend this controller
 */
public class BaseVUZController extends BaseController{

    protected LoginContext login;

    protected int roleIdAdmin;
    protected int roleIdDecanat;
    protected int roleIdDepartment;
    protected int roleIdGuest;

    //protected List history = new ArrayList();
    //protected Map historyUrls = new HashMap();

    public static final int MAX_HISTORY = 5;

    public JspContainer _history;
    HtmlContainer _historyPath;


    public void initialize(){
        try{
            super.initialize();
            _historyPath = new HtmlContainer("historyPath", this);
            replaceComponent(_history.getName(), _historyPath);

        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
        roleIdAdmin = Props.getProps(Application.APP_NAME, null).getIntProperty("RoleIdAdmin");
        roleIdDecanat = Props.getProps(Application.APP_NAME, null).getIntProperty("RoleIdDecanat");
        roleIdDepartment = Props.getProps(Application.APP_NAME, null).getIntProperty("RoleIdDepartment");
        roleIdGuest = Props.getProps(Application.APP_NAME, null).getIntProperty("RoleIdGuest");

    }

    public void pageRequested(PageEvent p) throws Exception{
        //check login
        super.pageRequested(p);
        MessageLog.writeDebugMessage("checking login...", this);
        login = getSessionManager().getLoginContext();
        if(login == null){
            // redirect to login page
            this.gotoSiteMapPage("LoginPage", "");
        }else{
            MessageLog.writeDebugMessage("login checked, user = [" + login.getLogin() + "]", this);
            MessageLog.writeDebugMessage("User role = [" + login.getRoleName() + "]", this);
            List linksHistory = getSessionManager().getLinksHistory();
            if(linksHistory==null){
                linksHistory = new ArrayList();
                getSessionManager().putValue(SessionManager.LINKS_HISTORY, linksHistory);
            }
            if(!isReferredByCurrentPage()){
                if(linksHistory.size() == MAX_HISTORY){
                  LinkHistory toDelete = (LinkHistory)linksHistory.get(0);
                  linksHistory.remove(toDelete);
                }
                linksHistory.add(new LinkHistory(getModuleName(), getModuleUrl()));

                _historyPath.removeAll();
                for(int i=0;i<linksHistory.size();i++){
                  LinkHistory linkHistory = (LinkHistory)linksHistory.get(i);
                  HtmlLink link = new HtmlLink(linkHistory.getName(), linkHistory.getUrl(), this);
                  link.add(new HtmlText(linkHistory.getName(), this));
                  _historyPath.add(link);
                  if(i < (linksHistory.size()-1)){
                    _historyPath.add(new HtmlText(" | ", this));
                  }
                }
            }
        }
    }

    /**
     * override it in the module controller if non-standart module name should be returned
     * @return module name
     */
    protected String getModuleName(){
        String pageName = getPageName();
        String hardcodedName = getHardcodedName(pageName);
        return hardcodedName == null ? pageName : hardcodedName;
    }

    private String getHardcodedName(String pageName) {
        return (String)PageNames.pageNames.get(pageName);
    }

    /**
     * override it in the module controller if non-standart url should be returned
     * @return url
     */
    protected String getModuleUrl(){
        return getPageURL();
    }

    //added by artem1
    //start

    /**
     * Возвращает экземпляр класса User, соответствуюещего залогиненному пользователю
     * @return Текущий пользователь
     * @throws HibernateException
     */
    protected User getLoggedUser() throws HibernateException {
        Session sess = HibernateSessionFactory.getSession();
        return (User)sess.get(User.class, this.login.getUserId());
    }

    protected boolean isGuest() {
        return this.login.getRoleId().intValue() == this.roleIdGuest;
    }

    protected boolean isAdmin() {
        return this.login.getRoleId().intValue() == this.roleIdAdmin;
    }

    protected boolean isDecanat() {
        return this.login.getRoleId().intValue() == this.roleIdDecanat;
    }

    protected boolean isDepartment() {
        return this.login.getRoleId().intValue() == this.roleIdDepartment;
    }
    //end

    static class PageNames{
        static Map pageNames;
        static {
            pageNames = new HashMap();
            pageNames.put("HomePage.jsp", "Главная страница");
            pageNames.put("GroupCard.jsp", "Учебная карточка");
            pageNames.put("EducationPlans.jsp", "Учебные планы");
            pageNames.put("EducationPlanDetails.jsp", "Детали учебного плана");

            pageNames.put("ExamList.jsp", "Экзаменационные ведомости");
            pageNames.put("ExamListDetails.jsp", "Детали экзаменационной ведомости");

            pageNames.put("VocabulariesList.jsp", "Список справочников");

            pageNames.put("Address.jsp", "Адреса");
            pageNames.put("Area.jsp", "Области");
            pageNames.put("BaseDiscipline.jsp", "Базовые дисциплины");
            pageNames.put("Benefit.jsp", "Льготы");
            pageNames.put("Card.jsp", "Карточки");
            pageNames.put("Country.jsp", "Страны");
            pageNames.put("Departament.jsp", "Отделы");
            pageNames.put("Discipline.jsp", "Дисциплины");
            pageNames.put("DisciplineTeacher.jsp", "Дисциплины и преподаватели");
            pageNames.put("Faculty.jsp", "Факультеты");

            pageNames.put("FacultyGroupCode.jsp", "Коды групп факультетов");
            pageNames.put("Hospice.jsp", "Общежития");
            pageNames.put("Language.jsp", "Языки");
            pageNames.put("OrderCategory.jsp", "Категории приказов");
            pageNames.put("Personal.jsp", "Личные данные");
            pageNames.put("Position.jsp", "Должности");
            pageNames.put("Region.jsp", "Регионы");
            pageNames.put("Settlement.jsp", "Месторасположения");
            pageNames.put("SettlementDefinition.jsp", "Типы месторасположений");
            pageNames.put("Speciality.jsp", "Специальности");

            pageNames.put("StudentEvent.jsp", "События");
            pageNames.put("StudentGroup.jsp", "Группы");
            pageNames.put("StudentStatus.jsp", "Статусы студентов");
            pageNames.put("Subfaculty.jsp", "Кафедры");
            pageNames.put("Teacher.jsp", "Преподаватели");
            pageNames.put("User.jsp", "Пользователи");
            pageNames.put("UserRole.jsp", "Роли пользователей");

            pageNames.put("PersonalCard.jsp", "Личное дело");
            pageNames.put("StudentsToGroups.jsp", "Списки групп студентов");
            pageNames.put("StudentSearch.jsp", "Списки студентов");
            pageNames.put("StudentBasket.jsp", "Список студентов");
            pageNames.put("OfficeOrder.jsp", "Приказ");
            pageNames.put("OfficeOrderSearch.jsp", "Приказы");



        }
    }
}
