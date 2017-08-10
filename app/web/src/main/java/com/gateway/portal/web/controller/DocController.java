package com.gateway.portal.web.controller;

import com.gateway.common.model.Result;
import com.gateway.portal.biz.service.CpiMethodParamService;
import com.gateway.portal.biz.service.CpiMethodService;
import com.gateway.portal.core.utils.DateUtils;
import com.gateway.portal.model.cpi.CpiMethod;
import com.gateway.portal.model.cpi.CpiMethodParam;
import com.gateway.portal.web.controller.dto.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class DocController extends BaseController{

    @Resource
    private CpiMethodService cpiMethodService;
    @Resource
    private CpiMethodParamService cpiMethodParamService;



    @RequestMapping(value = "/doc", method = { RequestMethod.GET })
    public ModelAndView doc() {
        ModelAndView result = new ModelAndView();
        return result;
    }

    @RequestMapping(value = "/apiData", method = { RequestMethod.GET })
    public Result apiData() throws IOException {
        Result result = new Result();

        List<String> headers = new ArrayList<String>(6);
        headers.add("参数");
        headers.add("类型");
        headers.add("是否必填");
        headers.add("最大长度");
        headers.add("描述");
        headers.add("示例值");

        List<Api> apis = new ArrayList<Api>();
        Api api = new Api();
        api.setName("网关入口");
        api.setApiName("1、统一请求地址：URL:http://{domain}/gateway");
        List<CpiMethodParam> params = new ArrayList<CpiMethodParam>();
        CpiMethodParam param = new CpiMethodParam();
        param.setParamName("method");
        param.setParamType("java.lang.String");
        param.setRequired("Y");
        param.setParamDesc("接口名称");
        param.setExampleValue("xkhstar.user.logon.sms");
        param.setIndex(1);
        params.add(param);

        param = new CpiMethodParam();
        param.setParamName("version");
        param.setParamType("java.lang.String");
        param.setRequired("Y");
        param.setParamDesc("接口版本号");
        param.setExampleValue("1.0.0");
        param.setIndex(2);
        params.add(param);

        param = new CpiMethodParam();
        param.setParamName("format");
        param.setParamType("java.lang.String");
        param.setRequired("Y");
        param.setParamDesc("返回格式");
        param.setExampleValue("json");
        param.setIndex(3);
        params.add(param);

        param = new CpiMethodParam();
        param.setParamName("sso");
        param.setParamType("java.lang.String");
        param.setRequired("N");
        param.setParamDesc("登录标识");
        param.setExampleValue("WQODIJQWNGWOQWIJW");
        param.setIndex(4);
        params.add(param);

        param = new CpiMethodParam();
        param.setParamName("bizParams");
        param.setParamType("java.lang.String");
        param.setRequired("N");
        param.setParamDesc("请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，格式为json。");
        param.setExampleValue("{\"extendedKey\": \"123\",\"extendedContext\":\"QQ\"}");
        param.setIndex(5);
        params.add(param);

        api.setParams(params);
        apis.add(api);

        List<CpiMethod> methods = this.cpiMethodService.queryByPage( null, -1, -1 );
        StringBuilder sb;
        Map<String, Object> queryCondition = new HashMap<>();
        queryCondition.put( "open", "Y" );
        for (CpiMethod c : methods) {
            sb = new StringBuilder();
            api = new Api();
            api.setName(c.getName());
            sb.append( "[" ).append( c.getMode() ).append("]").append( c.getApiName() ).append( "," ).append( "版本号[" )
                    .append( c.getApiVersion() ).append( "]" );
            api.setApiName(sb.toString());

            queryCondition.put( "methodId", c.getId() );
            List<CpiMethodParam> paramList = this.cpiMethodParamService.queryByPage( queryCondition, -1, -1 );
            api.setParams(paramList);
            //
            apis.add(api);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("headers", headers);
        data.put("apis", apis);

        result.setSuccess(true);
        result.addObject("data", data);

        return result;
    }

    @RequestMapping(value = "/MD", method = { RequestMethod.GET })
    public void initMD(HttpServletResponse response) throws IOException {
        String tTitle = "| 参数| 类型 | 是否必填 | 最大长度 | 描述 | 示例值| \n";
        String colu = "| -- | -- | -- | -- | -- | -- | \n";
        String par = " | {0} | {1} | {2}| {3}| {4}| {5}| {6} | \n";


        StringBuilder md = new StringBuilder();
        md.append( "# " ).append( "校开花v1.0接口" ).append( "\n" );
        md.append( "[TOC]" ).append( "\n" );
        md.append( "## 网关入口" ).append( "\n" );
        md.append( "1、统一请求地址：```URL:http://{domain}/gateway``` \n" );
        md.append( "2、公共参数 \n" );
        md.append( tTitle );
        md.append( colu );
        md.append( "| method| String | Y | /    | 接口名称 | xkhstar.user.logon.sms | \n" );
        md.append( "| version   | String | Y    | /    | 接口版本号  | 1.0.0  | \n" );
        md.append( "| format    | String | Y    | /    | 返回格式  | json | \n" );
        md.append( "| sso    | String | N    | /    | 登录标识  | WQODIJQWNGWOQWIJW | \n" );
        md.append( "| bizParams | String | N    | /    | 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，格式为json。 |  | \n" );
        md.append( "\n" );
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "open", "Y" );
        List<CpiMethod> methods = this.cpiMethodService.queryByPage( null, -1, -1 );
        for (CpiMethod c : methods) {
            md.append( "## " ).append( c.getName() ).append( "\n" );
            md.append( "1、接口名称：" ).append( "``` [" ).append( c.getMode() ).append("]").append( c.getApiName() ).append( "," ).append( "版本号[" )
                    .append( c.getApiVersion() ).append( "] ```\n" );
            params.put( "methodId", c.getId() );

            List<CpiMethodParam> paramList = this.cpiMethodParamService.queryByPage( params, -1, -1 );
            md.append( "2、业务参数：" ).append( "\n" );
            if (CollectionUtils.isNotEmpty( paramList )) {
                md.append( tTitle ).append( colu );
                for (CpiMethodParam p : paramList) {
                    md.append( MessageFormat.format( par, p.getParamName(), p.getParamType(), p.getRequired(), p.getLength() == null ? "/" : p.getLength(), p.getParamDesc(), "" ) );
                }
            } else {
                md.append( "无 \n" );
            }
        }
        response.setHeader( "Content-Disposition", "attachment; filename=" + DateUtils.getCurrentTime( "yyyyMMddHHmmss" ) + ".md" );
        response.getOutputStream().write( md.toString().getBytes() );

    }


}
