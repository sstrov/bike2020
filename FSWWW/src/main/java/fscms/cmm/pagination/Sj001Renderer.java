package fscms.cmm.pagination;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class Sj001Renderer extends AbstractPaginationRenderer {
	
	public Sj001Renderer() {
		firstPageLabel    = "<li class=\"page-item\"><a href=\"#none\" class=\"page-link\" aria-label=\"first\" onclick=\"{0}({1}); return false;\" title=\"처음 페이지\"><span aria-hidden=\"true\">&lt;&lt;</span></a></li>";
		previousPageLabel = "<li class=\"page-item\"><a href=\"#none\" class=\"page-link\" aria-label=\"Previous\" onclick=\"{0}({1}); return false;\" title=\"이전 페이지\"><span aria-hidden=\"true\">&lt;</span></a></li>";
		currentPageLabel  = "<li class=\"page-item active\"><a href=\"#none\" class=\"page-link\" title=\"선택됨\">{0}</a></li>";
		otherPageLabel    = "<li class=\"page-item\"><a href=\"#none\" class=\"page-link\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel     = "<li class=\"page-item\"><a href=\"#none\" class=\"page-link\" aria-label=\"Next\" onclick=\"{0}({1}); return false;\" title=\"다음 페이지\"><span aria-hidden=\"true\">&gt;</span></a></li>";
		lastPageLabel     = "<li class=\"page-item\"><a href=\"#none\" class=\"page-link\" aria-label=\"last\" onclick=\"{0}({1}); return false;\" title=\"마지막 페이지\"><span aria-hidden=\"true\">&gt;&gt;</span></a></li>";
	}

}
