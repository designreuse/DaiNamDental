package com.datawings.app.filter;

import com.datawings.app.common.BeanUtil;

public class BaseFilter {

	protected static int PAGE_SIZE_STANDARD = 20;

	protected Integer page;

	protected Integer rowCount;

	protected Integer pageSize;

	public BaseFilter() {
		init();
	}

	public void init() {
		BeanUtil.initSimplePropertyBean(this);
		this.page = 0;
		this.pageSize = PAGE_SIZE_STANDARD;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		Integer offset = page * pageSize;
		return offset;
	}

	public Integer getTotalPage() {
		Integer tot = 0;
		Integer rs = 0;
		if (rowCount != 0 && pageSize != 0) {
			rs = (rowCount % pageSize);
			tot = rowCount / pageSize;
			tot = (rs > 0) ? (tot + 1) : tot;
		}
		return tot;
	}
}
