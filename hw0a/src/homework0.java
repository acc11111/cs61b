public  class homework0 {

    public static void starTriangle() {

        for(int i = 0; i < 5; i++) {
            System.out.println("*".repeat(i + 1));
        }
    }

    public static void printIndexed(String s) {
        String output = "";
        for (int i = 0; i < s.length(); i++) {
            output += s.charAt(i);
            output += s.length() - 1 - i;
        }
        System.out.println(output);
    }

    public static String stutter(String s){
        String output = "";
        for(int i =0;i<s.length();i++){
            output += s.charAt(i);
            output += s.charAt(i);
        }
        return output;
    }

    public static int quadrant(int x,int y){
        if(x>0&&y>0){
            return 1;
        } else if (x<0&&y>0) {
            return 2;
        } else if (x<0&&y<0) {
            return 3;
        }else{
            return 4;
        }
    }
    public static void main(String[] args) {
        starTriangle();
        String s = "test for hw0a";
        printIndexed(s);
        System.out.println(stutter(s));
        System.out.println(quadrant(-2,3));
    }
}