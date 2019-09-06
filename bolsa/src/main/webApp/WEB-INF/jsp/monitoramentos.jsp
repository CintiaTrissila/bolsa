<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monitoramentos</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
 <div class="container">
  <div class="col-md-offset-1 col-md-10">
   <h2>Monitoramentos</h2>
   <hr />

   <input type="button" value="Novo Monitoramento"
    onclick="window.location.href='showForm'; return false;"
    class="btn btn-primary" />
    <br/><br/>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Monitoramentos</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>Empresa</th>
       <th>Conta</th>
       <th>Valor de Compra</th>
       <th>Valor de Venda</th>
       <th>Ações</th>
      </tr>

      <c:forEach var="tempMonitoramento" items="${monitoramentos}">
       
       <c:url var="updateLink" value="/monitoramento/updateForm">
        <c:param name="monitId" value="${tempMonitoramento.id}" />
       </c:url>

       <c:url var="deleteLink" value="/monitoramento/delete">
        <c:param name="monitId" value="${tempMonitoramento.id}" />
       </c:url>

       <tr>
        <td>${tempMonitoramento.empresa.nome}</td>
        <td>${tempMonitoramento.conta.email}</td>
        <td>${tempMonitoramento.precoCompra}</td>
        <td>${tempMonitoramento.precoVenda}</td>

        <td>
         <a href="${updateLink}">Alterar</a>
         | <a href="${deleteLink}"
         onclick="if (!(confirm('Você quer realmente excluir esse registro?'))) return false">Excluir</a>
        </td>

       </tr>

      </c:forEach>

     </table>

    </div>
   </div>
  </div>

 </div>
</body>
</html>