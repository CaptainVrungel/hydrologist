<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	String pageid = request.getParameter("page");
	String subpageid = request.getParameter("subpage");	
%>

<% if(subpageid != null){%>

	<% if(subpageid.equals("jsapi")) { %> 			<jsp:include page="jsapi.inc.jsp"/> 		<% } %>
	<% if(subpageid.equals("uform")) { %> 			<jsp:include page="uform.inc.jsp"/> 		<% } %>
	<% if(subpageid.equals("publicapi")) { %> 		<jsp:include page="publicapi.inc.jsp"/> 	<% } %>
	<% if(subpageid.equals("serverapi")) { %> 		<jsp:include page="serverapi.inc.jsp"/> 	<% } %>
	
<%} else {%>
	<jsp:include page="jsapi.inc.jsp"/>
<%}%>