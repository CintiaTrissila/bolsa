<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
      <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/css/style.css"/>
   </head>
   <body>
      <h1>Welcome</h1>
      <h2>${message}</h2>
       
     
         
      <a href="${pageContext.request.contextPath}/empresa/list">Empresas</a>  
      <a href="${pageContext.request.contextPath}/conta/list">Contas</a> 
      <a href="${pageContext.request.contextPath}/monitoramento/list">Monitoramentos</a>   
       
   </body>
   
</html>