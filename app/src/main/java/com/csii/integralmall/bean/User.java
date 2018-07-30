package com.csii.integralmall.bean;

public class User {

    /**
     * success : true
     * message : success
     * code : 200
     * timestamp : 1530180621178
     * result : {"id":79,"username":"aaa2","phone":"17622222222","email":null,"sex":null,"address":null,"file":null,"description":null,"points":null,"balance":null,"state":1,"token":"d3b102bb-2038-4365-b41b-a8cbf5f0e8f2","message":null,"wechatnum":null,"blognum":null}
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private ResultBean result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 79
         * username : aaa2
         * phone : 17622222222
         * email : null
         * sex : null
         * address : null
         * file : null
         * description : null
         * points : null
         * balance : null
         * state : 1
         * token : d3b102bb-2038-4365-b41b-a8cbf5f0e8f2
         * message : null
         * wechatnum : null
         * blognum : null
         */

        private int id;
        private String username;
        private String phone;
        private Object email;
        private Object sex;
        private Object address;
        private Object file;
        private Object description;
        private Object points;
        private Object balance;
        private int state;
        private String token;
        private Object message;
        private Object wechatnum;
        private Object blognum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getPoints() {
            return points;
        }

        public void setPoints(Object points) {
            this.points = points;
        }

        public Object getBalance() {
            return balance;
        }

        public void setBalance(Object balance) {
            this.balance = balance;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }

        public Object getWechatnum() {
            return wechatnum;
        }

        public void setWechatnum(Object wechatnum) {
            this.wechatnum = wechatnum;
        }

        public Object getBlognum() {
            return blognum;
        }

        public void setBlognum(Object blognum) {
            this.blognum = blognum;
        }
    }
}
