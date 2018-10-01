/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.providers;

import com.ethan.core.model.Pagination;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: qiang.caocq@ncs.com.sg
 * @creat_date: 2018-09-30 13:44
 **/
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class, Integer.class
        })
})
public class PageInterceptor implements Interceptor {
    private int pageSize;
    private int currPage;
    private int totalCount;
    private boolean isPagination=false;
    private static final String DEFAULT_PAGE_SQL=".*ByPage$";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取StatementHandler，默认是RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        //获取statementHandler包装类
        MetaObject hander = SystemMetaObject.forObject(statementHandler);

        //分离代理对象链
        while(hander.hasGetter("h")) {
            Object obj = hander.getValue("h");
            hander = SystemMetaObject.forObject(obj);
        }
        while(hander.hasGetter("target")) {
            Object obj = hander.getValue("target");
            hander = SystemMetaObject.forObject(obj);
        }
        //获取连接对象
        //Connection connection = (Connection) invocation.getArgs()[0];
        //object.getValue("delegate");  获取StatementHandler的实现类
        //获取查询接口映射的相关信息
        MappedStatement mappedStatement = (MappedStatement) hander.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();
        //statementHandler.getBoundSql().getParameterObject();
        //拦截以.ByPage结尾的请求，分页功能的统一实现
        if (mapId.matches(DEFAULT_PAGE_SQL)) {
            //获取进行数据库操作时管理参数的handler
            ParameterHandler parameterHandler= (ParameterHandler)hander.getValue("delegate.parameterHandler");
            //获取请求时的参数
            Object paraObject = parameterHandler.getParameterObject();
            //也可以这样获取
            //paraObject = (Map<String, Object>) statementHandler.getBoundSql().getParameterObject();
            String sql = (String) hander.getValue("delegate.boundSql.sql");
            Pagination pagination = null;
            Map<String,Object> pageMap = null;
            boolean isPage = false;
            if(paraObject instanceof Pagination) {
                pagination = (Pagination)paraObject;
                isPage = pagination.isPage();
                this.currPage=pagination.getCurrPage();
                this.pageSize=pagination.getPageSize();
                isPagination=true;
            }else if(paraObject instanceof Map) {
                pageMap = (Map<String,Object>)paraObject;
                if(pageMap.containsKey("currPage")) {
                    isPage = pageMap.get("isPage")==null?false:true;
                    this.currPage=(int) pageMap.get("currPage");
                    this.pageSize=(int) pageMap.get("pageSize");
                }else if(pageMap.containsKey("page")) {
                    pagination = (Pagination)pageMap.get("page");
                    isPage = pagination.isPage();
                    this.currPage = pagination.getCurrPage();
                    this.pageSize = pagination.getPageSize();
                }
                isPagination = true;
            }
            if(isPagination && isPage) {
                Connection connection = (Connection) invocation.getArgs()[0];
                PreparedStatement prepareStatement = connection.prepareStatement(contactCountSql(sql));

                //set query parameters ? in value
                BoundSql boundSql = (BoundSql) hander.getValue("delegate.boundSql");
                setPreparemeter(prepareStatement, mappedStatement, boundSql.getParameterObject(), boundSql);

                ResultSet rs = prepareStatement.executeQuery();
                while(rs.next()) {
                    this.totalCount = rs.getInt(1);
                }
                rs.close();
                prepareStatement.close();
                System.out.println(">>>>>>"+this.totalCount);
                if(pagination!=null) {
                    pagination.setTotalCount(totalCount);
                    pagination.setTotalPage(pagination.getTotalPage());
                }else if(pageMap !=null) {
                    pageMap.put("totalPage", totalCount);
                }
            }
            //也可以通过statementHandler直接获取
            //sql = statementHandler.getBoundSql().getSql();
            //构建分页功能的sql语句
            //将构建完成的分页sql语句赋值个体'delegate.boundSql.sql'，偷天换日
            hander.setValue("delegate.boundSql.sql", contactLimitSql(sql));
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object object) {
        return Plugin.wrap(object, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String limit1 = properties.getProperty("limit", "10");
        this.pageSize = Integer.valueOf(limit1);
//        this.dbType = properties.getProperty("dbType", "mysql");
    }
    private void setPreparemeter(PreparedStatement prepareStatement, MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        DefaultParameterHandler habdler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        habdler.setParameters(prepareStatement);
    }
    private String contactCountSql(String sql) {
        int firstIndex = sql.indexOf("select");
        int lastIndex = sql.indexOf("from");
        String character = sql.substring(firstIndex+6, lastIndex);
        return sql.replace(character, " count(1) ");
    }
    private String contactLimitSql(String sql) {
        String limitSql;
        sql = sql.trim();
        if (currPage<1) {
            currPage=1;
        }
        limitSql = sql + " limit " + (currPage - 1) * pageSize + "," + pageSize;
        return limitSql;
    }
}
