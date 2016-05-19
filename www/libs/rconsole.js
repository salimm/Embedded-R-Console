RConsole = function (url){

	this.url = url;

	this.socket= undefined;






// connections
	this.connect = function(){
		this.socket = new WebSocket(this.url);
		this.socket.onmessage = function(event){
			response = JSON.parse(event.data);
			processResponse(response);
		};
	};

	// this.isConnected = function(){

	// };

// event handler
	this.processResponse= function(response){
		if(response.type == "PRINT_MESSAGE"){
			processPrintMessage(response);
		}else if(response.type == "SHOW_MESSAGE"){
			processShowMessage(response);
		}else if(response.type == "PRINT_ERROR"){
			processPrintError(response);
		}else if(response.type == "SHOW_ERROR"){
			processShowError(response);
		}else if(response.type == "CLOSE"){
			processClose(response);
		}else if(response.type == "MISC_ACTION"){
			processMiscAction(response);
		}else if(response.type == "ENABLE_STATUS"){
			processEnableStatus(response);
		}
	};
//event handler

	this.processPrintMessage= function(response){

	};

	this.processShowMessage= function(response){
		
	};

	this.processPrintError= function(response){

	};


	this.processShowError= function(response){
		alert(response.text);
	};

	this.processClose= function(response){

	};

	this.processMiscAction= function(response){

	};

	this.processEnableStatus= function(response){

	};

	
}