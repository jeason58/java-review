package com.jeason.java.review.interview.yuncunchu;

/**
 * @Auther: jeason
 * @Date: 2018/10/30 20:13
 * @Description: 括号表达式格式判断：输入一个字符串或者byte[]，输出true/false
 * 比如：2-{(4+5)*[(6+8/4+3]} 正确
 *      {3-(6/2-1]} 错误
 */
public class BracketsExpressionJudgeDemo {

    // 判断括号表达式是否正确
    public static boolean isExpressionCorrect(String expression) {
        char[] charStack = new char[expression.length()/2 + 1];
        int top = -1;
        for (int k = 0; k < expression.length(); k++) {
            char ch = expression.charAt(k);
            if (ch == '{' || ch == '[' || ch == '(') {
                charStack[++top] = ch;
            } else if (ch == '}' || ch == ']' || ch == ')') {
                int pos = top;
                if (ch == ')') {  // 如果新字符为“右圆括号”
                    while (pos >= 0) {
                        if (charStack[pos--] == '(') {
                            top --;
                            break;
                        }
                    }
                } else if (ch == ']') { // 如果新字符为“右方括号”
                    while (pos >= 0) {
                        if (charStack[pos--] == '(') {
                            return false;
                        }
                    }
                    //重置pos位置
                    pos = top;
                    while (pos >= 0) {
                        if (charStack[pos--] == '[') {
                            top --;
                            break;
                        }
                    }
                } else if (ch == '}') {
                    while (pos >= 0) {
                        if (charStack[pos] == '[' || charStack[pos--] == '(') {
                            return false;
                        }
                    }
                    // 重置pos位置
                    pos = top;
                    while (pos >= 0) {
                        if (charStack[pos--] == '{') {
                            top --;
                            break;
                        }
                    }
                }
            }
        }

        if (top != -1) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String expression = "23*[24/(3+4/2)]-(5*8+3)";
        boolean isOk = isExpressionCorrect(expression);
        System.out.println("isOk: " + isOk);
    }
}
