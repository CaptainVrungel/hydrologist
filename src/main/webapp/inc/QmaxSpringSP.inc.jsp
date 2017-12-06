<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script src="web/js/lib.js"></script>
<script>
    /*СДЕЛАТЬ:
     Нарисовать на графике расход заданной вероятности
     Блокировать вывод соотнощения CsCv, если выбрана кривая Пирсона
     Кривая Пирсона возвращает не верные данные - ряд: 321 231 111, Q1% = 0
     Округление значений, которые вставляем на форму ввода параметров аналитической кривой
     Написать юнит тесты - на каждый расчет отдельный тест. На вход даём разные данные и смотрим что получилось
     Логирование операций - когда какой класс что получил на вход и что выдал на выходе

     Релиз2:
     РАсход на кривой и расход полученный расчетом не соответствуют (пример - 1%) - в последний раз не замечено
     Кнопка "Добавить вероятность для расчета"/ Может быть сделать перечень вероятностей отдельным списком - вводишь, значение появляется в списке
     Переделать вызов addRowToTable из pasteRangeToFields. Вызывать один раз (не в цикле)
     Единые правила скрытия элементов (hiiden, display:none или еще что) //тут была какая-то мысль что лучше... хидден хуже, потому что в хтмл прописан. Но было еще что-то
     Ряд не правильно вставляется с серидины - начинает с начала, а если не вставлять второй раз, то остаётся в текущей строке
     Округление значений - сделать переменным параметром
     Если ввели одинаковые расходы, то ругается unexpected token <
     Переделать addRowToTable, чтоб не приходилось второй раз эту функцию делать в getHasenGraph
     При построении кривой обеспеченности - выбирать, либо использовать расчетные параметры, либо ввести руками - релиз 2
     Погрещность вычислений - релиз 2
     Возможность сохранения расчета (пишем ряд в БД) - релиз 2
     Возможность печатать график - релиз 2
     Описание методики расчета - релиз 2
     Экспорт отчета с расчетами - релиз 2
     бросать понятные exeption - релиз 2
     Расчет графоаналитическим методом, возможность выбора метода, которым считаем, в зависимости от этого дергаем разные файлы - релиз 2
     По нажатию на Enter переводить на след строку - релиз 2
     Ограничение на максимально допустимое число в расходах
     */

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
    function CheckData() { // Проверяет, чтобы было не пусто, + чтобы были только цифры, точка или запятая
        var counter = 0;
        var tab = document.getElementById('tabl');
        var trs = tab.getElementsByTagName("TR");
        var colomnNumber = trs[0].getElementsByTagName("TD").length; // считаем количество столбцов в таблице
        //var inputs = tab.getElementsByTagName("input");
        var inputs = $('#tabl :input:not([id ^= Year_])');
        //var requir = /[^\d\.,]/; 	// допустимые значения - только цифры, точка и запятая
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
        var tab = document.getElementById('tabl');
        var trs = tab.getElementsByTagName("TR");
        var colomnNumber = trs[0].getElementsByTagName("TD").length; // считаем количество столбцов в таблице
        var inputs = tab.getElementsByTagName("input");
        var len = inputs.length;
        //console.log("inputs.length: "+len);
        var tds1 = trs[1].getElementsByTagName("TD"); // выбираем все ячейки из строки

        for(var i=0; i<len; i++){ //перебираем в цикле все инпуты
            //console.log("cicle number: "+i);
            var val = inputs[i].value;//Получаем значение, которое лежит в текущем input
            if(val != ''){ // если в инпуте не пусто

                var inputId = inputs[i].getAttribute('id'); // Получаем id текущего input
                var newInputId = inputId.substring(0, inputId.lastIndexOf("_")+1); // Откидываем от полученного в прошлой строке значения id всё что после "_" - т.е. сам номер, оставляем только текст
                var arrVal = [];
                arrVal = val.split(' ');//Берем содержимое ячейки, разбиваем на подстроки по пробелам
                arrValLen = arrVal.length;
                //console.log("Массив: "+arrVal);
                //console.log("Длина массива: "+arrValLen);
                if(arrValLen > 1){
                    //console.log("include starts");
                    for(var j=0; j<arrValLen; j++){
                        //console.log("Цикл внутри: "+j);
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
</script>

<div id = "paramsHiddenDiv" hidden = 'true'><input type = 'textarea' id = "paramsHiddenCs"></input></div>
<div class = 'CalcHeader'><h3>Максимальные расходы воды весеннего половодья при наличии данных наблюдений</h3></div>

<div class = "form-inline">
    <div class='headFields'><h4>Исходные данные:</h4></div>
    <div class = 'form-group'>
        <label>Число лет наблюдений:</label>
        <input type="text" id="rowsNum" onblur='pastRowsNum()' style="background-color: rgb(255, 255, 255)" value='3' size = '4'></input>
    </div>
    <div class = 'form-group'>
        <label>Вероятность для расчета:</label>
        <input type="text" name="PTarget_0" id="PTarget_0" size = '4' style="background-color: rgb(255, 255, 255)">
        ,%
    </div>

</div>

<div>
    <table id='tabl' class="table table-bordered">
        <tr class = "firstRow">
            <!--Переделать td на th. Не забыть что при этом изменится яваскрипт (выбор всех тр)-->
            <th class = "firstCell"> № </th>
            <th class = "firstCell"> Дата </th>
            <th class = "firstCell"> Расход воды Q,м<sup>3</sup>/с </th>
        </tr>

        <tr>
            <td> 1 </td>
            <td> <input type="text" name="Year_0" class = 'rangeDataInput' id="Year_0" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()'></input> </td> <!--Год, в котором наблюдался расход воды -->
            <td> <input type="text" name="WaterFlow_0" class = 'rangeDataInput' id="WaterFlow_0" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()' style="background-color: rgb(255, 250, 230)"></input> </td> <!--Расход воды-->
        </tr>
        <tr>
            <td> 2 </td>
            <td> <input type="text" name="Year_1" class = 'rangeDataInput' id="Year_1" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()'></input> </td> <!--Год, в котором наблюдался расход воды -->
            <td> <input type="text" name="WaterFlow_1" class = 'rangeDataInput' id="WaterFlow_1" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()' style="background-color: rgb(255, 250, 230)"></input> </td> <!--Расход воды-->
        </tr>
        <tr>
            <td> 3 </td>
            <td> <input type="text" name="Year_2" class = 'rangeDataInput' id="Year_2" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()'></input> </td> <!--Год, в котором наблюдался расход воды -->
            <td> <input type="text" name="WaterFlow_2" class = 'rangeDataInput' id="WaterFlow_2" onkeypress='CheckData()' onkeyup='checkAndPast()' onblur='checkAndPast()' style="background-color: rgb(255, 250, 230)"></input> </td> <!--Расход воды-->
        </tr>
    </table>
</div>
<div class = "brd"></div>
<button type='button' class="btn btn-primary" id = 'calculate_button' onClick='doCalculations();' disabled='disabled'/>Рассчитать параметры ряда</button><!--С помощью свойства disabled сделать так, чтобы если данные не введены, то кнопка была не доступна -->
<div id="rangeParamsInfo" hidden = 'true'></div>
<button type='button' id = 'graphForm_button' onClick='getHasenGraphForm();' disabled='disabled' class="btn btn-primary">Построить кривую</button>
<button type='button' id = 'changeData_button' onClick='changeData()' disabled='disabled' class="btn btn-default">Изменить исходные данные</button>
<div id="Div_whith_image" hidden = 'true' class = "brd">
    <figure>
        <figcaption><h4>Кривая обеспеченности максимальных расходов воды весеннего половодья</h4></figcaption>
        <img src="" width="742" height="525" id="image_Id" title="Кривая обеспеченности">
    </figure>
</div>
<div id="analystCalcParams" hidden="true">

</div>

<div id = "HasenGraphForm" hidden="true">
    <div id="HasenGraphFormContent">
        <div id='HasenGraphFormHeader'>
            <h4>Параметры кривой обеспеченности</h4>
        </div>
        <div class = 'fields'>
            <h4>Шкала:</h4>

            <div class = 'fieldRight'>
                <table>
                    <tr>
                        <td>
                            <label>Максимальное значение: </label>
                            <input type="text" size="4" id = "HasenMaxValue" />
                        </td>
                        <td>
                            <label>Минимальное значение: </label>
                            <input type="text" size="4" id = "HasenMinValue" value = '0'/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Шаг основных делений: </label>
                            <input type="text" size="4" id = "HasenStepMajor"/>
                        </td>
                        <td>
                            <label>Шаг промежуточных делений: </label>
                            <input type="text" size="4" id = "HasenStepMinor" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class = 'fieldsSeparator'> </div>
        <div class = 'fields'>
            <h4>Отображение точек:</h4>
            <div class = 'field'>
                <input type="checkbox" checked='checked' value="true" id = "HasenSignValues"/>
                <label>Подписать значения; </label>

                <input type="checkbox" checked='checked' value="true" id = "HasenFilledPoints"/>
                <label>Заливка точек; </label>

                <label>Размер точек: </label>
                <select size="1" id = "HasenPointsSize">
                    <option value=0.6>0.3</option>
                    <option value=0.6>0.6</option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                </select>
            </div>
        </div>
        <div class = 'fieldsSeparator'> </div>
        <div class = 'fields'>
            <h4>Подписи к графику:</h4>
            <div class = 'field'>
                <input type="checkbox" checked='checked' value="true" id = "AnalystCurvePointSign" />
                <label>Подписывать аналитические значения; </label>

                <label>Подпись шкалы ординат: </label>
                <input type="text" size="4" id = "HasenAxisName" value = "Q,м3/с"  />
            </div>
        </div>
        <div class = 'fields'>
            <div class = 'field'>
                <label>Название кривой: </label>
                <input type="text" size="52" id = "HasenGraphName"/>
            </div>
        </div>
        <div class = 'fieldsSeparator'> </div>
        <div class = 'fields'>
            <h4>Тип клетчатки вероятностей:</h4>
            <div class = 'field'>
                <label>Диапазон вероятностей: </label>
                <select size="1" id = "HasenNetType">
                    <option value=0.1>0.1 - 99.9</option>
                    <option value=0.01>0.01 - 99.99</option>
                </select>
            </div>
            <div class = 'field'>
                <input type="checkbox" value="true" id = "AnalystCurveCreate" onClick='enableAnalyticGraph();'/>
                <label>Построить аналитическую кривую </label>
            </div>
        </div>
        <div class = 'fieldsSeparator'> </div>
        <div class = 'fields' id = "AnalystCurveParams" hidden = "true">
            <h4>Аналитическая кривая:</h4>
            <div class = 'field'>
                <label>Тип распределения: </label>
                <select size="1" id = "AnalystCurveType" onClick = "blockCsCv()">
                    <option value = 'Gamma3'>Трехпараметрическое гамма распределение</option>
                    <option value = 'Krickiy'>Крицкого-Менкеля</option>
                    <option value = 'Pirson3' onClick = "blockCsCv()">Пирсона III типа</option>
                </select>
            </div>
            <div class = 'field' id  = "AnalystCurveCsCvDiv">
                <label>Соотношение Cs/Cv:</label>
                <select size="1" id = "AnalystCurveCsCv">
                    <option value = 1>Cs = Cv</option>
                    <option value = 1.5>Cs = 1,5Cv</option>
                    <option value = 2>Cs = 2Cv</option>
                    <option value = 2.5>Cs = 2.5Cv</option>
                    <option value = 3>Cs = 3Cv</option>
                    <option value = 3.5>Cs = 3.5Cv</option>
                    <option value = 4>Cs = 4Cv</option>
                </select>
            </div>
            <div class = 'field'>
                <label>Цвет точек аналитической кривой:</label>
                <select size="1" id = "AnalystCurvePointColor">
                    <option value = "34,139,34">Зеленый</option>
                    <option value = "255,0,0">Красный</option>
                    <option value = "255,255,0">Желтый</option>
                    <option value = "0,255,255">Голубой</option>
                    <option value = "0,0,255">Синий</option>
                    <option value = "255,0,255">Фиолетовый</option>
                    <option value = "0,0,0">Черный</option>
                </select>
            </div>
        </div>

        <div id = 'HasenGraphFormFooter'>
            <button type='button' id = 'graph_button' onClick='getHasenGraph();' disabled='disabled' class="btn btn-primary">Построить кривую</button>
            <button type='button' id = 'canselGraphButton' onClick='canselGraphForm();' class="btn btn-default">Отмена</button>
        </div>
    </div>
</div>
<button type='button' id = 'canselAllButton' onClick='canselAllButton();' class="btn btn-default">Вернуться к исходным данным</button>

</body>
</html>