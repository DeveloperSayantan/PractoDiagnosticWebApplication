document.getElementById("showFormLink").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default link behavior
    var form = document.getElementById("bookingForm");
    if (form.style.display === "none") {
        form.style.display = "block"; // Show the form
    } else {
        form.style.display = "none"; // Hide the form
    }
});

document.getElementById("showFeedbackLink").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default link behavior
    var form = document.getElementById("feedbackForm");
    if (form.style.display === "none") {
        form.style.display = "block"; // Show the form
    } else {
        form.style.display = "none"; // Hide the form
    }
});

document.getElementById("showOrderDetails").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default link behavior
    var form = document.getElementById("viewOrder");
    //window.location.href = "dashboard.html?session.userId";
    if (form.style.display === "none") {
        form.style.display = "block"; // Show the form
    } else {
        form.style.display = "none"; // Hide the form
    }
});