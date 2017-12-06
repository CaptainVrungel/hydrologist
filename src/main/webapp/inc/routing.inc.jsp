<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String pageid = request.getParameter("page");	
%>

<% if(pageid != null){%>
	<% if(pageid.equals("about")) { %> 			<jsp:include page="about.inc.jsp"/> 		<% } %>
	<% if(pageid.equals("usage")) { %> 			<jsp:include page="usage.inc.jsp"/> 		<% } %>
	<% if(pageid.equals("connecton")) { %> 		<jsp:include page="connecton.inc.jsp"/>	 	<% } %>
	<% if(pageid.equals("api")) { %> 			<jsp:include page="api.inc.jsp"/> 			<% } %>
	<% if(pageid.equals("contacts")) { %> 		<%@include file="../inc/contacts.inc.jsp"%> <% } %>
	<% if(pageid.equals("development")) { %> 	<jsp:include page="development.inc.jsp"/> 	<% } %>
	<% if(pageid.equals("documentation")) { %> 	<jsp:include page="documentation.inc.jsp"/> <% } %>
	<% if(pageid.equals("faq")) { %> 			<jsp:include page="faq.inc.jsp"/> 			<% } %>
	<% if(pageid.equals("QmaxSpringSP")) { %> 	<jsp:include page="QmaxSpringSP.inc.jsp"/> 	<% } %>
<%} else {%>
	<jsp:include page="about.inc.jsp"/>
<%}%>