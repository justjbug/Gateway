package com.gateway.portal.dal.dao.impl;

import com.gateway.portal.dal.dao.GroupMethodDAO;
import com.gateway.portal.model.group.GroupMethod;
import org.springframework.stereotype.Repository;

import com.gateway.portal.core.dao.impl.BaseDAOImpl;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("groupMethodDAO")
public class GroupMethodDAOImpl extends BaseDAOImpl<GroupMethod> implements GroupMethodDAO {

    public GroupMethodDAOImpl() {
        super(GroupMethod.class);
    }
}
