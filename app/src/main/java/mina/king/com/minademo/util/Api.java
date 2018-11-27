package mina.king.com.minademo.util;

public class Api {
    public static String host = "http://192.168.0.100:9090";
//    public static String host = "http://192.168.200.112:9090";
    public static String register = host+"/user/register";//注册
    public static String code = host+"/user/code";//获取验证码
    public static String login = host+"/user/login";//登陆
    public static String users = host+"/user/users";//查询所有患者列表
}
