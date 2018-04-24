
console.log("starting");
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
		console.log(user.username);
		document.getElementById("myUser").innerText = "ID: "+user.user_id + "User: "+user.username +"  Role: "+user.user_role;
//		document.getElementById("myUser").innerHtml = "<span id = \"authorId\">ID: "+user.user_id +
//			"</span>  User: "+user.username +"  Role: "+user.user_role;
//		let temp = document.createElement('span').setAttribute("id","authorId");
//		let text = document.createTextNode("ID: "+user.user_id+"\n");
//		temp.appendChild(temp);
//		temp = document.createElement('span').setAttribute("id","username");
//		let text = document.createTextNode("User: "+user.username +"\n");
//		temp.appendChild(temp);
//		document.getElementById("myUser").appendChild(temp);
//		temp = document.createElement('span').setAttribute("id","role");
//		let text = document.createTextNode("Role: "+user.user_role);
//		temp.appendChild(temp);
//		document.getElementById("myUser").appendChild(temp);
	} 
}