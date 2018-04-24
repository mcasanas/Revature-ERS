
console.log("starting");
sendAjaxGet("http://localhost:8080/ERS_MylesC/table",displayMessage);
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
	let myList = JSON.parse(xhr.responseText);
	
	for(i in myList){
		let id = myList[i].reim_id;
		let amt = myList[i].reim_amount;
		let st = myList[i].reim_submitted.split(".")[0];
		let rs = myList[i].rem_resolved && myList[i].reim_resolved.split(".")[0];
		let tType = myList[i].reim_type;
		let tStatus = myList[i].reim_status;
		nextRow = document.createElement("tr");
		nextRow.innerHTML = `<td> ${id} </td><td> ${amt} 
		</td> <td> ${st} </td> <td> ${rs} </td> <td> ${tType} </td>
		 <td> ${tStatus} </td> <td id="${myList[i].reim_id}"> Info </td>`;
		//console.log(nextRow);
		document.getElementById("ticketTable").appendChild(nextRow);
	}
}