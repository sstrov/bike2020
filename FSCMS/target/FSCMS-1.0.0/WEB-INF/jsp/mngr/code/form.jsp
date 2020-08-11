<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<link href="/resource/mngr/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script>
var f         = document.forms['frm'];
var admURI    = "${ admURI }";
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
<c:if test="${ !empty item.codeId }">
	actionUrl = 'update';
</c:if>

var tableId = "";
$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
				if(actionUrl == 'insert') {
					if(form.idCheck.value == "0") {
						alert("코드 아이디를 체크해 주세요.");
						form.codeId.focus();
						return false;
					}
				}
				
				$('input:text[name="codeDetailId"]').css('background-color', '#fff');
				$('input:text[name="codeDetailId"]').css('color', '#555');
				
				var flag = true;
				$('input:text[name="codeDetailId"]').each(function() {
					var inputs = $('input:text[name="codeDetailId"]');
					var index  = inputs.index(this);
					var val    = $(this).val();
					var obj    = this;
					
					$('input:text[name="codeDetailId"]').each(function(idx, item) {
						if(idx != index && $(this).val() == val) {
							$(obj).css('background-color', 'red');
							$(obj).css('color', '#fff');
							flag = false;
						}
					});
				});
				
				if(!flag) {
					alert('동일한 코드값은 등록하실 수 없습니다.');
					return false;
				}
	
				if(flag && confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/code/' + actionUrl + '.do?key=' + menuKey;
					f.submit();
				}
			}
		});
	
		// 공통코드 아이디 체크
		$("#codeId").keyup(function() {
			var f = document.forms["frm"];
			var altImg = "<strong>경고:</strong>";
			var okImg = "<strong>Success:</strong>";
			
			if(!codeIdCheck(this.value)) {
				$("#idStatus").html(altImg + " 유효하지 않은 공통코드 아이디 입니다. (예 : SIT001)");
				$("#idStatus").css("color", "red");
				f.idCheck.value = "0";
				return;
			}
			
			// 아이디 정보 조회
			$.ajax({
				url     : "/${ admURI }/code/isExistId.do?key=${ param.key }",
				type    : "POST",
				data    : {
					"codeId": this.value
				},
				dataType: "json",
				error   : function(request, status, error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
				success:function(result) {
					var item = result[0];
					if(item.msg != '') {
						$("#idStatus").html(altImg + item.msg);
						$("#idStatus").css("color", "red");
						f.idCheck.value = "0";
						return;
					} else {
						$("#idStatus").html(okImg + " 사용가능한 분류코드 입니다.");
						$("#idStatus").css("color", "blue");
						f.idCheck.value = "1";
					}
				}
			});
		});
		
		tableId = $('#datatable').DataTable({
			paging: false,
			searching: false,
			ordering: false,
			rowReorder: {
				update : false
			},
			language: {
				infoEmpty: ""
			}
		});
	</c:if>
});

function codeIdCheck(v) {
	var regularID = /^[A-Z]+[A-Z]+[A-Z]+[0-9]{3}$/;
	return regularID.test(v);
}

// 상세코드 추가
function addDti() {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	var str = $('#datatable tfoot').html();
	str = replaceAll(str, 'tmp_', '');

	$('#tgList').append(str);
	
	tableId = $('#datatable').DataTable({
		paging: false,
		searching: false,
		ordering: false,
		rowReorder: {
			update : false
		},
		language: {
			infoEmpty: ""
		}
	});
}

// 상세코드 삭제
function delDti(obj) {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	$(obj).parent().parent().remove();
	
	tableId = $('#datatable').DataTable({
		paging: false,
		searching: false,
		ordering: false,
		rowReorder: {
			update : false
		},
		language: {
			infoEmpty: ""
		}
	});
}

// 목록 이동
function goList() {
	f.action = '/${ admURI }/code/list.do?key=' + menuKey;
	f.submit();
}
</script>

<!-- 분류코드 정보 조회 -->
<jsp:include page="/WEB-INF/jsp/mngr/code/_inc_clSearch.jsp" />
</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
			<c:if test="${ !empty searchVO.fieldList }">
				<c:forEach var="sec" items="${ searchVO.fieldList }">
					<form:hidden path="${ sec.name }" />
				</c:forEach>
			</c:if>

			<input type="hidden" name="idCheck" value="0" />
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">분류코드 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="hidden" id="codeClId" name="codeClId" value="${ item.codeClId }" />
							<input type="text" id="codeClNm" name="codeClNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="${ item.codeClId }<c:if test="${ !empty item.codeClNm }"> (${ item.codeClNm })</c:if>" placeholder="분류코드 검색" readonly data-parsley-required="true" style="float:left; width:180px;" />
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="button" class="btn btn-success" onclick="openClForm();"><i class="fa fa-search"></i> 분류코드 검색</button>
							</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">코드 아이디 * :</label>
						<div class="col-md-6 col-sm-6">
							<c:choose>
								<c:when test="${ !empty item.codeId }">
									<input type="text" id="codeId" name="codeId" value="${ item.codeId }" class="form-control" disabled />
								</c:when>
								<c:otherwise>
									<input type="text" id="codeId" name="codeId" class="form-control" placeholder="코드 아이디 입력" data-parsley-required="true" data-parsley-minlength="6" maxlength="6" />
									<p id="idStatus" class="note"><strong>Note:</strong> 코드 아이디는 영문 대문자 3자리 + 숫자 3자리 조합으로 입력해 주세요. (예 : SIT001)</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">코드 명 * :</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" id="codeNm" name="codeNm" value="${ item.codeNm }" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> data-parsley-required="true" placeholder="코드 명 입력" maxlength="15" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">코드 설명 :</label>
						<div class="col-md-6 col-sm-6">
							<textarea id="codeDc" name="codeDc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> placeholder="코드 설명 입력" rows="3" maxlength="200">${ item.codeDc }</textarea>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">사용여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="useAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="Y" <c:if test="${ item.useAt eq 'Y' }">selected</c:if>>사용</option>
								<option value="N" <c:if test="${ item.useAt eq 'N' }">selected</c:if>>사용안함</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">저장</button>
							</c:if>
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
						</div>
					</div>
				
				</div>
			</div>
			
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">상세코드 등록 폼</h4>
					<small>* 항목이 없으면 해당항목은 저장되지 않습니다.</small>
				</div>
				<div class="panel-body panel-form">

					<div class="form-group">
						<div class="col-md-12 col-sm-12">
							<div class="table-responsive">
								<table id="datatable" data-reorder="true" class="table table-striped table-bordered">
									<colgroup>
										<col width="60">
										<col width="200">
										<col width="300">
										<col >
										<col width="80">
									</colgroup>
									<thead>
										<tr>
											<th>순서</th>
											<th scope="row">코드 값 *</th>
											<th scope="row">코드 명 *</th>
											<th scope="row">코드 설명</th>
											<th scope="row"><button type="button" class="btn btn-success" style="padding-top:0; padding-bottom:0;" onclick="addDti();" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>추가</button></th>
										</tr>
									</thead>
									<tbody id="tgList">
										<c:choose>
											<c:when test="${ !empty detailList }">
												<c:forEach var="dti" items="${ detailList }">
													<tr>
														<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
														<td>
															<input type="hidden" name="codeDetailSn" value="${ dti.codeDetailSn }" />
															<input type="text" name="codeDetailId" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="${ dti.codeDetailId }" maxlength="15" />
														</td>
														<td>
															<input type="text" name="codeDetailNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="${ dti.codeDetailNm }" />
														</td>
														<td>
															<input type="text" name="codeDetailDc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="${ dti.codeDetailDc }" />
														</td>
														<td><button type="button" class="btn btn-danger" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="delDti(this);">삭제</button></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
													<td>
														<input type="hidden" name="codeDetailSn" value="" />
														<input type="text" name="codeDetailId" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="" maxlength="15" />
													</td>
													<td>
														<input type="text" name="codeDetailNm" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="" />
													</td>
													<td>
														<input type="text" name="codeDetailDc" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> value="" />
													</td>
													<td><button type="button" class="btn btn-danger" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if> onclick="delDti(this);">삭제</button></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>

									<tfoot class="hide">
										<tr>
											<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
											<td>
												<input type="hidden" name="tmp_codeDetailSn" value="" />
												<input type="text" name="tmp_codeDetailId" class="form-control" value="" maxlength="15" />
											</td>
											<td>
												<input type="text" name="tmp_codeDetailNm" class="form-control" value="" />
											</td>
											<td>
												<input type="text" name="tmp_codeDetailDc" class="form-control" value="" />
											</td>
											<td><button type="button" class="btn btn-danger" onclick="delDti(this);">삭제</button></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<c:if test="${ !empty role && role.powW eq 'Y' }">
								<button type="submit" class="btn btn-primary">저장</button>
							</c:if>
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>