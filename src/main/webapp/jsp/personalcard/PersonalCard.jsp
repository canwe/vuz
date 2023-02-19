<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ taglib uri="/vuz.tld" prefix="vuz"%>

<%@ page extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html><title>Личное дело студента</title>
<head>
	<style type="text/css">@import url(../common/DynaCalendar/calendar-win2k-1.css);</style>
	<script type="text/javascript" src="../common/DynaCalendar/calendar.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/lang/calendar-ru.js"></script>
	<script type="text/javascript" src="../common/DynaCalendar/calendar-setup.js"></script>
<style type="text/css">
<!--
  .MenuCell {
    border-left-color:Blue;
	border-left-style:solid;
	border-left-width: 1px;
	border-top: none;
	border-bottom: none;
	border-right: none;
	background-color:#eeeeee;
	}
  .SelectedMenuCell {
    border-left: none;
    border-top-color:Blue;
	border-top-style:solid;
	border-top-width: 1px;
    border-bottom-color:Blue;
	border-bottom-style:solid;
	border-bottom-width: 1px;
    border-right-color:Blue;
	border-right-style:solid;
	border-right-width: 1px;
	}
-->
</style>
</head>
<salmon:page controller="com.itm.vuz.personalcard.controllers.PersonalCardController"/>
<jsp:include page="../TemplateBefore.jsp" flush="true" ></jsp:include>
<!--<salmon:body/>-->
<salmon:form name="pageForm">
<!--Page Content Begin-->
<salmon:datasource name="dsStudentModel" type="MODEL" model="com.itm.vuz.personalcard.models.StudentModel" autoretrieve="Never">
</salmon:datasource>

<salmon:datasource name="dsStudentHistory" type="MODEL" model="com.itm.vuz.personalcard.models.StudentHistoryModel" autoretrieve="Never">
</salmon:datasource>

<salmon:datasource name="dsStudentHistoryNotSigned" type="MODEL" model="com.itm.vuz.personalcard.models.StudentHistoryModel" autoretrieve="Never">
</salmon:datasource>

<salmon:table name="tabBody" width="100%" border="0">
  <salmon:tr>
    <salmon:td width="80%" valign="top">
	  <salmon:container name="ctPersonalCard" visible="True">
	    <salmon:displaybox name="dbStudentDetails" caption="Личное дело студента" width="100%"><salmon:displayboxheader>
		    <table width="100%" height="*">
			  <tr>
			    <td align="left" width="*">
		          <salmon:input type="submit" value="Сохранить" name="sbtUpdate"></salmon:input>
			      <salmon:input type="submit" value="Отменить" name="sbtCancel"></salmon:input>
			    </td>
				<td align="right">
			      <salmon:input type="submit" value="Удалить личное дело" name="sbtDelete"></salmon:input>
			    </td>
			  </tr>
			</table>
		  </salmon:displayboxheader><salmon:displayboxcontents>
            <table width="100%" cellspacing="0" cellpadding="4" style="border-collapse:collapse">
			  <tr>
			    <td colspan="4">
				  <table width="100%">
				    <tr>
					  <td width="20%" nowrap><salmon:text name="teGeneralInfoCap" text="Общие сведения" font="EmphasisFont"/></td>
					  <td><hr color="#6699FF"></td>
					</tr>
			      </table>
				</td>
		      </tr>
			  <tr>
			    <td colspan="2" rowspan="4" align="center" valign="middle"><salmon:img name="imgStudentFoto" src="None" height="100" width="80"/></td>
			    <td align="right" width="15%"><salmon:text name="teFamilyCap" font="TableHeadingFont" text="Фамилия"/></td>
				<td align="left" width="35%"><salmon:input name="teFamyliName" size="16" type="text" value="Введите фамилию" datasource="dsStudentModel:s.familyName"></salmon:input></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teNameCap" font="TableHeadingFont" text="Имя"/></td>
				<td align="left"><salmon:input name="teName" type="text" size="16" maxlength="50" value="Введите имя" datasource="dsStudentModel:s.name"></salmon:input></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="tePatronymicCap" font="TableHeadingFont" text="Отчество"/></td>
				<td align="left"><salmon:input name="tePatronymic" type="text" size="16" maxlength="50" value="Введите отчество" datasource="dsStudentModel:s.patronymic"></salmon:input></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teSexCap" font="TableHeadingFont" text="Пол"/></td>
				<td align="left"><salmon:input type="radiogroup" name="rbgSex" datasource="dsStudentModel:s.sex" orientation="Horizontal" captionpos="Right">
                  <salmon:option key="0" display="Муж."/>
                  <salmon:option key="1" display="Жен."/>
                </salmon:input></td>
			  </tr>
			  <tr>
			    <td colspan="4">
				  <table width="100%">
				    <tr>
					  <td width="23%" nowrap><salmon:text name="tePassportInfoCap" text="Паспортные данные" font="EmphasisFont"/></td>
					  <td><hr color="#6699FF"></td>
					</tr>
			      </table>
				</td>
			  </tr>
			  <tr>
			    <td align="right" valign="top" width="15%"><salmon:text name="tePassportDataCap" text="Паспорт" font="TableHeadingFont"/></td>
				<td width="35%"><salmon:input type="textarea" name="tePassportData" cols="25" rows="3" wrap="Hard" datasource="dsStudentModel:s.passportData"></salmon:input></td>
				<td align="right" valign="top"><salmon:text name="teRegistrationCAP" text="Регистрация" font="TableHeadingFont"/></td>
				<td align="left"><salmon:input type="textarea" name="teRegistration" cols="25" rows="3" wrap="Hard" datasource="dsStudentModel:s.registration"></salmon:input></td>
			  </tr>
			  <tr>
				<td align="right" valign="top"><salmon:text name="teAddressCap" text="Адрес" font="TableHeadingFont"/></td>
				<td colspan="3">
				  <salmon:lookup name="luAddress" lookupurl="AddressLookup.jsp" browseimage="../images/Browse.gif"
				    datasource="dsStudentModel:s.address.addressId" usepopup="True" popupwidth="400" popupheight="300"
					showdescription="True" usemodal="True" popupposition="center" editdescription="False"
					descriptiondatasource="dsStudentModel:s.address.settlement.area.region.country.name + ', ' + s.address.settlement.area.region.name + ', ' + s.address.settlement.area.name + ', ' + s.address.settlement.settlementDefinition.name + ' ' + s.address.settlement.name + ', ' + s.address.address" />
				</td>
			  </tr>
			  <tr>
			    <td colspan="4">
				  <table width="100%">
				    <tr>
					  <td width="23%" nowrap><salmon:text name="teEducationInfoCap" text="Учебные данные" font="EmphasisFont"/></td>
					  <td><hr color="#6699FF"></td>
					</tr>
			      </table>
				</td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teCountryCap" font="TableHeadingFont" text="Страна"/></td>
				<td align="left"><salmon:input name="ddlCountries" size="16" type="select" value="" datasource="dsStudentModel:s.country.countryId">
                  <salmon:option key="" display="" table="COUNTRY" keycolumn="COUNTRY_ID" displaycolumn="NAME" nulloption="True" />
                </salmon:input></td>
				<td align="right"><salmon:text name="teLanguageCap" font="TableHeadingFont" text="Язык"/></td>
				<td align="left"><salmon:input name="ddlLanguages" size="16" type="select" value="" datasource="dsStudentModel:s.language.languageId">
                  <salmon:option key="" display="" table="LANGUAGES" keycolumn="LANGUAGE_ID" displaycolumn="NAME" nulloption="True" />
                </salmon:input></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teStudentNumberCAP" text="№ студенческого билета" font="TableHeadingFont"/></td>
				<td align="left"><salmon:input type="text" name="teStudentNumber" size="16" maxlength="6" datasource="dsStudentModel:s.studentNumber"></salmon:input></td>
				<td align="right"><salmon:text name="teYearStarting" text="Дата начала обучения" font="TableHeadingFont"/></td>
				<td align="left"><vuz:calendar name="clndYearStarting" datasource="dsStudentModel:s.yearStarting" /></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teContractCAP" text="Контрактник" font="TableHeadingFont"/></td>
				<td align="left"><salmon:input type="checkbox" name="chbContract" datasource="dsStudentModel:s.contract"></salmon:input></td>
				<td align="right"><salmon:text name="teBenefitCAP" text="Льгота" font="TableHeadingFont"/></td>
				<td align="left">
				  <salmon:input multiple="False" name="ddlBenefits" size="16" type="select" value="" datasource="dsStudentModel:s.benefit.benefitId">
				    <salmon:option key="" display="" table="BENEFITS" keycolumn="BENEFIT_ID" displaycolumn="NAME" nulloption="False" />
				  </salmon:input>
				</td>
			  </tr>
			  <tr>
			    <td align="right">
					<salmon:text name="teHospiceCAP" text="Необходимо общежитие" font="TableHeadingFont"/>
				</td>
				<td align="left">
					<salmon:input type="checkbox" name="chbHospiceNeeded" datasource="dsStudentModel:s.hospiceNeeded"></salmon:input>
                    &nbsp;
                    <salmon:input type="select" name="ddlHospices" size="16" multiple="False" datasource="dsStudentModel:s.hospice.hospiceId">
						<salmon:option key="" display="" table="HOSPICE" keycolumn="HOSPICE_ID" displaycolumn="NAME" nulloption="False" />
					</salmon:input>
				</td>
				<td align="right"><salmon:text name="teLastEducationPlaceCAP" text="Последнее место учебы" font="TableHeadingFont"/></td>
				<td align="left"><salmon:input type="text" name="teLastEducationPlace" size="16" maxlength="20" datasource="dsStudentModel:s.lastEducationPlace"></salmon:input></td>
			  </tr>
			  <tr>
			    <td align="right"><salmon:text name="teStatusCAP" text="Статус студента" font="TableHeadingFont" /></td>
			    <td align="left">
				  <salmon:input type="select" name="ddlStudentStatus" size="16" multiple="False" datasource="dsStudentModel:s.studentStatus.studentStatusId">
				    <salmon:option key="" display="" table="STUDENT_STATUS" keycolumn="STUDENT_STATUS_ID" displaycolumn="NAME" nulloption="False" />
				  </salmon:input>
				</td>
			    <td align="right"><salmon:text name="teCourse_LeaderCAP" text="Староста" font="TableHeadingFont" /></td>
			    <td align="left"><salmon:input type="checkbox" name="chbCourseLeader" datasource="dsStudentModel:s.courseLeader"></salmon:input></td>
			  </tr>
			</table>
          </salmon:displayboxcontents></salmon:displaybox>
	  </salmon:container>
	  <salmon:container name="ctHistory" visible="False">
	    <salmon:displaybox name="dbStudentHistory" caption="Список событий"><salmon:displayboxcontents>
		    <salmon:text name="notSignedOrderTitle" font="FixedSmall.DisplayBoxHeadingFont" text="Готовящиеся приказы" />
            <salmon:datatable name="dtStudentHistoryNotSigned" width="100%" datasource="dsStudentHistoryNotSigned"><salmon:datatableheader>
                <salmon:tr>
				  <salmon:td>
				    <salmon:text name="notSigned.studentEvent.dateEventCAP" text="Дата" font="TableHeadingFont" />
				  </salmon:td>
                  <salmon:td>
                    <salmon:text name="notSigned.studentEvent.nameCAP29" text="Событие" font="TableHeadingFont" />
                  </salmon:td>
                  <salmon:td>
                    <salmon:text name="notSigned.commentCAP33" text="Комментарий" font="TableHeadingFont" />
                  </salmon:td>
                  <salmon:td>
                    <salmon:text name="notSigned.officeOrder.orderCategory.orderCategoryCAP30" text="Приказ" font="TableHeadingFont" />
                  </salmon:td>
                </salmon:tr>
              </salmon:datatableheader><salmon:datatablerows>
                <salmon:tr>
				  <salmon:td>
				    <salmon:text name="notSigned.studentEvent.dateEventTXT" font="DefaultFont" text="01.09.2006" datasource="dsStudentHistoryNotSigned:StudentHistory.dateEvent" displayformat="dd.MM.yyyy"/>
				  </salmon:td>
                  <salmon:td>
                    <salmon:text name="notSigned.studentEvent.nameTXT24" text="Стипендия" font="DefaultFont" datasource="dsStudentHistoryNotSigned:StudentHistory.studentEvent.name"/>
                  </salmon:td>
                  <salmon:td>
                    <salmon:text name="notSigned.commentTXT28" text="О назначении стипеднии" font="DefaultFont" datasource="dsStudentHistoryNotSigned:StudentHistory.comment"/>
                  </salmon:td>
                  <salmon:td>
				  	<salmon:text name="notSigned.orderCategoryCAP" text="Вид приказа:" font="TableHeadingFont" />&nbsp;
                    <salmon:text name="notSigned.officeOrder.orderCategory.orderCategoryTXT" text="Стипендия" font="DefaultFont" datasource="dsStudentHistoryNotSigned:StudentHistory.officeOrder.orderCategory.orderCategory"/>
					<br />
					<salmon:text name="notSigned.orderNumberCAP" text="№ приказа:" font="TableHeadingFont" />&nbsp;
					<salmon:text name="notSigned.orderNumberTXT" text="123-45" font="DefaultFont" datasource="dsStudentHistoryNotSigned:StudentHistory.officeOrder.externalNumber"/>
					<br />
					<salmon:text name="notSigned.orderDocumentCAP" text="Приложенный файл:" font="TableHeadingFont" />&nbsp;
					<salmon:text name="notSigned.officeOrder.pathToFile" text="C:\temp" font="DefaultFont" datasource="dsStudentHistoryNotSigned:StudentHistory.officeOrder.pathToFile"/>
                  </salmon:td>
                </salmon:tr>
              </salmon:datatablerows><salmon:datatablefooter></salmon:datatablefooter></salmon:datatable>
			<br />
		    <salmon:text name="signedEventTitle" font="FixedSmall.DisplayBoxHeadingFont" text="Зарегистрированные события" />
            <salmon:datatable name="dtStudentHistory" width="100%" datasource="dsStudentHistory"><salmon:datatableheader>
				<salmon:tr>
					<salmon:td clicksort="False" nowrap="True">
						<vuz:calendar name="clndEventDate" />
					</salmon:td>
					<salmon:td clicksort="False">
					  <salmon:input type="select" name="ddlStudentEvent" size="16" multiple="False">
                        <salmon:option key="" display="" keycolumn="EVENT_ID" displaycolumn="NAME" table="STUDENT_EVENT" nulloption="False" />
                      </salmon:input>
					</salmon:td>
					<salmon:td clicksort="False">
					  <salmon:input type="text" name="teEventComment" size="25" maxlength="250"></salmon:input>
					</salmon:td>
					<salmon:td clicksort="False">
					  <salmon:input type="submit" name="sbtAddSimpleEvent" value="Добавить событие"></salmon:input>
					</salmon:td>
				</salmon:tr>
                <salmon:tr>
				  <salmon:td>
				    <salmon:text name="studentEvent.dateEventCAP" text="Дата" font="TableHeadingFont" />
				  </salmon:td>
                  <salmon:td>
                    <salmon:text name="studentEvent.nameCAP29" text="Событие" font="TableHeadingFont" />
                  </salmon:td>
                  <salmon:td>
                    <salmon:text name="commentCAP33" text="Комментарий" font="TableHeadingFont" />
                  </salmon:td>				  
                  <salmon:td>
                    <salmon:text name="officeOrder.orderCategory.orderCategoryCAP30" text="Приказ" font="TableHeadingFont" />
                  </salmon:td>
                </salmon:tr>
              </salmon:datatableheader><salmon:datatablerows>
                <salmon:tr>
				  <salmon:td>
				    <salmon:text name="studentEvent.dateEventTXT" font="DefaultFont" text="01.05.2005" datasource="dsStudentHistory:StudentHistory.dateEvent" displayformat="dd.MM.yyyy"/>
				  </salmon:td>
                  <salmon:td>
                    <salmon:text name="studentEvent.nameTXT24" text="Выговор" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.studentEvent.name"/>
                  </salmon:td>
                  <salmon:td>
                    <salmon:text name="commentTXT28" text="За нарушение дисциплины" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.comment"/>
                  </salmon:td>				  
                  <salmon:td>
				  	<salmon:text name="officeOrder.orderCategoryCAP" text="Вид приказа:" font="TableHeadingFont" />&nbsp;
                    <salmon:text name="officeOrder.orderCategory.orderCategoryTXT" text="" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.officeOrder.orderCategory.orderCategory"/>
					<br />
					<salmon:text name="officeOrder.orderNumberCAP" text="№ приказа:" font="TableHeadingFont" />&nbsp;
                    <salmon:text name="officeOrder.orderNumberTXT" text="" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.officeOrder.externalNumber"/>					
					<br />
					<salmon:text name="officeOrder.orderDocumentCAP" text="Приложенный файл:" font="TableHeadingFont" />&nbsp;
                    <salmon:text name="officeOrder.pathToFileTXT" text="" font="DefaultFont" datasource="dsStudentHistory:StudentHistory.officeOrder.pathToFile"/>					
                  </salmon:td>
                </salmon:tr>
              </salmon:datatablerows><salmon:datatablefooter>
			  </salmon:datatablefooter></salmon:datatable>
          </salmon:displayboxcontents></salmon:displaybox>
	  </salmon:container>	
	</salmon:td>
    <salmon:td valign="Top" width="160pt">
	  <salmon:table name="tabMenu" width="100%" height="100%" border="0" cellspacing="0" cellpadding="4">
	    <salmon:tr><salmon:td classname="MenuCell" height="24px">&nbsp;</salmon:td></salmon:tr>
	    <salmon:tr><salmon:td classname="SelectedMenuCell" height="36px">
		  <salmon:input type="submit" name="sbtPersonalCardLink" value="Личное дело"></salmon:input>
		  <br />
		  <salmon:text name="teDesc1" text="Общие сведения о студенте: ФИО, адрес, паспортные данные и т.п." font="DDLBFont"/>
		</salmon:td></salmon:tr>
	    <salmon:tr><salmon:td classname="MenuCell" height="36px">
		  <salmon:input type="submit" name="sbtHistory" value="История"></salmon:input>
		  <br />
		  <salmon:text name="teDesc2" text="Список событий, изменений статуса студента в процессе обучения и т.п." font="DDLBFont"/>
		</salmon:td></salmon:tr>
   	    <salmon:tr><salmon:td classname="MenuCell" height="264px">&nbsp;</salmon:td></salmon:tr>
	  </salmon:table>
	</salmon:td>
  </salmon:tr>
</salmon:table>
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<jsp:include page="../TemplateAfter.jsp" flush="true"></jsp:include>
<salmon:endpage/>