
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // Add event listeners to all "More Info" buttons
    var moreInfoButtons = document.getElementsByClassName("more-info-btn");
    for (let button of moreInfoButtons) {
        button.onclick = function() {
            // Get employee data from data attributes
            var name = this.getAttribute("data-name");
            var email = this.getAttribute("data-email");
            var phone = this.getAttribute("data-phone"); // Get phone number
            var department = this.getAttribute("data-department");
            var position = this.getAttribute("data-position");

            // Update modal content
            document.getElementById("modal-name").innerText = name;
            document.getElementById("modal-email").innerText = email;
            document.getElementById("modal-department").innerText = department;
            document.getElementById("modal-position").innerText = position;
            document.getElementById("modal-phone-display").innerText = phone; // Set phone number in button

            // Show the modal
            modal.style.display = "block";
        };
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
            modal.style.display = "none";
        };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
            if (event.target == modal) {
            modal.style.display = "none";
        }
        };



