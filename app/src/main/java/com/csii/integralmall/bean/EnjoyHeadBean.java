package com.csii.integralmall.bean;

import java.util.List;

public class EnjoyHeadBean {


    /**
     * code : 200
     * message : success
     * result : [{"created":"2018-07-12 13:02:19","extendurl":"JFSC://EnjoyBig","icon":"","id":1207,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"潮人品牌","parentId":1222,"remark":"99积分享大牌","sortOrder":35,"status":1,"updated":"2018-07-23 15:47:55"},{"created":"2018-07-12 13:02:44","extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/cjdp1.png","id":1208,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"厨具大牌","parentId":1222,"remark":"低至2折","sortOrder":36,"status":1,"updated":"2018-07-12 13:02:48"},{"created":"2018-07-12 13:03:07","extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/smdp.png","id":1209,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"数码大牌","parentId":1222,"remark":"低至8折","sortOrder":37,"status":1,"updated":"2018-07-12 13:03:09"},{"extendurl":"JFSC://ClassGoodsList","icon":"http://192.168.1.198:80/images/imgs/jfdp.png","id":1210,"image":"","image1":"","image2":"","image3":"","isParent":false,"name":"家纺大牌","parentId":1222,"remark":"低至4折","sortOrder":38,"status":1}]
     * success : true
     * timestamp : 1532419964591
     */

    private int code;
    private String message;
    private boolean success;
    private long timestamp;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
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
}
