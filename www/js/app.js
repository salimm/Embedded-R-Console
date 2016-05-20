document.onreadystatechange = function(){


	window.rconsole = new RConsole('ws://localhost:8080/console/');
	window.rconsole.connect();
	window.rconsole.onPrintMessage = function(response){
		document.getElementById('msg').value = document.getElementById('msg').value + response.txt;
	}


	document.getElementById('cmd').onkeypress = function(e){

		if (e.keyCode == 13) {
			var cmd = document.getElementById('cmd').value;
			cmd = '{ "command": "'+cmd+'"}';
			window.rconsole.exec(cmd);
			document.getElementById('cmd').value = "";
		}
	};


};