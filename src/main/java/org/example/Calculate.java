package org.example;
import java.util.Stack;

/**
 * Calculate class, which calculates the value of an expression.
 * It supports basic arithmetic operations (+, -, *, /) and parentheses to determine operator precedence.
 * The expression is first checked, converted to postfix notation, and then evaluated.
 */
public class Calculate {
    /** Mathematical expression to be calculated */
    private String exp;

    /**
     * Constructor to initialize the Calculate object with a mathematical expression.
     *
     * @param str the input mathematical expression as a string
     */
    Calculate(String str){
        this.exp = str;
    }

    /**
     * Removes all spaces from the mathematical expression.
     */
    void delSpace(){
        StringBuilder strNew = new StringBuilder();
        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) != ' '){
                strNew.append(exp.charAt(i));
            }
        }
        exp = strNew.toString();
    }

    /**
     * Checks if the expression is valid.
     * This method verifies if the expression contains correct operators, balanced parentheses,
     * and proper use of numbers and symbols.
     *
     * @return true if the expression is valid, false otherwise
     */
    private boolean check(){
        if (exp.isEmpty()) return false;

        delSpace();

        int bracket = 0;

        char prevChar = ' ';

        for (int i = 0; i < exp.length(); i++) {
            if (bracket >= 0){

                switch (exp.charAt(i)) {
                    case '.': {
                        return false;
                    }
                    case '+': case '-': case '*': case '/':  {
                        if(i == 0 || i == exp.length() - 1) return false;
                        else
                        if ("+-*/)".indexOf(exp.charAt(i+1)) != -1)
                            return false;
                        break;
                    }

                    case '(': {
                        bracket++;
                        if ("+-*/)".indexOf(exp.charAt(i+1)) != -1)
                            return false;
                        else if (i == exp.length() - 1) return false;
                        break;
                    }

                    case ')': {
                        bracket--;
                        if (i == 0) return false;
                        else if( "+-*/(".indexOf(exp.charAt(i-1)) != -1)
                            return false;
                        break;
                    }

                    default:
                        if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                            if (i != 0)
                                if (exp.charAt(i-1) == ')' )
                                    return false;
                            if (i != exp.length()-1)
                                if (exp.charAt(i+1) == '(' )
                                    return false;
                        }
                        else return false;
                }
            }
            else return false;
        }
        return bracket == 0;
    }

    /**
     * Returns the operator's priority. Higher priority operators are calculated first.
     *
     * @param operator the operator character ('+', '-', '*', '/')
     * @return 2 for '*' and '/', 1 for '+' and '-', 0 otherwise
     */
    private int getOperatorPriority(char operator) {
        if (operator == '*' || operator == '/') return 2;
        if (operator == '+' || operator == '-') return 1;
        return 0;
    }


    /**
     * Converts the mathematical expression from infix to postfix notation.
     * @return the postfix expression as a string
     */
    private String toPostfix() {

        Stack<Character> operators = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char currentChar = exp.charAt(i);

            if (currentChar >= '0' && currentChar <= '9') {
                result.append(currentChar);
            } else if (currentChar == '(') {
                operators.push(currentChar);
            } else if (currentChar == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    result.append(' ').append(operators.pop());
                }
                operators.pop();
            } else if ("+-*/".indexOf(currentChar) != -1) {
                result.append(' ');
                while (!operators.isEmpty() && getOperatorPriority(operators.peek()) >= getOperatorPriority(currentChar)) {
                    result.append(operators.pop()).append(' ');
                }
                operators.push(currentChar);
            }
        }

        while (!operators.isEmpty()) {
            result.append(' ').append(operators.pop());
        }

        return result.toString();
    }

    /**
     * Computes the final result of the mathematical expression.
     * First, it checks the validity of the expression, then converts it to postfix notation,
     * and finally evaluates the result.
     *
     * @return the result of the computation as a string, or an error message if the expression is invalid
     */
    private double evaluatePostfix(String postfixExpr) {
        Stack<Double> operands = new Stack<>();
        StringBuilder operand = new StringBuilder();

        for (int i = 0; i < postfixExpr.length(); i++) {
            char currentChar = postfixExpr.charAt(i);

            if (currentChar >= '0' && currentChar <= '9') {
                operand.append(currentChar);
            } else if (currentChar == ' ') {
                if (!operand.isEmpty()) {
                    operands.push(Double.parseDouble(operand.toString()));
                    operand = new StringBuilder();
                }
            } else if ("+-*/".indexOf(currentChar) != -1) {
                double num2 = operands.pop();
                double num1 = operands.pop();
                switch (currentChar) {
                    case '+':
                        operands.push(num1 + num2);
                        break;
                    case '-':
                        operands.push(num1 - num2);
                        break;
                    case '*':
                        operands.push(num1 * num2);
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Ошибка: Деление на ноль");
                        }
                        operands.push(num1 / num2);
                        break;
                }
            }
        }

        return operands.pop();
    }

    /**
     * Computes the final result of the mathematical expression.
     * First, it checks the validity of the expression, then converts it to postfix notation,
     * and finally evaluates the result.
     *
     * @return the result of the computation as a string, or an error message if the expression is invalid
     */
    public String compute() {
        if (!check()) {
            return "Ошибка: Некорректное выражение.";
        }

        try {
            String postfixExpr = toPostfix();
            double result = evaluatePostfix(postfixExpr);
            return Double.toString(result);
        } catch (ArithmeticException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the string representation of the expression.
     *
     * @return the original expression as a string
     */
    @Override
    public String toString() {
        return exp;
    }

}
