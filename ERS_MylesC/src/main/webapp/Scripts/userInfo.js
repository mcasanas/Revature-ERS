//console.log("starting");
sendAjaxGet("http://localhost:8080/ERS_MylesC/userInfo",displayMessage);
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
	let user = JSON.parse(xhr.responseText);
	if(user !== null) {
		//document.getElementById("myUser").innerText = "ID: "+user.user_id + "User: "+user.username +"  Role: "+user.user_role;
		document.getElementById("myUser").innerHTML = `ID: ${user.user_id}   User:  ${user.username}  <span id="role">Role:  ${user.user_role}</span>`;
	}
	localStorage.setItem("role", user.user_role);
	
	if(document.getElementById("addButton")){
	if(localStorage.role == "Manager"){
		document.getElementById("addButton").style.display = "none";
	} else {
		document.getElementById("addButton").style.display = "";
	}
	}
}

