package com.jeason.java.review.algorithm;


/**
 *
 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

 任何左括号 ( 必须有相应的右括号 )。
 任何右括号 ) 必须有相应的左括号 ( 。
 左括号 ( 必须在对应的右括号之前 )。
 * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 一个空字符串也被视为有效字符串。
 示例 1:

 输入: "()"
 输出: True
 示例 2:

 输入: "(*)"
 输出: True
 示例 3:

 输入: "(*))"
 输出: True
 **/

public class JudgeStringIsValid {
    public static int stackTop = -1;
    public static char[] options = new char[]{'(', ')', ' '};

    public static boolean checkValidString(String s) {
        if (stackTop < -1) {
            return false;
        }

        if ("".equals(s)) {
            if (stackTop == -1) {
                return true;
            } else {
                return false;
            }
        }

        char curC = s.charAt(0);
        String nextS = "";
        if (s.length() == 1) {
            nextS = "";
        } else {
            nextS = s.substring(1);
        }

        if (curC == '(') {
            stackTop++;
        } else if (curC == ')') {
            stackTop--;
        } else if (curC == '*') {
            int top = stackTop;
            for (char c : options) {
                stackTop = top;
                if (c == '(') {
                    stackTop++;
                } else if (c == ')') {
                    stackTop--;
                }

                if (checkValidString(nextS)) {
                    return true;
                }
            }
            return false;
        }

        return checkValidString(nextS);
    }

    public static void main(String[] args) {
        boolean ok = checkValidString(" )(");
        System.out.println(ok);
    }
}
