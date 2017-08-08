function showHint(str)
{
var xmlhttp;
if (str.length==0)
  { 
  document.getElementById("myDiv").innerHTML="";//登录中的提示,在未输入信息时清空
  document.getElementById("myDiv2").innerHTML="";//注册中的提示，,在未输入信息时清空
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;//在登录模块中输出
    document.getElementById("myDiv2").innerHTML=xmlhttp.responseText;//在注册模块中输出
    }
  }
xmlhttp.open("GET","user/validate?code="+str,true);
xmlhttp.send();
}
