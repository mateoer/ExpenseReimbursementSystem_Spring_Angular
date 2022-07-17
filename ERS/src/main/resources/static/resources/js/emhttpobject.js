window.onload = function(){ 
    // ajaxGetCurrentUserInformation();
    getAllMyReimbursements();
    // document.getElementById('newReiReq').addEventListener("click", newReimbursementtRequest);
    // document.getElementById('Refresh').addEventListener("click", refreshReimbursements);
    // document.getElementById('filterR').addEventListener("click", filterReimbursemenetRequest);
}



function newReimbursementtRequest(){
    let xhttp = new XMLHttpRequest;

    xhttp.open('POST', `http://localhost:9005/ExpenseReimbursementSystem/json/newreimbursement`);
    xhttp.setRequestHeader("content-type", "application/json");

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState == 4 && xhttp.status== 200) {            
            
            let reiObj = xhttp.responseText;
            console.log(reiObj);
            
        }
    }

    

    let reqDesc = document.getElementById("reiDescription").value;
    let reqAmt = document.getElementById("reiAmount").value;

    let selectType = document.getElementById("selectType");

    
   
    selectType.addEventListener('change', function selectedTypes(){
        if (this.value == "1") {
            console.log(this.value);  
            return 1;          
        } 
        if (this.value == "2") {
            console.log(this.value);
            return 2;
        } 
        if (this.value == "3") {
            console.log(this.value);
            return 3;
        } 
        if (this.value == "4") {
            console.log(this.value);
            return 4;
        }
    });

    let reqType = parseInt(selectType.value);    
    

    // let newReiReq;
    if (reqType && reqDesc  && reqAmt ) {

        console.log("reqType: "+reqType);
        console.log("reqDesc: "+reqDesc);
        console.log("reqAmt: "+reqAmt);
        

        let newReiReq = {
            "reiTypeId" : reqType,
            "reiDescription" : reqDesc,
            "reiAmount" : reqAmt
    
        }
        xhttp.send(JSON.stringify(newReiReq));

    } else {

        console.log("reqType: "+reqType);
        console.log("reqDesc: "+reqDesc);
        console.log("reqAmt: "+reqAmt);
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






function getAllMyReimbursements() {
    let xhttp = new XMLHttpRequest;

    xhttp.onreadystatechange = function(){
        if (xhttp.readyState==4 && xhttp.status==200) {
            let allReis = JSON.parse(xhttp.responseText);
            console.log(allReis);
            ourDOMManipulationTWO(allReis);
            // ourDOMManipulationThree(allReis,options);
            // displayMyReisList(allReis);
        }
    }



    xhttp.open('GET', `http://localhost:9050/viewreimbursements?`);

    xhttp.send();
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

    xhttp.open('GET', `http://localhost:9005/ExpenseReimbursementSystem/json/emreimbursementlist?`);

    xhttp.send();
}    



function ourDOMManipulationThree(ourResponseObject){
    
    let tableBody = document.getElementById("empTableBody");
    tableBody.innerHTML = "";


    for(let i= 0; i< ourResponseObject.length; i++){
        ////////////CREATE ELEMENTS DYNAMICALLY////////////////

        //step 1: creaitng our new elements
        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");

        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");

        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");
        let newTD7 = document.createElement("td");
        let newTD8 = document.createElement("td");
        let newTD9 = document.createElement("td");
        let newTD10 = document.createElement("td");
        // let newTD11= document.createElement("td");
        // let newTD12= document.createElement("td");


        //step 2: populate our creations
        newTH.setAttribute("scope", "row");
        let myTextH = document.createTextNode(ourResponseObject[i].reiId);
        let myTextD1 = document.createTextNode(ourResponseObject[i].reiAmount);
        let myTextD2 = document.createTextNode(ourResponseObject[i].reiSubmitted); //
        
        let myTextD3 = document.createTextNode(ourResponseObject[i].reiResolved); ///

        let myTextD4 = document.createTextNode(ourResponseObject[i].reiDescription);
        let myTextD5 = document.createTextNode(ourResponseObject[i].reiAuthor);
        let myTextD6 = document.createTextNode(ourResponseObject[i].reiResolver);
        let myTextD7 = document.createTextNode(ourResponseObject[i].reiStatusId);
        let myTextD8 = document.createTextNode(ourResponseObject[i].reiTypeId);
        let myTextD9 = document.createTextNode(ourResponseObject[i].reiStatus);
        let myTextD10 = document.createTextNode(ourResponseObject[i].reiType);
        // let myTextD11 = document.createTextNode(ourResponseObject[i].resolverFName);
        // let myTextD12 = document.createTextNode(ourResponseObject[i].resolverLName);

        //all appending
        newTH.appendChild(myTextH);
        newTD1.appendChild(myTextD1);
        newTD2.appendChild(myTextD2);
        newTD3.appendChild(myTextD3);

        newTD4.appendChild(myTextD4);
        newTD5.appendChild(myTextD5);
        newTD6.appendChild(myTextD6);
        newTD7.appendChild(myTextD7);
        newTD8.appendChild(myTextD8);
        newTD9.appendChild(myTextD9);
        newTD10.appendChild(myTextD10);
        // newTD11.appendChild(myTextD11);
        // newTD12.appendChild(myTextD12);



        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);

        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD6);
        newTR.appendChild(newTD7);
        newTR.appendChild(newTD8);
        newTR.appendChild(newTD9);
        newTR.appendChild(newTD10);
        // newTR.appendChild(newTD11);
        // newTR.appendChild(newTD12);

        let newSelection = document.querySelector("#empTableBody");
        newSelection.appendChild(newTR);
    }
}

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


