//$(document).ready(function()
//{
//	 $.ajax({
//		 type: "POST",
//		 url: "getqualificacaorede",
//		 success: function(getqualificacaorede)
//		 {
//			 $("#qualificacao-rede").text(getqualificacaorede == "" ? "0%" : getqualificacaorede);
//		 }
//	 })
//	 
//	 $.ajax({
//			 type: "POST",
//			 url: "getbonusincentivorede",
//			 success: function(getbonusincentivorede)
//			 {
//				 $("#bonus-incentivo").text(getbonusincentivorede == "" ? "0%" : getbonusincentivorede );
//				 }
//			 })
//	 
//	 	 $.ajax({
//				 type: "POST",
//			 url: "getindicepagamentosdiretos",
//			 success: function(getindicepagamentosdiretos)
//			 {
//				 $("#diretos").text(getindicepagamentosdiretos == "" ? "0%" : getindicepagamentosdiretos);
//				 }
//			 })
//	 
//		 $.ajax({
//				 type: "POST",
//			 url: "getcontasativarede",
//			 success: function(getcontasativarede)
//			 {
//				 $("#contas-ativas").text(getcontasativarede == "" ? "0%" : getcontasativarede);
//			 }
//		 })
//})