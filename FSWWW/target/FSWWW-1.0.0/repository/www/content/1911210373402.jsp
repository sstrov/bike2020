<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/stat2/resource/static/css/E-openAPI/E1.css">
<link rel="stylesheet" href="/stat2/resource/static/css/E-openAPI/E2.css">

<div class="box-e type2 border-top">
	<div class="title-e">1. 사용할 Open API 검색</div>
	<div class="text-e">
		세종통계데이터, 데이터셋 등에서 Open API로 사용할 공공데이터를 검색
	</div>
</div>
<div class="box-e type2">
	<div class="title-e">2. 검색결과에 따른 명세서 확인</div>
	<div class="text-e api-box">
		<div class="data-tab-content api">
			<!-- FIXME: 디자인 미확정 -->
			<div class="bg-box">
				<p>요청주소: https://www.sejong.go.kr/stat2/openapi/getList.do
				</p>
				<p>요청제한횟수: 제한없음</p>
			</div>
			<div class="table-box">
				<div class="title-b">기본인자
					<div class="api-btn">
						<button type="button" class="key-request-btn" title="새창열림" onclick="window.open('http://data.go.kr');">인증키 신청
							<img src="/stat2/resource/static/image/common/api-icon-02.png" alt="인증키신처 아이콘">
						</button>
						<!-- <button>명세서 다운로드<img src="/resource/static/image/common/api-icon-03.png" alt="다운로드 아이콘"></button> -->
					</div>
				</div>
				<table class="table table-bordered">
					<caption>
						<strong>Open API 검색결과에 따른 명세서 확인 - 기본인자</strong>
						<p>Open API 검색결과에 따른 명세서 확인 - 기본인자에 대한 표이며, 변수명, 타입, 변수 설명, 설명을 제공.</p>
					</caption>
					<colgroup>
						<col style="width:20%" />
						<col style="width:20%" />
						<col style="width:20%" />
						<col style="width:40%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="first">변수명</th>
							<th scope="col">타입</th>
							<th scope="col">변수 설명</th>
							<th scope="col" class="last">설명</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>apiKey</td>
							<td>STRING(필수)</td>
							<td>인증키</td>
							<td>기본값: smaple key</td>
						</tr>
						<tr>
							<td>type</td>
							<td>STRING(필수)</td>
							<td>호출문서(json)</td>
							<td>기본값: json</td>
						</tr>
						<tr>
							<td>tblId</td>
							<td>String(필수)</td>
							<td>통계표 아이디</td>
							<td>기본값: 없음(DT_20802N_006)</td>
						</tr>
						<tr>
							<td>maxSize</td>
							<td>INTEGER(필수)</td>
							<td>최대 조회 개수</td>
							<td>기본값: 100 (최대 1000)</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- <div class="table-box">
				<div class="title-b">요청인자</div>
				<table class="table table-bordered">
					<caption>요청인자 목록 - 변수명, 타입, 변수설명 제공</caption>
					<colgroup>
						<col style="width:20%" />
						<col style="width:20%" />
						<col style="width:60%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="first">변수명</th>
							<th scope="col">타입</th>
							<th scope="col" class="last">변수설명</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>BIZPLC_NM</td>
							<td>STRING(선택)</td>
							<td>사업장명</td>
						</tr>
					</tbody>
				</table>
			</div> -->

			<div class="api-tab-container">
				<div class="api-tab-name-box">
					<a href="#this" class="api-tab-name">출력값</a>
					<a href="#this" class="api-tab-name">샘플test</a>
					<a href="#this" class="api-tab-name">메시지 설명</a>
					<div class="dummy"></div>
				</div>
				<div class="api-tab-content">
					<!-- 출력값 시작 -->
					<h2 class="hide">출력값 내용 시작</h2>
					<div class="table-box">
						<table class="table table-bordered">
							<caption>
								<strong>검색결과에 따른 명세서 확인 - 출력값</strong>
								<p>검색결과에 따른 명세서 확인 - 출력값에 대한 설명표이며 No, 출력명, 출력성명을 제공.</p>
							</caption>
							<colgroup>
								<col style="width:20%" />
								<col style="width:20%" />
								<col style="width:60%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="first">No</th>
									<th scope="col">출력명</th>
									<th scope="col" class="last">출력 설명</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>tblId</td>
									<td>테이블 아이디</td>
								</tr>
								<tr>
									<td>2</td>
									<td>tblNm</td>
									<td>테이블 명</td>
								</tr>
								<tr>
									<td>3</td>
									<td>itmNm</td>
									<td>항목 명</td>
								</tr>
								<tr>
									<td>4</td>
									<td>itmId</td>
									<td>항목 아이디</td>
								</tr>
								<tr>
									<td>5</td>
									<td>dt</td>
									<td>통계 수치</td>
								</tr>
								<tr>
									<td>6</td>
									<td>prdDe</td>
									<td>통계 년월</td>
								</tr>
								<tr>
									<td>7</td>
									<td>prdSe</td>
									<td>시점 구분</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 출력값 끝 -->
				</div>
				<script>
					// OPENAPI 호출
					function apiSearch() {
						$.ajax({
							url     : "/stat2/openapi/getList.do?key=${ param.key }",
							type    : "POST",
							data    : {
								"apiKey" : "OGVkODMzZGNiODU4OWViNjI1M2JiZmQzYjU5YzY2N2Q",
								"tblId"  : $("input[name=\"apiTblId\"]").val(),
								"maxSize": 100
							},
							dataType: "json",
							error   : function(request, status, error) {
								alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
								lodingOff(document.body);
							},
							success:function(result) {
								$("#apiResult").html(JSON.stringify(result));
							}
						});
					}
					</script>
				<div class="api-tab-content">
					<!-- 샘플	test 시작 -->
					<h2 class="hide">샘플	test 내용 시작</h2>
					<div class="api-input">
						<p>tblId(필수)</p>
						<input type="text" title="tblId 검색어를 입력하세요" name="apiTblId" value="DT_20802N_006">
						<button type="button" onclick="apiSearch();">검색</button>
					</div>
					<div class="table-box">
						<table class="table table-bordered table-a">
							<caption>
								<strong>Open API 검색결과에 따른 명세서 확인 - 샘플test</strong>
								<p>Open API 검색결과에 따른 명세서 확인 - 샘플test에 대한 표이며, 구분, Open API 샘플테스트를 제공.<p>
							</caption>
							<colgroup>
								<col style="width:30%" />
								<col style="width:70%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="first">구분</th>
									<th scope="col" class="last">Open API 샘플테스트</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>요청주소</td>
									<td>https://www.sejong.go.kr/stat2/openapi/getList.do</td>
								</tr>
								<tr>
									<td>API 결과</td>
									<td>
										<div id="apiResult" class="result"></div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 샘플	test 끝 -->
				</div>
				<div class="api-tab-content">
					<!-- 메시지 설명 시작 -->
					<h2 class="hide">메시지 설명 내용 시작</h2>
					<div class="table-box">
						<table class="table table-bordered table-a">
							<caption>
								<strong>Open API 검색결과에 따른 명세서 확인 - 메시지설명</strong>
								<p>Open API 검색결과에 따른 명세서 확인 - 메시지설명에 대한 표이며, 구분, 코드, 설명을 제공.<p>
							</caption>
							<colgroup>
								<col style="width:20%" />
								<col style="width:20%" />
								<col style="width:60%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="first">구분</th>
									<th scope="col">코드</th>
									<th scope="col" class="last">설명</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>ERROE</td>
									<td>300</td>
									<td class="align-left">필수 값이 누락되어 있습니다. 요청인자를 참고 하십시오</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>290</td>
									<td class="align-left">인증키가 유효하지 않습니다. 인증키가 없는 경우, 홈페이지에서 인증키를 신청하십시오</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>310</td>
									<td class="align-left">해당하는 서비스를 찾을 수 없습니다. 요청인자 중 SERVICE를 확인하십시오</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>333</td>
									<td class="align-left">요청위치 값의 타입이 유효하지 않습니다. 요청위치 값은 정수를 입력하세요.</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>336</td>
									<td class="align-left">데이터요청은 한번에 최대 1,000건을 넘을 수 없습니다.</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>337</td>
									<td class="align-left">일별 트래픽 제한을 넘은 호출입니다. 오늘은 더이상 호출할 수 없습니다</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>500</td>
									<td class="align-left">서버 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다.</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>600</td>
									<td class="align-left">데이터베이스 연결 오류입니다. 지속적으로 발생기 홈페이지로 문의(Q&A) 바랍니다</td>
								</tr>
								<tr>
									<td>ERROE</td>
									<td>601</td>
									<td class="align-left">SQL 문장 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다.</td>
								</tr>
								<tr>
									<td>INFO</td>
									<td>000</td>
									<td class="align-left">정상 처리되었습니다</td>
								</tr>
								<tr>
									<td>INFO</td>
									<td>300</td>
									<td class="align-left">관리자에 의해 인증키 사용이 제한되었습니다</td>
								</tr>
								<tr>
									<td>INFO</td>
									<td>200</td>
									<td class="align-left">해당하는 데이터가 없습니다.</td>
								</tr>
							</tbody>
						</table>
						<div class="list-btn-box">
							<button class="list-btn">목록</button>
						</div>
					</div>
					<!-- 메시지 설명 끝 -->
				</div>
			</div>
			<!-- 인증키 신청 시작 -->
			<!-- <div include-html="../include/key-request.html" class="request-wrap"></div>
			<div include-html="../include/key-request-finish.html" class="request-wrap"></div> -->
			<!-- 인증키 신청 끝 -->
		</div>
	</div>
</div>
<div class="box-e type2">
	<div class="title-e">3. 인증키 발급 신청</div>
	<div class="text-e">
		디자인 미확정
	</div>
</div>
<div class="box-e type2">
	<div class="title-e">4. URL 등록</div>
	<div class="text-e">
		<p>Open API REST 요청 URL 예시</p>
		<div class="background-box">
			http://<span class="api-url" title="Open API URL">www.sejong.go.kr/stat2/openapi</span>
			<span class="api-name" title="Open API명">/...../getList.do?apiKey=</span>
			<span class="api-value" title="인증키값">[ 인증키 ]</span>&tblId=DT_20802N_006&maxSize=100
		</div>
		<ol>
			<li>
				Open API URL : 세종시 공공데이터의 Open API 주소는 http://www.sejong.go.kr/openapi/getList.do 입니다.
			</li>
			<li>
				Open API 명 : 세종시 공공데이터의 Open API 서비스는 고유명을 가지고 있습니다. 다운로드 받으신 명세표에 요청 주소가 표기되어 있습니다
			</li>
			<li>
				기본인자 : 기본인자를 생략하면 명세표의 기본값으로 결과를 표기합니다. 인증키 (KEY)는발급을 받으신 후 발급 받은 인증키를 추가하여야 합니다. 만약 인증키가 없다면 기본값은
				sampl 로 처리되어 5건 만 출력되므로 반드시 인증키를 입력하셔야 합니다. 호출문서 (Type) 은 xml 이나 json 등 출력하고자 하는 타입의 형태를 지정합니다. 기본값은
				xml 입니다.<br>
				데이터 수가 많은 경우에는 페이지 위치를 증가시키면서 여러 번에 나누어 호출하셔야 합니다. 세종시 공공데이터는 1 회 요청에 최대 1,000 건 까지 데이터를 제공하므로
				1~1,000 범위로 지정되어야 합니다.
			</li>
		</ol>
	</div>
</div>
<script src="/stat2/resource/static/js/A-stateData/api-tab.js"></script>