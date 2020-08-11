<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/fs-xss.tld"                  prefix="xss" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resource/mngr/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="/resource/mngr/plugins/parsley/dist/parsley.js"></script>
<script src="/resource/mngr/plugins/parsley/src/i18n/ko.js"></script>

<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/resource/mngr/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resource/mngr/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>

<script src="/resource/mngr/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js"></script>
<script src="/resource/mngr/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

<script src="/resource/mngr/js/cmm/dates.js"></script>
<script src="/resource/mngr/js/cmm/utils.js"></script>
<script src="/resource/mngr/js/cmm/jquery_utils.js"></script>
<script>
var f         = document.forms['frm'];
var menuKey   = '${ param.key }';
var actionUrl = 'insert';
if(${action eq 'U'}){
	actionUrl = 'update';
}
var tableId = "";

$(document).ready(function() {
	jQuery('#frm').validate({
		submitHandler: function(form) {
			
			/* if($('#bbsCn').prop('tagName') != undefined) {
				if($('#bbsCn').attr('data-required') == "true") {
					if($('#bbsCn').summernote('isEmpty')) {
						alert("내용을 입력해 주세요.");
						return false;
					}
				}
			} */

			if(confirm("저장 하시겠습니까?")) {
				$('button[type="submit"]').prop('disabled', true);
				$('input').prop('disabled', false);

				lodingFixedOn("저장중 입니다.", 300, 180);

				f.action = '/${ admURI }/bbs/estbs/'+ actionUrl +'Ctgry.do?key=' + menuKey;
				f.submit();
			}
		}
	});
	
});

/**
 * 카테고리 추가
 */
function addCate() {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	var f = document.forms['frm'];

	if(isEmpty(f.cateNm)) {
		alert('카테고리 명은 필수 입력사항 입니다.');
		f.cateNm.focus();
		return false;
	}
	
	var num = $('#cateTable tbody tr').length + 1;

	var tableLayout = $('#cateTable tfoot').html();

	tableLayout = replaceAll(tableLayout, '{num}',  num);
	tableLayout = replaceAll(tableLayout, '{name}', f.cateNm.value);
	$('#cateTable tbody').append(tableLayout);
	
	f.cateNm.select();
	
	tableId = $('#cateTable').DataTable({
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

//제목 지정 삭제
function removeFix(obj) {
	if(tableId != "" && tableId != undefined) {
		tableId.destroy();
	}
	
	$(obj).parent().parent().remove();
	
	tableId = $('#cateTable').DataTable({
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
	f.action = '/${ admURI }/bbs/estbs/list.do?key=' + menuKey;
	f.submit();
}

function goForm() {
	f.action = '/${ admURI }/bbs/estbs/form.do?key=' + menuKey + '&bbsEstbsSn' + ${ bbsEstbsSn };
	f.submit();
}
</script>

</head>
<body>
<div class="row">
	<div class="col-md-12">
		<form:form id="frm" name="frm" method="post" commandName="searchVO" cssClass="form-horizontal form-bordered" data-parsley-validate="true">
	
			<input type="hidden" name="bbsCtgrySn" value="${ item.bbsCtgrySn }" />
			<input type="hidden" name="bbsEstbsSn" value="${ bbsEstbsSn }" />
			
			<div class="panel panel-inverse">
				<div class="panel-body panel-form">
					<div class="form-group">
						<table class="table table-striped table-bordered">
							<colgroup>
								<col >
								<col width="250">
							</colgroup>
							<thead>
								<tr>
									<th scope="row">서브 카테고리 명 *</th>
									<th scope="row">기능</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="text" name="cateNm" placeholder="서브 카테고리 명 입력" value="" class="form-control" />
									</td>
									<td>
										<button type="button" class="btn btn-default btn-sm" onclick="addCate();"><i class="fa fa-plus"></i> 추가</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				
					<div class="form-group">
						<table id="cateTable" data-reorder="true" class="table table-striped table-bordered">
							<colgroup>
								<col width="60">
								<col >
								<col width="80">
								<col width="80">
							</colgroup>
							<thead>
								<tr>
									<th>순서</th>
									<th scope="row">카테고리 명</th>
									<th scope="row">버튼</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ !empty cateList }">
									<c:forEach var="cate" items="${ cateList }" varStatus="idx">
										<tr>
											<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
											<td>
												<input type="hidden" name="bbsCtgrySubSn" value="${ cate.bbsCtgrySubSn }" />
												<input type="text" name="ctgrySubNm" class="form-control" placeholder="카테고리 명 입력" value="${ cate.ctgrySubNm }" />
											</td>
											<td>
												<button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
	
							<tfoot class="hide">
								<tr>
									<td style="text-align:center; vertical-align:middle;" class="f-s-600 text-inverse"><i class="fa fa-bars"></i></td>
									<td>
										<input type="hidden" name="bbsCtgrySubSn" value="" />
										<input type="text" name="ctgrySubNm" class="form-control" placeholder="카테고리 명 입력" value="{name}" />
									</td>
									<td>
										<button type="button" class="btn btn-danger btn-sm" onclick="removeFix(this);"><i class="fa fa-times"></i> 삭제</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<div class="col-md-6 col-sm-6">
							<button type="submit" class="btn btn-primary">저장</button>
							<button type="button" class="btn btn-default" onclick="goList();">목록</button>
							<button type="button" class="btn btn-default" onclick="goForm();">이전</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>