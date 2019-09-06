<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo Monitoramento</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Novo Monitoramento</div>
    </div>
    <div class="panel-body">
     <form:form action="saveMonitoramento" cssClass="form-horizontal"
      method="post" modelAttribute="monit">

      <form:hidden path="id" />
	
	  <div class="form-group">
	  	<label for="empresa" class="col-md-3 control-label">Empresa</label>
	  	<div class="col-md-9">	  	
			<select name="empresa.id">
			    <c:forEach items="${empList}" var="e">
			        <option value="${e.id}">${e.nome}</option>
			    </c:forEach>
			</select>
	  	</div>
	  </div>	
	  
	  <div class="form-group">
	  	<label for="empresa" class="col-md-3 control-label">Conta</label>
	  	<div class="col-md-9">	  	
			<select name="conta">
			    <c:forEach items="${contaList}" var="c">
			        <option value="${c.id}">${c.email}</option>
			    </c:forEach>
			</select>
	  	</div>
	  </div>	

      <div class="form-group">
       <label for="email" class="col-md-3 control-label">Valor de compra</label>
       <div class="col-md-9">
        <form:input path="precoCompra" cssClass="form-control" />
       </div>
      </div>
      </div>
      
      <div class="form-group">
       <label for="saldo" class="col-md-3 control-label">Valor de venda</label>
       <div class="col-md-9">
        <form:input path="precoVenda" cssClass="form-control" />
       </div>
      </div>


      <div class="form-group">
       <!-- Button -->
       <div class="col-md-offset-3 col-md-9">
        <form:button cssClass="btn btn-primary">Salvar</form:button>
       </div>
      </div>

     </form:form>
    </div>
   </div>
  </div>
 </div>
</body>
</html>