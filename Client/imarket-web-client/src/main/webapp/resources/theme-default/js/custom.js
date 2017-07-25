$(document).ready(function(){


	$(".number").keydown(function(e) {
		// Allow: backspace, delete, tab, escape, enter and .

		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||


				(e.keyCode == 65 && e.ctrlKey === true) ||

				(e.keyCode >= 35 && e.keyCode <= 40)) {

			return;
		}

		if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
			e.preventDefault();
		}
	});

	$(".number").on("contextmenu",function(e){
		return false;
	});
	$(':file').on('fileselect', function(event, numFiles, label) {
		console.log(numFiles);
		console.log(label);
	});
	$('#companyList').DataTable({


		"columnDefs": [ {
			"targets": 5,
			"orderable": false
		} ]
	});
	/***pell editor attach
	 * 
	var element = document.getElementById('description')
	attachPellEditor(element)***/
})

var duplicate = false;
function saveClick(){

	var count = 0;
	var id = $('#record_id').val();
	var mandatefields = ['companyName','leadSource','contactPerson1','contactJobTitle1','contactEmail1',
		'contactPhone1','industry','employeeCount','website','country','companyEmail','revenue'];

	for(i=0;i<mandatefields.length;i++){
		var value = $('#'+mandatefields[i]).val();
		$("#"+mandatefields[i]).removeClass('red');
		if (value==null  || value.trim()=="" || value == 'NONE'){
			$("#"+mandatefields[i]).addClass('red');
			count++;
		}
	}

	if(count>0){
		//
	}else{
		if(duplicate == false){
			$('#save-btn-final').click();
		}else{
			console.log(id)
			if(id == ''){
				console.log(id)
				onBlurCompanyName()
			}

		}

	}

}
function resetAll(){
	var mandatefields = ['companyName','leadSource','contactPerson1','contactJobTitle1','contactEmail1',
		'contactPhone1','industry','employeeCount','website','country','companyEmail','revenue'];

	for(i=0;i<mandatefields.length;i++){
		$("#"+mandatefields[i]).removeClass('red');
		 $('#'+mandatefields[i]).val('');
		
	}
}
function onUploadFile(){
	var file_text = $('#file_text').val() ;
	$('.notifyjs-wrapper').trigger('notify-hide');
	if(file_text.trim()==""||file_text==null || file_text == 'NONE'){
		$('#file_text').notify("Please select a file",{
			position : "bottom",
			autoHide: false,
			clickToHide: false
		}
		);
	}else{
		$('#upload-btn-final').click();
	}
}


$(document).on('change', ':file', function() {
	console.log($(this))
	console.log($(this).get(0).files)
	var input = $(this),
	numFiles = input.get(0).files ? input.get(0).files.length : 1,
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [numFiles, label]);
});



$(':file').on('fileselect', function(event, numFiles, label) {

	var input = $(this).parents('.input-group').find(':text'),
	log = numFiles > 1 ? numFiles + ' files selected' : label;
	console.log(input.length)
	if( input.length ) {
		input.val(log);
	} else {
		if( log ) console.log(log);
	}

});

window.reset = function (file,file_text) {

	file.val('');
	file_text.val("");

}

function emailValidation(e){
	var val = $(e).val();
	if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(val)){
		$('.notifyjs-wrapper').trigger('notify-hide');
	}else{
		$(e).notify("Email is not in Proper Format",{
			position : "right",
			autoHide: false,
			clickToHide: false
		}
		);
	}
}
function onBlurCompanyName(){
	var companyName = $('#companyName').val();
	$('.notifyjs-wrapper').trigger('notify-hide');
	var id = $('#record_id').val();
	if(companyName.trim() != "" && id == ''){
		$.ajax({
			url: "./isDuplicateCompany?companyName="+companyName,
			success: function(result){
				if(result == true){
					duplicate = true;
					$("#companyName").notify("Company Name already exists",{
						position : "right",
						autoHide: false,
						clickToHide: false
					}
					);
				}else{
					duplicate = false;

				}
			}
		});
	}

}

