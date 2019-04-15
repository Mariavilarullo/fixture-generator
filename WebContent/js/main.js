function filterPartidos(){
	
	var idCancha = 1;// $('#sede-select').find(":selected").attr('data-tipo');
		
	$.ajax({
		 type: "get",
		 url: "/springmvc/listarPartidos/"+idCancha+".htm",  
		 dataType: "json",
		 cache: false,
		 success: function(response){
			 console.log(response);
			 /*var partidoSelect = $('#partido-select');
			 
			 $('.partido-option').remove();
			 
			 $.each( response,function(partido) {
		            var row ="<p>" + partido.equipo1.nombre + "</p></br><p>" + partido.equipo2.nombre +"</p>";         
		            $('#test').append(row);
		      }*/
		},
		error : function(response) {
		 console.log(response);
		}
	  });
}

///filterPartidos();
