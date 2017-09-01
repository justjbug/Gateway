package com.gateway.portal.biz.facade;

import com.gateway.portal.core.utils.PageModel;
import com.gateway.portal.dto.CpiMethodDTO;
import com.gateway.portal.model.ResultModel;
import com.gateway.portal.model.cpi.CpiMethod;
import com.gateway.portal.model.cpi.CpiMethodParam;
import com.gateway.portal.view.CpiMethodView;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public interface CpiMethodFacade {

    /**
     * 根据方法名和版本号，获取方法对象
     * @param methodName
     * @param version
     * @return
     */
    CpiMethodDTO getCpiMethodDTO(String methodName, String version);


    /**
     * 创建方法
     * @param apiName
     * @param apiVersion
     * @param name
     * @param packageName
     * @param rpcMethodName
     * @param open
     * @param logon
     * @param mode
     * @param desc
     * @return
     */
    ResultModel<Integer> createCpiMethod(String apiName,String apiVersion,String name,String packageName,String rpcMethodName,String open,String logon,String mode,String desc);


    /**
     * 删除方法
     * @param id
     * @return
     */
    ResultModel<Integer> deleteCpiMethod(Integer id);


    /**
     * 更新方法
     * @param id
     * @param apiName
     * @param apiVersion
     * @param name
     * @param packageName
     * @param rpcMethodName
     * @param open
     * @param logon
     * @param mode
     * @param desc
     * @return
     */
    ResultModel<Integer> updateCpiMethod(Integer id,String apiName,String apiVersion,String name,String packageName,String rpcMethodName,String open,String logon,String mode,String desc);


    /**
     * 在方法下添加参数
     * @param methodId
     * @param paramName
     * @param paramType
     * @param paramDesc
     * @param index
     * @param require
     * @param domain
     * @param length
     * @return
     */
    ResultModel<String> createCpiMethodParam(Integer methodId, String paramName, String paramType, String paramDesc, Integer index, String require, String domain, Integer length);


    /**
     * 删除参数
     * @param id
     * @return
     */
    ResultModel<String> deleteCpiMethodParam(Integer id);


    /**
     * 更新参数
     * @param id
     * @param methodId
     * @param paramName
     * @param paramType
     * @param paramDesc
     * @param index
     * @param require
     * @param domain
     * @param length
     * @return
     */
    ResultModel<String> updateCpiMethodParam(Integer id,Integer methodId,String paramName,String paramType,String paramDesc,Integer index,String require,String domain,Integer length);


    /**
     * 查询方法列表
     * @param apiName
     * @param apiVersion
     * @param name
     * @param currPage
     * @param pageSize
     * @return
     */
    PageModel<CpiMethodView> queryCpiMethodByPage(String apiName, String apiVersion, String name, Integer currPage, Integer pageSize);


    /**
     * 查询方法参数的列表
     * @param currPage
     * @param pageSize
     * @return
     */
    PageModel<CpiMethodParam> queryCpiMethodParamByPage(Integer methodId,Integer currPage,Integer pageSize);


    /**
     * 批量更新参数
     * @param updates
     * @return
     */
    ResultModel<String> batchUpdateCpiMethodParam(List<CpiMethodParam> updates);


    /**
     * 方法测试
     * @param id
     * @return
     */
    ResultModel<String> basicMethodTest(Integer id);






}
