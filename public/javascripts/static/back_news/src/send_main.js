/*
 * @author kingda
 * @description 处理后台新闻发布的前端逻辑
 * @location static/back_news/src/main.js
 */
define(function(require){
	require("./dropdown.js");
	var editor = require("./kindEditor");
	var editorinstance = new editor();
	//初始化kindEditor
	editorinstance._init("#editor");
	//定义提交前的动作
	editorinstance._send("#submitbutton");
	//定义状态反馈
	editorinstance._status("#status");
	//定义选择框改变的行为
	editorinstance._selectChange(".newsClassSelect","#selectShow","#selectData");
})