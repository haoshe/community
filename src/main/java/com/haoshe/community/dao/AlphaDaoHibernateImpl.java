package com.haoshe.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate") // set a name to the bean
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
