package mina.king.com.minademo.login.model;

public class LoginModel {

    /**
     * code : 203
     * msg : 账号已注册
     * userInfo : {"userName":"1000","passWord":"lsk;q,f..","nickName":"超级用户","age":100,"sex":"男","accountType":2,"signature":"有事请联系管理员","userState":0}
     */

    private int code;
    private String msg;
    private UserInfoBean userInfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }

    public static class UserInfoBean {
        /**
         * userName : 1000
         * passWord : lsk;q,f..
         * nickName : 超级用户
         * age : 100
         * sex : 男
         * accountType : 2
         * signature : 有事请联系管理员
         * userState : 0
         */

        private String userName;
        private String passWord;
        private String nickName;
        private int age;
        private String sex;
        private int accountType;
        private String signature;
        private int userState;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserState() {
            return userState;
        }

        public void setUserState(int userState) {
            this.userState = userState;
        }

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    ", accountType=" + accountType +
                    ", signature='" + signature + '\'' +
                    ", userState=" + userState +
                    '}';
        }
    }
}
