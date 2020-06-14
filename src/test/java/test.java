public class test {
    public static void main(String[] args) {
        System.out.println("hello world~!");
        System.out.println(method1());
    }

    public static String method1(){
        String str = "";
        str = 1 != 1 ? "我是第一个值" : "我是第二个值";
        return str;
    }

}
