/*Добавляет в таблицу столько строк, сколько передали функции на вход*/
function addRowToTable(numOfRows, tableID){
    /*Проверяем, является ли переданный в функцию параметр numOfRows числом. Если нет, то возвращаем ошибку*/
    if(isNum(numOfRows) == false){
        numOfRows = 1;
    }
    /*Считываем элементы в переменные*/
    var tab = document.getElementById(tableID); // считываем в переменную tab таблицу с расходами
    var trs = tab.getElementsByTagName("TR"); //считываем в переменную trs все элементы tr (строки) из таблицы с расходами
    var tds = trs[0].getElementsByTagName("TH"); //считываем в переменную tds все элементы td (ячейки) из первой строки таблицы (заголовки таблицы)
    var x = trs[1].getElementsByTagName("TD"); // Считываем в переменную "х" все элементы td из первой строки таблицы
    var tds1 = trs[1].getElementsByTagName("TD"); // выбираем все ячейки из первой строки

    /*В цикле создаём столько строк, сколько передано в функцию*/
    for(var j=0; j<numOfRows; j++){
        var row = document.createElement("TR");//Создаём строку

        /*Проходим циклом по всем столбцам*/
        var tdLen = tds.length;
        for (var i=0; i<tdLen; i++){
            var y = x[i].getElementsByTagName("input").length; // Смотрим, сколько попало в x тэгов input (полей ввода)
            if (y == 0){ // Если в "х" не попало ни одного input, (это означает что имеем дело с ячейкой, в которой нет инпута)
                if (i == 0){ // в первый столбец записываем порядковый номер строки
                    var cell = document.createElement("TD");
                    cell.appendChild(document.createTextNode(trs.length));
                    row.appendChild(cell);
                }else{ // для всех строк, в которых нет input, кроме первой, оставляем пустое поле
                    var cell = document.createElement("TD");
                    row.appendChild(cell);
                }
            }else{
                var inputType = tds1[i].getElementsByTagName("input")[0].getAttribute('type'); // Получаем input type из первой строки таблицы
                var inputId = tds1[i].getElementsByTagName("input")[0].getAttribute('id'); // Получаем id из первой строки таблицы
                var newInputId = inputId.substring(0, inputId.lastIndexOf("_")+1); // Откидываем от полученного в прошлой строке значения id всё что после "_" - т.е. сам номер, оставляем только текст

                var cell = document.createElement("TD");
                var input = document.createElement('input');
                input.setAttribute('type', inputType); // устанавливаем для нового поля input type, такой же, какой стоит в первой строке таблицы
                input.setAttribute('class', 'rangeDataInput'); // устанавливаем для нового поля класс, такой же, какой стоит в первой строке таблицы. На этот класс навешиваем стили
                input.setAttribute('onkeypress', 'CheckData()');
                input.setAttribute('onkeyup', 'CheckData()');
                input.setAttribute('onblur', 'CheckData()');
                input.setAttribute('id', newInputId + (trs.length-1)); // устанавливаем для нового поля id, для этого к полученной подстроке подставляем номер текущей строки в таблице
                cell.appendChild(input);
                row.appendChild(cell);

            }
        }
        $('#'+tableID+' > tbody:last').append(row);
    }
    CheckData();
}

/*Удаляет из таблицы столько столбцов, сколько передали на вход*/
function removeRowFromTable(numOfRows, tableID){  // функция удаляет одну строку из таблицы (сначала считает количество строк, затем удаляет последнюю)
    var tab = document.getElementById(tableID);
    var trs = tab.getElementsByTagName("TR");
    for(var i=0; i<numOfRows; i++){
        var n = trs.length; // считаем сколько осталось в таблице строк
        if(n>2){ 					// если осталась всего одна строка в таблице, то не даём её удалить - выводим алерт
            $('#'+tableID+' tr:last-child').remove();
        }
        else{
            //alert ("Должна быть хотя бы одна строка!");
            break;
        }
    }
    CheckData();
}

/*Берет значение из поля с количеством строк, и вставляет/удаляет столько строк, сколько нехватает до заданного числа*/
function pastRowsNum(){
    var tab = document.getElementById('tabl');
    var trs = tab.getElementsByTagName("TR");
    var n = trs.length-1;
    var rowsNumInput = document.getElementById('rowsNum');
    var rowsNum = rowsNumInput.value;
    if(rowsNum === n){

    }
    if(rowsNum < n){
        var m = (n - rowsNum);
        removeRowFromTable(m, 'tabl');
    }
    if(rowsNum > n){
        var m = rowsNum - n;
        addRowToTable(m, 'tabl');
    }
    n = trs.length-1;
    rowsNumInput.value = n;
}

/*Берет значения из полей и формирует из них массив*/
function getRangeFromInputs(){
    var tab = document.getElementById('tabl');
    var trs = tab.getElementsByTagName("TR");
    var waterFlows = [];//Это расходы, введенные юзером
    var n = trs.length-1;

    for(var i=0; i<n; i++){
        waterFlows[i]=document.getElementById('WaterFlow_'+ i).value*1;
    }
    return waterFlows;
}

/*Получает введенные пользователем параметры кривой Хазена*/
function getHasenGraphParams(){
    var HasenMaxValue = document.getElementById('HasenMaxValue').value*1;
    var HasenMinValue = document.getElementById('HasenMinValue').value*1;
    var HasenStepMajor = document.getElementById('HasenStepMajor').value*1;
    var HasenStepMinor = document.getElementById('HasenStepMinor').value*1;
    var HasenGraphName = document.getElementById('HasenGraphName').value;
    var HasenAxisName = document.getElementById('HasenAxisName').value;
    var HasenFilledPoints;
    if(document.getElementById('HasenFilledPoints').checked){
        HasenFilledPoints = true;
    }else{
        HasenFilledPoints = false;
    };
    var HasenSignValues;
    if(document.getElementById('HasenSignValues').checked){
        HasenSignValues = true;
    }else{
        HasenSignValues = false;
    };
    var HasenNetType = document.getElementById('HasenNetType').value*1;
    var HasenPointsSize = document.getElementById('HasenPointsSize').value*1;
    var HasenAnalystCurveCreate; //Строим ли аналитическую кривую
    if(document.getElementById('AnalystCurveCreate').checked){
        HasenAnalystCurveCreate = true;
    }else{
        HasenAnalystCurveCreate = false;
    };
    var PTarget = document.getElementById('PTarget_0').value*1;

    var HasenGraphParams = { //Создаем один большой объект, в который записываем все параметры формы
        HasenMaxValue:HasenMaxValue,
        HasenMinValue:HasenMinValue,
        HasenStepMajor:HasenStepMajor,
        HasenStepMinor:HasenStepMinor,
        HasenGraphName:HasenGraphName,
        HasenYAxisName:HasenAxisName,
        HasenSignValues:HasenSignValues,
        HasenNetType:HasenNetType,
        HasenFilledPoints:HasenFilledPoints,
        HasenPointsSize:HasenPointsSize,
        HasenAnalystCurveCreate:HasenAnalystCurveCreate,
        PTarget:PTarget
    };
    if(HasenAnalystCurveCreate == true){//Если строим кривую, то сохраняем её параметры и добавляем их к общему списку
        var HasenAnalystCurveType = document.getElementById('AnalystCurveType').value;
        var HasenAnalystCurveCsCv = document.getElementById('AnalystCurveCsCv').value*1;
        HasenGraphParams['HasenAnalystCurveType'] = HasenAnalystCurveType;
        HasenGraphParams['HasenAnalystCurveCsCv'] = HasenAnalystCurveCsCv;
        HasenGraphParams['Cv'] = $("table#rangeParamsTable td:nth-child(2)").html();
        HasenGraphParams['Cs'] = $("table#rangeParamsTable td:nth-child(3)").html();
        var AnalystCurvePointColor = document.getElementById('AnalystCurvePointColor').value;
        HasenGraphParams['AnalystCurvePointColor'] = AnalystCurvePointColor;
        var AnalystCurvePointSign;
        if(document.getElementById('AnalystCurvePointSign').checked){
            AnalystCurvePointSign = true;
        }else{
            AnalystCurvePointSign = false;
        };
        HasenGraphParams['AnalystCurvePointSign'] = AnalystCurvePointSign;
    }

    return HasenGraphParams;
}
/*Выполняет расчет максимальных расходов воды весеннего половодья*/
function doCalculations(){
    var tab = document.getElementById('tabl');
    var trs = tab.getElementsByTagName("TR");
    var waterFlows = [];//Это расходы, введенные юзером
    var years = []; //Это годы, введенные юзером
    var pTarget = 1; //Это вероятность для расчета
    var P=[]; //Это вероятности, получим их сюда
    var answer = ""; //Сюда будем принимать ответ от сервера
    var n = trs.length-1;

    /*Формируем массив расходов*/
    for(var i=0; i<n; i++){
        //получаем расходы из полей, заменяем запятые на точки в расходах и годах
        years[i] = replaceCommaInNum(document.getElementById('Year_'+ i).value);
        waterFlows[i] = replaceCommaInNum(document.getElementById('WaterFlow_'+ i).value);
    }
    /*Готовим запрос на сервер*/
    pTarget = replaceCommaInNum(document.getElementById("PTarget_0").value); // Это вероятность, на которую будем считать
    var requestData ={}; // В этой переменной собираем запрос на сервер
    /*
     Структура запроса:
     Название: строка
     Годы: массив значений (предположительно числовых)
     Расходы воды: массив значений (предположительно числовых)
     Вероятность на которую считаем: число
     */
    requestData.ObjectName = "Noname River";  //Здесь собираем запрос, который отправим на сервер
    requestData.Dates = years;
    requestData.Range = waterFlows;
    requestData.Probability = pTarget;
    var jsonRequestString = JSON.stringify(requestData);
    console.log(jsonRequestString);
    /*Отправляем запрос на сервер*/
    var req = getXmlHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState != 4){
            return;
        }
        /*Получаем от сервера готовую таблицу, выводим её*/
        answer = req.responseText;
        //document.getElementById('paramsHiddenCv').value = answer.Cv; //Тут вот косяк. Нам надо как-то сохранить Cv и Cs, чтобы всё работало
        //document.getElementById('paramsHiddenCs').value = answer.Cs;

        document.getElementById("rangeParamsInfo").hidden = false;
        document.getElementById("rangeParamsInfo").innerHTML = answer;
    }
    req.open("POST", "classes/QmaxSpring.class.php", true);
    //req.setRequestHeader("Content-Type","text-plain");
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
    //req.setRequestHeader("Content-Length",jsonRequestString.length);
    req.send(jsonRequestString);

    $('#calculate_button , #tabl').css('display', 'none');
    $('#graphForm_button , #changeData_button').css('display', 'inline-block');
    $('.rangeDataInput , #rowsNum, #PTarget_0').prop("disabled", true);
    $('.rangeDataInput , #rowsNum, #PTarget_0').css("background-color", "#F2F2F2");
    //$('#tableData').css("display", 'inline-table');
    $('#rangeParamsInfo').attr('hidden', false);
}

/*Строит кривую вероятностей Хазена (получает ее от сервера)*/
function getHasenGraph(){
    if($('#HasenMinValue').val() > $('#HasenMaxValue').val()){
        alert("Введенные параметры шкалы не могут быть обработаны: минимальное значение превышает максимальное. При построении графика минмальное значение будет принятно равным 0");
    }
    var pictureId = ''; //Здесь будет идентификатор картинки, на которой изображена кривая Хазена
    var arrParams = getHasenGraphParams(); //Получаем параметры кривой, введенные пользователем
    var arrWaterFlows = getRangeFromInputs(); //Получаем массив расходов
    var requestString = {waterFlows:arrWaterFlows, HasenGraphParams:arrParams} //Склеиваем в единую строку расходы и параметры графика
    var jsonRequestString = JSON.stringify(requestString); // Преобразуем строку запроса в формат JSON
    var req = getXmlHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState != 4) {
            return;
        }
        answer = req.responseText; // Получаем от сервера Вероятности, выводим их.
        var answerJSON = JSON.parse(answer);
        pictureId = answerJSON['ImageID'];
        var img = document.getElementById("image_Id"); //Отрисовываем новую картинку
        img.setAttribute('src', 'TempFiles/'+pictureId+'.png');
        if(document.getElementById('AnalystCurveCreate').checked === true){ //Если пользователь велел кривую строить, то показываем блок с картинкой и таблицы
            $("#analystCalcParams").append(answerJSON['analystParamsTable']);
            $("#analystCalcParams").append(answerJSON['analystOrdinatesTable']);
            $('#analystCalcParams').attr('hidden', false); //Открываем блок с картинкой и параметрами
        }
    }
    req.open("POST", "classes/HasenGraph.class.php", true);
    req.setRequestHeader("Content-Type","text-plain");
    req.send(jsonRequestString);

    //Скрываем формочку и показываем нужные кнопки
    $('#HasenGraphForm , #HasenGraphFormContent , #AnalystCurveParams').attr('hidden', true);
    $('#HasenGraphForm').css('display', 'none');
    $('#Div_whith_image').attr('hidden', false);
    $('#canselAllButton').css('display', 'inline-block');
}

/*Проверяет введенные в поля данные */
function CheckData() {
    var counter = 0;

    var container = document.getElementById('initialData');
    var inputs = container.getElementsByTagName('input')
    var requir = /^([0-9]+)([.,]?)([0-9]*)$/; 	// Целые числа и числа с плавающей точкой (разделитель точка или запятая)

    for(var i=0; i<inputs.length; i++){
        var inputType = inputs[i].getAttribute('type');
        var requirTest = requir.test(inputs[i].value);	// проверяем, выполняется ли условие выше
        if(inputType == "text"){
            if(inputs[i].value == ''){ // если в инпуте пусто
                $("#calculate_button, #graph_button").prop("disabled", true);
                inputs[i].style.backgroundColor = 'rgb(255, 250, 230)';
                counter++;
            }else{
                if(requirTest === false){ // если в инпуте допустимые символы
                    $("#calculate_button, #graph_button").prop("disabled", true);
                    inputs[i].style.backgroundColor = 'rgb(255, 250, 230)';
                    counter++;
                }else{
                    inputs[i].style.backgroundColor = 'rgb(255, 255, 255)';
                }
            }
        }
    }
    if(counter === 0) {
        $("#calculate_button, #graph_button, #graphForm_button, #changeData_button").prop("disabled", false);
    }
}

/*Вставляет значения из буфера в поля на странице*/
function pasteRangeToFields(){

    var tab = document.getElementById('tableForm:initialDataTable');
    var trs = tab.getElementsByTagName("TR");
    var colomnNumber = trs[0].getElementsByTagName("TD").length; // считаем количество столбцов в таблице
    var inputs = tab.getElementsByTagName("input");
    var len = inputs.length;

    var tds1 = trs[1].getElementsByTagName("TD"); // выбираем все ячейки из строки

    for(var i=0; i<len; i++){ //перебираем в цикле все инпуты
        //console.log("cicle number: "+i);
        var val = inputs[i].value;//Получаем значение, которое лежит в текущем input
        if(val != ''){ // если в инпуте не пусто

            var arrVal = [];
            arrVal = val.split(' ');//Берем содержимое ячейки, разбиваем на подстроки по пробелам
            arrValLen = arrVal.length;

            if(arrValLen > 1){
                for(var j=0; j<len; j++){
                    inputs[j].value = arrVal[j];
                    if(j!=0){
                        if(!document.getElementById(newInputId + j)){ //Если существует поле для ввода, в котором id = название текущего столбца_i
                            addRowToTable(1, 'tabl'); //Создаем такое количество tr, сколько получилось подстрок
                        }
                    }
                    if(arrVal[j] != ''){
                        document.getElementById(newInputId + j).value = arrVal[j]; //выводим в созданные input подстроки по очереди
                    }
                }

            }
        }else{
        }
    }
    trs = tab.getElementsByTagName("TR");
    var n = trs.length-1;
    document.getElementById('rowsNum').value = n;
}

/*Вызывает функцию вставки значений из буфера и проверяет вставленные данные*/
function checkAndPast(){
    pasteRangeToFields();
    CheckData();
}

/*показывает форму настройки кривой Хазена*/
function getHasenGraphForm(){
    $('#graph_button').css('display', 'inline-block');
    $('#graphForm_button , #changeData_button').css('display', 'none');
    $('#HasenGraphForm , #HasenGraphFormContent').css('display', 'block');
    var sdf = getRangeFromInputs();
    HasenMaxValue = Math.max.apply(null, sdf); //Получаем максимальное значение из ряда;
    var HasenMaxValueGraph = customRound(HasenMaxValue*1.3, 0); //Считаем какое значение надо подставить в поле с максимальным значением на кривой
    $('#HasenMaxValue').attr('value', HasenMaxValueGraph);
    var stepValues = [1, 2, 5];
    stepValuesLen = stepValues.length;
    /*Подбираем шаг делений*/
    var valStep = customRound(HasenMaxValueGraph/10, 0);
    for(var j = 1; j<HasenMaxValueGraph; j=j*10){
        var breakPoint = 0;
        for(var i=0; i<stepValues.length; i=i+1){
            if(valStep < stepValues[i]*j){
                valStep = stepValues[i]*j;
                breakPoint = 1;
                break;
            }
        }
        if(breakPoint === 1){
            break;
        }
    }
    $('#HasenStepMajor').attr('value', valStep);
    $('#HasenStepMinor').attr('value', valStep/5);
}

/*Показывает форму настройки аналитической кривой*/
function enableAnalyticGraph(){
    if(document.getElementById('AnalystCurveCreate').checked){
        document.getElementById('AnalystCurveParams').hidden = false;
    }else{
        document.getElementById('AnalystCurveParams').hidden = true;
    }
}

function changeData(){
    $('#calculate_button').css('display', 'inline-block');
    $('#graphForm_button , #changeData_button').css('display', 'none');
    $('#rowsNum , #PTarget_0').attr('disabled', false);
    $('.rangeDataInput').prop("disabled", false);
    $('#tabl').css('display', 'inline-table');
    //Очищаем таблицу с данными
    //$('#tableData td:not(.firstCell)').empty();
    $('#rangeParamsInfo').attr('hidden', true);
    $('.rangeDataInput , #rowsNum, #PTarget_0').prop("disabled", false); //Возвращаем редактируемость полям вероятности и еще чему то
    $('.rangeDataInput , #rowsNum, #PTarget_0').css("background-color", "#FFF");  //Красим поля вероятности в белый цвет
}
function canselGraphForm(){
    $('#AnalystCurveParams').attr('hidden', true);
    $('#HasenGraphForm , #HasenGraphFormContent').css('display', 'none');
    $('#graph_button').css('display', 'none');
    $('#graphForm_button , #changeData_button').css('display', 'inline-block');
    $('#AnalystCurveCreate').attr('checked', false);
}
function canselAllButton(){
    changeData();
    $('#Div_whith_image , #analystCalcParams').attr('hidden', true);
    $('#canselAllButton').css('display', 'none');
}
//Блокирует выбранный элемент
function blockCsCv(){
    if($("#AnalystCurveType option:selected").val() == "Pirson3"){
        $("#AnalystCurveCsCv").prop("disabled", true);
        $("#AnalystCurveCsCvDiv").css('display', 'none');
    }else{
        $("#AnalystCurveCsCv").prop("disabled", false);
        $("#AnalystCurveCsCvDiv").css('display', 'block');
    }
}