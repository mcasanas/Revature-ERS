//console.log("ticketInfo");

	let windowString = window.location.href;
	let windowUrl = new URL(windowString);
	let n = windowUrl.searchParams.get("ticketId");
	sendAjaxGet("http://localhost:8080/ERS_MylesC/ticketInfo", displayMessage);
	let admin = 0;
	let status = "Pending";


//document.getElementById('login_btn').addEventListener('click', sendAjaxGet("http://localhost:8080/ERS_MylesC/session",displayMessage));

function sendAjaxGet(url, func){
	//event.stopPropagation();
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			func(this);
		}
	}
	xhr.open("GET",url);
	xhr.send();
}
function displayMessage(xhr){
	let myT = JSON.parse(xhr.responseText);
	document.getElementById("ticketId").innerText = "Ticket ID:  " +myT.reim_id;
	document.getElementById("descript").innerText = "Description:  "+myT.reim_descript;
	document.getElementById("amount").innerText = "Amount:  "+myT.reim_amount;
	document.getElementById("author").innerText = "Author:   "+myT.reim_author_name;
	document.getElementById("type").innerText = "Type:  "+myT.reim_type;
	document.getElementById("status").innerText = "Status:  "+myT.reim_status;
	
	status = myT.reim_status;
	
//	if ((myT.reim_status !== "Pending") | (document.getElementById("role").innerText === "Manager")){
//		console.log(document.getElementById("role").innerText);
//		document.getElementById("statusA").style.display = "none";
//		document.getElementById("statusB").style.display = "none";
//		document.getElementById("statusC").style.display = "none";
//	} else {
//		console.log(document.getElementById("role").innerText);
//		document.getElementById("statusA").style.display = "";
//		document.getElementById("statusB").style.display = "";
//		document.getElementById("statusC").style.display = "";
//	}
	displayStatusTool();
}

function displayStatusTool(){
	//console.log(document.getElementById("role").innerText);
	//console.log(localStorage.role);
	//console.log(status);
	if((localStorage.role == "Manager") & (status=="Pending")){
		document.getElementById("statusA").style.display = "";
		document.getElementById("statusB").style.display = "";
		document.getElementById("statusC").style.display = "";
	} else if ((localStorage.role == "Manager") & (status!="Pending")){ // reduntant lines
		document.getElementById("statusA").style.display = "none";
		document.getElementById("statusB").style.display = "none";
		document.getElementById("statusC").style.display = "none";
	} else if((localStorage.role != "Manager") & (status=="Pending")){
		document.getElementById("statusA").style.display = "none";
		document.getElementById("statusB").style.display = "none";
		document.getElementById("statusC").style.display = "none";
	} else {
		document.getElementById("statusA").style.display = "none";
		document.getElementById("statusB").style.display = "none";
		document.getElementById("statusC").style.display = "none";
	}
}
