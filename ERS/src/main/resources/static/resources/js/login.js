window.onload = function () {
  document.getElementById("login").addEventListener("click", userLogin);
};

function userLogin() {
  let xhttp = new XMLHttpRequest();

  xhttp.open("POST", `http://localhost:9050/login`);
  xhttp.setRequestHeader("content-type", "application/json");

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {

      console.log(xhttp.responseText);
      console.log("I'm in!");
      var urlBase = "http://localhost:9050";
      window.location = urlBase + xhttp.responseText;
    }
  };

  var userName = document.querySelector("#username").value;
  var userPassword = document.querySelector("#password").value;

  let userValidation = {
    "username": userName,
    "password": userPassword,
  };

  console.log("Sending: "+userValidation.username+" "+userValidation.password);
  xhttp.send(JSON.stringify(userValidation));
}
