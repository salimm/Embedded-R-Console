RConsole = function (url){

	this.url = url;

	this.socket= undefined;




	this.prototype.exec = function(cmd){
		this.socket.send(cmd);
	};

// connections
	this.prototype.connect = function(){
		this.socket = new WebSocket(this.url);
		this.socket.onmessage = function(event){
			response = JSON.parse(event.data);
			onResponse(response);
		};
	};

	// this.isConnected = function(){

	// };

// event handler
	this.prototype.onResponse= function(response){
		if(response.type == "PRINT_MESSAGE" && this.onShowError !== undefined){
			onPrintMessage(response);
		}else if(response.type == "SHOW_MESSAGE" && this.onShowError !== undefined){
			onShowMessage(response);
		}else if(response.type == "PRINT_ERROR" && this.onShowError !== undefined){
			onPrintError(response);
		}else if(response.type == "SHOW_ERROR" && this.onShowError !== undefined){		
			onShowError(response);
		}else if(response.type == "CLOSE" && this.onShowError !== undefined){
			onClose(response);
		}else if(response.type == "MISC_ACTION" && this.onShowError !== undefined){
			onMiscAction(response);
		}else if(response.type == "ENABLE_STATUS" && this.onShowError !== undefined){
			onEnableStatus(response);
		}
	};
//event handler
	/*event handler function should be in form of 
		function(response){
	
		}
	*/


	this.prototype.onPrintMessage= undefined;

	this.prototype.onShowMessage= undefined;

	this.prototype.onPrintError= undefined;

	this.prototype.onShowError= undefined;

	this.prototype.onClose= undefined;

	this.prototype.onMiscAction= undefined;

	this.prototype.onEnableStatus= undefined;

	
};