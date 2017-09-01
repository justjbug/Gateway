package com.gateway.portal.biz.facade.impl;

import com.gateway.portal.biz.facade.GroupMethodFacade;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.biz.service.GroupMethodService;
import com.gateway.portal.dto.GroupIntroductionDTO;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.model.group.GroupMethod;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("groupMethodFacade")
public class GroupMethodFacadeImpl implements GroupMethodFacade {

    @Resource
    private GroupMethodService groupMethodService;
    @Resource
    private CpiMethodService cpiMethodService;
    @Override
    public ResultModel<String> createGroup(String name, String code) {
        ResultModel<String> result = new ResultModel<>();

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        int count = this.groupMethodService.queryByCount(params);
        if(count > 0){
            result.setMessage("code已存在！");
            return result;
        }

        GroupMethod groupMethod = new GroupMethod();
        groupMethod.setName(name);
        groupMethod.setCode(code);
        int flag = (int)this.groupMethodService.create(groupMethod);
        if(flag > 0){
            result.setMessage("添加成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("添加失败！");
        }
        return result;
    }

    @Override
    public ResultModel<String> deleteGroup(Integer id) {
        ResultModel<String> result = new ResultModel<>();

        GroupMethod p = new GroupMethod();
        p.setId(id);
        int flag = this.groupMethodService.delete(p,false);
        if(flag > 0){
            //TODO 删除项目后，是否要删除接口
            result.setMessage("删除成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("删除失败！");
        }
        return result;
    }

    @Override
    public List<GroupIntroductionDTO> queryGroupIntroductionDTOByPage(Integer currPage, Integer pageSize) {
        List<GroupMethod> datas = this.groupMethodService.queryByPage(null,currPage,pageSize);
        List<GroupIntroductionDTO> result = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(datas)){
            for (GroupMethod groupMethod : datas) {
                Map<String,Object> params = new HashMap<>();
                params.put("likeApiName", groupMethod.getCode());
                int interfaceCount = this.cpiMethodService.queryByCount(params);
                Integer count = 54342983;
                DecimalFormat df = new DecimalFormat("#,###");
                String requestCount = df.format(count);
                result.add(new GroupIntroductionDTO(groupMethod.getId(), groupMethod.getName(), groupMethod.getCode(),interfaceCount,requestCount));
            }
        }
        return result;
    }

    @Override
    public ResultModel<String> updateGreoup(Integer id, String name, String code) {
        ResultModel<String> result = new ResultModel<>();

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        params.put("noId",id);
        int count = this.groupMethodService.queryByCount(params);
        if(count > 0){
            result.setMessage("code已存在！");
            return result;
        }

        GroupMethod p = new GroupMethod();
        p.setId(id);
        p.setName(name);
        p.setCode(code);
        int flag = this.groupMethodService.update(p);
        if(flag > 0){
            //TODO 更新后是否级联更新接口
            result.setMessage("更新成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("更新失败！");
        }
        return result;
    }
}
