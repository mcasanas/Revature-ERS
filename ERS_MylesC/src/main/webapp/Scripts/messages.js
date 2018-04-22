

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			//console.log(xhr.responseText);
			func(this);
		}
	}
	xhr.open("GET",url);
	xhr.send();
}

document.getElementById("login_btn").addEventListener("click", sendAjaxGet("http://localhost:8080/ERS_MylesC/session",displayMessage));

function displayMessage(xhr){
	
	let message = JSON.parse(xhr.responseText);
	if(message.message !== null) {
		document.getElementById("message").innerText = message.message;
	} 
}