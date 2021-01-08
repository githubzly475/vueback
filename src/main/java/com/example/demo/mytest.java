package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

/**
 * @Description :
 * @Date: 2020-12-08
 */
public class mytest {

    public static void main(String[] args) {
     /*   BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String aa=encoder.encode("123456");
        System.out.println(aa);*/

        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();
        ArrayList<String> xt = new ArrayList<>();
        ArrayList<String> bt = new ArrayList<>();
        a1.add("a");
        a1.add("b");
        a1.add("c");
        a1.add("d");
        a1.add("e");

        a2.add("e");
        a2.add("a");
        a2.add("b");

     /*   a1.retainAll(a2);
        System.out.println(a1);*/

        for (int i = 0; i <a1.size() ; i++) {
            boolean flag=true;
            for (int j = 0; j <a2.size() ; j++) {
                if(a1.get(i).equals(a2.get(j))){
                    flag=false;
                    xt.add(a1.get(i));
                    break;
                }
            }
            if(flag){
            bt.add(a1.get(i));
            }
        }

        System.out.println("ddd"+xt);
        System.out.println(bt);
    }
}
