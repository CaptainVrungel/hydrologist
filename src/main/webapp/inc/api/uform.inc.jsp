<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div id = "nav nav-pills nav-justified-1">
		<ul class="nav nav-tabs">
			<li role="presentation"><a href="index.jsp?page=api&subpage=jsapi">Javascript API</a></li>
			<li role="presentation" class="active"><a href="index.jsp?page=api&subpage=uform">Универсальная форма</a></li>
			<li role="presentation"><a href="index.jsp?page=api&subpage=publicapi">Публичное REST API</a></li>
			<li role="presentation"><a href="index.jsp?page=api&subpage=serverapi">Приватное SOAP/REST API</a></li>
		</ul>
	</div>
	<div id = "UForm" >
		<h3>Универсальная форма</h3>
		<p>
			Универсальная форма представляет собой WEB-интерфейс, позволяющий конечным пользователям прикладных информационных систем взаимодействовать с хранилищем напрямую. 
		</p>
		<p><a href="files/Спецификация УФ 3.3.4.docx">Спецификация универсальной формы ЦХЭД</a></p>
		

		<div class="well">
			<h4>Пример использования универсальной формы</h4>
			<jsp:include page="UfEx1.jsp"/>
		</div>
	</div>
