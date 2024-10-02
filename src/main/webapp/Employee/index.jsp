<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %>

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
          <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
          %>
          <h5 style="color: red"> <%= error %>.</h5>
          <%
            }
          %>
          <ul class="noBullet">
            <li>
              <label for="username"></label>
              <input
                type="text"
                class="inputFields"
                id="username"
                name="name"
                placeholder="Username"
                value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>"              />
            </li>


            <li>
              <label for="email"></label>
              <input
                type="email"
                class="inputFields"
                id="email"
                name="email"
                placeholder="Email"
                value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"              />

            </li>
            <li>
              <label for="phone"></label>
              <input
                type="tel"
                class="inputFields"
                id="phone"
                name="phone"
                placeholder="Numéro de téléphone"
                value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>"              />


            </li>
            <li>
              <label for="department"></label>
              <select id="department" name="department">
                <option value="">Sélectionnez un Département</option>
                <option value="Ressources Humaines" <%= "Ressources Humaines".equals(request.getAttribute("department")) ? "selected" : "" %>>Ressources Humaines</option>
                <option value="Marketing" <%= "Marketing".equals(request.getAttribute("department")) ? "selected" : "" %>>Marketing</option>
                <option value="Ventes" <%= "Ventes".equals(request.getAttribute("department")) ? "selected" : "" %>>Ventes</option>
              </select>
              <label for="position"></label>
              <select id="position" name="position">
                <option value="">Sélectionnez un poste</option>
                <option value="Developer" <%= "Developer".equals(request.getAttribute("position")) ? "selected" : "" %>>Developer</option>
                <option value="Manager" <%= "Manager".equals(request.getAttribute("position")) ? "selected" : "" %>>Manager</option>
                <option value="HR Specialist" <%= "HR Specialist".equals(request.getAttribute("position")) ? "selected" : "" %>>HR Specialist</option>
                <option value="Marketing Specialist" <%= "Marketing Specialist".equals(request.getAttribute("position")) ? "selected" : "" %>>Marketing Specialist</option>
                <option value="Sales Representative" <%= "Sales Representative".equals(request.getAttribute("position")) ? "selected" : "" %>>Sales Representative</option>
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
                      height="24"
                    >
                      <path fill="none" d="M0 0h24v24H0z"></path>
                      <path
                        fill="currentColor"
                        d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"
                      ></path>
                    </svg>
                  </div>
                </div>
                <span>Ajouter</span>
              </button>
            </li>
          </ul>
          <%
            String message = (String) request.getAttribute("AddEmployee");
            if (message != null) {
          %>
          <div class="alert_c">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
             <%= message %>.
          </div>
          <%
            }
          %>


        </form>

      </div>
    <%
      List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    %>
    <h1 class="num_empl"><%= employees != null ? employees.size() : "" %></h1>

    <jsp:include page="ListEmployee.jsp" />

  </body>

<script src="assets/js/js.js"></script>

  <script
          src="https://kit.fontawesome.com/e9ea9ee727.js"
          crossorigin="anonymous"
  ></script>
</html>
