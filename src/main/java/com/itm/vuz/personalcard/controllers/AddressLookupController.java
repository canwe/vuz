//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Area;
import com.itm.vuz.common.domain.Country;
import com.itm.vuz.common.domain.Region;
import com.itm.vuz.common.domain.Settlement;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.events.*;

import java.util.Iterator;


/**
 * AddressLookupController: a SOFIA generated controller
 */
public class AddressLookupController extends BaseVUZController implements SubmitListener, PageListener, ValueChangedListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _areaDD3;
    public com.salmonllc.html.HtmlDropDownList _countryDD4;
    public com.salmonllc.html.HtmlDropDownList _regionDD5;
    public com.salmonllc.html.HtmlDropDownList _settlementDD6;
    public com.salmonllc.jsp.JspDataTable _datatable1;

//DataSources
    public com.itm.vuz.personalcard.models.AddressQBE _dsAddressQBE;
    public com.itm.vuz.vocabularies.models.HibernateAddressModel _dsAddress;

//DataSource Column Constants
    public static final String DSADDRESSQBE_SETTLEMENT = "Settlement";

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
        addPageListener(this);
        this._datatable1.setScrollOnClickSort(false);

        this._countryDD4.addValueChangedListener(this);
        this._regionDD5.addValueChangedListener(this);
        this._areaDD3.addValueChangedListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) {
    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

    /**
     * При изменении выбранных строк в списках выбора формы поиска перезагружаем
     * связанные наборы данных
     *
     * @param e
     * @return
     * @throws Exception
     */
    public boolean valueChanged(ValueChangedEvent e) throws Exception {
        if (e.getComponent() == this._countryDD4) {
            //Заполняем список регионов,
            Long countryID = Long.valueOf(e.getNewValue());
            this.fillRegionList(this.getCountry(countryID));
            //районов региона,
            Long regionID = Long.valueOf(this._regionDD5.getValue());
            this.fillAreaList(this.getRegion(regionID));
            //нас.пунктов района
            Long areaID = Long.valueOf(this._areaDD3.getValue());
            this.fillSettlementList(this.getArea(areaID));
        }
        if (e.getComponent() == this._regionDD5) {
            //Заполняем список районов региона,
            Long regionID = Long.valueOf(e.getNewValue());
            this.fillAreaList(this.getRegion(regionID));
            //нас.пунктов района
            Long areaID = Long.valueOf(this._areaDD3.getValue());
            this.fillSettlementList(this.getArea(areaID));
        }
        if (e.getComponent() == this._areaDD3) {
            //Заполняем список нас.пунктов района
            Long areaID = Long.valueOf(e.getNewValue());
            this.fillSettlementList(this.getArea(areaID));
        }
        return true;
    }

    /**
     * Возвращает страну по ее идентификатору
     *
     * @param countryId идентификатор
     * @return Страна
     */
    public Country getCountry(Long countryId) throws Exception {
        if (countryId != null) {
            return (Country) HibernateSessionFactory.getSession().get(Country.class, countryId);
        }
        return null;
    }

    /**
     * Заполняет список регионов регионами заданной страны
     *
     * @param c Страна
     */
    public void fillRegionList(Country c) {
        //сначала очищаем список
        this._regionDD5.resetOptions();

        if ((c != null) && (c.getRegions().size() > 0)) {
            //если страна задана и имеет список регионов, то заполняем ими список
            Iterator iter = c.getRegions().iterator();
            while (iter.hasNext()) {
                Region r = (Region) iter.next();
                this._regionDD5.addOption(r.getRegionId().toString(), r.getName());
            }
            this._regionDD5.setSelectedIndex(0);
        }
    }

    /**
     * Возвращает регион по его идентификатору
     *
     * @param regionID Идентификатор региона
     * @return Регион
     */
    public Region getRegion(Long regionID) throws Exception {
        if (regionID != null) {
            return (Region) HibernateSessionFactory.getSession().get(Region.class, regionID);
        }
        return null;
    }

    /**
     * Заполняет список районов районами заданного региона
     *
     * @param r Регион
     */
    public void fillAreaList(Region r) {
        this._areaDD3.resetOptions();

        if ((r != null) && (r.getAreas().size() > 0)) {
            Iterator iter = r.getAreas().iterator();
            while (iter.hasNext()) {
                Area a = (Area) iter.next();
                this._areaDD3.addOption(a.getAreaId().toString(), a.getName());
            }
            this._areaDD3.setSelectedIndex(0);
        }
    }

    /**
     * Возвращает район по его идентификатору
     *
     * @param areaID идентификатор района
     * @return Район
     */
    public Area getArea(Long areaID) throws Exception {
        if (areaID != null) {
            return (Area) HibernateSessionFactory.getSession().get(Area.class, areaID);
        }
        return null;
    }

    /**
     * Заполняет список нас.пунктов нас.пунктами заданного района
     * @param a Район
     */
    public void fillSettlementList(Area a) {
        this._settlementDD6.resetOptions();

        if ((a != null) && (a.getSettlements().size() > 0)) {
            Iterator iter = a.getSettlements().iterator();
            while (iter.hasNext()) {
                Settlement s = (Settlement) iter.next();
                this._settlementDD6.addOption(
                    s.getSettlementId().toString(),
                    s.getSettlementDefinition().getName() + " " + s.getName());
            }
            this._settlementDD6.setSelectedIndex(0);
    }
}
}
