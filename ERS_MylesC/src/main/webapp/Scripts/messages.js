
//console.log("starting");
sendAjaxGet("http://localhost:8080/ERS_MylesC/session",displayMessage);
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
	let message = JSON.parse(xhr.responseText);
	if(message.message == null | message.message == undefined | message.message == "") {
		document.getElementById("message").innerText = "";
	} else {
		document.getElementById("message").innerText = message.message;
		console.log(message.message);
	}
}