package abdn.scnu.cs;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String a=input.next();
        String b=input.next();
        String c=input.next();
        String[] abc={a,b,c};
        RunGame.main(abc);
    }
}
