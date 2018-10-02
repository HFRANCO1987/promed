function escapeTerm(request){
	request.term = escape(request.term);
}

function irParaTopo(){
	window.scrollTo(0,0)
}

function somenteNumero(event) {
	volta = false; 
	navIE = true; 
	if( event.keyCode ){ 
		ecc = event.keyCode; 
	}else{ 
		ecc = event.which; 
		navIE = false; 
	} 
	if( (ecc == 8 || ecc < 48 || ecc > 57) ){
		if( navIE ){     
			event.returnValue = false;     
		}else{     
			event.preventDefault();     
		} 
	}
}

/**
 * Mascara para moeda Parametros: objTextBox - objeto da jsf que ira receber a
 * mascara sepMil - separador inteiro que ser� utilizado sepDec - separador
 * decimal que ser� utilizado tamanho - tamanho do valor monetario
 * qtdCasasDecimais - quantidade de casas decimais que ser� implementada
 */
function moeda(objTextBox, sepMil, sepDec, tamanho, qtdCasasDecimais, e){
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var ie = (typeof window.ActiveXObject != 'undefined');  
	var whichCode = (ie) ? e.keyCode : e.which;
	
	
	if (whichCode == 13 || whichCode == 8 || whichCode == 9 || whichCode == 0) 
	   return true;
	key = String.fromCharCode(whichCode); // Valor para o c�digo da Chave
	
	if (strCheck.indexOf(key) == -1) 
	   return false; // Chave inv�lida
	   
	len = objTextBox.value.length;
	
	for(i = 0; i < len; i++)
		if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != sepDec)) break;
	aux = '';
	for(; i < len; i++){
		if (strCheck.indexOf(objTextBox.value.charAt(i))!=-1)
		   aux += objTextBox.value.charAt(i);
	}	   
	aux += key;
	len = aux.length;
	
	if (len == tamanho){
		return false;
	}
	if (len == 0) 
		objTextBox.value = '';
	// com duas casas decimais
	if (qtdCasasDecimais == 2){
	  if (len == 1) 
	  	objTextBox.value = '0'+ sepDec + '0' + aux;  
	  if (len == 2) 
	    objTextBox.value = '0'+ sepDec + aux;  
	}
	// com tr�s casas decimais
	if (qtdCasasDecimais == 3){
	  if (len == 1) 
	  	objTextBox.value = '0'+ sepDec + '00' + aux;  
	  if (len == 2) 
	    objTextBox.value = '0'+ sepDec + '0' + aux;
	  if (len ==3) 
		objTextBox.value = '0' + sepDec + aux;
	}
	// com duas casas decimais
	if (qtdCasasDecimais == 4){
	  if (len == 1) 
		objTextBox.value = '0'+ sepDec + '0' + '0' + '0' + aux;
	  if (len == 2) 
		objTextBox.value = '0'+ sepDec + '0' + '0' + aux;
	  if (len == 3) 
		objTextBox.value = '0'+ sepDec + '0' + aux;
	  if (len == 4)
		objTextBox.value = '0'+ sepDec + aux;
	}
	
	if (len > qtdCasasDecimais){
	    var num = qtdCasasDecimais + 1;
	    aux2 = '';  
		for (j = 0, i = len - num; i >= 0; i--) {  
			if (j == 3) {  
				aux2 += sepMil;  
				j = 0;  
			}  
			aux2 += aux.charAt(i);  
			j++;  
		}  
		objTextBox.value = '';  
		len2 = aux2.length;  
		for (i = len2 - 1; i >= 0; i--)  
			objTextBox.value += aux2.charAt(i);  
		objTextBox.value += sepDec + aux.substr(len - qtdCasasDecimais, len);  
	}
	return false;
} 

function MascaraCPF_CNPJ(field , evento){
	/*Formato  XXX.XXX.XXX-XX  ||  XX.XXX.XXX/XXXX-XX
		Testado em: IE, Firefox, Safari, Opera, Chrome
	*/
	var e;
	var cpf;
	var cnpj;
	
	if(evento && evento.type == "paste"){
		evento.returnValue =  false;
		return;
	}
	
	if(evento && evento.type == "keypress"){
		e = (evento.which) ? evento.which : evento.keyCode;
		if(!((e > 47 && e < 58) || e == 0 || e == 8)){
			if(evento.which && evento.which != 0){
				evento.preventDefault();
				return;
			}else{
				evento.keyCode = 0;
				return;
			}
		}
		if(evento.ctrlKey){
			if(evento.which && evento.which != 0){
				evento.preventDefault();
				return;
			}else{
				evento.keyCode = 0;
				return;
			}
		}
	}
	if(e != 8){
		cpf = field.value.length < 14;
		cnpj = !cpf;
		if(cpf){
			var srt = field.value;
			while(srt.indexOf('.') > -1 || srt.indexOf('-') > -1){
				srt = srt.replace('.', '').replace('-','');
			}		
			if((srt.length % 3) == 0 && srt.length > 0 ){
				if(srt.length == 9){
					field.value = field.value + '-';
				}else{
					field.value = field.value + '.';
				}
			}		
		}
		if(cnpj){
			if(field.value.length == 14){
				var srt = field.value;
				while(srt.indexOf('.') > -1 || srt.indexOf('-') > -1  || srt.indexOf('/') > -1){
					srt = srt.replace('.', '').replace('-', '').replace('/', '');
				}
				field.value = srt.substr(0, 2) + '.' + srt.substr(2, 3) + '.' + srt.substr(5, 3) + '/' + srt.substr(8, srt.length -8);
			}else{
				if(field.value.length == 15){
					field.value += '-';
				}else{
					if(field.value.length >= 18){
						field.value = field.value.substr(0, 18);
						if(evento.which && evento.which != 0){
							evento.preventDefault();
							return;
						}else{
							evento.keyCode = 0;
							return;
						}
					}			
				}		
			}	
		}
	}
}
