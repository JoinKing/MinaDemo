package mina.king.com.minademo.users.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

public class UsersBean {


    /**
     * code : 200
     * msg : 成功
     * list : [{"userName":"1000","passWord":"lsk;q,f..","nickName":"超级用户","age":100,"sex":"男","accountType":2,"signature":"有事请联系管理员","userState":0},{"userName":"17688831088","passWord":"Aa1234","nickName":"","headImage":"","age":0,"sex":"","accountType":0,"signature":"","userState":0,"email":"viphwq@163.com"}]
     */

    private int code;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UsersBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", list=" + list +
                '}';
    }

    public static class ListBean extends BaseObservable {
        /**
         * userName : 1000
         * passWord : lsk;q,f..
         * nickName : 超级用户
         * age : 100
         * sex : 男
         * accountType : 2
         * signature : 有事请联系管理员
         * userState : 0
         * headImage :
         * email : viphwq@163.com
         */

        private String userName;
        private String passWord;
        private String nickName;
        private int age;
        private String sex;
        private int accountType;
        private String signature;
        private int userState;
        private String headImage;
        private String email;

        @Bindable
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

        @Bindable
        public String getNickName() {

            return nickName;
        }

        public void setNickName(String nickName) {
            if (null==nickName||nickName.isEmpty()){
                this.nickName = userName;
            }else {
                this.nickName = nickName;
            }
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

        @Bindable
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

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    ", accountType=" + accountType +
                    ", signature='" + signature + '\'' +
                    ", userState=" + userState +
                    ", headImage='" + headImage + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
