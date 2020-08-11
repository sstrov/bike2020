package fscms.cmm.pagination;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class AdminRenderer extends AbstractPaginationRenderer {
	
	public AdminRenderer() {
		firstPageLabel    = "<li class=\"paginate_button previous\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><i class=\"fa fa-lg fa-step-backward\"></i>&nbsp;</a></li>";
		previousPageLabel = "<li class=\"paginate_button previous\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><i class=\"fa fa-lg fa-caret-left\"></i>&nbsp;</a></li>";
		currentPageLabel  = "<li class=\"paginate_button active\"><a href=\"#none\">{0}</a></li>";
		otherPageLabel    = "<li class=\"paginate_button\"><a href=\"#none\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel     = "<li class=\"paginate_button next\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><i class=\"fa fa-lg fa-caret-right\"></i></a></li>";
		lastPageLabel     = "<li class=\"paginate_button next\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><i class=\"fa fa-lg fa-step-forward\"></i>&nbsp;</a></li>";
	}

}
