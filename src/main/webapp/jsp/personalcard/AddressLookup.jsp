<%@ taglib uri="/taglib.tld" prefix="salmon"%>
<%@ page errorPage="../ErrorPage.jsp" extends="com.salmonllc.jsp.JspServlet" pageEncoding="UTF-8"%>
<html>
<head><title>Выбор адреса</title></head>
<!-- BaseController child class shoudl be used as page Controller to handle the shared GUI elements -->
<salmon:page  controller="com.itm.vuz.personalcard.controllers.AddressLookupController"/>
<!--<salmon:body/>-->
<salmon:form name="pageForm">
<!--Page Content Begin-->
 <salmon:datasource name="dsAddressQBE" type="QBE" model="com.itm.vuz.personalcard.models.AddressQBE">
 </salmon:datasource>
 <salmon:datasource name="dsAddress" type="MODEL" model="com.itm.vuz.vocabularies.models.HibernateAddressModel" autoretrieve="Never">
 </salmon:datasource>
 <salmon:searchformdisplaybox name="sfAddressFind" caption="Выбор адреса" width="100%" searchbuttoncaption="Найти" cancelbuttoncaption="Отмена" qbebuilder="dsAddressQBE"  >
   <table width="60%" >
     <tr>
       <td align="right"><salmon:text name="CountryCAP4" text="Страна" font="ColumnCaptionFont" /></td>
       <td>
	     <salmon:input name="CountryDD4" onchange="document.forms['pageForm'].submit();" size="16" type="select" value="">
		   <salmon:option table="COUNTRY" key="" keycolumn="COUNTRY_ID" display="" displaycolumn="NAME" />
		 </salmon:input>
	   </td>
     </tr>
	 <tr>
	   <td align="right"><salmon:text name="RegionCAP5" text="Регион" font="ColumnCaptionFont" /></td>
       <td><salmon:input type="select" name="RegionDD5" onchange="document.forms['pageForm'].submit();" size="16"></salmon:input></td>
	 </tr>
	 <tr>
	   <td align="right"><salmon:text name="AreaCAP3" text="Район" font="ColumnCaptionFont" /></td>
       <td><salmon:input type="select" name="AreaDD3" onchange="document.forms['pageForm'].submit();" size="16"></salmon:input></td>
	 </tr>	 
     <tr>
       <td align="right"><salmon:text name="SettlementCAP6" text="Нас. пункт" font="ColumnCaptionFont" /></td>
       <td><salmon:input type="select" name="SettlementDD6" size="16" datasource="dsAddressQBE:Settlement"></salmon:input></td>
     </tr>
   </table>
 </salmon:searchformdisplaybox> 
 <salmon:listformdisplaybox name="lfAddressList" caption="Список адресов" mode="Display_single_page" 
         width="100%" addbuttonvisible="False" savebuttonvisible="False" datasource="dsAddress" searchformdisplaybox="sfAddressFind" 
		 lookupreturnexp="Address.addressId"
		 lookupdescreturnexp="Address.settlement.area.region.country.name + ', ' + Address.settlement.area.region.name + ', ' + Address.settlement.area.name + ', ' + Address.settlement.settlementDefinition.name + ' ' + Address.settlement.name + ', ' + Address.address" autocreatelink="True" >
   <salmon:datatable name="datatable1" width="100%" rowsperpageselector="False" pageselectortype="ScrollBar" datasource="dsAddress" ><salmon:datatableheader>
       <salmon:tr>
	     <salmon:td>
	       <salmon:text name="teAddressIDCAP" text="id" font="TableHeadingFont"/>	       </salmon:td>
		 <salmon:td><salmon:text name="teAddressCAP" text="Адрес" font="TableHeadingFont"/></salmon:td>
       </salmon:tr>
     </salmon:datatableheader><salmon:datatablerows>
       <salmon:tr>
	     <salmon:td><salmon:text name="teAddressID" text="AddressId goes here" font="LargeLinkFont" datasource="dsAddress:Address.addressId"/></salmon:td>
		 <salmon:td><salmon:text name="teAddress" text="Адрес" font="TextEditFont" datasource="dsAddress:Address.address"/></salmon:td>
       </salmon:tr>
     </salmon:datatablerows></salmon:datatable>
 </salmon:listformdisplaybox> 
<!--Page Content End-->
</salmon:form>
<!--<salmon:endbody/>-->
<salmon:endpage/>
</html>