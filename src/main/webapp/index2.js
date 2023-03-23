/**
 * 
 */

 function addProduct(){
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
			 alert(json.message);
			 document.getElementById("productName1").value = "";
			 document.getElementById("productCount1").value = "";
			 document.getElementById("productPrice1").value = "";
			 document.getElementById("url").value = "";
			 }
			 else if(json.statusCode == 101){
				 alert(json.message);
				 document.getElementById("productPrice1").value = "";
			 }
			 else if(json.statusCode == 102){
				 alert(json.message);
				 document.getElementById("productCount1").value = "";
			 }
			 else if(json.statusCode == 103){
				 alert(json.message);
				 document.getElementById("productName1").value = "";
			     document.getElementById("productCount1").value = "";
			     document.getElementById("productPrice1").value = "";
			     document.getElementById("url").value = "";
			 }
			 else{
				 alert(json.message);
				
			 }
		 }
	 }
	 var category = document.getElementById("itemsCategory1").value;
	 var product = document.getElementById("productName1").value;
	 var count = document.getElementById("productCount1").value;
	 var price = document.getElementById("productPrice1").value;
	 var url = document.getElementById("url").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AddAProduct");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&product=" + product + "&count=" + count + "&price=" + price + "&url=" + url);
 }
 
 
 function deleteProduct(){
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 // var js = JSON.stringify(this.responseText);
			 var json = JSON.parse(this.responseText);
			 console.log(json);
			 if(json.statusCode == 200){
				 alert(json.message);
				 document.getElementById("productName2").value = "";
			 }
			 else {
				 alert(json.message);
				 document.getElementById("productName2").value = "";
			 }
		 }
	 }
	 
	 var category = document.getElementById("itemsCategory2").value;
	 var product = document.getElementById("productName2").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/DeleteAProduct");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&product=" + product);
 }
 
 function editAProductName(){
	 document.getElementById("editName").style.display = "block";
	 document.getElementById("editName").style.display = "flex";
	 document.getElementById("productEdit").style.display = "none";
	 document.getElementById("editPrice").style.display = "none";
	 document.getElementById("editImage").style.display = "none";
 }
 function editProductName(){
	 
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 alert(json.message);
				 document.getElementById("productName3").value = "";
				 document.getElementById("productName4").value = "";
			 }
			 else if(json.statusCode == 101){
				 alert(json.message);
			 }
			 else{
				 alert(json.message);
				 document.getElementById("productName3").value = "";
				 document.getElementById("productName4").value = "";
			 }
		 }
	 }
	 
	 var category = document.getElementById("itemsCategory3").value;
	 var oldName = document.getElementById("productName3").value;
	 var newName = document.getElementById("productName4").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/EditAProduct");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&oldName=" + oldName + "&newName=" + newName);
	 
 }
 
 function editAProductPrice(){
	 document.getElementById("editPrice").style.display = "block";
	 document.getElementById("editPrice").style.display = "flex";
	 document.getElementById("productEdit").style.display = "none";
	 document.getElementById("editName").style.display = "none";
	 document.getElementById("editImage").style.display = "none";
 }
 
 
 function editProductPrice(){
	 
	  var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 alert(json.message);
				 document.getElementById("productName7").value = "";
				 document.getElementById("productPrice").value = "";
			 }
			 else if(json.statusCode == 101){
				 alert(json.message);
			 }
			 else{
				 alert(json.message);
				 document.getElementById("productName7").value = "";
				 document.getElementById("productPrice").value = "";
			 }
		 }
	 }
	 
	 var category = document.getElementById("itemsCategory6").value;
	 var oldName = document.getElementById("productName7").value;
	 var price = document.getElementById("productPrice").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/EditAProductPrice");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&oldName=" + oldName + "&price=" + price);
 }
 
 function editAProductImage(){
	 document.getElementById("editImage").style.display = "block";
	 document.getElementById("editImage").style.display = "flex";
	 document.getElementById("productEdit").style.display = "none";
	 document.getElementById("editName").style.display = "none";
	 document.getElementById("editPrice").style.display = "none";
 }
 
 function editProductImage(){
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 alert(json.message);
				 document.getElementById("productName8").value = "";
				 document.getElementById("productImage").value = "";
			 }
			 else if(json.statusCode == 101){
				 alert(json.message);
			 }
			 else{
				 alert(json.message);
				 document.getElementById("productName8").value = "";
				 document.getElementById("productImage").value = "";
			 }
		 }
	 }
	 
	 var category = document.getElementById("itemsCategory7").value;
	 var oldName = document.getElementById("productName8").value;
	 var image = document.getElementById("productImage").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/EditAProductImage");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&oldName=" + oldName + "&image=" + image);
 }
 
 
 
 
 function increaseQuantity(){
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 alert(json.message);
				 document.getElementById("productName5").value = "";
				 document.getElementById("quantity1").value = "";
			 }
			 else if(json.statusCode == 101){
				 alert(json.message);
				 document.getElementById("quantity1").value = "";
			 }
			 else if(json.statusCode == 102){
				 alert(json.message);
				 document.getElementById("productName5").value = "";
				 document.getElementById("quantity1").value = "";
			 }
			 else{
				 alert(json.message);
			 }
		 }
	 }
	 
	 var category = document.getElementById("itemsCategory4").value;
	 var productName = document.getElementById("productName5").value;
	 var quantity = document.getElementById("quantity1").value;
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/IncreaseProductCount");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category + "&productName=" + productName + "&quantity=" + quantity);
 }
 
 function openInputBox(){
	   document.getElementById("see").style.display = "block";
	   document.getElementById("see").style.display = "flex";
	   document.getElementById("productName6").style.display = "block";
 }
 function seeSpecificProductCount(){
	
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	
	
	var category = document.getElementById("itemsCategory5").value;
	 var element = document.getElementById("relatedProducts5").options.length-1;
	 for(var i = element; i > -1; i--){
		 document.getElementById("relatedProducts5").remove(i);
	 }
	 
	 var item = document.getElementById("productName6").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 var json = JSON.parse(this.responseText);
				 if(json.statusCode == 200){
					 document.getElementById("div").style.display = "block";
					 document.getElementById("productTable").innerHTML = "";
					 document.getElementById("resultDiv").style.display = "block";
					 document.getElementById("resultDiv").style.display = "flex";
					 document.getElementById("productTable").innerHTML += "<th>Price</th><th>Product</th><th>Quantity</th><th>Image</th>";
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("productTable").innerHTML += "<td>" + json.arr[i].price + "</td><td>" + Object.keys(json.arr[i].item) + "</td><td>" +Object.values(json.arr[i].item) + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.arr[i].url + "' style = 'width : 150px; height : 150px'/></div></td>";
					 }
				 }
			 }
			 else {
			alert(json.message);
		}
		document.getElementById("productTable").innerHTML += "<br><br><br>";
		 }
	 }
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + category);
 }
 
 function seeAllProductCount(){
	
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	
	 document.getElementById("see").style.display = "none";
	 var category = document.getElementById("itemsCategory5").value;

	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
				 var json = JSON.parse(this.responseText);
				 if(json.statusCode == 200){
					 document.getElementById("div").style.display = "block";
					 document.getElementById("productTable").innerHTML = "";
					 document.getElementById("resultDiv").style.display = "block";
					 document.getElementById("resultDiv").style.display = "flex";
					 document.getElementById("productTable").innerHTML += "<th>Price</th><th>Product</th><th>Quantity</th><th>Image</th>";
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("productTable").innerHTML += "<td>" + json.arr[i].price + "</td><td>" + Object.keys(json.arr[i].item) + "</td><td>" +Object.values(json.arr[i].item) + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.arr[i].url + "' style = 'width : 150px; height : 150px'/></div></td>";
					 }
				 }
				 else{
					 alert(json.message);
				 }
				 document.getElementById("productTable").innerHTML += "<br><br><br>";
		 }
	 }
	 
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/ProductAndQuantity");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("category=" + category);
 }
 
 function goToAdminChoices(){
	 document.getElementById("resultDiv").style.display = "none";
	 document.getElementById("productName6").value = "";
	 document.getElementById("see").style.display = "none";
	 document.getElementById("productName6").style.display = "none";
 }
 function mouseDown1(){
	 var table = document.getElementById("itemsCategory1").value;
	  var element = document.getElementById("relatedProducts1").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts1").remove(j);
	 }
	 var item = document.getElementById("productName1").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts1").innerHTML += "<option value = '" + Object.keys(json.arr[i].item)+ "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table);
 }
 
 function putValuesToInputBoxForAddAProduct(){
	 document.getElementById("productName1").value = document.getElementById("relatedProducts1").value;
 }
 
 function mouseDown2(){
	  var table = document.getElementById("itemsCategory2").value;
	  var element = document.getElementById("relatedProducts2").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts2").remove(j);
	 }
	 var item = document.getElementById("productName2").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts2").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table);
 }
 
  function putValuesToInputBoxForDeleteAProduct(){
	 document.getElementById("productName2").value = document.getElementById("relatedProducts2").value;
 }
 
 function mouseDown3(){
	 var table = document.getElementById("itemsCategory3").value;
	 var element = document.getElementById("relatedProducts3").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts3").remove(j);
	 }
	 var item = document.getElementById("productName3").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts3").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table);
	 
 }
 
 function putValuesToInputBoxForEditAProduct(){
	 document.getElementById("productName3").value = document.getElementById("relatedProducts3").value;
 }
 
 function mouseDown4(){
	 var table = document.getElementById("itemsCategory4").value;
	 var element = document.getElementById("relatedProducts4").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts4").remove(j);
	 }
	 var item = document.getElementById("productName5").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts4").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table);
	 
 }
 
 function putValuesToInputBoxForIncreaseProductCount(){
	 document.getElementById("productName5").value = document.getElementById("relatedProducts4").value;
 }
 
 function mouseDown5(){
	 var table = document.getElementById("itemsCategory5").value;
	 var element = document.getElementById("relatedProducts5").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts5").remove(j);
	 }
	 var item = document.getElementById("productName6").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts5").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table );
 }
 
 function putValuesToInputBoxForSeeProductCount(){
	 document.getElementById("productName6").value = document.getElementById("relatedProducts5").value;
}
 
 function mouseDown6(){
	 var table = document.getElementById("itemsCategory6").value;
	 var element = document.getElementById("relatedProducts6").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts6").remove(j);
	 }
	 var item = document.getElementById("productName7").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts6").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table );
 }
 
 function putValuesToInputBoxForEditAProductPrice(){
	 document.getElementById("productName7").value = document.getElementById("relatedProducts6").value;
 }
 
 
 function mouseDown7(){
	 var table = document.getElementById("itemsCategory7").value;
	 var element = document.getElementById("relatedProducts7").options.length-1;
	 for(var j = element; j > -1; j--){
		 document.getElementById("relatedProducts7").remove(j);
	 }
	 var item = document.getElementById("productName8").value;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.onreadystatechange = function(){
		 if(xhr.readyState == 4 && xhr.status == 200){
			 var json = JSON.parse(this.responseText);
			 if(json.statusCode == 200){
				 if(json.arr.length > 0){
					 for(var i = 0; i < json.arr.length; i++){
						 document.getElementById("relatedProducts7").innerHTML += "<option value = '" + Object.keys(json.arr[i].item) + "'>" + Object.keys(json.arr[i].item) + "</option>";
					 }
				 }
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/AllProducts");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhr.send("item=" + item + "&category=" + table );
 }
 
 function putValuesToInputBoxForEditAProductImage(){
	 document.getElementById("productName8").value = document.getElementById("relatedProducts7").value;
 }
 
 
function logout(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			window.location.href = "index1.html";
		}
	}
	xhr.open("GET", "http://localhost:8080/NewSuperMarket/UserLogOut");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function seeChanges(){
    document.getElementById("productDelete").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";

	 document.getElementById("resultDiv").style.display = "block";
	 document.getElementById("resultDiv").style.display = "flex";
	 document.getElementById("productTable").innerHTML = "";
	 document.getElementById("div").style.display = "none";
	 document.getElementById("editName").style.display = "none";
	 document.getElementById("editPrice").style.display = "none";
	 document.getElementById("editImage").style.display = "none";
	
	var category = document.getElementById("categories").value;
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(this.responseText);
			console.log(json.list);
		if(json.statusCode == 200){
			document.getElementById("div").style.display = "block";
			if(category == "AddedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Name</th><th>Product Count</th><th>Product Price</th><th>Image</th>";
			    for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td>" + "<td>" + json.list[i].quantity + "</td>" + "<td>" + json.list[i].price + "</td> <td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "DeletedProducts"){
		         document.getElementById("productTable").innerHTML += "<th>Price</th><th>Category</th><th>Product Name</th><th>Image</th>";
				 for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "EditedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Old Name</th><th>Product New Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productOldName + "</td>" + "<td>" + json.list[i].productNewName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "QuantityIncreasedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Name</th><th>Starting Quantity</th> <th>Total Quantity</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td>" + "<td>" + json.list[i].startingQuantity + "</td>" + "<td>" + json.list[i].quantity + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "EditedProductsPrices"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Old Price</th><th>Product Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td><td>" + json.list[i].productOldPrice + "</td><td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "EditedProductsImages"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Old Image</th><th>Product Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td><td><div style = 'width : 5%; height : 5%'><img src = '" + json.list[i].productOldImage + "' style = 'width : 150px; height : 150px' /></div></td><td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			
			
			document.getElementById("productTable").innerHTML += "<br><br><br>";
		}
		else {
			alert(json.message);
		}
		}
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/TodayChanges");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("category=" + category);
}

function seeChangesBetweenTwoDates(){
	document.getElementById("resultDiv").style.display = "block";
	document.getElementById("resultDiv").style.display = "flex";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("productTable").innerHTML = "";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	
	
	var category = document.getElementById("categories1").value;
	var date1 = document.getElementById("startDate").value;
	var date2 = document.getElementById("endDate").value;
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
				document.getElementById("div").style.display = "block";
				document.getElementById("resultDiv").style.display = "block";
				document.getElementById("resultDiv").style.display = "flex";
			if(category == "AddedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Date</th><th>Price</th><th>Category</th><th>Product Name</th><th>Product Count</th><th>Product Price</th><th>Image</th>";
			    for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].date + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td>" + "<td>" + json.list[i].quantity + "</td>" + "<td>" + json.list[i].price + "</td> <td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "DeletedProducts"){
		         document.getElementById("productTable").innerHTML += "<th>Date</th><th>Price</th><th>Category</th><th>Product Name</th><th>Image</th>";
				 for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].date + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px'/></div></td>";
			}
			}
			else if(category == "EditedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Date</th><th>Price</th><th>Category</th><th>Product Old Name</th><th>Product New Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].date + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productOldName + "</td>" + "<td>" + json.list[i].productNewName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "QuantityIncreasedProducts"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Date</th><th>Price</th><th>Category</th><th>Product Name</th><th>Starting Quantity</th> <th>Total Quantity</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].date + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td>" + "<td>" + json.list[i].productName + "</td>" + "<td>" + json.list[i].startingQuantity + "</td>" + "<td>" + json.list[i].quantity + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "EditedProductsPrices"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Old Price</th><th>Product Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td><td>" + json.list[i].productOldPrice + "</td><td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else if(category == "EditedProductsImages"){
				document.getElementById("productTable").innerHTML += "<th>Status</th><th>Price</th><th>Category</th><th>Product Old Image</th><th>Product Name</th><th>Image</th>";
				for(var i = 0; i < json.list.length; i++){
				document.getElementById("productTable").innerHTML += "<td>" + json.list[i].status + "</td><td>" + json.list[i].price + "</td><td>" + json.list[i].category + "</td><td><div style = 'width : 5%; height : 5%'><img src = '" + json.list[i].productOldImage + "' style = 'width : 150px; height : 150px' /></div></td><td>" + json.list[i].productName + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
			}
			}
			else{
				console.log("Nothing");
			}
			document.getElementById("productTable").innerHTML += "<br><br><br>";
		}
		else{
			alert(json.message);
		}
		}
		
	}
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/ChangesBetweenTwoDates");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("date1=" + date1 + "&date2=" + date2 + "&category=" + category);
}

function addAProductDiv(){
	document.getElementById("productAdd").style.display = "block";
	document.getElementById("productAdd").style.display = "flex";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function deleteAProductDiv(){
	document.getElementById("productDelete").style.display = "block";
	document.getElementById("productDelete").style.display = "flex";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function editAProductDiv(){
	document.getElementById("productEdit").style.display = "block";
	document.getElementById("productEdit").style.display = "flex";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function increaseAProductQuantityDiv(){
	
	document.getElementById("increaseCount").style.display = "block";
	document.getElementById("increaseCount").style.display = "flex";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function viewProductQuantityDiv(){
	document.getElementById("seeCount").style.display = "block";
	document.getElementById("seeCount").style.display = "flex";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function todayChangesDiv(){
	document.getElementById("todayChanges").style.display = "block";
	document.getElementById("todayChanges").style.display = "flex";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function changesBetweenParticularDates(){
	document.getElementById("changesBetweenDates").style.display = "block";
	document.getElementById("changesBetweenDates").style.display = "flex";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
}

function viewAllCustomers(){
	
	document.getElementById("customers").style.display = "block";
	document.getElementById("customers").style.display = "flex";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("customerTable").innerHTML = "";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	 
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(xhr.responseText);
			document.getElementById("div").style.display = "block";
			document.getElementById("customerTable").innerHTML += "<th>Customer Name</th><th>Mobile Number</th><th>Pin Number</th>";
			for(var i = 0; i < json.list.length; i++){
				document.getElementById("customerTable").innerHTML += "<td class = 'data'>" + json.list[i].customer + "</td><td class = 'data'>" + json.list[i].mobile + "</td><td>" + json.list[i].pin + "</td>";
			}
		}
	}
	
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/ViewCustomers");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(null);
}


function viewWhichCustomerBoughtWhichThings(){
	document.getElementById("viewThingsBoughtByCustomer").style.display = "block";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "flex";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("customers").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	
}

function seeTheThingsWhichBoughtByTheCustomer(){
	
	var pin = document.getElementById("customerId").value;
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			var json = JSON.parse(xhr.responseText);
			if(json.statusCode == 200){
				document.getElementById("productTable").innerHTML = "";
				document.getElementById("productTable").innerHTML += "<th>Date</th><th>Product</th><th>Quantity</th><th>Image</th>";
				document.getElementById("resultDiv").style.display = "block";
	            document.getElementById("resultDiv").style.display = "flex";
	            document.getElementById("productDelete").style.display = "none";
	            document.getElementById("productAdd").style.display = "none";
	            document.getElementById("productEdit").style.display = "none";
	            document.getElementById("increaseCount").style.display = "none";
	            document.getElementById("seeCount").style.display = "none";
	            document.getElementById("todayChanges").style.display = "none";
	            document.getElementById("changesBetweenDates").style.display = "none";
	            document.getElementById("customers").style.display = "none";
	            document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	            document.getElementById("div").style.display = "block";
	            document.getElementById("editName").style.display = "none";
	            document.getElementById("editPrice").style.display = "none";
	            document.getElementById("editImage").style.display = "none";
				
				for(var i = 0; i < json.list.length; i++){
					document.getElementById("productTable").innerHTML += "<td>" + json.list[i].date + "</td><td>" + json.list[i].product + "</td><td>" + json.list[i].quantity + "</td><td><div style = 'width : 5%; height : 5%;' ><img src = '" + json.list[i].url + "' style = 'width : 150px; height : 150px;'/></div></td>";
				}
			}
			else{
				alert(json.message);
			}
		}
	}
	xhr.open("POST", "http://localhost:8080/NewSuperMarket/admin/ViewThingsWhichBoughtByCustomer")
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("pin=" + pin);
}

function viewDailyIncome(){
	
	document.getElementById("customers").style.display = "block";
	document.getElementById("customers").style.display = "flex";
	document.getElementById("changesBetweenDates").style.display = "none";
	document.getElementById("productAdd").style.display = "none";
	document.getElementById("productDelete").style.display = "none";
	document.getElementById("productEdit").style.display = "none";
	document.getElementById("increaseCount").style.display = "none";
	document.getElementById("seeCount").style.display = "none";
	document.getElementById("todayChanges").style.display = "none";
	document.getElementById("resultDiv").style.display = "none";
	document.getElementById("viewThingsBoughtByCustomer").style.display = "none";
	document.getElementById("div").style.display = "none";
	document.getElementById("customerTable").innerHTML = "";
	document.getElementById("editName").style.display = "none";
	document.getElementById("editPrice").style.display = "none";
	document.getElementById("editImage").style.display = "none";
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if(xhr.status == 200 && xhr.readyState == 4){
			document.getElementById("div").style.display = "block"
			var json = JSON.parse(xhr.responseText);
			document.getElementById("customerTable").innerHTML += "<th>Date</th><th>Income</th>";
			for(var i = 0; i < json.list.length; i++){
				document.getElementById("customerTable").innerHTML += "<td class = 'data'>" + json.list[i].date + "</td><td class = 'data'>" + json.list[i].amount + " Rs." + "</td>";
			}
		}
	}
	xhr.open("GET", "http://localhost:8080/NewSuperMarket/admin/ViewDailyIncome");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}