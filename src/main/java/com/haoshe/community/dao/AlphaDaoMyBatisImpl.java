package com.haoshe.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary        // tells Spring that when multiple beans of the same type exist, this bean should be preferred for autowiring.
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "MyBatis";
    }
}
