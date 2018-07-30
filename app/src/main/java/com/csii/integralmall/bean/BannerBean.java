package com.csii.integralmall.bean;

import java.util.List;

public class BannerBean {


    /**
     * code : 200
     * message : success
     * result : [{"created":"2018-04-17 20:41:02","fullUrl":"","id":32,"panelId":7,"picUrl":"http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-1_61bdc2f4f9.png","picUrl2":"http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-3_8fa7866d59.png","picUrl3":"http://ow2h3ee9w.bkt.clouddn.com/banner-3d-item-1-box-33.png","productId":150635087972564,"productImageBig":"http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-1_61bdc2f4f9.png","productName":"支付测试商品 IPhone X 全面屏 全面绽放","salePrice":1,"sortOrder":1,"subTitle":"此仅为支付测试商品 拍下不会发货","type":0,"updated":"2018-04-17 20:58:41"},{"created":"2018-04-17 21:08:22","fullUrl":"","id":33,"panelId":7,"picUrl":"http://oweupqzdv.bkt.clouddn.com/bg_left.png","picUrl2":"http://ow2h3ee9w.bkt.clouddn.com/phone_left2.png","picUrl3":"http://oweupqzdv.bkt.clouddn.com/erji_left.png","productId":150642571432835,"productImageBig":"http://oweupqzdv.bkt.clouddn.com/bg_left.png","productName":"捐赠商品","salePrice":1,"sortOrder":2,"subTitle":"您的捐赠将用于本站维护 给您带来更好的体验","type":0,"updated":"2018-04-20 10:47:19"},{"created":"2018-04-17 21:08:30","fullUrl":"","id":34,"panelId":7,"picUrl":"https://s1.ax1x.com/2018/05/19/Ccdiid.png","picUrl2":"","picUrl3":"","productId":150635087972564,"productImageBig":"https://s1.ax1x.com/2018/05/19/Ccdiid.png","productName":"支付测试商品 IPhone X 全面屏 全面绽放","salePrice":1,"sortOrder":3,"subTitle":"此仅为支付测试商品 拍下不会发货","type":0,"updated":"2018-04-17 21:08:32"},{"created":"2018-04-18 23:44:48","fullUrl":"","id":35,"panelId":7,"picUrl":"http://ow2h3ee9w.bkt.clouddn.com/24401108web1.png","picUrl2":"","picUrl3":"","productId":150642571432843,"productImageBig":"http://ow2h3ee9w.bkt.clouddn.com/24401108web1.png","productName":"坚果 3","salePrice":1999,"sortOrder":4,"subTitle":"漂亮得不像实力派","type":0,"updated":"2018-04-20 11:41:46"},{"created":"2018-04-19 15:09:40","fullUrl":"","id":51,"panelId":7,"picUrl":"http://192.168.1.198:80/2018/07/12/0500e3eb8fa44b818e676d9ce0e3aeda.png","picUrl2":"","picUrl3":"","productId":153112532232605,"productImageBig":"http://192.168.1.198:80/2018/07/12/0500e3eb8fa44b818e676d9ce0e3aeda.png","productName":"测试耳机","salePrice":32,"sortOrder":6,"subTitle":"测试耳机","type":0,"updated":"2018-07-12 19:08:07"}]
     * success : true
     * timestamp : 1531718171053
     */

    private int code;
    private String message;
    private boolean success;
    private long timestamp;
    private List<ResultBannerBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ResultBannerBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBannerBean> result) {
        this.result = result;
    }

    public static class ResultBannerBean {
        /**
         * created : 2018-04-17 20:41:02
         * fullUrl :
         * id : 32
         * panelId : 7
         * picUrl : http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-1_61bdc2f4f9.png
         * picUrl2 : http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-3_8fa7866d59.png
         * picUrl3 : http://ow2h3ee9w.bkt.clouddn.com/banner-3d-item-1-box-33.png
         * productId : 150635087972564
         * productImageBig : http://static.smartisanos.cn/index/img/store/home/banner-3d-item-1-box-1_61bdc2f4f9.png
         * productName : 支付测试商品 IPhone X 全面屏 全面绽放
         * salePrice : 1.0
         * sortOrder : 1
         * subTitle : 此仅为支付测试商品 拍下不会发货
         * type : 0
         * updated : 2018-04-17 20:58:41
         */

        private String created;
        private String fullUrl;
        private int id;
        private int panelId;
        private String picUrl;
        private String picUrl2;
        private String picUrl3;
        private long productId;
        private String productImageBig;
        private String productName;
        private double salePrice;
        private int sortOrder;
        private String subTitle;
        private int type;
        private String updated;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getFullUrl() {
            return fullUrl;
        }

        public void setFullUrl(String fullUrl) {
            this.fullUrl = fullUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPanelId() {
            return panelId;
        }

        public void setPanelId(int panelId) {
            this.panelId = panelId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicUrl2() {
            return picUrl2;
        }

        public void setPicUrl2(String picUrl2) {
            this.picUrl2 = picUrl2;
        }

        public String getPicUrl3() {
            return picUrl3;
        }

        public void setPicUrl3(String picUrl3) {
            this.picUrl3 = picUrl3;
        }

        public long getProductId() {
            return productId;
        }

        public void setProductId(long productId) {
            this.productId = productId;
        }

        public String getProductImageBig() {
            return productImageBig;
        }

        public void setProductImageBig(String productImageBig) {
            this.productImageBig = productImageBig;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
