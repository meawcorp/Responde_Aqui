$(document).ready(function(){
	$('#cidade-select').change(function() {
		var id_cidade = $("option:selected", this).val();
		   
		$.ajax({
			type: 'GET',
			url: '/selectcampus/' + id_cidade,
			dataType: "json",
			contentType: "application/json",
			success: function(campus){
	            if (campus.length !== 0) {
	            	if($('.options')[0]){
	            		$('#campus-select').find('.options').remove();
	            	}
	                $(campus).each(function (i, camp) {
	                    $('<option>').val(camp.id).text(camp.nome).addClass('options').appendTo('#campus-select');
	                });
	            }else{
	            	$('#campus-select').find('.options').remove();
	            }
			}
		});
	});
	
	$('#campus-select').change(function() {
		var id_campus = $("option:selected", this).val();
		   
		$.ajax({
			type: 'GET',
			url: '/selectcurso/' + id_campus,
			dataType: "json",
			contentType: "application/json",
			success: function(cursos){
	            if (cursos.length !== 0) {
	            	if($('.options')[0]){
	            		$('#curso-select').find('.options').remove();
	            	}
	                $(cursos).each(function (i, curso) {
	                    $('<option>').val(curso.id).text(curso.nome).addClass('options').appendTo('#curso-select');
	                });
	            }else{
	            	$('#curso-select').find('.options').remove();
	            }
			}
		});
	});
	
});


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
		    		$( "div.success" ).fadeIn( 300 ).delay( 2000 ).fadeOut( 400 );
		    	}else{
		    		$( "div.failure" ).fadeIn( 300 ).delay( 2000 ).fadeOut( 400 );
		    	}
		    }
		}); 
	}
}

function editarFormulario(descricao, dt_criacao, dt_fechamento, id, id_campus, id_cidade, id_curso, id_usuario, link, n_respostas, sexo, titulo, turno){

	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

    var formulario = {
			descricao: descricao,
			dt_criacao: dt_criacao,
			dt_fechamento: dt_fechamento,
			id: id,
			id_campus: id_campus,
			id_cidade: id_cidade,
			id_curso: id_curso,
			id_usuario: id_usuario,
			link: link,
			n_respostas: n_respostas,
			sexo: sexo,
			titulo: titulo,
			turno: turno
	}
    
	var confirmacao = confirm("Este formulário será atualizado");
	if (confirmacao == true) {
		$.ajax({
		    type: "POST",
		    url: "/editarformulario",
	        contentType : 'application/json; charset=utf-8',
	        dataType : 'json',
		    data: JSON.stringify(formulario),
		    async : true,
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader(header, token);
		    },
		    success: function(response){
		    	if(response == 1){
		    		$( "div.success" ).fadeIn( 300 ).delay( 2000 ).fadeOut( 400 );
		    	}else{
		    		$( "div.failure" ).fadeIn( 300 ).delay( 2000 ).fadeOut( 400 );
		    	}
		    }
		}); 
	}
}
