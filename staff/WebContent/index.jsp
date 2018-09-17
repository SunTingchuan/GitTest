<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>人员资料</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
body {
	font-family: "微软雅黑";
	background: url(img/bg.jpg);
	background-position: center center;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
}

#bgimg img {
	display: block;
	outline: none;
	border: 0;
	height: 100%;
	width: 100%;
}

table {
	border-collapse: collapse;
}

table td {
	padding: 0;
}

#all {
	height: 460px;
	width: 630px;
	border: solid 1px #BBB;
	background-color: white;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	border-radius: 10px;
}

#headline {
	padding: 20px;
}

#colorlump {
	height: 20px;
	width: 10px;
	background-color: #333;
	border: solid 1px #bbb;
}

#caption {
	font-size: 26px;
	font-weight: bold;
	color: #333;
}

#contentbutton {
	padding: 20px;
}

#insertbut {
	width: 80px;
	height: 30px;
	background-color: #FFEFBF;
	border: solid 1px #FFE28A;
	font-size: 14px;
}

#updatebut {
	width: 80px;
	height: 30px;
	background-color: #DFEFFF;
	border: solid 1px #BFDFFF;
	font-size: 14px;
}

#deletebut {
	width: 80px;
	height: 30px;
	background-color: #EEE;
	border: solid 1px #BBB;
	font-size: 14px;
}

#idbut {
	width: 32px;
	height: 28px;
	background-color: #EEE;
	border: solid 1px #BBB;
	font-size: 14px;
	background-image: url(img/btn-search.png);
}

#idtxt {
	width: 190px;
	height: 28px;
	font-size: 14px;
	border: solid 1px #BBB;
	color: #BBB;
}

#contenttable {
	height: 93px;
	padding: 20px;
	overflow: auto;
	line-height: 60px;
	overflow-x: hidden;
}

#tablecontent {
	font-size: 14px;
	border: solid #BBB;
	cellspacing: 0px;
	cellpadding: 0px;
	border-width: 1px 0px 0px 1px;
}

#contenttable table td {
	height: 28px;
	border: solid #BBB;
	border-width: 0px 1px 1px 0px;
}

#th {
	background-color: #EEE;
}

#box {
	padding: 20px;
}

#inputbox {
	height: 70px;
	width: 300px;
	font-size: 14px;
	padding: 20px;
	border: solid 1px #BBB;
}

#name {
	height: 28px;
	width: 230px;
	font-size: 14px;
	border: solid 1px #BBB;
	color: #BBB;
}

#sex {
	height: 28px;
	width: 230px;
	font-size: 14px;
	border: solid 1px #BBB;
	color: #BBB;
}
</style>
<script>
	/*查询数据*/
	function selectFun() {
		$("#callMethod").val("select");/*改变隐藏域的value值，让后台调用相应的方法*/
		var callMethod = $("#callMethod").val();/*获取隐藏域的value值*/
		$.ajax({
			url : "select",
			type : "get",
			data : {
				callMethod : callMethod,
			},
			success : function(jsonstr) {
				console.log(jsonstr);
				$('#tablecontent tr:gt(0)').remove();//删除之前的数据
				var str = '';
				var json = JSON.parse(jsonstr);
				console.log(json);
				for (var i = 0; i < json.length; i++) {
					console.log(json[i]);
					str = '<tr align="center"><td><input type="radio" id="numberid" name="number" value="'
							+ json[i].id
							+ '" onclick="radioFun()"></td><td>'
							+ json[i].id
							+ '</td><td>'
							+ json[i].name
							+ '</td><td>' + json[i].sex + '</td></tr>';
					$('#tablecontent').append(str);
				}
			},
			error : function() {
				alert('请求有误');
			}
		});
	}
	
	/*插入数据*/
	function insertFun() {
		var name = $("#name").val();
		var sex = $("#sex").val();
		if (name == "" || sex == "") {
			alert("内容不能为空");
			return;
		} else if (sex.length > 1) {
			alert("内容过长");
			return;
		} else {
			$("#callMethod").val("insert");/*改变隐藏域的value值，让后台调用相应的方法*/
			var callMethod = $("#callMethod").val();/*获取隐藏域的value值*/
			var name = $("#name").val();
			var sex = $("#sex").val();
			$.ajax({
				url : "select",
				type : "get",
				data : {
					callMethod : callMethod,
					name : name,
					sex : sex
				},
				success : function() {
					selectFun();
				},
				error : function() {
					alert('请求有误');
				}
			});
		}
	}
	
	/*修改数据*/
	function updateFun() {
		$("#callMethod").val("update");/*改变隐藏域的value值，让后台调用相应的方法*/
		var callMethod = $("#callMethod").val();/*获取隐藏域的value值*/
		var number = $("input[name='number']:checked").val();
		var name = $("#name").val();
		var sex = $("#sex").val();
		$.ajax({
			url : "select",
			type : "get",
			data : {
				callMethod : callMethod,
				number : number,
				name : name,
				sex : sex
			},
			success : function() {
				selectFun();
			},
			error : function() {
				alert('请求有误');
			}
		});
	}
	
	/*删除数据*/
	function deleteFun() {
		var d = confirm("确定删除");
		if (d == true) {
			$("#callMethod").val("delete");/*改变隐藏域的value值，让后台调用相应的方法*/
			var callMethod = $("#callMethod").val();/*获取隐藏域的value值*/
			var number = $("input[name='number']:checked").val();
			$.ajax({
				url : "select",
				type : "get",
				data : {
					callMethod : callMethod,
					number : number
				},
				success : function() {
					selectFun();
				},
				error : function() {
					alert('请求有误');
				}
			});
		} else {
			return;
		}
	}
	
	/*条件查询*/
	function queryFun() {
		if ($("#idtxt").val() == "") {
			alert("内容不能为空");
			return;
		} else {
			$("#callMethod").val("query");/*改变隐藏域的value值，让后台调用相应的方法*/
			var callMethod = $("#callMethod").val();/*获取隐藏域的value值*/
			var idtxt = $("#idtxt").val();
			$.ajax({
				url : "select",
				type : "get",
				data : {
					callMethod : callMethod,
					idtxt : idtxt
				},
				success : function(jsonstr) {
					console.log(jsonstr);
					$('#tablecontent tr:gt(0)').remove();//删除之前的数据
					var str = '';
					var json = JSON.parse(jsonstr);
					console.log(json);
					for (var i = 0; i < json.length; i++) {
						console.log(json[i]);
						str = '<tr align="center"><td><input type="radio" id="numberid" name="number" value="'
								+ json[i].id
								+ '" onclick="radioFun()"></td><td>'
								+ json[i].id
								+ '</td><td>'
								+ json[i].name
								+ '</td><td>'
								+ json[i].sex + '</td></tr>';
						$('#tablecontent').append(str);
					}
				},
				error : function() {
					alert('请求有误');
				}
			});
		}
	}
	
	/*按钮启用*/
	function radioFun() {
		$('#deletebut').attr("disabled", false);
		$('#updatebut').attr("disabled", false);
		
		var name = $("input:checked").parents('tr').find('td').eq(2).text();
		$("#name").val(name);

		var sex = $("input:checked").parents('tr').find('td').eq(3).text();
		$("#sex").val(sex);
	}
</script>
</head>
<body onload="selectFun()">
	<div id="all">
		<div id="headline">
			<table>
				<tr>
					<td><div id="colorlump"></div></td>
					<td width="8"></td>
					<td><div id="caption">HGDB-瀚高安全可靠数据库</div></td>
				</tr>
			</table>
			<hr />
		</div>
		<div id="contentbutton">
			<table>
				<tr align="center">
					<td><input id="insertbut" type="button" value="新增"
						onclick="insertFun()"></td>

					<td width="5"></td>

					<td><input id="updatebut" type="button" value="修改"
						disabled="disabled" onclick="updateFun()"></td>

					<td width="5"></td>

					<td><input id="deletebut" type="button" value="删除"
						disabled="disabled" onclick="deleteFun()"></td>

					<td><input type="hidden" id="callMethod" name="callMethod"
						value="select"></td>

					<td width="298" align="right"><input id="idtxt" name="idtxt"
						type="text" placeholder="请输入查询的序号"></td>

					<td width="5"></td>

					<td><input type="button" id="idbut" name="idbut" value=""
						onclick="queryFun()"></td>
				</tr>
			</table>
		</div>
		<div id="contenttable">
			<table id="tablecontent">
				<tr id="th" align="center">
					<td width="120">选择</td>
					<td width="140">序号</td>
					<td width="200">姓名</td>
					<td width="120">性别</td>
				</tr>
			</table>
		</div>
		<div id="box">
			<div id="inputbox">
				<table>
					<tr>
						<td>姓名：
						<td />
						<td><input id="name" type="text" name="name"
							placeholder="请输入姓名"></td>
					<tr />
					<tr>
						<td height="10">
						<td />
					<tr />
					<tr>
						<td>性别：
						<td />
						<td><input id="sex" type="text" name="sex"
							placeholder="请输入性别：‘男’或‘女’"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>