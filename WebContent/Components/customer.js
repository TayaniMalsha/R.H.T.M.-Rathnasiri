$(document).ready(function(){

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event){
	
// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
// Form validation-------------------
	 var status = validateUserForm();

	 if (status != true){
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }
	 
// If valid------------------------
	 var type = ($("#hiddenIDSave").val() == "") ? "POST" : "PUT";
	 
	 console.log("type: ", type);
	 
	 $.ajax({
			
		url : "CustomerAPI",
		type : type,
		data : $("#formCustomer").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onUserSaveComplete(response.responseText,status);
		}
			
	});
	
});


function onUserSaveComplete(response, status){

	console.log("status:", status );
	
	if(status == "success"){
		
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			
			$("#divUsersGrid").html(resultSet.data);
			
		}else if(resultSet.status.trim() == "error"){
			
			$("#alertError").text(resultSet.data);
			$("$alertError").show();
		}
		
	}else if(status == "error"){
		
		$("#alertError").text("Error while Saving.");
		$("#alertError").show();
		
	}else{
		$("#alertError").text("Unknown error while saving.");
		$("#alertError").show();
	}
	
	$("#hiddenmIDSave").val("");
	$("#formSystemUser")[0].reset();
	
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event){
	 $("#hiddenIDSave").val($(this).closest("tr").find('#hiddenIDUpdate').val());
	 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#address").val($(this).closest("tr").find('td:eq(2)').text());
});


$(document).on("click", ".btnRemove", function(event){
	
	$.ajax(
	{
		url  : "CustomerAPI",
		type : "DELETE",
		data : "cId=" + $(this).data("cid"),
		dataType : "text",
		complete : function(response, status)
		{
			console.log(status);
			onUserDeleteComplete(response.responseText, status);
		}
		
	});
	
});

function onUserDeleteComplete(response, status){
	
	console.log(status);
	
	if(status == "success"){
		
		console.log(response);
		
		var resultSet = JSON.parse(response);
		console.log(resultSet);
		
		if(resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
			
			$("#divUsersGrid").html(resultSet.data);
			
		}else if(resultSet.status.trim() == "error"){
			
			$("#alertError").text(resultSet.data);
			$("$alertError").show();
		}
		
	}else if(status == "error"){
		
		$("#alertError").text("Error while Deleting.");
		$("#alertError").show();
		
	}else{
		
		$("#alertError").text("Unknown error while Deleting.");
		$("#alertError").show();
	}
	
}


// CLIENTMODEL=========================================================================
function validateUserForm(){
	

	if ($("#name").val().trim() == ""){
		return "Insert Name.";
	}
	

	if ($("#email").val().trim() == ""){
		return "Insert Email.";
	} 


	if ($("#address").val().trim() == ""){
		return "Insert Address.";
	}
	
	
	return true;
}