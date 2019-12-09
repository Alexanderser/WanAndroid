package com.foxconn.lau.wanandroid.bean.main;

import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 上午 11:53
 */
//{data=
// [{desc=享学~, id=29.0, imagePath=https://www.wanandroid.com/blogimgs/fa822a30-00fc-4e0d-a51a-d704af48205c.jpeg,
// isVisible=1.0, order=0.0, title=在下 Android &ldquo; 高手 &rdquo;！, type=0.0,
// url=https://mp.weixin.qq.com/s/KX9tvauMgDVHUx0yCmFnNQ},
// {desc=, id=6.0, imagePath=https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png,
// isVisible=1.0, order=1.0, title=我们新增了一个常用导航Tab~, type=1.0, url=https://www.wanandroid.com/navi},
// {desc=一起来做个App吧, id=10.0, imagePath=https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png,
// isVisible=1.0, order=1.0, title=一起来做个App吧, type=1.0, url=https://www.wanandroid.com/blog/show/2},
// {desc=, id=20.0, imagePath=https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png,
// isVisible=1.0, order=2.0, title=flutter 中文社区 , type=1.0, url=https://flutter.cn/}], errorCode=0.0, errorMsg=}
//2019-12-04
public class BannerInfo {

    private List<BannerChild> data;
    private int errorCode;
    private String errorMsg;

    public class BannerChild{
        private String desc;
        private int id;
        private String imagePath;
        private int isVisible;
        private int order;
        private String title;
        private int type;
        private String url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "BannerInfo{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public List<BannerChild> getData() {
        return data;
    }

    public void setData(List<BannerChild> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BannerInfo{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
