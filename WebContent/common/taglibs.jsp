<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--  system values--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="addr" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<c:set var="siteName" value="青鸟自动化办公系统" />
<%-- css file define--%>
<c:set var="index_css" value="${ctx}/css/index.css"/>
<c:set var="validate_css" value="${ctx}/css/validate.css"/>
<%-- js file define --%>
<c:set var="jquery_js" value="${ctx}/js/jquery.js"/>
<c:set var="jquery_form_js" value="${ctx}/js/jquery.form.js"/>
<c:set var="jquery_select_js" value="${ctx}/js/jquery.select.js"/>
<c:set var="WdatePicker_js" value="${ctx}/js/My97DatePicker/WdatePicker.js"/>
<c:set var="thickbox_js" value="${ctx}/js/thickbox.js"/>
<c:set var="validate_js" value="${ctx}/js/jquery.validate.js"  />
<c:set var="area_js" value="${ctx}/js/area.js"  />
<c:set var="jmpopups_js" value="${ctx}/js/jquery.jmpopups.js"  />
<c:set var="map_js" value="${ctx}/js/map.js"  />
<c:set var="cookie_js" value="${ctx}/js/jquery.cookie.js"  />
<c:set var="util_js" value="${ctx}/js/util.js"  />
<%@ page trimDirectiveWhitespaces="true" %>