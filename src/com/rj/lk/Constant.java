package com.rj.lk;

/**
 * 全局变量
 */
public class Constant {
//    public static final String URL_DOMAIN_NAME = "http://10.0.2.2:8080";//本地
    public static final String URL_DOMAIN_NAME = "http://172.20.10.5:8080";
//    public static final String URL_DOMAIN_NAME = "http://192.168.191.2:8080";
//    public static final String URL_DOMAIN_NAME = "http://192.168.1.110:8080";//租的地方


    public static final String URL_NAME = "/aa";
    public static final String URL_ALTERNAME = URL_DOMAIN_NAME + URL_NAME + "/phone/user/update1";
    public static final String URL_ALTER_SEX = URL_DOMAIN_NAME + URL_NAME + "/phone/user/update2";
    public static final String URL_RVALID_IDCARD = URL_DOMAIN_NAME + URL_NAME + "/phone/user/update3";

    public static final String URL_ALTER = URL_DOMAIN_NAME + URL_NAME + "/phone/user/detail";
    public static final String URL_LOGIN = URL_DOMAIN_NAME + URL_NAME + "/phone/login";
    public static final String URL_REGISTER = URL_DOMAIN_NAME + URL_NAME + "/servlet/AddUserServlet";
    public static final String URL_REPORT_SUBMIT = URL_DOMAIN_NAME + URL_NAME + "/phone/bicycle/addDestroyBicycle";
    public static final String URL_USE_BICYCLE = URL_DOMAIN_NAME + URL_NAME + "/phone/bicycle/getBicyclePassword";
    public static final String URL_END_BICYCLE = URL_DOMAIN_NAME + URL_NAME + "/phone/bicycle/endOrder";
    public static final String URL_COST_BICYCLE = URL_DOMAIN_NAME + URL_NAME + "/phone/payment";



    public static final String URL_GET_ACTIVITY = URL_DOMAIN_NAME + URL_NAME + "/phone/activity/getActivityList";

    public static final String REQUEST_RESULT_OK = "1";
    public static final String REQUEST_RESULT_NOTOK = "0";
}
