package com.compassecg.test720.compassecg.unitl;

/**
 * Created by anim on 2016/6/l1.
 */
public class Connector {
    int cityid = 0;

    public static String hedaer = "http://120.26.141.238/syqapp/index.php/home/index/getHeaderImg/user_account/";
    public static String lll = "http://192.168.1.177/ygxd";
//    public static String staticUrll = "http://120.26.141.238/ygxd/ygxd.php/";//服务器地址*/


    public static String staticUrll = "http://192.168.1.177/ygxd/ygxd.php/";//内网地址

    public static String loginindex = staticUrll + "Index/";
    public static String getIndex = loginindex + "index";//首页
    public static String Login = staticUrll + "Login/";  //登录
    public static String gainCode = Login + "gainCode";//获取验证码
    public static String zhuce = Login + "zhuce";//注册
    public static String login = Login + "login";//登录
    public static String editPwd = Login + "editPwd";//忘记密码


    public static String Personage = staticUrll + "Personage/";//个人中心
    public static String Personindex = Personage + "index";//个人信息
    public static String Personinfo = Personage + "info";//个人简介
    public static String editInfo = Personage + "editInfo";//修改个人信息

    public static String PersoneditPwd = Personage + "editPwd";//修改密码
    public static String editPhone = Personage + "editPhone";//修改手机号
    public static String attestation = Personage + "attestation";//医生认证申请

    public static String homeInfo = Personage + "homeInfo";//个人主页简介
    public static String group = Personage + "group";//他的组

    public static String applyGroup = Personage + "applyGroup";//申请加组
    public static String answer = Personage + "answer";//个人主页-回答列表(会诊)
    public static String feedback = Personage + "feedback";//意见反馈
    public static String magazine = Personage + "magazine";//我的杂志
    public static String courseware = Personage + "courseware";//我的课件
//    public static String staticUrl = "http://192.168.1.178/ygxd/ygxd.php/";

    //论坛
    public static String Forum = staticUrll + "Forum/";
    public static String index = Forum + "index";  //论坛搜索
    public static String problemDetails = Forum + "problemDetails";  //帖子(论坛)详情
    public static String answerDetails = Forum + "answerDetails";  //回答详情
    public static String report = Forum + "report";  //举报—论坛&会诊
    public static String addProblem = Forum + "addProblem";  //添加问题
    public static String addAnswer = Forum + "addAnswer";  //添加回答(论坛、会诊)
    public static String delProblem = Forum + "delProblem";  //删除问题—论坛&会诊
    public static String delAnswer = Forum + "delAnswer";  //删除回答—论坛&会诊
    public static String zan = Forum + "zan";//点赞
    public static String addComment = Forum + "addComment";
    public static String Doctor = staticUrll + "Doctor/";//
    public static String collectProblem = Doctor + "collectProblem";
    public static String addGuanzhu = Doctor + "addGuanzhu";//添加关注
    public static String myGroup = Doctor + "myGroup";//我的组列表
    public static String sendInvite = Doctor + "sendInvite";//发送邀请进组消息
    public static String doctorList = Doctor + "doctorList";//医生列表
    public static String groupOut = Doctor + "groupOut";//退出组


    public static String Study = staticUrll + "Study/";//学习中心
    public static String Studyindex = Study + "index";//学习中心首页
    public static String pdfDetails = Study + "pdfDetails";//pdf详情
    public static String Studymore = Study + "more";//课件杂志更多

}
