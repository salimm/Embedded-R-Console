function replaceAll (target,search, replacement) {
	return target.split(search).join(replacement);
}

RConsole = function (url){

	this.url = url;

	this.socket= undefined;




	this.exec = function(cmd){
		this.socket.send(cmd);
	};

// connections
	this.connect = function(){
		this.socket = new WebSocket(this.url);
		rc = this;
		this.socket.onmessage = function(event){
			console.log(event.data);
			response = JSON.parse(event.data);
			rc.onResponse(response);
		};
	};

	// this.isConnected = function(){

	// };

// event handler
	this.onResponse= function(response){
		response.txt = this.prepareString(response.txt);
		if(response.type == "PRINT_MESSAGE" && this.onPrintMessage !== undefined){
			this.onPrintMessage(response);
		}else if(response.type == "SHOW_MESSAGE" && this.onShowMessage !== undefined){
			this.onShowMessage(response);
		}else if(response.type == "PRINT_ERROR" && this.onPrintError !== undefined){
			this.onPrintError(response);
		}else if(response.type == "SHOW_ERROR" && this.onShowError !== undefined){		
			this.onShowError(response);
		}else if(response.type == "CLOSE" && this.onClose !== undefined){
			this.onClose(response);
		}else if(response.type == "MISC_ACTION" && this.onMiscAction !== undefined){
			this.onMiscAction(response);
		}else if(response.type == "ENABLE_STATUS" && this.onEnableStatus !== undefined){
			this.onEnableStatus(response);
		}
	};

	this.prepareString = function(str){
		str = replaceAll(str,"\\n","\n");
		str = replaceAll(str,"\\r","\r");
		str = replaceAll(str,"\\t","\t");
		return str;
	};
//event handler
	/*event handler function should be in form of 
		function(response){
	
		}
	*/


	this.onPrintMessage= undefined;

	this.onShowMessage= undefined;

	this.onPrintError= undefined;

	this.onShowError= undefined;

	this.onClose= undefined;

	this.onMiscAction= undefined;

	this.onEnableStatus= undefined;

	
};


