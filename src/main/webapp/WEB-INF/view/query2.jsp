<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售数据查询</title>

<!-- js引用处 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.2/datagrid-detailview.js"></script>
</head>
<body>
	<div id="pp_spu_list_tool" style="padding: 5px; height: auto;display:inline-block;">
		<a href="javascript:void(0)" class="easyui-linkbutton addbtn" iconCls="icon-add" plain="true" onclick="add_spu_div_open()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton editbtn" iconCls="icon-edit" plain="true" onclick="edit_attr_name()">修改</a>
			
		<%--<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" onclick="delete_attr_name();" plain="true">禁用</a>--%>
	</div>
	<div style="float:right;display:inline-block;height:35px;line-height:35px;">
	  	SPU名称：<input type="text" name="spu_name" id="spu_name" class="easyui-textbox"> 
		产品编号：<input type="text" name="skuNumber" id="skuNumber" class="easyui-textbox"> 
		出厂编号: <input type="text" name="manufacturing" id="manufacturing" class="easyui-textbox"> 
		品牌: 
		<input id="brandNameSearch" class="easyui-combobox searchText" name="brandName" 
			data-options="url:'${pageContext.request.contextPath}/spu/addLoadBrandData',method:'get',valueField:'id',textField:'name',panelHeight:'200',loadFilter:function(data){return data.datas}," 
		    style="line-height: 20px; width: 120px; border: 1px solid #ccc">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	</div>
	<table id="pp_spu_list_dg" 
	    class="easyui-datagrid admin-user-form" 
		style="width:100%;" 
		url="${pageContext.request.contextPath}/spu/queryAllData"
		pagination="true" 
		singleSelect="true" 
		fitColumns="false" 
		method='get' 
		checkOnSelect="true"
		data-options="rownumbers:true,loadFilter:pp_spu_list_dg,nowrap:false,striped:true,remoteSort:true">
		<thead>
			<tr>
				<%--<th data-options="field:'ck',checkbox:true"></th>--%>
				<th data-options="field:'id',hidden:true,width:80,align:'center'">id</th>
				<th data-options="field:'state',width:80,align:'center'">状态</th>
				<th data-options="field:'super_classify_id',hidden:true"></th>
				<th data-options="field:'super_classify_name',width:150,align:'left'">一级级分类</th>
				<th data-options="field:'classify_id',hidden:true"></th>
				<th data-options="field:'classify_name',width:150,align:'left'">二级分类</th>
				<th data-options="field:'second_classify_id',hidden:true"></th>
				<th data-options="field:'second_classify_name',width:150,align:'left'">三级分类</th>
				<th data-options="field:'brandId',hidden:true"></th>
				<th data-options="field:'brand_name',width:200,align:'left'">品牌</th>
				<th data-options="field:'key_attr_name_values',width:180,align:'center'">公共属性</th>
				<th data-options="field:'sale_attr_name_values',width:100,align:'center'">销售属性</th>
				<th data-options="field:'spuName',width:300,align:'center'">显示名称</th>
				<%-- <shiro:hasAnyRoles name="${superAdmin },${saleManager},${productManager}">
					<th data-options="field:'action',width:100,align:'center'" formatter="formatAction">操作</th>
				</shiro:hasAnyRoles> --%>
			</tr>
		</thead>
	</table>
	
		<!-- 产品经理发布产品时填写库存的对话框 -->
	<div id="exportNewSku" class="easyui-window" data-options="modal:true" closed="true" style="width: 380px; height: 230px; padding: 10px"
	title="批量上传产品">
	<form id="importForm" enctype="multipart/form-data"  onsubmit="return false;" action="${pageContext.request.contextPath}/excel/importNewSpuProduct">
		<div style="margin-bottom:20px">
			<div>选择上传文件:</div>
			<input name="file" id="file" type="file"  style="width:100%">
		</div>
			<p style="color: red">以后所有库存都不导入，全为0</p>
<!-- 		<div style="margin-bottom:20px">
			<div>选择是否是清潋品牌:</div>
			<select id="whether" name="whether" class="easyui-combobox typeText" style="width: 100%">
						<option value="2">否</option>
						<option value="1">是</option>
			</select>
		</div> -->
		<div class="modal-footer" style="text-align: center; margin: auto;">
			<button class="btn" onclick="closeNewDialog()">关闭</button>
			<button class="btn" id="saveNewBtn"  onclick="saveNewDialog()">保存</button>
		</div>
		</form>
	</div>
	
	<div id="importUpdateSku" class="easyui-window" data-options="modal:true" closed="true" style="width: 380px; height: 230px; padding: 10px"
	title="批量修改产品">
	<form id="importUpdateForm" enctype="multipart/form-data"  onsubmit="return false;" action="${pageContext.request.contextPath}/excel/importUpdateSpuProduct">
		<div style="margin-bottom:20px">
			<div>选择上传文件:</div>
			<input name="updateFile" id="updateFile" type="file"  style="width:100%">
		</div>
		<p style="color: red">以后所有库存都不导入，全为0</p>
	<!-- 	<div style="margin-bottom:20px">
			<div>选择是否是清潋品牌:</div>
			<select id="isQinglianPro" name="whether" class="easyui-combobox typeText" style="width: 100%">
						<option value="2">否</option>
						<option value="1">是</option>
			</select>
		</div> -->
		<div class="modal-footer" style="text-align: center; margin: auto;">
			<button class="btn" onclick="closeUpdateDialog()">关闭</button>
			<button class="btn" id="saveUpdateBtn" onclick="saveUpdateDialog()">保存</button>
		</div>
		</form>
	</div>
	
	
	</body>
	
	<script type="text/javascript">
		// $.messager.confirm();
		var list = $("#pp_spu_list_dg"),
			stateText=['下架','禁用',"上架"];
		function changeState(id,state){
			$.messager.confirm("提示","是否确认"+stateText[state],function(e){
				if(state===2){
					state=0;
				}else if(state===0){
					state=2;	
				}
				if(e){
					$.get("${pageContext.request.contextPath}/spu/spuUpOrDown",{
						spu_id:id,
						state:state,
					}).then(function(res){
						$.messager.alert("info","修改成功");
						// doSearch();
						list.datagrid("reload");
					}).fail(function(res){
						$.messager.alert("err","修改失败");
					});
				}
			});
		}
		function add_spu_div_open() {
			window.parent.closeTab("SPU管理");
			window.parent.showTabs("${pageContext.request.contextPath}/maint/spuDatagridOpen", "SPU管理");
		}
		//修改当前行
		function edit_attr_name() {
			var row = $("#pp_spu_list_dg").datagrid('getSelected');
			if (row) {
				window.parent.closeTab("SPU管理");
				window.parent.showTabs("${pageContext.request.contextPath}/maint/spuDatagridOpen?id=" + row.id, "SPU管理");
			} else {
				$.messager.alert("警告","必须有选中一个有效的行");
			}
		}

		function delete_attr_name(){
			var row=list.datagrid('getSelected');
			if (row) {
				changeState(row.id,1);
			} else {
				$.messager.alert("警告","必须有选中一个有效的行");
			}
		}

		//刷新
		function doSearch() {
			list.datagrid("load", {
				spu_brand_id:$("#brandNameSearch").combobox("getValue"),
				product_number:$("#skuNumber").val(),
				manufacturing_code:$("#manufacturing").val(),
				spu_name:$("#spu_name").val()
			});	
		}

		
	</script>
	<script type="text/javascript">
		/*产品经理对产品状态进行管理*/
		function formatAction(value, row, index) {
			if (row.status == 'new') {
				return '<a href="#" onclick="changeState(\'putAway\',this)">上架</a> ';
			} else if (row.status == 'putaway') {
				return '<a href="#" onclick="changeState(\'offShelf\',this)">下架</a> ';
			} else if (row.status == 'offshelf') {
				return '<a href="#" onclick="changeState(\'putAway\',this)">上架</a> ';
			}
		}
		// function changeState
		var pp_spu_list_dg = new MTVACOMP.DataGrid($('#pp_spu_list_dg'));
		pp_spu_list_dg.setGridType(new MTVACOMP.NomalGrid());
		//0是上架 显示的时候应该点击上级 反向的
		
		pp_spu_list_dg = function (data) {
			var formatData = {
				row:[],
				total:0
			},
			spuList = data.content||[];
			formatData.rows=spuList.map(function(item,index){
				item.state='<a href="javascript:changeState('+item.id+","+item.state+')">'+stateText[item.state]+'</a>';
				return item;
			});
			formatData.total =  data.totalElements;
			return formatData;
		}
		function closeUpdateDialog(){
			//$("#isQinglianPro").combobox("setValue","");
			$("#updateFile").val("");
			$('#importUpdateSku').window('close');
		}
		function importUpdateExcel(){
			$('#importUpdateSku').window('open');
		}
		function saveUpdateDialog(){
			$.messager.confirm("警告", "是否将当前上传的表格，覆盖现有平台上此品牌所有的数据，上传后平台此品牌数据将以此表格为基准，以前的数据无法找回，此操作非常的危险！！！", function(state) {
				if(state){
					$.messager.confirm("警告", "你已经确认覆盖平台此品牌所有的数据，以前的数据无法找回，此操作非常的危险，想清楚了吗？", function(state) {
						if(state){
							$("#saveUpdateBtn").attr("disabled",true);
							var formData = new FormData($("#importUpdateForm")[0]);
							$.ajax({
							  url: "${pageContext.request.contextPath}/excel/importUpdateSpuProduct",
							  type: "POST",
							  data: formData,
							  processData: false,  // 不处理数据
							  contentType: false,   // 不设置内容类型
							  success:function(data){
								  if(data.state !="success"){
									  $.messager.alert("警告",data.msg+",错误码为:"+data.state);
								  }else{
									  $.messager.alert("警告","上传成功，请刷新查看数据");
									  closeUpdateDialog();
									  $("#pp_spu_list_dg").datagrid("reload");
								  }
								  $("#saveUpdateBtn").attr("disabled",false);
							  }
							});
						}
					});
				}
			});
		}
		
		
		function importNewExcel(){
			$('#exportNewSku').window('open');
		}
		function clearNewDiglog(){
			//$("#isQinglian").combobox("setValue","");
			$("#file").val("");
		}
		function saveNewDialog(){
			$("#saveNewBtn").attr("disabled",true);
			var formData = new FormData($("#importForm")[0]);
			$.ajax({
			  url: "${pageContext.request.contextPath}/excel/importNewSpuProduct",
			  type: "POST",
			  data: formData,
			  processData: false,  // 不处理数据
			  contentType: false,   // 不设置内容类型
			  success:function(data){
				  if(data.state !="success"){
					  $.messager.alert("警告",data.msg+",错误码为:"+data.state);
				  }else{
					  $.messager.alert("警告","上传成功，请刷新查看数据");
					  closeNewDialog();
					  $("#pp_spu_list_dg").datagrid("reload");
				  }
				  $("#saveNewBtn").attr("disabled",false);
			  }
			});
		}
		function closeNewDialog(){
			$('#exportNewSku').window('close');
			clearNewDiglog();
		}
	</script>