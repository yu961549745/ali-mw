package yjt.diamond;

/**
 * @author chengxu
 */
public class ConfigBean {
    private String stringValue;
    private int intValue;
    private boolean booleanValue;
    private User user;
    private String chineseValue;

    public static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChineseValue() {
        return chineseValue;
    }

    public void setChineseValue(String chineseValue) {
        this.chineseValue = chineseValue;
    }
}
