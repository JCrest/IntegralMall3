package com.csii.integralmall.common;

public class Api {

//    测试机器上ip端口是   192.168.1.198:7777
//    生产机器上ip端口是   192.168.1.107:7777

    public static final String SERVER_IP = "192.168.1.198:7777";
//    public static final String SERVER_IP ="192.168.1.107:7777";

    //     基本路径
    public static final String BASE_URL = "http://" + SERVER_IP;

    //    用户注册 （post请求）
    public static final String REGISTER = BASE_URL + "/member/register";

    //    用户登陆（post请求）
    public static final String LOGIN = BASE_URL + "/member/login";

    //    退出登录 （get请求）
    public static final String LOGINOUT = BASE_URL + "/member/loginOut";

    //    用户找回密码 （post请求）
    public static final String USERFINDPASSWORD = BASE_URL + "/member/userfindpassword";

    //    修改密码（post请求）
    public static final String MODIFYPASSWORD = BASE_URL + "/member/modifypassword";

    //修改手机号（post请求）
    public static final String MODIFYPHONE = BASE_URL + "/member/modifyphone";

    //    修改昵称（post请求）
    public static final String NICKNAMESET = BASE_URL + "/member/nicknameset";

    //    用户头像上传（post请求）
    public static final String IMGAEUPLOAD = BASE_URL + "/member/imgaeUpload";

    //    商品列表展示（post请求）
    public static final String ALLGOODS = BASE_URL + "goods/allGoods";

    //    商品详情展示接口（get请求）
    public static final String PRODUCTDET = BASE_URL + "/goods/productDet";

    //    意见反馈接口（post请求）
    public static final String SUGGESTION = BASE_URL + "/member/suggestion";

    //    收货地址列表（post请求）
    public static final String ADDRESSLIST = BASE_URL + "/member/addresslist";

    //    收货地址添加（post请求）
    public static final String ADDRESSADD = BASE_URL + "/member/addressadd";

    //    收货地址修改（post请求）
    public static final String ADDRESSEDIT = BASE_URL + "/member/addressedit";

    //    设置为默认收货地址（post请求）
    public static final String ADDRESS_SET_DEFAULT = BASE_URL + "/member/address_set_default";

    //    收货地址删除（post请求）
    public static final String ADDRESSDELETE = BASE_URL + "/member/addressdelete";

    //    翻牌的提前准备，给前端准备需求的礼品信息（post请求）
    public static final String PREPAREPRESENT = BASE_URL + "/game/preparepresent";

    //    翻牌获奖的接口，翻牌之后客户端把获取的奖品详情通过 json 的方式传递过来（post请求）
    public static final String WINAWARD = BASE_URL + "/game/winaward";

    //    准备签到日历表的接口（post请求）
    public static final String ATTENDENCE_PREPARE = BASE_URL + "/game/attendence_prepare";

    //    签到成功的接口（post请求）
    public static final String ATTENDENCE = BASE_URL + "/game/attendence";

    //    订单列表的接口（post请求）
    public static final String ORDERLIST = BASE_URL + "/member/orderList";

    //    订单详情的接口（post请求）
    public static final String ORDERDETAIL = BASE_URL + "/member/orderDetail";

    //    删除订单的接口（post请求）
    public static final String DELORDER = BASE_URL + "/member/delOrder";

    //    取消订单的接口（post请求）
    public static final String CANCELORDER = BASE_URL + "/member/cancelOrder";

    //    首页banner返回内容数据的接口（get请求）
    public static final String INDEXBANNER = BASE_URL + "/index/indexbanner";

    //    首页的下面板块的返回内容数据的接口（get请求）
    public static final String HOMEPAGE = BASE_URL + "/index/homepage";

    //    根据父分类id获取到对应子分类的所有信息（享大牌头部数据 id = 1199  get请求）
    public static final String GETCATBYPARENTID = BASE_URL + "/index/getCatByParentId";

    //    享大牌 返回内容数据的接口（get请求）
    public static final String GETCATANDPRODUCTBYPARENTID = BASE_URL + "/index/getCatAndProductByParentId";

    //    充值中心 充话费（充话费 selfid=1234  充流量 selfid=1235  get请求）
    public static final String GETPRODUCTSBYSELFID = BASE_URL + "/index/getProductsBySelfId";

    //    积分账单的接口（可以接收  form-data 或者 x-www-form-urlencoded 类型的数据 post请求）
    public static final String POINTS_RECORD_LIST = BASE_URL + "/points/points_record_list";


}
