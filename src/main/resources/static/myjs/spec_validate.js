$(document).ready(function(){
	//1.hide error section
	$("#specCodeError").hide();
	$("#specNameError").hide();
	$("#specNoteError").hide();
	
	//2.declare error variable
	var specCodeError = false;
	var specNameError = false;
	var specNoteError = false;
	
	
	//3.validate function
	function validate_specCode(){
		var val= $("#specCode").val();
		var exp =/^[A-Z]{4,10}$/;
		if (val== ''){
			$("#specCodeError").show();
			$("#specCodeError").html("*<b>Code </b>cannot be empty");
			$("#specCodeError").css("color", "red");
			specCodeError= false;
		}else if(!exp.test(val)){
			$("#specCodeError").show();
			$("#specCodeError").html("*<b>Code </b>must be 4-12 char");
			$("#specCodeError").css("color", "red");
			specCodeError= false;
		}else{
			var id = 0; //for register
	                if($("#id").val()!=undefined) { //edit page
						specCodeError = true;
						id = $("#id").val();
					}
                    $.ajax({
						url:'checkCode',
						data: {"code": val,"id":id},
						success:function(resTxt) {
							if(resTxt!='') {
								$("#specCodeError").show();
                   				$("#specCodeError").html(resTxt);
                    			$("#specCodeError").css('color','red');
                    			specCodeError = false;
							} else {
								$("#specCodeError").hide();
								specCodeError = true;
							}
						}
						
					});
		}
		return specCodeError;
		
	}
	function validate_specName(){
		var val= $("#specName").val();
		var exp =/^[A-Za-z0-9\s]{4,60}$/;
		if(val==''){
			$("#specNameError").show();
			$("#specNameError").html("*<b>Name </b>cannot be empty");
			$("#specNameError").css("color", "red");
			specNameError= false;
			
		}else if(!exp.test(val)){
			$("#specNameError").show();
			$("#specNameError").html("*<b>Name </b>must be 4-60 char");
			$("#specNameError").css("color", "red");
			specNameError= false;
			
		}else{
			$("#specCodeError").hide();
			specNameError= true;
			
			
		}return specNameError;
	}
	
	function validate_specNote(){
		var val= $("#specNote").val();
		var exp=/^[A-Za-z0-9\s\.\-\'\,]{10,250}$/;
		if (val==''){
			$("#specNoteError").show();
			$("#specNoteError").html("*<b>Note </b>cannot be empty");
			$("#specNoteError").css("color", "red");
			specNoteError= false;
			
		}else if (!exp.test(val)){
			$("#specNoteError").show();
			$("#specNoteError").html("*<b>Note </b>must be 10- 250 char");
			$("#specNoteError").css("color", "red");
			specNoteError= false;
			
		}else {
			$("#specNoteError").hide();
			specNoteError= true;
			
		}return specNoteError;
		
	}
	//4. action event
	
	$("#specCode").keyup(function(){
		$(this).val($(this).val().toUppercase());
		validate_specCode();
		
	});
	$("#specName").keyup(function(){
		 validate_specName();
	});
	$("#specNote").keyup(function(){
		validate_specNote();
	});
	
	//5. on submit
	$("#specForm").submit(function(){
		validate_specCode();
		validate_specName();
		validate_specNote();
		if (specCodeError&& specNameError && specNoteError) return true;
		else return false;
		
		
	});
	
});