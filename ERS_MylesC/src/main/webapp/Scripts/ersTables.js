window.onload = function(){
	sendAjaxGet("http://localhost:8080/ERS_MylesC/table",displayMessage);
}

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
		let rs = myList[i].reim_resolved && myList[i].reim_resolved.split(".")[0];
		let tType = myList[i].reim_type;
		let tStatus = myList[i].reim_status;
		
		let trTag = (tStatus==="Approved")?"<td style= \"background-color:green\">":((tStatus==="Denied")?"<td style= \"background-color:red\">":"<td>");
		
		nextRow = document.createElement("tr");
		nextRow.setAttribute("style","border: 1px solid black;");
		nextRow.innerHTML = `<td id="${myList[i].reim_id}" style="background-color:grey; border:1px solid yellow" onclick="getInfo(${myList[i].reim_id})"> Info </td>
		<td> ${id} </td><td> ${amt} 
		</td> <td> ${st} </td> <td> ${rs} </td> <td> ${tType} </td>
		 ${trTag} ${tStatus} </td>`;
		document.getElementById("ticketTable").appendChild(nextRow);
	}
}

function getInfo(n){
	//console.log(n);
	document.getElementById("selectId").innerText = n;
	//localStorage.setItem("ticketId", n);
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			window.location = '/ERS_MylesC/Views/ERS_Info.html?ticketId='+n;
		}
	}
	xhr.open("POST","http://localhost:8080/ERS_MylesC/table");
	xhr.send(n);
	
}
