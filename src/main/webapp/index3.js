/**
 * 
 */

addEventListener("DOMContentLoaded", (event) => {
	selectCategory();
});

var listLength = 0;
var favouriteList;
var pinNumber;
//selectCategory();
 function logOut(){
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(this.readyState == 4 && this.status == 200){
			 window.location.href = "index1.html";
		 }
	 }
	 
	 xhr.open("GET", "http://localhost:8080/NewSuperMarket/UserLogOut");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
 }
 
 function selectCategory(){
	 var product = document.getElementById("productSelect").value;
	 var category = document.getElementById("options").value;
	 searchProducts(product, category);
 }
 
 
 function searchProducts(product, category){
	 console.log(document.getElementById("aa"));
	  document.getElementById("prod").innerHTML = "";
	  document.getElementById("box").style.display = "block";
	  document.getElementById("box").style.display = "flex";
	 
	 getProductsFromFavouritesForNotes();
	 console.log(favouriteList + ".......");
	 var xhr = new XMLHttpRequest();
	 document.getElementById("itemsCount").innerText = listLength + " items ";
	 xhr.onreadystatechange = () =>{
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(xhr.responseText);
			 if(json.statusCode == 200){
			 for(var i = 0; i < json.list.length; i++){
				 if(i <= 45){
				 document.getElementById("prod").innerHTML += "<div class = 'productBoxes'><div class = 'imageBoxes' ><img src = '" + json.list[i].url + "' style = 'width : 80px; height : 80px;'/></div><h5 style = 'font-size : 1rem;'>" +  json.list[i].product + "</h5><p style = 'font-size : 1em' >Price : Rs.  " + json.list[i].price + "</p><br><div id = 'buyCount'><input type = 'number' class = 'count' id = '" + json.list[i].product + "sample" + "' style = 'display : none;' value = '1' /><div id = 'buy' class = '" + json.list[i].product + "sample"+ "' onclick = 'buyTheProducts(this)'>Buy Now </div><i style = 'font-size : 1.5rem;' id = '" + json.list[i].product + "favourite'" +  "class='fa-solid fa-heart " + json.list[i].product+ "' onclick = 'addAndRemoveFromFavourites(this)' ></i></div></div>";
			     for(var j = 0; j < favouriteList.length; j++){
					 if(json.list[i].product == favouriteList[j].product){
						 console.log(favouriteList[j].product + ",,,,,,/");
						 document.getElementById(json.list[i].product + "favourite").classList.add("addToFavourite");
					 }
				 }
				 }
				 else{
					 break;
				 }
			 }
			 }
			 else{
				 alert(json.message);
			 }
		 }
	 }
	 
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/viewProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("product=" + product + "&category=" + category);
 }
 
 function buyTheProducts(list){
	 var product = list.getAttribute("class");
	 console.log(product);
	 var quantity = document.getElementById(product).value;
	 console.log(quantity);
	 
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = () => {
		 console.log(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
              getItemsCountInCart();
			}
			else{
				//document.getElementById(product).value = "";
				alert(json.message);
				console.log(json.message);
			}
		}
		
	 }
	 product = product.slice(0, product.length-6);
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/AddToCart");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("product=" + product + "&quantity=" + quantity);
	 
 }
 
 function addAndRemoveProducts(){
	 document.getElementById("prod").style.display = "block";
	 document.getElementById("prod").style.display = "flex";
	 document.getElementById("purchase").style.display = "block";
	 document.getElementById("purchase").style.display = "flex";
	 document.getElementById("viewCart").style.display = "block";
	 document.getElementById("continuePurchase").style.display = "block";
	 document.getElementById("continuePurchase").style.display = "flex";
	  
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = () => {
		 if(xhr.readyState == 4 && xhr.status == 200){
			 document.getElementById("viewCart").innerHTML = "";
			 var json = JSON.parse(xhr.responseText);
			 getItemsCountInCart();
			 document.getElementById("itemsCount").innerText = listLength + " items ";
			 for(var i = 0; i < json.list.length; i++){
				 var a = json.list[i].product;
				
				//document.getElementById("view").innerHTML += "<div class = 'pro' id = '" + json.list[i].product + "'><td><div class = 'image'><img src = '" + json.list[i].url + "'style = 'width : 50px; height : 50px;' /></div></td><td>" + json.list[i].product + "</td><td>" + json.list[i].price + " Rs. </td><td>" + json.list[i].quantity + " No. </td><td><input type = 'number' class = 'count' id = '" + json.list[i].product + "samples' onchange = 'addQuantityToBuy(this)';/></td><td><input type = 'button' style = 'width : 7%; height : 30%;' onclick = 'removeProductFromCart(this)' value = 'x' id = 'prodBuy' class = '" + json.list[i].product + "'/><td></div>";
				 document.getElementById("viewCart").innerHTML += "<div class = 'pro' id = '" + json.list[i].product + "'><div class = 'image' ><img src = '" + json.list[i].url + "' style = 'width : 60px; height : 50px;' /></div><p>" + json.list[i].product + "</p><p>" + json.list[i].price + " Rs." + "</p><input type = 'number' class = 'count' id = '" + json.list[i].product + "samples" + "' onchange = 'addQuantityToBuy(this);' value = '" + json.list[i].quantity + "' /><input type = 'button' style = 'width :50%; height : 30%;' onclick = 'removeProductFromCart(this)' value = 'x' id = 'prodBuy' class = '" + json.list[i].product + "'/></div>" ;
			     document.getElementById(json.list[i].product).style = "display : grid; grid-template-columns : 2fr 7fr 2fr 2fr 1fr;";
			    // document.getElementById(json.list[i].product + "samples").value = json.list[i].quantity ;
			     console.log(json.list[i].quantity);
			 }
		 }
	 }
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/FinalOrderOfProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send(null);
 }
 
 function addQuantityToBuy(element){
	 var sample = element.getAttribute("id");
	 //document.getElementById(product + "sample").value = document.getElementById(sample).value;
	 var quantity = document.getElementById(sample).value;
	 console.log(document.getElementById(sample).value);
	 var product = sample.replace("samples", "").replace("sampls", "");
	 
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = () => {
		 console.log(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
              getItemsCountInCart();
			}
			else{
				//document.getElementById(product).value = "";
				alert(json.message);
				console.log(json.message);
			}
		}
		
	 }
	 //product = product.slice(0, product.length-6);
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/AddToCart");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("product=" + product + "&quantity=" + quantity);
 }
 
 function getItemsCountInCart(){
	  var xhr = new XMLHttpRequest();
	  
	  
	 xhr.onreadystatechange = () => {
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(xhr.responseText);
			 document.getElementById("itemsCount").innerText = json.list.length + " items ";
			 listLength = json.list.length;
		 }
	 }
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/FinalOrderOfProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send(null);
 }
 
 function removeProductFromCart(prod){ 
	 listLength -= 1;
	 var product = prod.getAttribute("class");
	 console.log(product);
	 document.getElementById(product).remove();
	 document.getElementById("itemsCount").innerText = listLength + " items";
	 
	 var xhr = new XMLHttpRequest();
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/RemoveProductsFromList");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("product=" + product);
	 console.log(product);
 }
 
 function payment(){
	  var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = () => {
		  if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(xhr.responseText);
			 pinNumber = json.pin;
			  alert("Total Amount : " + json.amount + " Rs. ....And your pin " + json.pin);
		  }
	  }
	  xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/BillPayment");
	  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhr.send(null);
 }
 
 function getAddress(){
	 getItemsCountInCart();
	 if(listLength > 0){
	 document.getElementById("deliveryDetails").style.display = "block";
	 document.getElementById("deliveryDetails").style.display = "flex";
	 document.getElementById("purchase").style.display = "none";
	 document.getElementById("viewCart").style.display = "none";
	 document.getElementById("continuePurchase").style.display = "none";
	  }
	  else{
		  alert("You didn't choose any product");
	 document.getElementById("purchase").style.display = "none";
	 document.getElementById("viewCart").style.display = "none";
	 document.getElementById("continuePurchase").style.display = "none";
	  }
	 
 }
 function backToPurchase(){
	 document.getElementById("purchase").style.display = "none";
	 document.getElementById("viewCart").style.display = "none";
	 document.getElementById("continuePurchase").style.display = "none";
 }
 
function showQRCode(){
	document.getElementById("scanQR").style.display = "block";
	document.getElementById("scanQR").style.display = "flex";
	document.getElementById("deliveryDetails").style.display = "none";
	 document.getElementById("purchase").style.display = "none";
	 document.getElementById("viewCart").style.display = "none";
	 document.getElementById("continuePurchase").style.display = "none";
	 payment();
}

function allOk(){
	 var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = () => {
		  if(xhr.readyState == 4 && xhr.status == 200){
			 alert( "Products will be in 1 hour.....Thank you for your visit, come again");
			 logOut();
		  }
	  }
	  xhr.open("GET", "http://localhost:8080/NewSuperMarket/user/BillPayment");
	  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhr.send();
}

function addAndRemoveFromFavourites(element){
	//var product = element.getAttribute("class");
	var id = element.getAttribute("id");
	console.log("product = " + product);
	console.log("id = " + id);
//	product = product.slice(18);
	/*product = product.replace(" addToFavourite", "");
	product = product.replace(" removeFromFavourite", "");
	product = product.replace(" Favourite", "");
	product = product.replace(" favourite", "");
	product = product.replace("favourite", "");
	*/
	//var id = product + "favourite";
	//console.log(id + "..................");
	//console.log(product);
	var product = id.replace("favourite", "");
	var n = 0;
	if(document.getElementById(id).classList.contains("addToFavourite")){
		document.getElementById(id).classList.remove("addToFavourite");
		document.getElementById(id).classList.add("removeFromFavourite");
		n = -1;
	}
	else if(document.getElementById(id).classList.contains("removeFromFavourite")){
		document.getElementById(id).classList.remove("removeFromFavourite");
		document.getElementById(id).classList.add("addToFavourite");
		n = 1;
	}
	else{
		document.getElementById(id).classList.add("addToFavourite");
		n = 1;
	}
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if(xhr.status == 200 && xhr.readyState == 4){
			console.log("aa");
		}
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/AddAndRemoveFromFavourites");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("product=" + product + "&n=" + n);
}

function getProductsFromFavourites(){
	document.getElementById("box").style.display = "none";
    var xhr = new XMLHttpRequest();
    document.getElementById("prod").innerHTML = "";
    xhr.onreadystatechange = () => {
		if(xhr.status == 200 && xhr.readyState == 4){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
				for(var i = 0; i < json.list.length; i++){
                	 document.getElementById("prod").innerHTML += "<div class = 'productBoxes' id = '" + json.list[i].product + "fav" + "'><div class = 'imageBoxes' ><img src = '" + json.list[i].url + "' style = 'width : 80px; height : 80px;'/></div><h5>" +  json.list[i].product + "</h5><br><p>Price : Rs.  " + json.list[i].price + "</p><br><div id = 'buyCount'><input type = 'number' class = 'count' style = 'display : none' id = '" + json.list[i].product + "sampls" + "' onchange = 'addQuantityToBuy(this);' value = '1'/><div id = 'buy' class = '" + json.list[i].product + "sampls"+ "' onclick = 'buyTheProducts(this)'>Buy Now </div><i class='fa-solid fa-xmark " + json.list[i].product + "fav" + "' id = 'removeFromFavourites'" + "onclick = 'removeFromFavourites(this)'></i></div></div>";
				}
			}
		}
	}
    xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/GetProductsFromFavourites");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(null);
}


function getProductsFromFavouritesForNotes(){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
		if(xhr.status == 200 && xhr.readyState == 4){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
				favouriteList = json.list;
			}
		}
	}
    xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/GetProductsFromFavourites");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(null);
}


function removeFromFavourites(element){
	var product = element.getAttribute("class");
	console.log(product);
	product = product.slice(18);
	console.log(product);
	document.getElementById(product).remove();
	
	product = product.substring(0, product.length-3);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/user/RemoveProductFromFavourites");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("product=" + product);
}
