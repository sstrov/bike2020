package fscms.mods.contest.vo;

import java.lang.reflect.Field;
import java.util.List;

import fscms.cmm.util.excel.ExcelUtil;
import fscms.cmm.util.excel.TableList;

public class ContestSearchVO extends ContestVO{

private static final long serialVersionUID = 1L;
	
	@TableList(caption="검색타입", order=1)
	private String sc                 = "";
	
	@TableList(caption="검색키워드", order=2)
	private String sw                 = "";
	
	@TableList(caption="현재 페이지 번호", order=3)
	private int    pageIndex          = 1;	// 현재 페이지
	private int    pageUnit           = 10;	// 페이지 갯수 (번호 갯수)
	private int    pageSize           = 5;	// 페이지 사이즈 (목록 갯수)
	private int    firstIndex         = 0;
	private int    lastIndex          = 0;
	private int    recordCountPerPage = 10;
	
	// 조건식
	@TableList(caption="정렬기준", order=4)
	private String  orderBy = "REGIST_DE desc";
	@TableList(caption="목록 개수", order=5)
	private Integer maxList = 10;
	
	@TableList(caption="일자조회_시작", order=6)
	private String  sc_wDateS;
	@TableList(caption="일자조회_종료", order=7)
	private String  sc_wDateE;
	@TableList(caption="상태", order=8)
	private String  sc_state;
	
	private String  sc_type;
	
	private String[] sc_stateArr;

	// 검색 조건 필드 (상세, 등록/수정 페이지에서 사용하는 항목)
	List<Field> fieldList;
	
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getMaxList() {
		return maxList;
	}
	public void setMaxList(Integer maxList) {
		this.maxList = maxList;
	}
	public String getSc_wDateS() {
		return sc_wDateS;
	}
	public void setSc_wDateS(String sc_wDateS) {
		this.sc_wDateS = sc_wDateS;
	}
	public String getSc_wDateE() {
		return sc_wDateE;
	}
	public void setSc_wDateE(String sc_wDateE) {
		this.sc_wDateE = sc_wDateE;
	}
	public String getSc_state() {
		return sc_state;
	}
	public void setSc_state(String sc_state) {
		this.sc_state = sc_state;
	}
	public String[] getSc_stateArr() {
		if(sc_stateArr == null) {
			return null;
		}
		String[] newArr = new String[sc_stateArr.length];
		System.arraycopy(sc_stateArr, 0, newArr, 0, sc_stateArr.length);
		return newArr;
	}
	public void setSc_stateArr(String[] sc_stateArr) {
		String[] newArr = new String[sc_stateArr.length];
		System.arraycopy(sc_stateArr, 0, newArr, 0, sc_stateArr.length);
		this.sc_stateArr = newArr;
	}
	public List<Field> getFieldList() {
		List<Field> fieldList = ExcelUtil.annotaionFieldList(this.getClass(), TableList.class, true);
		return fieldList;
	}
	public String getSc_type() {
		return sc_type;
	}
	public void setSc_type(String sc_type) {
		this.sc_type = sc_type;
	}
}
