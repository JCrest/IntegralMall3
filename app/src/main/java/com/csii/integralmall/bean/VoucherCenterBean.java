package com.csii.integralmall.bean;

import java.util.List;

public class VoucherCenterBean {

    /**
     * code : 200
     * message : success
     * result : {"resultlist":[{"marketprice":0,"pointprice":200,"productId":153173933402448,"productImageBig":"http://192.168.1.198:80/2018/07/16/82a7c747b5394f4ca6bf9293532d3e27.png","productName":"10元话费","salePrice":0,"subTitle":"200积分兑换"},{"marketprice":20,"pointprice":350,"productId":153173950210876,"productImageBig":"http://192.168.1.198:80/2018/07/16/f9014d48b6aa4136882fcf9057d02336.png","productName":"20元话费","salePrice":0,"subTitle":"350积分兑换"},{"marketprice":30,"pointprice":500,"productId":153174024204176,"productImageBig":"http://192.168.1.198:80/2018/07/16/306252a1f85a4013a6c5d74dda212e46.png","productName":"30元话费","salePrice":0,"subTitle":"500积分兑换"},{"marketprice":50,"pointprice":800,"productId":153174090784329,"productImageBig":"http://192.168.1.198:80/2018/07/16/e3250210baa14bd981caeae975533bf4.png","productName":"50元话费","salePrice":0,"subTitle":"800积分兑换"},{"marketprice":100,"pointprice":1500,"productId":153174098932497,"productImageBig":"http://192.168.1.198:80/2018/07/16/529cd711ca8d449ea2c9043319609d44.png","productName":"100元话费","salePrice":0,"subTitle":"1500积分兑换"},{"marketprice":200,"pointprice":2999,"productId":153174107345156,"productImageBig":"http://192.168.1.198:80/2018/07/16/7d3bfffee7b847ca94d987f166be15ea.png","productName":"200元话费","salePrice":0,"subTitle":"2999积分兑换"},{"marketprice":300,"pointprice":4499,"productId":153174115401702,"productImageBig":"http://192.168.1.198:80/2018/07/16/8391112281bf4e63ae592a87d80024af.png","productName":"300元话费","salePrice":0,"subTitle":"4499积分兑换"},{"marketprice":500,"pointprice":7199,"productId":153174123092273,"productImageBig":"http://192.168.1.198:80/2018/07/16/f5d63d112d7e47d8a17797ce399f39e6.png","productName":"500元话费","salePrice":0,"subTitle":"7199积分兑换"}]}
     * success : true
     * timestamp : 1531986973643
     */

    private int code;
    private String message;
    private ResultBean result;
    private boolean success;
    private long timestamp;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        private List<ResultlistBean> resultlist;

        public List<ResultlistBean> getResultlist() {
            return resultlist;
        }

        public void setResultlist(List<ResultlistBean> resultlist) {
            this.resultlist = resultlist;
        }

        public static class ResultlistBean {
            /**
             * marketprice : 0.0
             * pointprice : 200.0
             * productId : 153173933402448
             * productImageBig : http://192.168.1.198:80/2018/07/16/82a7c747b5394f4ca6bf9293532d3e27.png
             * productName : 10元话费
             * salePrice : 0.0
             * subTitle : 200积分兑换
             */

            private double marketprice;
            private double pointprice;
            private long productId;
            private String productImageBig;
            private String productName;
            private double salePrice;
            private String subTitle;

            public double getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(double marketprice) {
                this.marketprice = marketprice;
            }

            public double getPointprice() {
                return pointprice;
            }

            public void setPointprice(double pointprice) {
                this.pointprice = pointprice;
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

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }
        }
    }
}
