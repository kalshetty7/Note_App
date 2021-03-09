<%@ include file="header.jsp"%>




<body>
	<br>
	<div class="container">
		<div id="summernote"></div>
		<br>
		<div class="two-btns" style="margin-left: 40%">
			<button onclick="save()" class="btn btn-info btn-lg">Save</button>
			<button onclick="reset()" class="btn btn-warning btn-lg">Reset</button>
		</div>
	</div>


	<div class="container">
	<!-- 
		<label class="label label-default">Find Notes : </label> <input
			type="text" id="searchText" placeholder="Enter Search text" />
		<button onclick="find()" class="btn btn-info btn-lg">Find</button>
		<br><br><br>
 -->

		<!-- hh -->
<br><br>
		<div class="form-row">
		<div class="form-group mr-2">
				<label for="searchText"><h5>Find Notes : </h5></label> 
				</div>
				<div class="form-group mr-2">
				<input type="text" class="form-control" id="searchText" placeholder="Enter Search text" />
				</div>
				<div class="form-group mr-2">
				<button onclick="find()" class="btn btn-info">Find</button>
				</div>
		</div>
		<!-- hh -->
	</div>

	<div class="container">
		<c:if test="${not empty notes}">
			<table class="table table-bordered table-hover">
				<thead class="thead-light">
					<tr>
						<th>Sr.No.</th>
						<th>Title</th>
						<th><button onclick="deleteNotes()" class="btn btn-danger">Delete</button>
							<br /> <input type="checkbox" onclick="checkAll('dn')" /></th>
					</tr>
				</thead>
				<c:forEach items="${notes}" var="n" varStatus="c">
					<tr>
						<td>${c.count}</td>
						<td><a href="${pageContext.request.contextPath}/edit/${n.id}">${n.title}</a></td>
						<td><input type="checkbox" class="dn" nid="${n.id}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>