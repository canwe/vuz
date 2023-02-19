package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.models.BaseHibernateModel;
import com.itm.vuz.common.domain.Position;

/**
 * User: artem1
 * Date: 25.06.2006
 * Time: 21:13:58
 */
public class PositionModel extends BaseHibernateModel {

    public PositionModel() {
        this.addBeanDefinition(Position.class, "Position");
    }
}
