package com.shsxt.page;

import java.util.List;

public class SearchResult<T> {
	private Pagination pagination;
	private List<T> result;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
