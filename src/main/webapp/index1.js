/**
 * 
 */
var mobileNumberOfAdmin;
var pinOfAdmin;

function adminOptions(){
	document.getElementById("logOut").style.display = "block";
	document.getElementById("userBlock").style.display = "none";
	document.getElementById("userBlockForSignUp").style.display = 'none';
	document.getElementById("forgotPinBlock").style.display = "none";
	document.getElementById("adminSettings").style.display = "flex";
	document.getElementById("adminSettings").style.display = "block";
	document.getElementById("adminSettings").style = "display : flex; position : absolute; left : 85%; top : 10%; width : 10%; height : 15%; flex-wrap : wrap; border : 1px solid black;";
    
}

function user(){
	if(document.getElementById("userBlock").classList.contains("optionsOfEveryOne")){
	document.getElementById("userBlock").style.display = "block";	
	document.getElementById("userBlock").style.display = "flex";	
	document.getElementById("userBlockForSignUp").style.display = 'none';
	document.getElementById("forgotPinBlock").style.display = "none";
	}
	if(document.getElementById("userBlock").classList.contains("optionsOfAdmin")){
		setTimeout(admin, 100);
	}

}
function admin(){
	document.getElementById("userBlock").classList.add("optionsOfAdmin");
	document.getElementById("userBlock").style.display = "none";
	document.getElementById("userBlockForSignUp").style.display = 'none';
	
	setTimeout(adminOptions, 500);
}

  
function viewAdminProfile(){
	
	document.getElementById("adminSettings").style.display = "none";
	document.getElementById("adminProfile").style.display = "block";
	document.getElementById("adminProfile").style.display = "flex";
	document.getElementById("adminProfile").innerHTML += "<p style = 'font-size : 1.5rem' >Admin MobileNumber : </p>" + "<input type = 'text' style = 'width : 80%; height : 12%; font-size : 1.5rem' id = 'adminMobileNumberInProfile' value = '"+ mobileNumberOfAdmin + "'/>" + "<p style = 'font-size : 1.5rem'>Admin PinNumber : </p>" + "<input type = 'text' style = 'width : 80%; height : 12%; font-size : 1.5rem' id = 'adminPinNumberInProfile' value = '" + pinOfAdmin + "'/>" + "<div style = 'width : 100%; height : 10%; display : flex; justify-content : space-evenly;'><input type = 'button' onclick = 'editAdminProfile()' value = 'Save'/>" + "<input type = 'button' onclick = 'backToAdminSettings()' value = 'CANCEL'/></div>";
	
}

function editAdminProfile(){
	var adminMobileNumberInProfile = document.getElementById("adminMobileNumberInProfile").value;
	var adminPinNumberInProfile = document.getElementById("adminPinNumberInProfile").value;
	var oldNumber = document.getElementById("userMobileNumber").value;
	var oldPin = document.getElementById("userId").value;
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(this.status);
			var json = JSON.parse(xhr.responseText);
			console.log(json.message);
			if(json.statusCode == 200){
				setTimeout(backToAdminSettings, 500);
			}
			else{
				alert(json.message);
			}
		}
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/EditTheProfileOfAdmin");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("adminMobileNumberInProfile=" + adminMobileNumberInProfile + "&adminPinNumberInProfile=" + adminPinNumberInProfile + "&oldNumber=" + oldNumber + "&oldPin=" + oldPin);
	
}

function backToAdminSettings(){
	document.getElementById("adminProfile").style.display = "none";
	document.getElementById("adminProfile").innerHTML = "";
}

function optionsOfAdmin(){
	
	window.location.href = "index2.html";
}

function logOutForUser(){
	document.getElementById("userBlock").classList.add("optionsOfEveryOne");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.status == 200){
			window.location.href = "index1.html";
		}
	}
	
	xhr.open("GET", "http://localhost:8080/NewSuperMarket/UserLogOut");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}


function userLogIn(){
	var mobileNumber = document.getElementById("userMobileNumber").value;
	var id = document.getElementById("userId").value;
	
	mobileNumberOfAdmin = mobileNumber;
    pinOfAdmin = id;
  
	userLoginProcess(mobileNumber, id);
}

function userLoginProcess(mobileNumber, id){
	var xhr4 = new XMLHttpRequest();
	
	xhr4.onreadystatechange = function(){
		if(this.readyState == 4){
			if(this.status == 200){
				var json = JSON.parse(xhr4.responseText);
				if(json.statusCode == 201){
					document.getElementById("userBlock").classList.remove("optionsOfEveryOne");
					setTimeout(admin, 500);
				}
				else if(json.statusCode == 200){
					document.getElementById("userBlock").classList.remove("optionsOfEveryOne");
					setTimeout(getStartedBuying, 500);
				}
				else if(json.statusCode == 1000){
					window.location.href = "index3.html";
				}
				else if(json.statusCode == 1001){
					window.location.href = "index2.html";
				}
				else{
					alert(json.message);
				}
			}
		}
	}
	
	
	var reqParams = {};
	reqParams.userMobileNumber = mobileNumber;
	reqParams.userId = id;
	
	xhr4.open("POST", "http://localhost:8080/NewSuperMarket/User");
	xhr4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr4.send("userMobileNumber=" + reqParams.userMobileNumber + "&userId=" + reqParams.userId);
	
}



function signUpPage(){
	document.getElementById("userBlock").style.display = "none";
	document.getElementById("userBlockForSignUp").style.display = 'block';
	document.getElementById("userBlockForSignUp").style.display = 'flex';
	document.getElementById("forgotPinBlock").style.display = "none";
}

function userSignUp(){
	var name = document.getElementById("userNameForSignUp").value;
	var mobile = document.getElementById("mobileNumberForSignUp").value;
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
	 if(this.readyState == 4 && this.status == 200){
		 var json = JSON.parse(this.responseText);
		 if(json.statusCode == 200){
			 setTimeout(getStartedBuying, 500);
		 }
		 else{
			 alert(json.message);
		 }
	 }
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/UserSignUp");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("name=" + name + "&mobile=" + mobile);
	}

function getStartedBuying(){
	document.getElementById("userBlock").style.display = "none";
	document.getElementById("userBlockForSignUp").style.display = "none";
	document.getElementById("forgotPinBlock").style.display = "none";
	
	var startPurchase = document.createElement("div");
	document.getElementById("body").append(startPurchase);
	
	startPurchase.innerHTML += "<div id = 'whole'><img src = 'bigBasket_Logo.png' id = 'logo'/><img src = 'groceriesBag.png' id = 'groceryImage' /><div id = 'welcome' ><p><b><center>Fresh groceries waiting for you in one click</center></b></p><div onclick = 'buyItems()' id = 'getStarted'>Get Started</div></div></div>" ;
}

function buyItems(){
	window.location.href = "index3.html";
}

function forgotPin(){
	document.getElementById("userBlock").style.display = "none";
	document.getElementById("userBlockForSignUp").style.display = 'none';
	document.getElementById("forgotPinBlock").style.display = "block";
	document.getElementById("forgotPinBlock").style.display = "flex";
	
	
}

function forgotPinAndSeeThePin(){
	
	var mobile = document.getElementById("userMobileNumberForForgotPin").value;
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if(xhr.status == 200 && xhr.readyState == 4){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
				alert("Your pin is : " + json.pin);
				mobileNumberOfAdmin = mobile;
				pinOfAdmin = json.pin;
				userLoginProcess(mobile, json.pin);
			}
			else{
				alert(json.message);
			}
		}
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/SeePinBecauseOfForgotThePin");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("mobile=" + mobile);
}
