<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Employee List</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
</head>
<body>

<div class="contianer">
  <div class="signupSection">
    <form action="addEmployee" method="POST" class="signupForm" name="signupform">
      <h2>Ajouter un nouvel employé</h2>

      <!-- Display error message -->
      <c:if test="${not empty error}">
        <h5 style="color: red">${error}.</h5>
      </c:if>

      <ul class="noBullet">
        <li>
          <label for="username"></label>
          <input
                  type="text"
                  class="inputFields"
                  id="username"
                  name="name"
                  placeholder="Username"
                  value="${name != null ? name : ''}" />
        </li>

        <li>
          <label for="email"></label>
          <input
                  type="email"
                  class="inputFields"
                  id="email"
                  name="email"
                  placeholder="Email"
                  value="${email != null ? email : ''}" />
        </li>

        <li>
          <label for="phone"></label>
          <input
                  type="tel"
                  class="inputFields"
                  id="phone"
                  name="phone"
                  placeholder="Numéro de téléphone"
                  value="${phone != null ? phone : ''}" />
        </li>

        <li>
          <label for="department"></label>
          <select id="department" name="department">
            <option value="">Sélectionnez un Département</option>
            <option value="Ressources Humaines"
                    <c:if test="${department == 'Ressources Humaines'}">selected</c:if>>Ressources Humaines</option>
            <option value="Marketing"
                    <c:if test="${department == 'Marketing'}">selected</c:if>>Marketing</option>
            <option value="Ventes"
                    <c:if test="${department == 'Ventes'}">selected</c:if>>Ventes</option>
          </select>
        </li>

        <li>
          <label for="position"></label>
          <select id="position" name="position">
            <option value="">Sélectionnez un poste</option>
            <option value="Developer"
                    <c:if test="${position == 'Developer'}">selected</c:if>>Developer</option>
            <option value="Manager"
                    <c:if test="${position == 'Manager'}">selected</c:if>>Manager</option>
            <option value="HR Specialist"
                    <c:if test="${position == 'HR Specialist'}">selected</c:if>>HR Specialist</option>
            <option value="Marketing Specialist"
                    <c:if test="${position == 'Marketing Specialist'}">selected</c:if>>Marketing Specialist</option>
            <option value="Sales Representative"
                    <c:if test="${position == 'Sales Representative'}">selected</c:if>>Sales Representative</option>
          </select>
        </li>

        <li id="center-btn">
          <button type="submit">
            <div class="svg-wrapper-1">
              <div class="svg-wrapper">
                <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24">
                  <path fill="none" d="M0 0h24v24H0z"></path>
                  <path
                          fill="currentColor"
                          d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z">
                  </path>
                </svg>
              </div>
            </div>
            <span>Ajouter</span>
          </button>
        </li>
      </ul>

      <!-- Include alert messages -->
      <%@ include file="../alert/alert.jsp" %>
    </form>
  </div>


    <jsp:include page="ListEmployee.jsp" />

  </body>

<script src="assets/js/js.js"></script>

  <script
          src="https://kit.fontawesome.com/e9ea9ee727.js"
          crossorigin="anonymous"
  ></script>
</html>
