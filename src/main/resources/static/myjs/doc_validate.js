$(document).ready(function(){
	
	//1.hide error section
	$("firstnameError").hide();
	$("lastnameError").hide();
	$("emailError").hide();
	$("addressError").hide();
	$("mobileError").hide();
	$("[name='docgenError']").hide();
	$("noteError").hide();
	//$("specializationError").hide();
	//$("photoError").hide;
	
	//2.declare error variable
	var firstnameError = false;
	var lastnameError = false;
	var emailError= false;
	var addressError= false;
	var mobileError= false;
	var docgenError= false;
	var noteError= false;
	
	//3.write validate function with ajax validation\
	function validate_firstname(){
		return null;
		
	}
	
	function validate_lastname(){
		return null;
		
	}
	
	function validate_email(){
		return null;
		
	}
	function validate_address(){
		return null;
		
	}
	function validate_mobile(){
		return null;
		
	}
	function validate_firstname(){
		return null;
		
	}
	function validate_docgen(){
		return null;
		
	}
	function validate_note(){
		return null;
		
	}
	//function validate_photo(){
		//return null;
		
	//}
	
	//4.on click event validate the data
	$("#firstname").keyup(function(){
		 validate_firstname();
	});
	$("#lastname").keyup(function(){
		 validate_lastname();
	});
	$("#email").keyup(function(){
		 validate_email();
	});
	$("#address").keyup(function(){
		 validate_address();
	});
	$("#mobile").keyup(function(){
		 validate_mobile();
	});
	$("[name='docgen']").keyup(function(){
		 validate_docgen();
	});
	$("#note").keyup(function(){
		 validate_note();
	});
	//$("#photo").keyup(function(){
		// validate_photo();
	//});
	
	//5.submit the data on click create
	$("#docForm").submit (function(){
		
	});
});

