function apagarFormulario(id){
	
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	var confirmacao = confirm("Este formulário será removido");
	if (confirmacao == true) {
		$("#" + id).remove();
		$.ajax({
		    type: "POST",
		    url: "/meusformularios",
		    data: { id: id}, // parameters
		    async : true,
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader(header, token);
		    },
		    success: function(response){
		    	if(response == 1){
		    		alert("Formulário removido com sucesso!");
		    	}
		    }
		}); 
	}
}