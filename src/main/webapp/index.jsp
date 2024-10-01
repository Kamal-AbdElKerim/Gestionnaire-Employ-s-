<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Employee List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="assets/css/main.scss" />

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
                <option value="HR" <%= "HR".equals(request.getAttribute("department")) ? "selected" : "" %>>Ressources Humaines</option>
                <option value="Marketing" <%= "Marketing".equals(request.getAttribute("department")) ? "selected" : "" %>>Marketing</option>
                <option value="Sales" <%= "Sales".equals(request.getAttribute("department")) ? "selected" : "" %>>Ventes</option>
              </select>
              <label for="position"></label>
              <select id="position" name="position">
                <option value="">Sélectionnez un poste</option>
                <option value="Developer" <%= "Developer".equals(request.getAttribute("position")) ? "selected" : "" %>>Développeur</option>
                <option value="Manager" <%= "Manager".equals(request.getAttribute("position")) ? "selected" : "" %>>Manager</option>
                <option value="HR Specialist" <%= "HR Specialist".equals(request.getAttribute("position")) ? "selected" : "" %>>Spécialiste RH</option>
                <option value="Marketing Specialist" <%= "Marketing Specialist".equals(request.getAttribute("position")) ? "selected" : "" %>>Spécialiste Marketing</option>
                <option value="Sales Representative" <%= "Sales Representative".equals(request.getAttribute("position")) ? "selected" : "" %>>Représentant Commercial</option>
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
      <div class="list">
        <!-- one user  -->
        <div class="OnePost">
          <div class="img">
            <img src="assets/image/images.png" alt="" />
          </div>
          <div class="profile">
            <h3>islam</h3>
            <p>islam@gmail.com</p>
          </div>
          <div class="moreinfo">
            <!-- HTML !-->
            <button class="button-33" role="button" type="button" id="myBtn">
              <i class="fa-regular fa-circle-question"></i>
            </button>
            <button
              class="button-33 bg_denger"
              role="button"
              type="button"
              id="myBtn"
            >
              <i class="fa-solid fa-trash-can"></i>
            </button>
          </div>
        </div>
        <!-- one user  -->
        <!-- one user  -->
        <div class="OnePost">
          <div class="img">
            <img src="assets/image/images.png" alt="" />
          </div>
          <div class="profile">
            <h3>islam</h3>
            <p>islam@gmail.com</p>
          </div>
          <div class="moreinfo">
            <!-- HTML !-->
            <button class="button-33" role="button" type="button" id="myBtn">
              <i class="fa-regular fa-circle-question"></i>
            </button>
            <button
              class="button-33 bg_denger"
              role="button"
              type="button"
              id="myBtn"
            >
              <i class="fa-solid fa-trash-can"></i>
            </button>
          </div>
        </div>
        <!-- one user  -->
      </div>

      <!-- The Modal -->
      <div id="myModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="cookie-card">
            <span class="title">🍪 islam</span>
            <span class="close">&times;</span>
            <p class="description">Email : islam@gmail.com</p>
            <p class="description">Département : Département 1</p>
            <p class="description">Poste : Poste 1</p>
            <div class="actions">
              <button disabled class="accept">
                0669474622
                <span> <i class="fa-solid fa-phone-volume"></i></span>
              </button>
              <button class="accept bg-green">
                <i class="fa-regular fa-pen-to-square"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {
      modal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
      modal.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    };
  </script>
  <script
    src="https://kit.fontawesome.com/e9ea9ee727.js"
    crossorigin="anonymous"
  ></script>




</html>
