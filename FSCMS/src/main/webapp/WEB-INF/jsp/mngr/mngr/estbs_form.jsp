<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>

<script>
var f = document.forms['frm'];

$(document).ready(function() {
	<c:if test="${ !empty role && role.powW eq 'Y' }">
		jQuery('#frm').validate({
			submitHandler: function(form) {
	
				if(confirm("저장 하시겠습니까?")) {
					$('button[type="submit"]').prop('disabled', true);
					$('input').prop('disabled', false);
	
					lodingFixedOn("저장중 입니다.", 300, 180);
	
					f.action = '/${ admURI }/mngr/estbs/save.do?key=${ param.key }';
					f.submit();
				}
			}
		});
	</c:if>
});

// 아이피 추가
function addIp(id) {
	var str = $('#' + id + ' tfoot').html();
	str = replaceAll(str, 'tmp_', '');

	$('#' + id + ' #tgList').append(str);
}
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
	<div class="row">
		<div class="col-md-12">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">등록 폼</h4>
				</div>
				<div class="panel-body panel-form">
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">아이피 차단 여부 * :</label>
						<div class="col-md-6 col-sm-6">
							<select name="ipIntrcpAt" class="form-control" <c:if test="${ empty role || role.powW ne 'Y' }">disabled</c:if>>
								<option value="Y" <c:if test="${ item.ipIntrcpAt eq 'Y' }">selected</c:if>>접근 차단</option>
								<option value="N" <c:if test="${ item.ipIntrcpAt eq 'N' }">selected</c:if>>차단 안함</option>
							</select>
						</div>
					</div>

					<c:if test="${ !empty role && role.powW eq 'Y' }">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4"></label>
							<div class="col-md-6 col-sm-6">
								<button type="submit" class="btn btn-primary">저장</button>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 class="panel-title">허용 아이피 등록 폼</h4>
					<small>* 허용할 아이피정보를 입력해 주세요. (아이피 차단 여부가 접근 차단일 시 적용됨)</small>
				</div>
				<div class="panel-body panel-form">

					<div class="form-group">
						<div class="col-md-12 col-sm-12">
							<div class="table-responsive">
								<table id="datatable1" class="table table-striped table-bordered">
									<colgroup>
										<col width="200">
										<col >
										<col width="80">
									</colgroup>
									<thead>
										<tr>
											<th scope="row">아이피</th>
											<th scope="row">설명</th>
											<th scope="row"><button type="button" class="btn btn-success" style="padding-top:0; padding-bottom:0;" onclick="addIp('datatable1');">추가</button></th>
										</tr>
									</thead>
									<tbody id="tgList">
										<c:choose>
											<c:when test="${ !empty ipList }">
												<c:forEach var="ip" items="${ ipList }">
													<c:if test="${ ip.ipEstbsTy eq '1' }">
														<tr>
															<td>
																<input type="hidden" name="ipEstbsSn" value="${ ip.ipEstbsSn }" />
																<input type="hidden" name="ipEstbsTy" value="1" />
																<input type="text" name="registIp" class="form-control" value="${ ip.registIp }" maxlength="20" onkeyup="ipCheck(this);" />
															</td>
															<td>
																<input type="text" name="ipEstbsDc" class="form-control" value="${ ip.ipEstbsDc }" maxlength="40" />
															</td>
															<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
														</tr>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td>
														<input type="hidden" name="ipEstbsSn" value="" />
														<input type="hidden" name="ipEstbsTy" value="1" />
														<input type="text" name="registIp" class="form-control" value="" maxlength="20" onkeyup="ipCheck(this);" />
													</td>
													<td>
														<input type="text" name="ipEstbsDc" class="form-control" value="" maxlength="40" />
													</td>
													<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>

									<tfoot class="hide">
										<tr>
											<td>
												<input type="hidden" name="tmp_ipEstbsSn" value="" />
												<input type="hidden" name="tmp_ipEstbsTy" value="1" />
												<input type="text" name="tmp_registIp" class="form-control" value="" maxlength="20" onkeyup="ipCheck(this);" />
											</td>
											<td>
												<input type="text" name="tmp_ipEstbsDc" class="form-control" value="" maxlength="40" />
											</td>
											<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h4 class="panel-title">차단 아이피 등록 폼</h4>
					<small>* 등록된 아이피는 어떤경우에도 접근불가 (허용 아이피 등록 보다 우선시 됨)</small>
				</div>
				<div class="panel-body panel-form">

					<div class="form-group">
						<div class="col-md-12 col-sm-12">
							<div class="table-responsive">
								<table id="datatable2" class="table table-striped table-bordered">
									<colgroup>
										<col width="200">
										<col >
										<col width="80">
									</colgroup>
									<thead>
										<tr>
											<th scope="row">아이피</th>
											<th scope="row">설명</th>
											<th scope="row"><button type="button" class="btn btn-success" style="padding-top:0; padding-bottom:0;" onclick="addIp('datatable2');">추가</button></th>
										</tr>
									</thead>
									<tbody id="tgList">
										<c:choose>
											<c:when test="${ !empty ipList }">
												<c:forEach var="ip" items="${ ipList }">
													<c:if test="${ ip.ipEstbsTy eq '2' }">
														<tr>
															<td>
																<input type="hidden" name="ipEstbsSn" value="${ ip.ipEstbsSn }" />
																<input type="hidden" name="ipEstbsTy" value="2" />
																<input type="text" name="registIp" class="form-control" value="${ ip.registIp }" maxlength="20" onkeyup="ipCheck(this);" />
															</td>
															<td>
																<input type="text" name="ipEstbsDc" class="form-control" value="${ ip.ipEstbsDc }" maxlength="40" />
															</td>
															<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
														</tr>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td>
														<input type="hidden" name="ipEstbsSn" value="" />
														<input type="hidden" name="ipEstbsTy" value="2" />
														<input type="text" name="registIp" class="form-control" value="" maxlength="20" onkeyup="ipCheck(this);" />
													</td>
													<td>
														<input type="text" name="ipEstbsDc" class="form-control" value="" maxlength="40" />
													</td>
													<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>

									<tfoot class="hide">
										<tr>
											<td>
												<input type="hidden" name="tmp_ipEstbsSn" value="" />
												<input type="hidden" name="tmp_ipEstbsTy" value="2" />
												<input type="text" name="tmp_registIp" class="form-control" value="" maxlength="20" onkeyup="ipCheck(this);" />
											</td>
											<td>
												<input type="text" name="tmp_ipEstbsDc" class="form-control" value="" maxlength="40" />
											</td>
											<td><button type="button" class="btn btn-danger" onclick="$(this).parent().parent().remove();">삭제</button></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>