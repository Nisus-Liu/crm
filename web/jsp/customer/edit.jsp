<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <TITLE>添加客户</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>

    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>

    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>

    <script type="text/javascript">

        // 显示下拉框
        $(document).ready(function () {
            var url = '${pageContext.request.contextPath}/baseDict';
            alert('<s:property value="cust_name"/>');
            var loadType = "006";
            var toId = "slt_test";
            var val = "${cust_level.dictId}";
            var txt = "dictItemName"
            loadSelectBox(url, loadType, toId, val, txt, val);
        })

    </script>

</HEAD>
<BODY>
<FORM id=form1 name=form1
      action="${pageContext.request.contextPath }/customer_update"
      method=post>
    <%--隐藏域: 存放客户ID--%>

    <input type="hidden" name="cust_id" value="<s:property value='customer.cust_id'/>"/>

    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
                              border=0></TD>
            <TD width="100%" background=${pageContext.request.contextPath }/images/new_020.jpg
                height=20></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
                    src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
            <TD vAlign=top width="100%" bgColor=#ffffff>
                <TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
                    <TR>
                        <TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>
                <TABLE cellSpacing=0 cellPadding=5 border=0>
                    <TR>
                        <td>客户名称：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_name" value="${cust_name }">
                        </td>
                        <%--下拉框--%>
                        <td>客户级别 ：</td>
                        <td>
                            <%--^ 替换--%>
                            <%--<select class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_level">--%>
                                <%--<s:iterator value="#cust_level_list" var="baseDict">--%>
                                    <%--<option value="<s:property value='#baseDict'/>"--%>
                                            <%--<s:if test="#customer.cust_level.dictId==#baseDict.dictId">selected="selected"</s:if> >--%>
                                        <%--<s:property value="#baseDict.dictItemName"/></option>--%>
                                <%--</s:iterator>--%>
                            <%--</select>--%>
                            <%--使用struts2的标签定义select下来框--%>
                            <s:select list="#cust_level_list" name="cust_level.dictId" listKey="dictId" listValue="dictItemName"/>
                            <select id="slt_test">

                            </select>
                        </td>
                    </TR>

                    <TR>
                        <td>信息来源：</td>
                        <td>
                            <select class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_source.dictId">
                                <s:iterator value="#cust_source_list" var="baseDict">
                                    <option value="<s:property value='#baseDict.dictId'/>"
                                            <s:if test="cust_source.dictId==#baseDict.dictId">selected="selected"</s:if> >
                                        <s:property value="#baseDict.dictItemName"/></option>
                                </s:iterator>
                            </select>
                        </td>

                        <td>所属行业 ：</td>
                        <td>
                            <select class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_industry.dictId">
                                <s:iterator value="#cust_industry_list" var="baseDict">
                                    <option value="<s:property value='#baseDict.dictId'/>"
                                            <s:if test="cust_industry.dictId==#baseDict.dictId">selected="selected"</s:if> >
                                        <s:property value="#baseDict.dictItemName"/></option>
                                </s:iterator>
                            </select>
                        </td>

                    </TR>
                    <TR>
                        <td>联系电话 ：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_phone" value="${cust_phone }">
                        </td>
                        <td>客户地址 ：</td>
                        <td>
                        <INPUT class=textbox id=sChannel2
                        style="WIDTH: 180px" maxLength=50 name="cust_address" value="${cust_address }">
                        </td>
                    </TR>

                    <tr>
                        <td rowspan=2>
                            <INPUT class=button id=sButton2 type=submit
                                   value=" 保存 " name=sButton2>
                        </td>
                    </tr>
                </TABLE>


            </TD>
            <TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
                <IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
                              border=0></TD>
            <TD align=middle width="100%"
                background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
</FORM>
</BODY>
</HTML>
