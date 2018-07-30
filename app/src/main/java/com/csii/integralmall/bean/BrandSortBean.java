package com.csii.integralmall.bean;

import java.util.List;

public class BrandSortBean {

    /**
     * code : 200
     * message : success
     * result : {"resultlist":[{"tbItemCat":{"created":"2018-07-12 13:02:19","extendurl":"JFSC://EnjoyBig","icon":"","id":1207,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"潮人品牌","parentId":1222,"remark":"99积分享大牌","sortOrder":35,"status":1,"updated":"2018-07-23 15:47:55"},"tbItemList":[{"marketprice":239,"pointprice":0,"productId":153233267392212,"productImageBig":"http://192.168.1.198:80/2018/07/23/e6821eecd17a47bdbd80b23881939bc4.jpg","productName":"花花公子长袖衬衫男韩版修身印花衬衣","salePrice":150,"subTitle":"2018春装青年男装免烫寸衫潮"}]},{"tbItemCat":{"created":"2018-07-12 13:02:44","extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/cjdp1.png","id":1208,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"厨具大牌","parentId":1222,"remark":"低至2折","sortOrder":36,"status":1,"updated":"2018-07-12 13:02:48"},"tbItemList":[{"marketprice":0,"pointprice":0,"productId":153148204407276,"productImageBig":"http://192.168.1.198:80/2018/07/13/197bc306b347464e9dd80c382949625f.png","productName":"炊大皇平底不粘煎锅","salePrice":69,"subTitle":"炊大皇平底不粘煎锅"},{"marketprice":279,"pointprice":0,"productId":153148210844586,"productImageBig":"http://192.168.1.198:80/2018/07/13/174fe0aeb5fc4d69b35ac80bfecad439.png","productName":"康宁晶彩透明玻璃锅","salePrice":99,"subTitle":"康宁晶彩透明玻璃锅"},{"marketprice":0,"pointprice":0,"productId":153148195844843,"productImageBig":"http://192.168.1.198:80/2018/07/13/e9a3dde4f4e14e25b487383a5e5a8458.png","productName":"苏泊尔22cm不粘汤锅","salePrice":99,"subTitle":"苏泊尔22cm不粘汤锅"}]},{"tbItemCat":{"created":"2018-07-12 13:03:07","extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/smdp.png","id":1209,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"数码大牌","parentId":1222,"remark":"低至8折","sortOrder":37,"status":1,"updated":"2018-07-12 13:03:09"},"tbItemList":[{"marketprice":0,"pointprice":0,"productId":153148338308251,"productImageBig":"http://192.168.1.198:80/2018/07/13/420cb8578bfe4af8bbcf0205e33d0707.png","productName":"尼康J5微单相机","salePrice":999,"subTitle":"尼康J5微单相机"},{"marketprice":0,"pointprice":0,"productId":153148331816564,"productImageBig":"http://192.168.1.198:80/2018/07/13/34670635599641929364835ee7de2850.png","productName":"哈曼卡人工智能音箱","salePrice":1399,"subTitle":"哈曼卡人工智能音箱"},{"marketprice":0,"pointprice":0,"productId":153148344998047,"productImageBig":"http://192.168.1.198:80/2018/07/13/f5d80bf5cbfa41f888d28816330d6b86.png","productName":"Beats X蓝牙运动耳机","salePrice":1599,"subTitle":"Beats X蓝牙运动耳机"}]},{"tbItemCat":{"extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/jfdp.png","id":1210,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"家纺大牌","parentId":1222,"remark":"低至4折","sortOrder":38,"status":1},"tbItemList":[{"marketprice":39.8,"pointprice":99,"productId":153232913590297,"productImageBig":"http://192.168.1.198:80/2018/07/23/4bcd153b87594aa2b3b26d50977c2eac.png","productName":"思侬家纺前卫枕芯一对","salePrice":9,"subTitle":"枕芯一对"},{"marketprice":0,"pointprice":99,"productId":153148356881782,"productImageBig":"http://192.168.1.198:80/2018/07/13/35c999ee6acb4edfb4923716f2a26e09.png","productName":"御棉堂全针织毛巾被","salePrice":59,"subTitle":"御棉堂全针织毛巾被"}]}]}
     * success : true
     * timestamp : 1532419225317
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
             * tbItemCat : {"created":"2018-07-12 13:02:19","extendurl":"JFSC://EnjoyBig","icon":"","id":1207,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"潮人品牌","parentId":1222,"remark":"99积分享大牌","sortOrder":35,"status":1,"updated":"2018-07-23 15:47:55"}
             * tbItemList : [{"marketprice":239,"pointprice":0,"productId":153233267392212,"productImageBig":"http://192.168.1.198:80/2018/07/23/e6821eecd17a47bdbd80b23881939bc4.jpg","productName":"花花公子长袖衬衫男韩版修身印花衬衣","salePrice":150,"subTitle":"2018春装青年男装免烫寸衫潮"}]
             */

            private TbItemCatBean tbItemCat;
            private List<TbItemListBean> tbItemList;

            public TbItemCatBean getTbItemCat() {
                return tbItemCat;
            }

            public void setTbItemCat(TbItemCatBean tbItemCat) {
                this.tbItemCat = tbItemCat;
            }

            public List<TbItemListBean> getTbItemList() {
                return tbItemList;
            }

            public void setTbItemList(List<TbItemListBean> tbItemList) {
                this.tbItemList = tbItemList;
            }

            public static class TbItemCatBean {
                /**
                 * created : 2018-07-12 13:02:19
                 * extendurl : JFSC://EnjoyBig
                 * icon :
                 * id : 1207
                 * image :
                 * image1 :
                 * image2 :
                 * image3 :
                 * isParent : false
                 * name : 潮人品牌
                 * parentId : 1222
                 * remark : 99积分享大牌
                 * sortOrder : 35
                 * status : 1
                 * updated : 2018-07-23 15:47:55
                 */

                private String created;
                private String extendurl;
                private String icon;
                private int id;
                private String image;
                private String image1;
                private String image2;
                private String image3;
                private boolean isParent;
                private String name;
                private int parentId;
                private String remark;
                private int sortOrder;
                private int status;
                private String updated;

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getExtendurl() {
                    return extendurl;
                }

                public void setExtendurl(String extendurl) {
                    this.extendurl = extendurl;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getImage1() {
                    return image1;
                }

                public void setImage1(String image1) {
                    this.image1 = image1;
                }

                public String getImage2() {
                    return image2;
                }

                public void setImage2(String image2) {
                    this.image2 = image2;
                }

                public String getImage3() {
                    return image3;
                }

                public void setImage3(String image3) {
                    this.image3 = image3;
                }

                public boolean isIsParent() {
                    return isParent;
                }

                public void setIsParent(boolean isParent) {
                    this.isParent = isParent;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public int getSortOrder() {
                    return sortOrder;
                }

                public void setSortOrder(int sortOrder) {
                    this.sortOrder = sortOrder;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getUpdated() {
                    return updated;
                }

                public void setUpdated(String updated) {
                    this.updated = updated;
                }
            }

            public static class TbItemListBean {
                /**
                 * marketprice : 239.0
                 * pointprice : 0.0
                 * productId : 153233267392212
                 * productImageBig : http://192.168.1.198:80/2018/07/23/e6821eecd17a47bdbd80b23881939bc4.jpg
                 * productName : 花花公子长袖衬衫男韩版修身印花衬衣
                 * salePrice : 150.0
                 * subTitle : 2018春装青年男装免烫寸衫潮
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
}

