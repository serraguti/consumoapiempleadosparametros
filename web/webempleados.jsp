<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller" class="controllers.ControllerEmpleados"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="oficio" value="${param.cajaoficio}"/>
        <c:set var="incremento" value="${param.cajaincremento}"/>
        <c:if test="${oficio != null}">
            <h1>Incrementado</h1>
            <c:set var="mensaje" value="${controller.incrementarSalarioOficios(oficio, incremento)}"/>
        </c:if>
        <h1>Consumo Api Empleados Parametros</h1>
        <form method="post">
            <label>Oficio: </label>
            <input type="text" name="cajaoficio"/><br/>
            <label>Incremento salarial: </label>
            <input type="text" name="cajaincremento"/><br/>
            <button type="submit">Incrementar salarios</button>
        </form>
        <hr/>
        <table border="1">
            <thead>
                <tr>
                    <th>Apellido</th>
                    <th>Oficio</th>
                    <th>Salario</th>
                    <th>Departamento</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${controller.empleados}" var="emp">
                    <tr>
                        <td>${emp.apellido}</td>
                        <td>${emp.oficio}</td>
                        <td style="color:red">${emp.salario}</td>
                        <td>${emp.idDepartamento}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
