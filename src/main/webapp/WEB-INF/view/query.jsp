
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售数据查询</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="${pageContext.request.contextPath}/AdminLTE/bootstrap/css/bootstrap.min.css" media="all" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/plugins/datatables/dataTables.bootstrap.css">
    <link href="${pageContext.request.contextPath}/aliyunoss/css/jquery-confirm.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.btn{
			padding:3px 5px;
			margin-right:3px;
			margin-top:3px;
			height:26px;width:62px;
			font-size:12px;
		}
	</style>
</head>
<body style="background-color:#ecf0f5">
<div class="content">
<!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
           销售数据查询
            <small>销售数据查询</small>
        </h1>
      
    </section>
       <div class="box">
        <div class="box-header">
            <div class="row">
                <div class="col-sm-12">
                 <div class="col-sm-3" style="width: 200px">
                        <div class="dataTables_length" >
                        <label><span style="font-weight:bold;">省&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select id="province" name="province" aria-controls="example1" class="form-control input-sm"  style="width:110px;height:34px">
                                   	<option value="">--请选择省--</option>
                                </select>
                          </label>
                        </div>
                  </div>
                   <div class="col-sm-3" style="width: 200px">
                        <div class="dataTables_length" >
                         <label><span style="font-weight:bold;">市&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                 <select id="city" name="city" aria-controls="example1" class="form-control input-sm"  style="width:110px;height:34px">
                                    <option value="">--请选择市--</option>
                                </select>
                          </label>
                        </div>
                  </div>
                  <div class="col-sm-3" style="width: 200px">
                        <div class="dataTables_length" >
                            <label><span style="font-weight:bold;">销售员&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select name="salesman" id="salesman" aria-controls="example1" class="form-control input-sm"  style="width:110px;height:34px">
                                    <option value="">请选择销售员</option>
                                </select>
                            </label>
                        </div>
                  </div>
                  <div class="col-sm-3" style="width: 200px">
                        <div class="dataTables_length" >
                            <label><span style="font-weight:bold;">品类&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select name="classification" id="classification" aria-controls="example1" class="form-control input-sm"  style="width:110px;height:34px">
                                    <option value="">请选择品类</option>
                                   
                                </select>
                            </label>
                        </div>
                  </div>
                </div>
                <div class="col-sm-12">
                  
                      <div class="form-group">
                        <label  class="col-sm-3 control-label" style="text-align: right;margin-top:8px;margin-right:-15px;padding-left: 0px;;width:72px">订单日期</label>
                        <div class="col-sm-9" style="width: 15%;">
                          <input type="date" class="form-control" id="startTime"/>
                        </div>
                        <label  class="col-sm-3 control-label" style="text-align: right;margin-top:8px;margin-right:-15px;padding-left: 0px;;width:30px">至</label>
                        <div class="col-sm-9" style="width: 15%">
                          <input type="date" class="form-control" id="endTime"/>
                        </div>  
                  </div>
                  	<div class="btn-group" role="group" aria-label="..." style="float: left">
							<!-- 在这里添加了id  用于js提交 -->
							<button id = "submit" type="button" class="btn btn-primary">查询</button>
							
							<button type="button" style="margin-left:50px" id="btn_download" 
							class="btn btn-primary" onClick ="exportExcel()">导出excel</button>
						</div>
                </div>
            </div>
        </div>
        <div class="box-body">
        <!-- 如果用户列表非空 -->
        <table id="_tab" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>省</th>
                <th>市</th>
                <th>销售员</th>
                <th>品类</th>
                <th>数量</th>
                <th>金额</th>
            </tr>
            </thead>
            <tbody>

        </table>
        </div>
    </div>

</div>




<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/AdminLTE/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/AdminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/aliyunoss/js/jquery-confirm.min.js"></script>

<script src="${pageContext.request.contextPath}/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminLTE/plugins/fastclick/fastclick.js"></script>
<script>
$(function(){
	//加载销售员的信息
	$.ajax({
	      type: 'GET',
	      url: '${pageContext.request.contextPath}/getSalesmans',
	      contentType:'application/json;charset=UTF-8',
	       success: function (response) {
	    	   //遍历返回数据,并把里面的数据追加到option选项中
	    	    var salesman = document.getElementById("salesman");
	    	    for (var i=0; i<response.length; i++) {
	    			   if(response[i]!=null){
	    				   var option = document.createElement("option");
	    				   option.value = response[i].salesmanId;
	    				   option.innerHTML = response[i].salesman;
	    				   salesman.appendChild(option);
	    			   }
	    	    	}
				}
			});
	
	
	
	//加载品类的信息
 	$.ajax({
	      type: 'GET',
	      url: '${pageContext.request.contextPath}/getClassifications',
	      contentType:'application/json;charset=UTF-8',
	       success: function (response) {
	    	   //遍历返回数据,并把里面的数据追加到option选项中
	    	    var classification = document.getElementById("classification");
	           for (var i=0; i<response.length; i++) {
	               var option = document.createElement("option");
	               option.value = response[i].classificationId;
	               option.innerHTML = response[i].className;
	               classification.appendChild(option);
	           }
				}
			}); 
	//加载省数据
	$.ajax({
	      type: 'GET',
	      url: '${pageContext.request.contextPath}/getProvinces',
	      contentType:'application/json;charset=UTF-8',
	       success: function (response) {
	    	   //遍历省市数据,并把里面省的数据追加到option选项中
	           for (var i=0; i<response.length; i++) {
	               var option = document.createElement("option");
	               option.value = response[i].provincnId;
	               option.innerHTML = response[i].province;
	               province.appendChild(option);
	           }
				}
			});
	
	var province = document.getElementById("province");
	    //省级下拉框发生改变事件
	    province.onchange = function() {
	      var cities;
	      //遍历省市数据,把省级下点击的那一个选项的值和省市数据中的
	      //省级数据对比,如果相等,取出当前的市的数据
	      $.ajax({
	      type: 'GET',
	      url: '${pageContext.request.contextPath}/getCities?proid='+this.value,
	      contentType:'application/json;charset=UTF-8',
	       success: function (response) {
	    	
	      //获得市级下拉框对象
	      var city = document.getElementById("city");
	      //每次点击省级后,市级初始化,避免高级重复追加
	      city.innerHTML = "<option value=''>--请选择市--</option>";
	      //遍历市级数据,并取出市级数据,追加到市级对象中
	      for (var i=0; i<response.length; i++) {
	          var option = document.createElement("option");
	          option.innerHTML = response[i].city;
	          option.value = response[i].cityId;
	          city.appendChild(option);
	      }
				}
			});
	  
	    }
});


	
	var _tab;
	
	$(function () {
		//初始化表格
		var No=0;
		_tab = $('#_tab').DataTable( {
	        "paging": true,
	        "lengthChange": false,
	        "iDisplayLength":20,
	        "searching": false,
	        "ordering": false,
	        "info": true,
	        "autoWidth": false,
	        "scrollX": true,
	        "bInfo":false,
	        "serverSide":true,   //启用服务器端分页
	        "language":{"url":"${pageContext.request.contextPath}/AdminLTE/plugins/datatables/language.json"},
	        "ajax":{"url":"${pageContext.request.contextPath}/toQuery","type":"post"},
	        "columns": [
       
	            { "data":null },
	            { "data":null },
	            { "data":null },
	            { "data":null },
	            { "data":null },
	            { "data":null }
	        ],
	        "columnDefs": [
							{
								targets : 0,
								data : null,
								render : function(data) {

									return data.province;
								}
							},
							{
								targets : 1,
								data : null,
								render : function(data) {

									return data.city;
								}
							},
							{
								targets : 2,
								data : null,
								render : function(data) {

									return data.salesman;
								}
							}
							,
							{
								targets : 3,
								data : null,
								render : function(data) {

									return data.classification;
								}
							},
							{
								targets : 4,
								data : null,
								render : function(data) {
									return data.number;
								}
							},
							{
								targets : 5,
								data : null,
								render : function(data) {
									return data.price;
								}
							}]
	    } ).on('preXhr.dt',function(e,settings,data) {
			No=0;
	    } );
		//当你需要多条件查询，你可以调用此方法，动态修改参数传给服务器 ,
		window.reloadTable = function(oTable,province,city,salesman,classification,startTime,endTime) {
			var provinceId = $(province).val();
			var cityId = $(city).val();
			var salesmanId = $(salesman).val();
			var classificationId = $(classification).val();
			var startTime = $(startTime).val();
			var endTime = $(endTime).val();
			var param = {
				"provinceId" : provinceId,
				"cityId"  : cityId,
				"salesmanId"  : salesmanId,
				"classificationId"  : classificationId,
				"startTime"  : startTime,
				"endTime"  : endTime
			};
			var paramTo = {
				"param" : JSON.stringify(param)
			};
			oTable.settings()[0].ajax.data = paramTo;
			oTable.ajax.reload();
		}
		
		$("#submit").click(function() {
			window.reloadTable(_tab, "#province","#city", "#salesman","#classification","#startTime","#endTime");
		});
	 });

		/* 导出数据*/
		function exportExcel(){
			var provinceId = $("#province").val();
			var cityId = $("#city").val();
			var salesmanId = $("#salesman").val();
			var classificationId = $("#classification").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var param = {
				"provinceId" : provinceId,
				"cityId"  : cityId,
				"salesmanId"  : salesmanId,
				"classificationId"  : classificationId,
				"startTime"  : startTime,
				"endTime"  : endTime
			};
		
			var p  = JSON.stringify(param).replace("{","").replace("}","");
			
			window.location.href = "${pageContext.request.contextPath}/exportExcel?param="+p;
			
			/* window.location.href ="${pageContext.request.contextPath}/exportExcel?provinceId="+provinceId+
			"&cityId="+cityId+"&salesmanId="+salesmanId+"&classificationId="+classificationId+"&startTime="+startTime+"&endTime"; */
		}
	
</script>
</body>
</html>













