package br.challenge.skipthedishes.model;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;

public abstract class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;

	public Integer page = 0;

	public Integer pageSize = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PageRequest toPageRequest() {
		if (page == null || pageSize == null) {
			return null;
		}
		return PageRequest.of(page, pageSize);

	}
}
