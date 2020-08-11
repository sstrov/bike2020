<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/library.jsp" %>

<%@ taglib uri="/WEB-INF/tld/fs-string.tld"   prefix="string" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="row">
	<!-- begin col-3 -->
	<div class="col-lg-4 col-md-6 col-sm-6">
		<div class="widget widget-stats bg-blue-grey">
			<div class="stats-title">전체 접속자 수</div>
			<div class="stats-number"><fmt:formatNumber type="number">${ tCount }</fmt:formatNumber></div>
			<div class="stats-desc">&nbsp;</div>
		</div>
	</div>

	<div class="col-lg-4 col-md-6 col-sm-6">
		<div class="widget widget-stats bg-blue">
			<div class="stats-title">오늘 접속자 수</div>
			<div class="stats-number"><fmt:formatNumber type="number">${ todateTCount }</fmt:formatNumber></div>
			<div class="stats-desc">&nbsp;</div>
		</div>
	</div>

	<div class="col-lg-4 col-md-12 col-sm-12">
		<div class="widget widget-stats bg-deep-purple">
			<div class="stats-title">금일 게시글 등록 수</div>
			<div class="stats-number"><fmt:formatNumber type="number">${ bbsToDateCount }</fmt:formatNumber></div>
			<div class="stats-desc">&nbsp;</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-8">
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<h4 class="panel-title">${ toMonth }월 접속자 통계표</h4>
			</div>
			<div class="panel-body">
				<div id="interactive-chart" class="height-sm"></div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-4">
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<h4 class="panel-title">${ toMonth }월 브라우저 점유율</h4>
			</div>
			<div class="panel-body">
				<div id="donut-chart" class="height-sm"></div>
			</div>
		</div>
	</div>
</div>
<script src="/resource/mngr/plugins/flot/jquery.flot.min.js"></script>
<script src="/resource/mngr/plugins/flot/jquery.flot.time.min.js"></script>
<script src="/resource/mngr/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="/resource/mngr/plugins/flot/jquery.flot.pie.min.js"></script>
<script>
$(document).ready(function() {
	<c:set var="maxVal" value="0" />
	
	var data1 = [
		<c:if test="${ !empty toStatList }">
			<c:forEach var="item" items="${ toStatList }" varStatus="i">
				<c:if test="${ !i.first }">,</c:if>
				[${ item.statsDate }, ${ item.tCount }]
				
				<c:if test="${ maxVal le item.tCount }">
					<c:set var="maxVal" value="${ item.tCount }" />
				</c:if>
			</c:forEach>
		</c:if>
	];
	var data2 = [
		<c:if test="${ !empty beStatList }">
			<c:forEach var="item" items="${ beStatList }" varStatus="i">
				<c:if test="${ !i.first }">,</c:if>
				[${ item.statsDate }, ${ item.tCount }]
				
				<c:if test="${ maxVal le item.tCount }">
					<c:set var="maxVal" value="${ item.tCount }" />
				</c:if>
			</c:forEach>
		</c:if>
	];
	var xLabel = [
		<c:forEach begin="1" end="31" step="1" varStatus="i">
			<c:if test="${ !i.first }">,</c:if>
			[${ i.count }, ${ i.count }]
		</c:forEach>
	];
	$.plot($("#interactive-chart"), [{
			data: data1, 
			label: "현재월 접속수", 
			color: "#348fe2",
			lines: { show: true, fill:false, lineWidth: 2 },
			points: { show: true, radius: 3, fillColor: "#FFFFFF" },
			shadowSize: 0
		}, {
			data: data2,
			label: '전월 접속수',
			color: "#00ACAC",
			lines: { show: true, fill:false, lineWidth: 2 },
			points: { show: true, radius: 3, fillColor: "#FFFFFF" },
			shadowSize: 0
		}], {
			xaxis: {  ticks:xLabel, tickDecimals: 0, tickColor: "rgba(45, 53, 60, 0.2)" },
			yaxis: {  ticks: 10, tickColor: "rgba(45, 53, 60, 0.2)", min: 0, max: ${ maxVal + 50 } },
			grid: { 
			hoverable: true, 
			clickable: true,
			tickColor: "rgba(45, 53, 60, 0.2)",
			borderWidth: 1,
			backgroundColor: 'transparent',
			borderColor: "rgba(45, 53, 60, 0.2)"
		},
		legend: {
			labelBoxBorderColor: "rgba(45, 53, 60, 0.2)",
			margin: 10,
			noColumns: 1,
			show: true
		}
	});
	var previousPoint = null;
	$("#interactive-chart").bind("plothover", function (event, pos, item) {
		$("#x").text(pos.x.toFixed(2));
		$("#y").text(pos.y.toFixed(2));
		if (item) {
			if (previousPoint !== item.dataIndex) {
				previousPoint = item.dataIndex;
				$("#tooltip").remove();
				var y = item.datapoint[1].toFixed(2);

				var content = item.series.label + " " + y;
				showTooltip(item.pageX, item.pageY, content);
			}
		} else {
			$("#tooltip").remove();
			previousPoint = null;
		}
		event.preventDefault();
	});
	
	var donutData = [
		<c:if test="${ !empty toBrowList }">
			<c:forEach var="item" items="${ toBrowList }" varStatus="i">
				<c:if test="${ !i.first }">,</c:if>
				{ label: "${ item.statsBrwsrNm }",  data: ${ item.tCount }}
			</c:forEach>
		</c:if>
	];
	$.plot('#donut-chart', donutData, {
		series: {
			pie: {
				innerRadius: 0.5,
				show: true,
				label: {
					show: true
				}
			}
		},
		legend: {
			show: true
		}
	});
});

function showTooltip(x, y, contents) {
	$('<div id="tooltip" class="flot-tooltip">' + contents + '</div>').css( {
		top: y - 45,
		left: x - 55
	}).appendTo("body").fadeIn(200);
}
</script>
</body>
</html>