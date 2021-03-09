<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Note Application</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/summernote/dist/summernote-bs4.css" />
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/summernote/dist/summernote-bs4.js"></script>

<script>
	let content = '', id = 0, notearea = ''
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300,
			width : 1000
		});
		notearea = document.querySelector('.note-editable')
		id = '<c:out value = "${note.id}"/>'
		if (id == '')
			id = 0
		if (id != 0) {
			content = '<c:out value = "${note.text}"/>'
			notearea.innerHTML = htmlDecode(content)
		}
	});


	function htmlEncode(value) {
		"use strict";
		return $(notearea).text(value).html();
	}

	function htmlDecode(value) {
		"use strict";
		return $(notearea).html(value).text();
	}

	function find() {
		let searchValue=document.querySelector('#searchText').value
		let form = document.createElement('form');
		form.action = "find"
		form.method = "POST"
		form.hidden = true;
		let searchText = document.createElement('input')
		searchText.setAttribute("type", "text");
		searchText.setAttribute("name", "searchText");
		searchText.setAttribute("value",searchValue);
		form.appendChild(searchText)
		document.querySelector('body').appendChild(form)
		form.submit();
	}


	function save() {
		let form = document.createElement('form');
		form.action = "save"
		form.method = "POST"
		form.hidden = true;
		let content = document.createElement('input')
		content.setAttribute("type", "text");
		content.setAttribute("name", "text");
		content.setAttribute("value",notearea.innerHTML);
		form.appendChild(content)

		let inputid = document.createElement('input')
		inputid.setAttribute("type", "text");
		inputid.setAttribute("name", "id");
		inputid.setAttribute("value", id);
		form.appendChild(inputid)

		document.querySelector('body').appendChild(form)
		form.submit();
	}

	function reset() {
		let form = document.createElement('form');
		form.action = "reset"
		form.method = "POST"
		form.hidden = true;
		document.querySelector('body').appendChild(form)
		form.submit();
	}

	function checkAll(targetClass){
		let al = document.querySelectorAll('.'+targetClass);
		al.forEach((i)=>{
			i.click();
		})
	}

	function deleteNotes(){
		let ids = new Array();
		let al = document.querySelectorAll('.dn');
		al.forEach((i)=>{
			if(i.checked)
				ids.push(i.getAttribute('nid'));
		})
		let form = document.createElement('form');
		form.action="delete"
		form.method="POST"
		form.hidden = true;
		let input = document.createElement('input')
		input.setAttribute("type", "text");
		input.setAttribute("name", "ids"); 
		input.setAttribute("value", ids); 
		form.appendChild(input)
		document.querySelector('body').appendChild(form)
		form.submit();
	}
</script>

<style type="text/css">
table, td {
	border: 1px solid black;
	text-align: center;
}

table {
	margin-top: 5%
}

.table-bordered td, .table-bordered th {
	border-color: black !important;
}
</style>
</head>