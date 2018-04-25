window.onload = function(){
	sendAjaxGet("http://localhost:8080/ERS_MylesC/table",displayMessage);
}

document.getElementById('ers_role_select').addEventListener("change" , statusFilter);


function statusFilter(){
	let table = document.getElementById("ticketTable");
	let filter = document.getElementById('ers_role_select').value;
	let rows = table.getElementsByTagName("tr");
	let data;
	//console.log(rows.length);
	for(var i= 1; i<rows.length; i++){
		data = rows[i].getElementsByTagName("td")[6];
		if(filter == 1){
			rows[i].style.display = "";
			continue;
		}
		if(data){
			rows[i].style.display = "";
			if(data.innerText == filter){
				rows[i].style.display = "";
			} else {
				rows[i].style.display = "none";
			}	
		} else {
			rows[i].style.display = "";
		}
	}	
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
		</td> <td>${st}</td> <td>${rs}</td> <td>${tType}</td>
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
