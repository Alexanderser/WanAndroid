package com.foxconn.lau.wanandroid.bean.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 下午 03:23
 */
public class ArticleInfo {

    private ArticleData data;
    private int errorCode;
    private String errorMsg;

    public ArticleData getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public class ArticleData {
        private int curPage;
        private List<Article> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public ArticleData() {
            this.datas = new ArrayList<>();
        }

        public List<Article> getListData() {
            return datas;
        }

        public int getCurPage() {
            return curPage;
        }

        public int getOffset() {
            return offset;
        }

        public boolean isOver() {
            return over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public int getSize() {
            return size;
        }

        public int getTotal() {
            return total;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public void setDatas(List<Article> datas) {
            this.datas = datas;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "ArticleData{" +
                    "curPage=" + curPage +
                    ", datas=" + datas +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pageCount=" + pageCount +
                    ", size=" + size +
                    ", total=" + total +
                    '}';
        }
    }
}
