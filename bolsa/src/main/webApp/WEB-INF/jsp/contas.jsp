<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contas</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
 <div class="container">
  <div class="col-md-offset-1 col-md-10">
   <h2>Contas</h2>
   <hr />

   <input type="button" value="Nova Conta"
    onclick="window.location.href='showForm'; return false;"
    class="btn btn-primary" />
    <br/><br/>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Contas</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>E-mail</th>
       <th>Número de ações</th>
       <th>Saldo</th>
       <th>Ações</th>
      </tr>

      <c:forEach var="tempConta" items="${contas}">

	   <c:url var="depositarLink" value="/conta/depositarForm">
        <c:param name="contaId" value="${tempConta.id}" />
       </c:url>
       
       <c:url var="updateLink" value="/conta/updateForm">
        <c:param name="contaId" value="${tempConta.id}" />
       </c:url>

       <c:url var="deleteLink" value="/conta/delete">
        <c:param name="contaId" value="${tempConta.id}" />
       </c:url>

       <tr>
        <td>${tempConta.email}</td>
        <td>${tempConta.numeroAcoes}</td>
        <td>${tempConta.saldo}</td>

        <td>
        <a href="${depositarLink}">Depositar</a>
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