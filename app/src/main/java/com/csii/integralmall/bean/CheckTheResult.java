package com.csii.integralmall.bean;

import java.util.List;

public class CheckTheResult {


    /**
     * code : 200
     * message : 查询到用户签到数据!!
     * result : {"first_day_ofmonth":0,"issigntoday":1,"nowtimestamp":1532062407294,"signincount":1,"tbslist":[{"createtime":"2018-07-20 12:41:30","day_of_week":"5","presentstate":"","signinstate":"1","tbmemberid":100,"tbmembersigninid":17}]}
     * success : true
     * timestamp : 1532062407294
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
        /**
         * first_day_ofmonth : 0
         * issigntoday : 1
         * nowtimestamp : 1532062407294
         * signincount : 1
         * tbslist : [{"createtime":"2018-07-20 12:41:30","day_of_week":"5","presentstate":"","signinstate":"1","tbmemberid":100,"tbmembersigninid":17}]
         */

        private int first_day_ofmonth;
        private int issigntoday;
        private long nowtimestamp;
        private int signincount;
        private List<TbslistBean> tbslist;

        public int getFirst_day_ofmonth() {
            return first_day_ofmonth;
        }

        public void setFirst_day_ofmonth(int first_day_ofmonth) {
            this.first_day_ofmonth = first_day_ofmonth;
        }

        public int getIssigntoday() {
            return issigntoday;
        }

        public void setIssigntoday(int issigntoday) {
            this.issigntoday = issigntoday;
        }

        public long getNowtimestamp() {
            return nowtimestamp;
        }

        public void setNowtimestamp(long nowtimestamp) {
            this.nowtimestamp = nowtimestamp;
        }

        public int getSignincount() {
            return signincount;
        }

        public void setSignincount(int signincount) {
            this.signincount = signincount;
        }

        public List<TbslistBean> getTbslist() {
            return tbslist;
        }

        public void setTbslist(List<TbslistBean> tbslist) {
            this.tbslist = tbslist;
        }

        public static class TbslistBean {
            /**
             * createtime : 2018-07-20 12:41:30
             * day_of_week : 5
             * presentstate :
             * signinstate : 1
             * tbmemberid : 100
             * tbmembersigninid : 17
             */

            private String createtime;
            private String day_of_week;
            private String presentstate;
            private String signinstate;
            private int tbmemberid;
            private int tbmembersigninid;

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDay_of_week() {
                return day_of_week;
            }

            public void setDay_of_week(String day_of_week) {
                this.day_of_week = day_of_week;
            }

            public String getPresentstate() {
                return presentstate;
            }

            public void setPresentstate(String presentstate) {
                this.presentstate = presentstate;
            }

            public String getSigninstate() {
                return signinstate;
            }

            public void setSigninstate(String signinstate) {
                this.signinstate = signinstate;
            }

            public int getTbmemberid() {
                return tbmemberid;
            }

            public void setTbmemberid(int tbmemberid) {
                this.tbmemberid = tbmemberid;
            }

            public int getTbmembersigninid() {
                return tbmembersigninid;
            }

            public void setTbmembersigninid(int tbmembersigninid) {
                this.tbmembersigninid = tbmembersigninid;
            }
        }
    }
}
