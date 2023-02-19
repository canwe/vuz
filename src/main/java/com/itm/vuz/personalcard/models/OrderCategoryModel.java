package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.domain.OrderCategory;
import com.itm.vuz.common.models.BaseHibernateModel;

/**
 * Created by IntelliJ IDEA.
 * User: artem1
 * Date: 19.07.2006
 * Time: 22:19:33
 * To change this template use File | Settings | File Templates.
 */
public class OrderCategoryModel extends BaseHibernateModel{
    public OrderCategoryModel() {
        this.addBeanDefinition(OrderCategory.class, "OrderCategory");
    }
}
