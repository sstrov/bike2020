---------------------------------------------------------------------
-- FSCMS v1.0 데이터 등록
-- 2020.01.21
---------------------------------------------------------------------

---------------------------------------------------------------------
-- e-지방지표 자료 등록
---------------------------------------------------------------------

-- 통계조사등록
INSERT INTO TN_STAT VALUES ('SJEXA02', 'e-지방지표');

-- 통계분야 등록
INSERT INTO TN_REALM VALUES ('REXA101', 'SJEXA02', '', '인구', 1, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA102', 'SJEXA02', '', '가족', 2, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA103', 'SJEXA02', '', '건강', 3, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA104', 'SJEXA02', '', '교육', 4, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA105', 'SJEXA02', '', '소득과 소비', 5, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA106', 'SJEXA02', '', '고용과 노동', 6, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA107', 'SJEXA02', '', '주거와 교통', 7, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA108', 'SJEXA02', '', '문화와 여가', 8, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA109', 'SJEXA02', '', '성장과 안정', 9, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA110', 'SJEXA02', '', '안전', 10, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA111', 'SJEXA02', '', '환경', 11, 'Y', '20200121', 'SYS', '20200121', 'SYS');
INSERT INTO TN_REALM VALUES ('REXA112', 'SJEXA02', '', '사회통합', 12, 'Y', '20200121', 'SYS', '20200121', 'SYS');

-- 통계표 그룹 (인구) INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', '', 1, '20200121', 'SYS');
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20631', 1, '20200121', 'SYS');			-- 고령인구비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20701', 2, '20200121', 'SYS');			-- 남녀성비
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL12501E', 3, '20200121', 'SYS');		-- 노령화지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1EA1011_01', 4, '20200121', 'SYS');		-- 농가인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21261', 5, '20200121', 'SYS');			-- 등록외국인 현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21271', 6, '20200121', 'SYS');			-- 인구 천명당 외국인수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B80A18', 7, '20200121', 'SYS');			-- 사망률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B82A01', 8, '20200121', 'SYS');			-- 사망자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B26001_A021', 9, '20200121', 'SYS');	-- 순이동인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ZB7024', 10, '20200121', 'SYS');		-- 어가인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20621', 11, '20200121', 'SYS');		-- 인구증가율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1IN1503_01', 12, '20200121', 'SYS');		-- 인구총조사 인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B26001_A022', 13, '20200121', 'SYS');	-- 전입인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B26001_A023', 14, '20200121', 'SYS');	-- 전출인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20651E', 15, '20200121', 'SYS');		-- 주민등록인구
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1BPB001E', 16, '20200121', 'SYS');		-- 추계인구(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1BPB002E', 17, '20200121', 'SYS');		-- 추계인구(시도/시/군/구)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B81A01', 18, '20200121', 'SYS');		-- 출생아수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1IN1503_02', 19, '20200121', 'SYS');		-- 평균연령
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B83A10', 20, '20200121', 'SYS');		-- 평균 재혼연령
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B83A09', 21, '20200121', 'SYS');		-- 평균 초혼연령
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B81A17', 22, '20200121', 'SYS');		-- 합계출산율

-- 통계표 그룹 (가족)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21161', 1, '20200121', 'SYS');			-- 1인가구비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSFA139R', 2, '20200121', 'SYS');		-- 가족관계 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL12701', 3, '20200121', 'SYS');			-- 독거노인가구비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSFA021R', 4, '20200121', 'SYS');		-- 부모 생활비 주 제공자
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSWE011R', 5, '20200121', 'SYS');		-- 생활여건의변화
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20951', 6, '20200121', 'SYS');			-- 유아 천명당 보육시설수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B8000I_01', 7, '20200121', 'SYS');		-- 조이혼율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B8000I_02', 8, '20200121', 'SYS');		-- 조혼이율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B83A35', 9, '20200121', 'SYS');			-- 혼인건수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1B83A23', 10, '20200121', 'SYS');		-- 혼인형태

-- 통계표 그룹 (건강)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21021E', 1, '20200121', 'SYS');		-- EQ-5D
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL11001E', 2, '20200121', 'SYS');		-- 결핵신고 신환자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20291E', 3, '20200121', 'SYS');		-- 고위험음주율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSHE020R', 4, '20200121', 'SYS');		-- 규칙적 운동 실천율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL0301E', 5, '20200121', 'SYS');			-- 기대여명
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21031E', 6, '20200121', 'SYS');		-- 비만율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21041E', 7, '20200121', 'SYS');		-- 스트레스 인지율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20991E', 8, '20200121', 'SYS');		-- 음주율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20971', 9, '20200121', 'SYS');			-- 인구 천명당 의료기관병상수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL8101E', 10, '20200121', 'SYS');		-- 의료기관 수술인원
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSHE102R', 11, '20200121', 'SYS');		-- 의료서비스 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20981', 12, '20200121', 'SYS');		-- 인구 천명당 의료기관 종사 의사수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21011E', 13, '20200121', 'SYS');		-- 주관적 건강수준인지율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21001E', 14, '20200121', 'SYS');		-- 흡연율

-- 통계표 그룹 (교육)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21171', 1, '20200121', 'SYS');			-- 교원1인당 학생수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21181', 2, '20200121', 'SYS');			-- 대학교 수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL8901', 3, '20200121', 'SYS');			-- 대학교 교원수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL8801', 4, '20200121', 'SYS');			-- 대학교 학생수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21191', 5, '20200121', 'SYS');			-- 인구 천명당 사설학원수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21201', 6, '20200121', 'SYS');			-- 유치원수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21221', 7, '20200121', 'SYS');			-- 유치원 교원수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21211', 8, '20200121', 'SYS');			-- 유치원 원아수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21231', 9, '20200121', 'SYS');			-- 초등학교수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21251', 10, '20200121', 'SYS');		-- 초등학교 교원수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21241', 11, '20200121', 'SYS');		-- 초등학교 학생수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15001', 12, '20200121', 'SYS');		-- 학급당 학생수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSED064R', 13, '20200121', 'SYS');		-- 학교교육의 효과
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSED019R', 14, '20200121', 'SYS');		-- 학생의 학교생활만족도

-- 통계표 그룹 (소득과 소비)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1HDNA01_01', 1, '20200121', 'SYS');		-- 가구 소득
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1HDNA01_02', 2, '20200121', 'SYS');		-- 가구 자산 및 부채
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1EA1501', 3, '20200121', 'SYS');			-- 농업소득
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1EA1611', 4, '20200121', 'SYS');			-- 농가부채
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSIC050R', 5, '20200121', 'SYS');		-- 소득만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSIC060R', 6, '20200121', 'SYS');		-- 소비생활 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1J17112', 7, '20200121', 'SYS');			-- 소비자물가지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20581', 8, '20200121', 'SYS');			-- 소비자물가 등락률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1C86_03', 9, '20200121', 'SYS');			-- 1인당 지역총소득
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1C86_04', 10, '20200121', 'SYS');		-- 1인당 개인소득
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1C86_01', 11, '20200121', 'SYS');		-- 1인당 민간소비지출액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6701E', 12, '20200121', 'SYS');		-- 예금은행예금액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6801E', 13, '20200121', 'SYS');		-- 예금은행대출금액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSIC010R', 14, '20200121', 'SYS');		-- 주관적소득수준

-- 통계표 그룹(고용과 노동)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7014S_01', 1, '20200121', 'SYS');		-- 경제활동인구(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A02S_01', 2, '20200121', 'SYS');		-- 경제활동인구(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7014S_02', 3, '20200121', 'SYS');		-- 경제활동참가율(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A02S_02', 4, '20200121', 'SYS');		-- 경제활동참가율(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7014S_03', 5, '20200121', 'SYS');		-- 고용률(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A02S_03', 6, '20200121', 'SYS');		-- 고용률(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20561', 7, '20200121', 'SYS');			-- 고용보험 신규취득자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL1101', 8, '20200121', 'SYS');			-- 구인배수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15007E', 9, '20200121', 'SYS');		-- 구직급여 신청자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15005E', 10, '20200121', 'SYS');		-- 근로시간
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSLA0812R', 11, '20200121', 'SYS');		-- 근로여건만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15002', 12, '20200121', 'SYS');		-- 비정규직근로자 비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15003', 13, '20200121', 'SYS');		-- 상용직 비중(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15004', 14, '20200121', 'SYS');		-- 상용직 비중(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7104S', 15, '20200121', 'SYS');		-- 실업률(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A01S', 16, '20200121', 'SYS');		-- 실업률(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15006', 17, '20200121', 'SYS');		-- 월평균 임금 및 임금상승률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7015S', 18, '20200121', 'SYS');		-- 청년고용률(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A03_A01S', 19, '20200121', 'SYS');	-- 청년고용률(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20531E', 20, '20200121', 'SYS');		-- 청년실업률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1DA7030S', 21, '20200121', 'SYS');		-- 취업자수(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES3A02S_04', 22, '20200121', 'SYS');	-- 취업자수(시/군)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20541', 23, '20200121', 'SYS');		-- 취업자증감

-- 통계표 그룹(주거와 교통)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL7401E', 1, '20200121', 'SYS');			-- 건축착공면적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL7701', 2, '20200121', 'SYS');			-- 건축허가면적증감률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20331', 3, '20200121', 'SYS');			-- 교통문화지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21051', 4, '20200121', 'SYS');			-- 자동차 천대당 교통사고발생건수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20761', 5, '20200121', 'SYS');			-- 노외주차장현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20721', 6, '20200121', 'SYS');			-- 도로포장률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21291E', 7, '20200121', 'SYS');		-- 도로지역면적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20421E', 8, '20200121', 'SYS');		-- 1인당 도시지역면적 현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20161E', 9, '20200121', 'SYS');		-- 아파트매매가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20191E', 10, '20200121', 'SYS');		-- 아파트월세가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20181E', 11, '20200121', 'SYS');		-- 아파트월세통합가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20171E', 12, '20200121', 'SYS');		-- 아파트전세가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL14001', 13, '20200121', 'SYS');		-- 음주운전교통사고비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20731', 14, '20200121', 'SYS');		-- 1인당 자동차 등록대수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15008', 15, '20200121', 'SYS');		-- 점유형태별 가구분포
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20071', 16, '20200121', 'SYS');		-- 주차장확보율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL1701', 17, '20200121', 'SYS');			-- 주택가격상승률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL7501E', 18, '20200121', 'SYS');		-- 주택건설실적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13401E', 19, '20200121', 'SYS');		-- 주택보급률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1JU1501', 20, '20200121', 'SYS');		-- 주택수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13501E', 21, '20200121', 'SYS');		-- 주택매매가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20151E', 22, '20200121', 'SYS');		-- 주택월세가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20141E', 23, '20200121', 'SYS');		-- 주택월세통합가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13601E', 24, '20200121', 'SYS');		-- 주택전세가격지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20881E', 25, '20200121', 'SYS');		-- 지가변동률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20201E', 26, '20200121', 'SYS');		-- 토지거래면적

-- 통계표 그룹(문화와 여가)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSCL050R', 1, '20200121', 'SYS');		-- 국내관광여행횟수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21281', 2, '20200121', 'SYS');			-- 인구 천명당 도시공원조성면적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20931', 3, '20200121', 'SYS');			-- 인구 십만명당 문화기반시설수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSCL030R', 4, '20200121', 'SYS');		-- 문화예술 및 스포츠관람현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSCL091R', 5, '20200121', 'SYS');		-- 여가활용 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSCL092R', 6, '20200121', 'SYS');		-- 여가활용 불만족 이유
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL4001E', 7, '20200121', 'SYS');			-- 인터넷이용률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL10901', 8, '20200121', 'SYS');			-- 지정등록문화재현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL2401', 9, '20200121', 'SYS');			-- 인구 십만명당 체육시설수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSCL060R', 10, '20200121', 'SYS');		-- 해외여행경험및횟수

-- 통계표 그룹(성장과 안정)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1C81', 1, '20200121', 'SYS');			-- GRDP(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1C65_03E', 2, '20200121', 'SYS');			-- GRDP(시도/시/군/구)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1C86_02', 3, '20200121', 'SYS');			-- 1인당 GRDP(시도)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1JC1501', 4, '20200121', 'SYS');			-- 가구수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20571', 5, '20200121', 'SYS');			-- 경제성장률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6601E', 6, '20200121', 'SYS');			-- 광공업생산지수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1EA1045', 7, '20200121', 'SYS');			-- 농가수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1K31005_01', 8, '20200121', 'SYS');		-- 대형소매점판매액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6101', 9, '20200121', 'SYS');			-- 도소매업사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6201', 10, '20200121', 'SYS');			-- 도소매업종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1K31005_02', 11, '20200121', 'SYS');		-- 백화점판매액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6501', 12, '20200121', 'SYS');			-- 부도업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20831', 13, '20200121', 'SYS');		-- 사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15012', 14, '20200121', 'SYS');		-- 종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20841', 15, '20200121', 'SYS');		-- 인구 천명당 사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20851', 16, '20200121', 'SYS');		-- 인구 천명당 종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6301', 17, '20200121', 'SYS');			-- 서비스업사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15010', 18, '20200121', 'SYS');		-- 서비스업생산 증감률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL7001', 19, '20200121', 'SYS');			-- 수입액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6901', 20, '20200121', 'SYS');			-- 수출액
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20551E', 21, '20200121', 'SYS');		-- 신설법인수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ZB7001', 22, '20200121', 'SYS');		-- 어가수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL7101E', 23, '20200121', 'SYS');		-- 어음부도율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL5901', 24, '20200121', 'SYS');			-- 운수업사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL6001', 25, '20200121', 'SYS');			-- 운수업종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20921', 26, '20200121', 'SYS');		-- 재정자립도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20891', 27, '20200121', 'SYS');		-- 재정자주도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL5701', 28, '20200121', 'SYS');			-- 제조업사업체수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL5801', 29, '20200121', 'SYS');			-- 제조업종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15009', 30, '20200121', 'SYS');		-- 제조업생산 증감률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15011E', 31, '20200121', 'SYS');		-- 지방세
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20121', 32, '20200121', 'SYS');		-- 친환경인증농산물출하현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20131', 33, '20200121', 'SYS');		-- 친환경인증축산물출하현황

-- 통계표 그룹(안전)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21091', 1, '20200121', 'SYS');			-- 119안전센터 1개센터당 담당주민수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL15013', 2, '20200121', 'SYS');			-- 경찰공무원 1인당 담당주민수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21101', 3, '20200121', 'SYS');			-- 구조·구급대원 1인당 담당주민수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL3001', 4, '20200121', 'SYS');			-- 인구 천명당 범죄발생건수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13901', 5, '20200121', 'SYS');			-- 뺑소니교통사고율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSA010R', 6, '20200121', 'SYS');		-- 사회안전에 대한 인식
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL3401', 7, '20200121', 'SYS');			-- 소년 천명당 소년범죄발생건수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21061', 8, '20200121', 'SYS');			-- 소방공무원 1인당 담당주민수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21071', 9, '20200121', 'SYS');			-- 소방서 1개서당 담당주민수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21111', 10, '20200121', 'SYS');		-- 소방안전교육 이수율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL10005', 11, '20200121', 'SYS');		-- 아동 십만명당 안전사고사망률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSA131R', 12, '20200121', 'SYS');		-- 야간 보행에 대한 안전도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20361', 13, '20200121', 'SYS');		-- 자연재해위험 개선지구지정현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20321', 14, '20200121', 'SYS');		-- 중요범죄발생및검거현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20341', 15, '20200121', 'SYS');		-- 지역안전등급현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20351', 16, '20200121', 'SYS');		-- 특정관리대상시설현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL8601', 17, '20200121', 'SYS');			-- 화재발생건수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21081', 18, '20200121', 'SYS');		-- 주민 만명당 화재발생건수

-- 통계표 그룹(환경)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL9901', 1, '20200121', 'SYS');			-- 강수량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13201', 2, '20200121', 'SYS');			-- 개발제한구역
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL9801', 3, '20200121', 'SYS');			-- 기온
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSEN015R', 4, '20200121', 'SYS');		-- 녹지환경 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1EB002', 5, '20200121', 'SYS');			-- 논경지면적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20061', 6, '20200121', 'SYS');			-- 도시가스 보급률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSEN053R', 7, '20200121', 'SYS');		-- 미세먼지 인식도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20741', 8, '20200121', 'SYS');			-- 상수도보급률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21321', 9, '20200121', 'SYS');			-- 주민 1인당 생활폐기물배출량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20401', 10, '20200121', 'SYS');		-- 신·재생에너지원별 (고유단위)생산량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20411', 11, '20200121', 'SYS');		-- 신·재생에너지원별(열량) 생산량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21311', 12, '20200121', 'SYS');		-- 일반폐기물재활용률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL4801E', 13, '20200121', 'SYS');		-- 전력판매량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20391', 14, '20200121', 'SYS');		-- 최종에너지소비량
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21301E', 15, '20200121', 'SYS');		-- 폐수배출업소수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20751', 16, '20200121', 'SYS');		-- 하수도보급률

-- 통계표 그룹(사회통합)
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20301', 1, '20200121', 'SYS');			-- 건강보험적용인구현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13801E', 2, '20200121', 'SYS');		-- 국민기초생활보장 수급자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20311', 3, '20200121', 'SYS');			-- 급여형태별 요양급여실적
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSP062R', 4, '20200121', 'SYS');		-- 기부율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20961', 5, '20200121', 'SYS');			-- 노인 천명당 노인여가복지시설수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13001', 6, '20200121', 'SYS');			-- 보건및사회복지 사업체비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL13101', 7, '20200121', 'SYS');			-- 보건및사회복지사업 종사자비율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSP052R', 8, '20200121', 'SYS');		-- 사회단체 참여율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1ES4G07S', 9, '20200121', 'SYS');		-- 임금근로자의 사회보험 가입률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20941', 10, '20200121', 'SYS');		-- 인구 십만명당 사회복지시설수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20271', 11, '20200121', 'SYS');		-- 사회복지시설 종사자수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20261', 12, '20200121', 'SYS');		-- 사회복지전담 공무원현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSP041R', 13, '20200121', 'SYS');		-- 사회적 관계망
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSP020R', 14, '20200121', 'SYS');		-- 삶의 만족도
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL8001E', 15, '20200121', 'SYS');		-- 요양기관수
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20911', 16, '20200121', 'SYS');		-- 일반회계중 사회복지예산비중
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL20901', 17, '20200121', 'SYS');		-- 일반회계중 일반공공행정예산비중
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL21121E', 18, '20200121', 'SYS');		-- 인구십만명당 자살률
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'INH_1SSSP181R', 19, '20200121', 'SYS');		-- 자원봉사활동 참여율
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL10801', 20, '20200121', 'SYS');		-- 재난재해관리기금현황
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL2101E', 21, '20200121', 'SYS');		-- 지방자치단체공무원 현원
INSERT INTO TN_STBL_GRP VALUES ('REXA101', '208', 'DT_1YL2201', 22, '20200121', 'SYS');			-- 인구 천명당 지방자치단체 공무원현원

-- 통계표정보 (인구)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20631', 1, 1, '고령인구비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B83A09', 1, 1, '평균 초혼연령', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B83A10', 1, 1, '평균 재혼연령', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20701', 1, 1, '남녀성비', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL12501E', 1, 1, '노령화지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1IN1503_02', 1, 1, '평균연령', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B82A01', 1, 1, '사망자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B26001_A021', 1, 1, '순이동인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21261', 1, 1, '등록외국인 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21271', 1, 1, '인구 천명당 외국인수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20621', 1, 1, '인구증가율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B26001_A022', 1, 1, '전입인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B26001_A023', 1, 1, '전출인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20651E', 1, 1, '주민등록인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B81A01', 1, 1, '출생아수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B81A17', 1, 1, '합계출산율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1IN1503_01', 1, 1, '인구총조사 인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1EA1011_01', 1, 1, '농가인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1ZB7024', 1, 1, '어가인구', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (가족)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21161', 1, 1, '1인가구비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B8000I_01', 1, 1, '조혼이율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B83A35', 1, 1, '혼인건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B8000I_02', 1, 1, '조혼이율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1B83A23', 1, 1, '혼인형태', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSWE011R', 1, 1, '생활여건의 변화', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20951', 1, 1, '유아 천명당 보육시설수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL12701', 1, 1, '독거노인가구비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSFA139R', 1, 1, '가족관계 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSFA021R', 1, 1, '부모 생활비 주제공자', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (건강)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21021E', 1, 1, 'EQ-5D 지표', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21011E', 1, 1, '주관적건강수준 인지율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL0301E', 1, 1, '기대여명', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL11001E', 1, 1, '결핵신고 신환자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20291E', 1, 1, '고위험음주율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20991E', 1, 1, '음주율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21031E', 1, 1, '비만율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21041E', 1, 1, '스트레스 인지율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21001E', 1, 1, '흡연율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSHE020R', 1, 1, '규칙적 운동 실천율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL8101E', 1, 1, '의료기관 수술인원', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20971', 1, 1, '인구 천명당 의료기관 병상수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20981', 1, 1, '인구 천명당 의료기관 종사 의사수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSHE102R', 1, 1, '의료서비스 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (교육)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21171', 1, 1, '교육 1인당 학생수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL8901', 1, 1, '대학교 교원수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL8801', 1, 1, '대학교 학생수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21221', 1, 1, '유치원 교원수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21211', 1, 1, '유치원 원아수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21251', 1, 1, '초등학교 교원수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21241', 1, 1, '초등학교 학생수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15001', 1, 1, '학급당 학생수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21201', 1, 1, '유치원수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21191', 1, 1, '인구 천명당 사설학원수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21181', 1, 1, '대학교 수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21231', 1, 1, '초등학교수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSED019R', 1, 1, '학생의 학교생활 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSED064R', 1, 1, '학교교육의 효과', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (소득과 소비)
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1EA1501', 1, 1, '농업소득', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSIC050R', 1, 1, '소득만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSIC050R', 1, 1, '주관적 소득수준', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSIC060R', 1, 1, '소비생활만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1EA1611', 1, 1, '농가부채', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6801E', 1, 1, '예금은행 대출금액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6701E', 1, 1, '예금은행 예금액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20581', 1, 1, '소비자물가 등락률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1J17112', 1, 1, '소비자물가지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (고용과 노동)
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7014S_02', 1, 1, '경제활동참가율(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7014S_01', 1, 1, '경제활동인구(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7014S_03', 1, 1, '고용률(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7104S', 1, 1, '실업률(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7030S', 1, 1, '취업자수(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1DA7015S', 1, 1, '청년고용률(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20531E', 1, 1, '청년실업률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20541', 1, 1, '취업자증감', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL1101', 1, 1, '구인배수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15007E', 1, 1, '구직급여 신청자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSLA0812R', 1, 1, '근로여건 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20561', 1, 1, '고용보험 신규취득자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15002', 1, 1, '비정규직근로자 비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15003', 1, 1, '상용직 비중(시도)', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15006', 1, 1, '월평균 임금 및 임금상승률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (주거와 교통)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL7701', 1, 1, '건축허가면적 증감률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL7501E', 1, 1, '주택건설실적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13401E', 1, 1, '주택보급률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1JU1501', 1, 1, '주택수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20201E', 1, 1, '토지거래 면적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20161E', 1, 1, '아파트 매매가격 지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20191E', 1, 1, '아파트 월세가격 지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20181E', 1, 1, '아파트 월세 통합가격 지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20171E', 1, 1, '아파트 전세가격 지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL1701', 1, 1, '주택가격 상승률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13501E', 1, 1, '주택 매매가격지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20151E', 1, 1, '주택월세 가격지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20141E', 1, 1, '주택월세 통합가격지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13601E', 1, 1, '주택 전세가격지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20881', 1, 1, '지가변동률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20761', 1, 1, '노외주차장현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20721', 1, 1, '도로포장률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20731', 1, 1, '1인당 자동차 등록대수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20071', 1, 1, '주차장 확보율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21291E', 1, 1, '도시지역면적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20421', 1, 1, '1인당 도시지역면적 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL14001', 1, 1, '음주운전 교통사고 비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21051', 1, 1, '자동차 천대당 교통사고 발생 건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20331', 1, 1, '교통문화지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (문화와 여가)
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSCL050R', 1, 1, '국내 관광여행 횟수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSCL060R', 1, 1, '해외여행 겸험 및 횟수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSCL030R', 1, 1, '문화예술 및 스포츠 관람 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSCL091R', 1, 1, '여가활용 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSCL092R', 1, 1, '여가활용 불만족 이유', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20931', 1, 1, '인구 십만명당 문화기반시설수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL2401', 1, 1, '인구 십만명당 체육시설수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL10901', 1, 1, '지정등록문화재 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21281', 1, 1, '인구 천명당 도시공원 조성면적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL4001E', 1, 1, '인터넷 이용률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (성장과 안정)
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1C81', 1, 1, 'GRDP', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1C86_02', 1, 1, '1인당 GRDP', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20571', 1, 1, '경제성장률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL7101E', 1, 1, '어음부도율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20921', 1, 1, '재정자립도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20891', 1, 1, '재정자주도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15011E', 1, 1, '지방세', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20831', 1, 1, '사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20841', 1, 1, '인구 천명당 사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20851', 1, 1, '인구 천명당 종사자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20551E', 1, 1, '신설법인수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1JC1501', 1, 1, '가구수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15012', 1, 1, '종사자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1ZB7001', 1, 1, '어가수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1EA1045', 1, 1, '농가수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20121', 1, 1, '친환경인증 농산물 출하현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20131', 1, 1, '친환경인증 축산물 출하현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6601E', 1, 1, '광공업생산지수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL5701', 1, 1, '제조업사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL5801', 1, 1, '제조업종사자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15009', 1, 1, '제조업생산 증감률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1K31005_01', 1, 1, '대형소매점 판매액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6101', 1, 1, '도소매업사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6201', 1, 1, '도소매업종사자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1K31005_02', 1, 1, '백화점 판매액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6301', 1, 1, '서비스업사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL5901', 1, 1, '운수업사업체수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6001', 1, 1, '운수업종사자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL7001', 1, 1, '수입액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL6901', 1, 1, '수출액', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (안전)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL3401', 1, 1, '소년 천명당 소년범죄 발생 건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL3001', 1, 1, '인구 천명당 범죄발생건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20321', 1, 1, '중요범죄 발생 및 검거 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21081', 1, 1, '주민 1만명당 화재발생 건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL8601', 1, 1, '화재발생 건수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13901', 1, 1, '뺑소니 교통사고율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL10005', 1, 1, '아동 십만명당 안전사고 사망률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSA010R', 1, 1, '사회안전에 대한 인식', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSA131R', 1, 1, '야간보행에 대한 안전도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21061', 1, 1, '소방공무원 1인당 담당 주민수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21071', 1, 1, '소방서 1개서당 담당 주민수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21111', 1, 1, '소방안전교육 이수율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20341', 1, 1, '지역안전등급 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20351', 1, 1, '특정관리 대상시설 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21091', 1, 1, '119안전센터 1개센터당 담당 주민수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21101', 1, 1, '구조·구급대원 1인당 담당 주민수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20361', 1, 1, '자연재해위험 개선지구지정현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL15013', 1, 1, '경찰공무원 1인당 담당주민수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (환경)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL9801', 1, 1, '기온', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL9901', 1, 1, '강수량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20401', 1, 1, '신·재생에너지원별(고유단위) 생산량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20411', 1, 1, '신·재생에너지원별(열량) 생산량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20391', 1, 1, '최종에너지 소비량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20061', 1, 1, '도시가스 보급률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL4801E', 1, 1, '전력판매량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSEN053R', 1, 1, '미세먼지 인식도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20741', 1, 1, '상수도보급률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20751', 1, 1, '하수도보급률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21311', 1, 1, '일반폐기물 재활용률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21321', 1, 1, '주민 1인당 생활폐기물 배출량', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21301', 1, 1, '폐수배출업소수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSEN015R', 1, 1, '녹지환경 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13201', 1, 1, '개발제한구역', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1EB002', 1, 1, '논 경지면적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표정보 (사회통합)
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20301', 1, 1, '건강보험 적용인구 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20311', 1, 1, '급여형태별 요양급여 실적', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13801E', 1, 1, '국민 기초생활보장 수급자수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL8001E', 1, 1, '요양기관수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20961', 1, 1, '노인 천명당 노인여가복지시설수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20261', 1, 1, '사회복지 전담공무원 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20271', 1, 1, '사회복지시설 종사자 수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20941', 1, 1, '인구 십만명당 사회복지시설수', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13001', 1, 1, '보건 및 사회복지 사업체 비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL13101', 1, 1, '보건 및 사회복지사업 종사자 비율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20911', 1, 1, '일반회계중 사회복지예산 비중', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL2101E', 1, 1, '지방자치단체 공무원 현원', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL2201', 1, 1, '인구 천명당 지방자치단체 공무원 현원', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL20901', 1, 1, '일반회계중 일반공공행정예산 비중', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL10801', 1, 1, '재난 재해 관리기금 현황', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'DT_1YL21121E', 1, 1, '인구 십만명당 자살률', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSP020R', 1, 1, '삶의 만족도', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSP052R', 1, 1, '사회단체 참여율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSP181R', 1, 1, '자원봉사활동 참여율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);
INSERT INTO TN_STBL_INFO VALUES ('208', 'INH_1SSSP062R', 1, 1, '기부율', null, 'Y', null, null, null, null, null, null, null, null, null, 1, '20200122', 'SYS', '20200122', 'SYS', null, null, 'Y', 'SJEXA02', 'e-지방지표', null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'N', null, 0);

-- 통계표 대상항목 분류 (인구)
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20631', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B83A09', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B83A10', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20701', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL12501E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1IN1503_02', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B82A01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B26001_A021', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21261', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21271', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20621', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B26001_A022', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B26001_A023', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20651E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B81A01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B81A17', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1IN1503_01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1EA1011_01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1ZB7024', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '인구', null, null, null, null, null, null);

-- 자료 항목 리스트 (인구)
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20631', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A09', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '남편', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A09', 1, 'A', '002', null, 'N', 2, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '아내', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A10', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '남편', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A10', 1, 'A', '002', null, 'N', 2, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '아내', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20701', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL12501E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1IN1503_02', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B82A01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B26001_A021', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21261', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21271', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20621', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B26001_A022', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B26001_A023', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20651E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B81A01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B81A17', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1IN1503_01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1EA1011_01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1ZB7024', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 가족
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21161', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B8000I_01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B83A35', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B8000I_02', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1B83A23', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSWE011R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20951', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL12701', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSFA139R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSFA021R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '가족', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21161', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B8000I_01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A35', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B8000I_02', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A23', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '모두 초혼', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A23', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '모두 재혼', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A23', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '남초혼 여재혼', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A23', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '남재혼 여초혼', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1B83A23', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '미상', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSWE011R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '많이 좋아짐', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSWE011R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 좋아짐', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSWE011R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '변화없음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSWE011R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 나빠짐', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSWE011R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '많이 나빠짐', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20951', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL12701', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA139R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA139R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA139R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA139R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA139R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '장남 또는 맏며느리', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '아들(장남 포함) 또는 며느리', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '딸 또는 사위', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '모든 자녀(아들과 딸)', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '부모 스스로 해결', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSFA021R', 1, 'A', '006', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '기타', null, null, null, null, null);

-- 건강
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21021E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21011E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL0301E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL11001E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20291E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20991E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21031E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21041E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21001E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSHE020R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL8101E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20971', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20981', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSHE102R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '건강', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21021E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21011E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL0301E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL11001E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20291E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20991E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21031E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21041E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21001E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE020R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL8101E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20971', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20981', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE102R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE102R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE102R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE102R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSHE102R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

-- 교육
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21171', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL8901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL8801', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21221', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21211', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21251', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21241', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21201', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21191', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21181', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21231', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSED019R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSED064R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '교육', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21171', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL8901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL8801', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21221', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21211', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21251', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21241', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21201', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21191', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21181', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21231', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED019R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED019R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED019R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED019R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED019R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우효과있음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간효과있음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '별로효과없음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '전혀효과없음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSED064R', 1, 'A', '006', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '모르겠음', null, null, null, null, null);

-- 소득과 소비
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1EA1501', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSIC050R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSIC060R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1EA1611', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6801E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6701E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20581', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1J17112', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '소득과 소비', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1EA1501', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '소득 있음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC050R', 1, 'A', '006', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC060R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC060R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC060R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC060R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSIC060R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1EA1611', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6801E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6701E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20581', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1J17112', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 고용과 노동
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7014S_02', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7014S_01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7014S_03', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7104S', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7030S', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1DA7015S', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20531E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20541', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL1101', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15007E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSLA0812R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20561', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15002', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15003', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15006', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '고용과 노동', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7014S_02', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7014S_01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7014S_03', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7104S', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7030S', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1DA7015S', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20531E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20541', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL1101', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15007E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSLA0812R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSLA0812R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSLA0812R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSLA0812R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSLA0812R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20561', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15002', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15003', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15006', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 주거와 교통
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL7701', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL7501E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13401E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1JU1501', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20201E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20161E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20191E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20181E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20171E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL1701', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13501E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20151E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20141E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13601E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20881', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20761', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20721', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20731', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20071', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21291E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20421', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL14001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21051', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20331', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '주거와 교통', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL7701', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL7501E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL13401E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1JU1501', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20201E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20161E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20191E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20181E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20171E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL1701', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL13501E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20151E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20141E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL13601E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20881', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20761', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20721', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20731', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20071', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21291E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20421', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL14001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21051', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20331', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '계', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20331', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '운전행태영역', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20331', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '교통안전영역', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20331', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보행행태영역', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20331', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '교통약자영역', null, null, null, null, null);

-- 문화야 여가
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSCL050R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSCL060R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSCL030R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSCL091R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSCL092R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20931', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL2401', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL10901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21281', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL4001E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '문화야 여가', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL050R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL060R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL030R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '미술관 관람', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL030R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '스포츠 관람', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL030R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '연극·마당극·뮤지컬', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL030R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '영화', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL030R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '음악·연주회', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL091R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL091R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL091R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL091R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL091R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL092R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '건강, 체력 부족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL092R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '경제적 부담', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL092R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '시간 부족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL092R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '여가시설 부족', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSCL092R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '적당한 취미가 없어서', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20931', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL2401', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL10901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21281', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL4001E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 성장과 안정
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1C81', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1C86_02', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20571', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL7101E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20921', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20891', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15011E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20831', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20841', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20851', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20551E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1JC1501', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15012', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1ZB7001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1EA1045', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20121', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20131', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6601E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL5701', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL5801', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15009', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1K31005_01', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6101', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6201', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1K31005_02', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6301', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL5901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL7001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL6901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '성장과 안정', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1C81', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1C86_02', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20571', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL7101E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20921', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20891', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15011E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20831', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20841', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20851', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20551E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1JC1501', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15012', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1ZB7001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1EA1045', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20121', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20131', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6601E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL5701', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL5801', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15009', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1K31005_01', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6101', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6201', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1K31005_02', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6301', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL5901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL7001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL6901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 안전
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL3401', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL3001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20321', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21081', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL8601', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL10005', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSA010R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSA131R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21061', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21071', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21111', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20341', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20351', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21091', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21101', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20361', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL15013', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '안전', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL3401', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL3001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20321', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21081', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL8601', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL13901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL10005', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA010R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 안전', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA010R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '비교적 안전', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA010R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA010R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '비교적 안전하지 않음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA010R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 안전하지 않음', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSSA131R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21061', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21071', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21111', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20341', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '교통사고', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20341', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '화재', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20341', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '범죄', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20341', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '자연재해', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20341', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '생활안전', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20351', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '관리대장', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20351', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '중점관리시설', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20351', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '재난위험시설', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21091', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21101', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20361', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL15013', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 환경
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL9801', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL9901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20401', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20411', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20391', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20061', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL4801E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSEN053R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20741', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20751', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21311', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21321', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21301', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSEN015R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13201', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1EB002', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '환경', null, null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL9801', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL9901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20401', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '태양열', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20401', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '태양광', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20401', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '풍력', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20401', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '수력', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20401', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '지열', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20411', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20391', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20061', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL4801E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN053R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '전혀 불안하지 않음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN053R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '별로 불안하지 않음', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN053R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN053R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불안함', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN053R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불안함', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20741', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL20751', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21311', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21321', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL21301', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN015R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 좋다', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN015R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 좋다', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN015R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN015R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 나쁘다', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1SSEN015R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 나쁘다', null, null, null, null, null);

INSERT INTO TN_ITM_LIST VALUES ('208', 'DT_1YL13201', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT INTO TN_ITM_LIST VALUES ('208', 'INH_1EB002', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

-- 사회통합
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20301', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20311', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13801E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL8001E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20961', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20261', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20271', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20941', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13001', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL13101', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20911', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL2101E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL2201', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL20901', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL10801', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'DT_1YL21121E', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSP020R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSP052R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSP181R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);
INSERT INTO TN_OBJ_ITM_CLS VALUES ('208', 'INH_1SSSP062R', 1, 'A', 1, 1, 'N', 'N', '20200122', 'SYS', null, null, '사회통합', null, null, null, null, null, null);

INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20301', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20311', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL13801E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL8001E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20961', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20261', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20271', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20941', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL13001', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL13101', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20911', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL2101E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL2201', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL20901', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL10801', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'DT_1YL21121E', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);

INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP020R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 만족', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP020R', 1, 'A', '002', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 만족', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP020R', 1, 'A', '003', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '보통', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP020R', 1, 'A', '004', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '약간 불만족', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP020R', 1, 'A', '005', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '매우 불만족', null, null, null, null, null);

INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP052R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP181R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);
INSERT TN_ITM_LIST VALUES ('208', 'INH_1SSSP062R', 1, 'A', '001', null, 'N', 1, 'N', null, null, null, 'N', null, null, null, '20200122', 'SYS', '세종', null, null, null, null, null);