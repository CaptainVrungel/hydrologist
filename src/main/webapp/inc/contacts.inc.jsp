<%@ page language="java" contentType="text/html; charset=UTF-8"
			   pageEncoding="UTF-8"%>
<div id = "content">
	<h3>Контактная информация</h3>
	<br>
	<table>
		<tr>
			<td><label for="userName">Имя пользователя </label></td>	<td><input type="text" id="userName" size = '20'/></td>
		</tr>
		<tr>
			<td><label for="calculationMethod">Метод расчёта </label></td>	<td><input type="text" id="calculationMethod" size = '20'/></td>
		</tr>
		<tr>
			<td></d><label for="accountid">Аккаунт </label></td><td><input type="text" id="accountid" size = '20'/></td>
		</tr>
	</table>

	<script>
        function getUrl(){
            var userName = document.getElementById("userName").value;
            var calculationMethod = document.getElementById("calculationMethod").value;
            var accountid = document.getElementById("accountid").value;

            var url = "http://localhost:8080/hello?userName=" + userName + "&calculationMethod=" + calculationMethod + "accountid=" +accountid;
            var link = "<a href = " + url + ">Fire!</a>"
            console.log(link);
            document.getElementById("link").innerHTML = link;
        }

	</script>
	<button onclick="getUrl()">Send data</button>
	<div id = "link"></div>
</div>