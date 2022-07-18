window.onload = function(){ 
    // ajaxGetCurrentUserInformation();
    getAllReimbursements();
    // document.getElementById('approveRei').addEventListener("click", approveReimbursemenetRequest);
    // document.getElementById('denyRei').addEventListener("click", denyReimbursemenetRequest);
    // document.getElementById('filterR').addEventListener("click", filterReimbursemenetRequest);
    // document.getElementById('Refresh').addEventListener("click", refreshReimbursements);

}

function approveReimbursemenetRequest() {
    
    let xhttp = new XMLHttpRequest;

    xhttp.open('POST', `http://localhost:9005/ExpenseReimbursementSystem/json/approvereimbursement`);
    xhttp.setRequestHeader("content-type", "application/json");

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState == 4 && xhttp.status== 200) {            
            
            let reiObj = xhttp.responseText;
            console.log(reiObj);
            
        }
    }   

    let reiId = document.getElementById("reiAppId").value;

    if (reiId) {

        let approvedRei = {
            "reiId" : reiId
        }
    
        xhttp.send(JSON.stringify(approvedRei));       

    } else {        
        console.log("Missing parameters. Cannot send an empty request");
    }
}


function denyReimbursemenetRequest() {
    
    let xhttp = new XMLHttpRequest;

    xhttp.open('POST', `http://localhost:9005/ExpenseReimbursementSystem/json/denyreimbursement`);
    xhttp.setRequestHeader("content-type", "application/json");

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState == 4 && xhttp.status== 200) {            
            
            let reiObj = xhttp.responseText;
            console.log(reiObj);
            
        }
    }   

    let reiId = document.getElementById("reiDenId").value;

    if (reiId) {

        let deniedRei = {
            "reiId" : reiId
        }
    
        xhttp.send(JSON.stringify(deniedRei));       

    } else {        
        console.log("Missing parameters. Cannot send an empty request");
    }
}


function filterReimbursemenetRequest() {
    
    let getFilterId = document.getElementById("filterRei");
    let filter = parseInt(getFilterId.value);

    if (filter === 1 || filter === 2 || filter === 3) {
        filterFunction(filter);
    }else if (filter === 0) {
        let noFilter = "";
        filterFunction(noFilter);
    }
   
}

function filterFunction(filter) {
    let table, tr, td, i, txtValue;
    // filter = "Pending";
    table = document.getElementById("empTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[6];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }       
    }
}


function refreshReimbursements() {
    let xhttp = new XMLHttpRequest;

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState==4 && xhttp.status==200) {
            let allReis = JSON.parse(xhttp.responseText);
            console.log(allReis);
            // ourDOMManipulationTWO(allReis);
            ourDOMManipulationThree(allReis);
            // displayMyReisList(allReis);
        }
    }

    xhttp.open('GET', `http://localhost:9005/ExpenseReimbursementSystem/json/reimbursementlist?`);

    xhttp.send();
}    



/* function ourDOMManipulationThree(ourResponseObject){
    
    let tableBody = document.getElementById("empTableBody");
    tableBody.innerHTML = "";


    for(let i= 0; i< ourResponseObject.length; i++){
        ////////////CREATE ELEMENTS DYNAMICALLY////////////////

        //step 1: creaitng our new elements
        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");

        
        // let newTD0 = document.createElement("td");
        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");

        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");
        // let newTD7 = document.createElement("td");
        // let newTD8 = document.createElement("td");
        let newTD9 = document.createElement("td");
        let newTD10 = document.createElement("td");
        let newTD11= document.createElement("td");
        let newTD12= document.createElement("td");


        //step 2: populate our creations
        newTH.setAttribute("scope", "row");

        // let myTextD0 = document.createTextNode(i);
        let myTextH = document.createTextNode(ourResponseObject[i].reiId);
        let myTextD1 = document.createTextNode(ourResponseObject[i].reiAmount);
        let myTextD2 = document.createTextNode(ourResponseObject[i].reiSubmitted); //
        
        let myTextD3 = document.createTextNode(ourResponseObject[i].reiResolved); ///

        let myTextD4 = document.createTextNode(ourResponseObject[i].reiDescription);
        let myTextD5 = document.createTextNode(ourResponseObject[i].reiAuthor);
        let myTextD6 = document.createTextNode(ourResponseObject[i].reiResolver);
        // let myTextD7 = document.createTextNode(ourResponseObject[i].reiStatusId);
        // let myTextD8 = document.createTextNode(ourResponseObject[i].reiTypeId);
        let myTextD9 = document.createTextNode(ourResponseObject[i].reiStatus);
        let myTextD10 = document.createTextNode(ourResponseObject[i].reiType);
        let myTextD11 = document.createTextNode(ourResponseObject[i].resolverFName);
        let myTextD12 = document.createTextNode(ourResponseObject[i].resolverLName);

        //all appending
        // newTD0.appendChild(myTextD0);
        newTH.appendChild(myTextH);
        newTD1.appendChild(myTextD1);
        newTD2.appendChild(myTextD2);
        newTD3.appendChild(myTextD3);

        newTD4.appendChild(myTextD4);
        newTD5.appendChild(myTextD5);
        newTD6.appendChild(myTextD6);
        // newTD7.appendChild(myTextD7);
        // newTD8.appendChild(myTextD8);
        newTD9.appendChild(myTextD9);
        newTD10.appendChild(myTextD10);
        newTD11.appendChild(myTextD11);
        newTD12.appendChild(myTextD12);



        // newTR.appendChild(newTD0);
        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);

        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD6);
        // newTR.appendChild(newTD7);
        // newTR.appendChild(newTD8);
        newTR.appendChild(newTD9);
        newTR.appendChild(newTD10);
        newTR.appendChild(newTD11);
        newTR.appendChild(newTD12);

        let newSelection = document.querySelector("#empTableBody");
        newSelection.appendChild(newTR);
    }
} */


function ourDOMManipulationTWO(ourResponseObject){
    //we are about to dome some HEAVY DOM Manipulation

    /*
        you COULD check to see if they are logged in as an employee or manager then
        dynamically add new buttons and/or html elements
    */

    for(let i= 0; i< ourResponseObject.length; i++){
        ////////////CREATE ELEMENTS DYNAMICALLY////////////////

        //step 1: creaitng our new elements

        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");
        
        // let newTD0 = document.createElement("td");
        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");

        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");
        // let newTD7 = document.createElement("td");
        // let newTD8 = document.createElement("td");
        let newTD9 = document.createElement("td");
        let newTD10 = document.createElement("td");
        // let newTD11= document.createElement("td");
        // let newTD12= document.createElement("td");

        //step 2: populate our creations
        newTH.setAttribute("scope", "row");
        // let myTextD0 = document.createTextNode(i);
        let myTextH = document.createTextNode(ourResponseObject[i].reiId);
        let myTextD1 = document.createTextNode(ourResponseObject[i].rei_amount);
        let myTextD2 = document.createTextNode(ourResponseObject[i].rei_submittedDate); //
        
        let myTextD3 = document.createTextNode(ourResponseObject[i].rei_resolvedDate); ///

        let myTextD4 = document.createTextNode(ourResponseObject[i].rei_description);
        let myTextD5 = document.createTextNode(ourResponseObject[i].rei_author);
        let myTextD6 = document.createTextNode(ourResponseObject[i].rei_resolver);
        // let myTextD7 = document.createTextNode(ourResponseObject[i].reiStatusId);
        // let myTextD8 = document.createTextNode(ourResponseObject[i].reiTypeId);
        let myTextD9 = document.createTextNode(ourResponseObject[i].reiStatus);
        let myTextD10 = document.createTextNode(ourResponseObject[i].reiType);
        // let myTextD11 = document.createTextNode(ourResponseObject[i].resolverFName);
        // let myTextD12 = document.createTextNode(ourResponseObject[i].resolverLName);

        //all appending
        // newTD0.appendChild(myTextD0);
        newTH.appendChild(myTextH);
        newTD1.appendChild(myTextD1);
        newTD2.appendChild(myTextD2);
        newTD3.appendChild(myTextD3);

        newTD4.appendChild(myTextD4);
        newTD5.appendChild(myTextD5);
        newTD6.appendChild(myTextD6);
        // newTD7.appendChild(myTextD7);
        // newTD8.appendChild(myTextD8);
        newTD9.appendChild(myTextD9);
        newTD10.appendChild(myTextD10);
        // newTD11.appendChild(myTextD11);
        // newTD12.appendChild(myTextD12);


        // newTR.appendChild(newTD0);
        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);

        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD6);
        // newTR.appendChild(newTD7);
        // newTR.appendChild(newTD8);
        newTR.appendChild(newTD9);
        newTR.appendChild(newTD10);
        // newTR.appendChild(newTD11);
        // newTR.appendChild(newTD12);

        let newSelection = document.querySelector("#empTableBody");
        newSelection.appendChild(newTR);
    }
}












function getAllReimbursements() {
    let xhttp = new XMLHttpRequest;

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState==4 && xhttp.status==200) {
            let allReis = JSON.parse(xhttp.responseText);
            console.log(allReis);
            ourDOMManipulationTWO(allReis);
            //displayReisList(allReis);
        }
    }

    xhttp.open('GET', `http://localhost:9050/viewreimbursements?`);

    xhttp.send();
}    

// function displayReisList(reis) {

//     let ol = document.getElementById("demo");
//     for(let i = 0; i < reis.length; i++) {
//         let li = document.createElement("li");
//         let text = document.createTextNode(reis[i].reiId + " " +reis[i].reiDescription);
//         li.appendChild(text);
//         ol.appendChild(li);
//     }

// }

// function displayResult(string, location) {
//     document.getElementById(location).innerText = string;
// }









/////////////GET USER INFO AND DISPLAY

function ajaxGetCurrentUserInformation(){
	let xhttp = new XMLHttpRequest();

   xhttp.onreadystatechange = function (){

        if(xhttp.readyState==4 && xhttp.status==200){
            let currentUser = JSON.parse(xhttp.responseText);

            ourDOMManipulationONE(currentUser);
        }
   }

    xhttp.open('GET', `http://localhost:9005/ExpenseReimbursementSystem/json/getCurrentUserObject`);

   xhttp.send();
}

function ourDOMManipulationONE(ourResponseObject){
	console.log(ourResponseObject);

	document.getElementById("welcomeTextName").innerText = "Welcome, " + ourResponseObject.firstName +" "+ ourResponseObject.lastName; 	
	
}