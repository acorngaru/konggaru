package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Page<T> {
    // 출력 데이터 개수
    private int rows;

    // 페이지 번호
    private int currentPageNo = 1;

    // 총 데이터 개수
    private int totalItems;

    // 총 페이지 개수
    private int totalPages;

    // 보여줄 페이지 수
    private int pageCount = 3;

    // 보여줄 처음 페이지 번호
    private int startPage;

    // 보여줄 마지막 페이지 번호
    private int endPage;

    // 다음 페이지 이동 가능 여부
    private boolean hasNextPage;

    // 이전 페이지 이동 가능 여부
    private boolean hasPrevPage;

    // 아이템
    private List<T> items;

    public void process(int rows, int currentPageNo, int totalItems, List<T> items) {
        this.rows = rows;
        this.currentPageNo = currentPageNo;
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / rows);

        this.hasPrevPage = this.currentPageNo > 1;
        this.hasNextPage = this.currentPageNo < totalPages;

        this.endPage = (int) (Math.ceil((double) this.currentPageNo / this.pageCount) * pageCount);
        this.startPage = this.endPage - pageCount + 1;

        this.endPage = Math.min(this.endPage, this.totalPages);
    }
}
