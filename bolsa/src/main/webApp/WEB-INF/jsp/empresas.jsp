<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Empresas</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
 <div class="container">
  <div class="col-md-offset-1 col-md-10">
   <h2>Empresas</h2>
   <hr />

   <input type="button" value="Nova Empresa"
    onclick="window.location.href='showForm'; return false;"
    class="btn btn-primary" />
    <br/><br/>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Empresas</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>Nome</th>
       <th>Valor ação</th>
       <th>Ações</th>
      </tr>

      <c:forEach var="tempEmpresa" items="${empresas}">

       <c:url var="updateLink" value="/empresa/updateForm">
        <c:param name="empresaId" value="${tempEmpresa.id}" />
       </c:url>

       <c:url var="deleteLink" value="/empresa/delete">
        <c:param name="empresaId" value="${tempEmpresa.id}" />
       </c:url>

       <tr>
        <td>${tempEmpresa.nome}</td>
        <td>${tempEmpresa.valorAcao}</td>

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