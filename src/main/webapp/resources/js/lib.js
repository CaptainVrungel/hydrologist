/*Округление*/
function customRound(value, accuracy){
	if(isNum(value) == true || isNum(accuracy) == true){
			value = Math.round(value*(Math.pow(10, accuracy)))/(Math.pow(10, accuracy));
			return value;
	}else{
		return 'function customRound ERROR value and accuracy should be a number';
	}
}
	
/*Проверка на число*/
function isNum(num) {
	if ( num == 0 ) return true;
	return res = ( num / num ) ? true : false;
}

/*Считает количество переданных на вход объектов, возвращает их количество*/
function countOfOject(obj) {
  var t = typeof(obj);
  var i=0;
  if (t!="object" || obj == null) return 0;
  for (x in obj) i++;
  return i;
}
function replaceCommaInNum(val){
	val = val.replace(/\,/g, ".")*1;
	return val;
}

/* Функция возвращат объект XMLHttpRequest */
function getXmlHttpRequest()
{
    if (window.XMLHttpRequest)
    {
        try
        {
            return new XMLHttpRequest();
        }
        catch (e){}
    }
    else if (window.ActiveXObject)
    {
        try
        {
            return new ActiveXObject('Msxml2.XMLHTTP');
        } catch (e){}
        try
        {
            return new ActiveXObject('Microsoft.XMLHTTP');
        }
        catch (e){}
    }
    return null;
}

function showProgress(data) {

	if(data.status == "begin"){
		document.getElementById("loading_wrapper").style.display = "block";
	} else if(data.stackTrace == "success"){
		document.getElementById("loading_wrapper").style.display = "none";
	}
}
	