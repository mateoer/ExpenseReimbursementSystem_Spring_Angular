window.onload = function () {
  document.getElementById("login").addEventListener("click", userLogin);
};

function userLogin() {
  let xhttp = new XMLHttpRequest();

  xhttp.open("POST", `http://localhost:9050/login`);
  xhttp.setRequestHeader("content-type", "application/json");

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      // let regObj = xhttp.responseText;
      // console.log(regObj);

      ///use this if the response text is the URI for user home page
      console.log(xhttp.responseText);
      console.log("I'm in!");
      var urlBase = "http://localhost:9050";
      window.location = urlBase + xhttp.responseText;

      ///this if response text only says valid or invalid
      //maybe go with the above line instead of this one
      /////////this part should redirect to user home page after successfull login
      // window.location.replace("../html/logout.html");
    }
  };

  var userName = document.querySelector("#username").value;
  var userPassword = document.querySelector("#password").value;

  let userValidation = {
    "username": userName,
    "password": userPassword,
  };

  console.log("Sending: "+userValidation.username+" "+userValidation.password);
  // console.log("Inside the json block");
  xhttp.send(JSON.stringify(userValidation));
//   xhttp.send(userValidation);
}
