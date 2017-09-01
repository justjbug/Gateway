package com.gateway.portal.biz.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.gateway.portal.biz.service.GroupMethodService;
import org.springframework.stereotype.Service;

import com.gateway.portal.core.service.impl.BaseServiceImpl;
import com.gateway.portal.dal.dao.GroupMethodDAO;
import com.gateway.portal.model.group.GroupMethod;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Service("groupMethodService")
public class GroupMethodServiceImpl extends BaseServiceImpl<GroupMethod> implements GroupMethodService {

    @Resource
    private GroupMethodDAO groupMethodDAO;

    @PostConstruct
    public void init(){
        super.setBaseDao(groupMethodDAO);
    }

}
