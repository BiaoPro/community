define(function(require){
	require('jquery');
	require("bootstrap");
	var Alert = require("./alert");
	var alert = new Alert();
	alert._init("#status");
})