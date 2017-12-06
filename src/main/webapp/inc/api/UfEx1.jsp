<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<script type="text/javascript" src="http://test.doc-upload.mos.ru/uform3.0/js/ched-form.js"></script>
		
		<form class="form-horizontal">
		  <div class="form-group">
			<label for="inputChanel" class="col-sm-2 control-label">Канал:</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="inputChanel" placeholder="Chanel" value = "test">
			</div>
		  </div>
		  <div class="form-group">
			<label for="inputService" class="col-sm-2 control-label">Сервис:</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="inputService" placeholder="Service" value = "index">
			</div>
		  </div>
		   <div class="form-group">
			<label for="inputID_1" class="col-sm-2 control-label">ID 1</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="inputID_1" placeholder="{Идентификатор документа в ЦХЭД}" value = "{857E07CB-261B-4C94-B20E-602D28FFF960}">
			</div>
		  </div>
		  <div class="form-group">
			<label for="inputID_2" class="col-sm-2 control-label">ID 2</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="inputID_2" placeholder="{Идентификатор документа в ЦХЭД}" value = "{DED18B61-7A24-4F3D-9B71-E195EF639FE1}">
			</div>
		  </div>
		  <div class="form-group">
			<label for="inputID_3" class="col-sm-2 control-label">ID 3</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="inputID_3" placeholder="{Идентификатор документа в ЦХЭД}" value = "{857E07CB-261B-4C94-B20E-602D28FFF960}">
			</div>
		  </div>
		</form>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			  <button onClick="getUform()" class="btn btn-primary">Встроить форму ЦХЭД на страницу</button>
			</div>
		  </div>
		  <br>
	<div id = "uformDiv" ></div>
			
		<script type="text/javascript">
			
		function getUform(){
			var onFormLoad = function(uform){
				console.info("Форма загружена");
			}

			var onUpload = function(data){
				console.info(data);
			}

			var onError = function (errorObject){
				console.info(errorObject);
			}
			
			var channel = document.getElementById("inputChanel").value;
			var service = document.getElementById("inputService").value;
			inputs = document.getElementsByTagName('input');
			console.log(inputs);
			
			var docID = [];
			for(var i = 2; i<inputs.length; i++){
				docID[i] = inputs[i].value;
			}
			console.log(docID);
			
			var configs = {
				domContainerId: "uformDiv",
				channel : channel,
				service: service,
				handleErrors: true,
				preloadedDocuments: docID,
				enabled: true,
				callbacks: {
					onFormLoad: onFormLoad,
					onUpload : onUpload,
					onError: onError
				}
			}
			
			var uForm = createUForm(configs);
				
		}
		</script>
	
	