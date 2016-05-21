/* David Li V00818631
*  InfixCalc.java
*  This program takes infix expressions converts them into a postfix expression for evaluation
*
*
*/
//add modolus
public class InfixCalc {
    /**
     * First ensure the arithmetic operations plus parentheses
     * are surrounded by spaces. Then go ahead and split up the
     * whole expression using spaces (i.e, the operands will be
     * nicely separated from everything else by at least a single
     * space). This will not work for negative numbers.
     */
	 //this method will check the precedence of the operators
	private static int checkPrecedence(String op) {
		switch (op) {
			case "+":
			case "-":
				return 1;
			case "*":
			case "/":
				return 2;
			case "^":
				return 3;
/* 			case "(":
			case ")":
				return 4; */
			default:
				throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}

	private static boolean isOperator(String s) {
		String operators = "*/+-^";
		if(operators.indexOf(s) != -1) {
			return true;
		} 
		else {
			return false;
		}
	}
	private static boolean isOperand(String s) {
		return s.matches("-?\\d+");  //match a number with optional '-' and decimal.
	}
	//this function will convert an infix expression into postfix
	public static StringList toPostFix(String[] tokens) {
		StringList alist = new StringList();
		StringStackRefBased stack = new StringStackRefBased();
		String popped = "";
		String peekTop = "";
		String openParen = "";
		String ch = "";
			//loop for the length of the expression
			for(int i = 0; i<tokens.length; i++) {
				ch = tokens[i];
				try{
					// add operands to the list
					if(isOperand(ch)) {
						alist.insertTail(ch);
					}
					//add brackets to the stack
					else if((ch.equals("("))) {
						stack.push((ch));
					}
					//add operators to the stack if the stack is empty
					else if(isOperator((ch))){
						if(stack.isEmpty()) {
							stack.push(ch);
						}
						else {
							/*pop operators from the stack the precedence of the current character is less than the top of the stack and
							the stack is not empty and the current item on the stack is not a "("
							*/
							peekTop = stack.peek();
							while(!peekTop.equals("(") &&checkPrecedence(peekTop) >= checkPrecedence(ch) && !stack.isEmpty()) {
								peekTop = stack.peek();
								
								//if an exponent is encountered keep it on the stack until a non exponent operator is reached
								if(!ch.equals("^")) {
									popped = stack.pop();
									alist.insertTail(popped);
								}
								else{
									break;
								}

								if(!(stack.isEmpty())) {
									peekTop = stack.peek();
								}
							}
							//add the new operator to the stack
							stack.push(ch); 
						}
					}
					
					//pop until the closing "(" is reached	
					else if((ch.equals(")"))) {
						//go until finding the opening brace
						peekTop = stack.peek();
						while(!peekTop.equals("("))
						{
							popped = stack.pop();
							alist.insertTail(popped);
							if(!(stack.isEmpty())) {
								peekTop = stack.peek();
							}
						}
						openParen = stack.pop();
					}
					//add anything that is not operand, operator or parentheses to the stack
					else {
						stack.push(ch);
					}

				}
				catch(StringStackException e){
					System.out.println(e);
				}
			}
		//add all the other items to the stack
		try {
			while(!(stack.isEmpty())) {
				alist.insertTail(stack.pop());	
			}
		}
		catch(StringStackException e){
			System.out.println(e);
		}
		return alist;
	}
	
    public static String[] tokenize(String s) {
        // Note the missing minus character...
        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/",
            "\\^"};

        // First eliminate any quotation marks
        s = s.replaceAll("'", " ");
        s = s.replaceAll("\"", " ");

        // Now all operators or parentheses, surround the character
        // with a single space on either side.
        for (int i = 0; i < symbols.length; i++) {
            String regex = symbols[i];
            String replace = " " + regex + " ";
            s = s.replaceAll(regex, replace);
        }

        // Special case: If there is a unary minus, then then
        // what appears to the right of the symbol is whitespace
        // and a digit; what appears to the left whitespace
        // and a non-digit symbol.
        s = s.replaceAll("(^|([\\(\\+\\-\\*\\/]))\\s+\\-\\s+(\\d+)", "$1 -$3");

        // Eliminate extra whitespaces at start and end of the
        // transformed string. 
        s = s.trim();

        // Finally, take advantage of the whitespace to create an
        // array of strings where each item in the array is one
        // of the elements in the original string passed in to this
        // method.
        return s.split("\\s+");
    }

	//evaluate the postfix expression
    public static String evaluatePostfix(StringList expr) {

		int r = 0;
		String operator = "";
		String result = ""; 
		String popped = "";
		//first and second integers in the infix expression
		int secondInfix = 0;
		int firstInfix = 0;
		//stop processing if there is only a single item in the list
		if(expr.getLength() == 1) {
			result = (expr.retrieve(0));
			return result;
		}
		//stackRef stores the tokens from the infix order
		StringStackRefBased stackRef = new StringStackRefBased();
		StringStackRefBased stackCalc = new StringStackRefBased();
		String peekTop = "";
		try {
			//place items onto the stack in postfix order
			for(int q = 1; q<= expr.getLength(); q++) {
				int curr = expr.getLength()-q;
				stackRef.push(expr.retrieve(curr));
			}
			//perform operators equal to the number of tokens divided by 2
			for(int p=0;p<=expr.getLength()/2;p++) {
				if(!stackRef.isEmpty()) {
					peekTop = stackRef.peek();
				}
				//add operands to stackCalc 
				while(!isOperator(peekTop)) {
					if(!stackRef.isEmpty()) {
						popped = stackRef.pop();
						stackCalc.push(popped);
						if(!stackRef.isEmpty()) {
							peekTop = stackRef.peek();
						}
					}
					else {
						break;
					}

				}
				secondInfix = Integer.parseInt("" + stackCalc.pop()); 
				//if there is only one item in stack put in back in the stack and return it
				if(stackCalc.isEmpty() && stackRef.isEmpty()){
					stackCalc.push("" + secondInfix);
					break;
				}
				firstInfix = Integer.parseInt("" + stackCalc.pop());
				//get the operator
				operator = stackRef.pop();

				//perform addition, subtraction, multiplication, division and exponentialion
				switch (operator)
				{
					case "+":
						r=firstInfix+secondInfix;
						System.out.println(firstInfix +" + " + secondInfix +" = " + r);
						stackRef.push("" + r);
						break;
					case "-":
						r=firstInfix-secondInfix;
						System.out.println(firstInfix +" - " + secondInfix +" = " + r);
						stackRef.push("" + r);
						break;
					case "*":
						r=firstInfix*secondInfix;
						System.out.println(firstInfix +" * " + secondInfix +" = " + r);
						stackRef.push("" + r);
						break;
					case "/":
						if (secondInfix == 0) {
							throw new ArithmeticException("Argument 'divisor' is 0");
						}
						r=firstInfix/secondInfix;
						System.out.println(firstInfix +" / " + secondInfix +" = " + r);
						stackRef.push("" + r);
						break;
					case "^":
						r=firstInfix;
						for(int k =1; k < secondInfix; k++)  {
							r*=firstInfix;
						}
						//if the power is 0 the resultant number is 1
						if(secondInfix == 0) {
							r=1;
						}
						System.out.println(firstInfix +" ^ " + secondInfix +" = " + r);
						stackRef.push("" + r);
						break;
					default:
				}			
			}
			if(!stackCalc.isEmpty()) {
				result += stackCalc.pop();
			}
		}
		catch(StringStackException e) {
			System.out.println(e);
			return "<syntax error>";
		}
		catch(NumberFormatException e) {
			System.out.println(e);
			return "<noninteger>";
		}
		catch(ArithmeticException e) {
			System.out.println(e);
			return "<unvalid division ocurred>";
		}
        return result;
    }

    public static String evaluateExpression(String expr) {
		//if the string has unbalanced parentheses return unbalanced
		if(!isBalanced(expr)) {
			return "<unbalanced ()>";
		}
		String[] infix = tokenize(expr);
		StringList postfix = toPostFix(infix);
        String result = evaluatePostfix(postfix);
        return result;
    }
	//this method will determine if the brackets are balanced or not
	public static boolean isBalanced(String expr) {
		boolean passed = false;
		String[] tokens = tokenize(expr);
		StringStackRefBased stack = new StringStackRefBased();
		try {
			for(int i = 0; i < tokens.length; i++) {
				if(tokens[i].equals("(")) {
					stack.push(tokens[i]);
				}
				if(tokens[i].equals(")")) {
					stack.pop();
				}
			}
			//stack should be empty after cycling 
			if(!(stack.isEmpty())) {
					passed = false;
				}
			else {
				passed = true;
			}
		}
		catch(StringStackException e){
			passed = false;
		}
		return passed;
	}
	
	//uses a given list, and checks in each entry in the linked list 
	public static boolean compareAndReport(StringList given,
         String expected[])
    {
        boolean passed = true;
        String result;
		
        for (int i = 0; i < expected.length; i++) {
            result = given.retrieve(i);
            if (!result.equals(expected[i])) {
                passed = false;
                break;
            }
        }
        return passed;
    }
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("usage: java InfixCalc '<expression>'");
            System.exit(1);
        }
        System.out.println(evaluateExpression(args[0]));
		
		String infix ="";
		String postfix= "";
        //Test behavior of isBalanced with balanced brackets 
		boolean passed = true;
		passed = isBalanced("(fun)(Games)");
        System.out.println("Test 1 (isBalanced): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behavior of isBalanced with one brace
		passed = !isBalanced(")");
		System.out.println("Test 2 (isBalanced): " +
            (passed ?  "passed": "FAILED"));
		
		//Test behavior of isBalanced with multiple matching braces
		passed = isBalanced("((5+5)(1+2)(3+4))(5)(6)");
		System.out.println("Test 3 (isBalanced): " +
            (passed ?  "passed": "FAILED"));
		
		passed = !isBalanced(")(1+2)(3+4)(5+6))(7)(8)(");
		System.out.println("Test 4 (isBalanced): " +
            (passed ?  "passed": "FAILED"));
		
		//more isOperand tests
		passed = isOperand("-45");
		System.out.println("Test 5 (isOperand): " +
            (passed ?  "passed": "FAILED"));
			
		passed = isOperand("5");
		System.out.println("Test 6 (isOperand): " +
            (passed ?  "passed": "FAILED"));
		
		passed = isOperand("235");
		System.out.println("Test 7 (isOperand): " +
            (passed ?  "passed": "FAILED"));
			
		passed = !isOperand("a");
		System.out.println("Test 8 (isOperand): " +
            (passed ?  "passed": "FAILED"));
		
		//operators tests
		passed = isOperator("+");
		System.out.println("Test 9 (isOperator): " +
            (passed ?  "passed": "FAILED"));
		
		passed = isOperator("-");
		System.out.println("Test 10 (isOperator): " +
            (passed ?  "passed": "FAILED"));
		
		passed = isOperator("/");
		System.out.println("Test 11 (isOperator): " +
            (passed ?  "passed": "FAILED"));
			
		passed = isOperator("^");
		System.out.println("Test 12 (isOperator): " +
            (passed ?  "passed": "FAILED"));
		
		passed = isOperator("*");
		System.out.println("Test 13 (isOperator): " +
            (passed ?  "passed": "FAILED"));
		
		passed = !isOperator("23");
		System.out.println("Test 14 (isOperator): " +
            (passed ?  "passed": "FAILED"));
		
		//create is operator tests
		infix = "((10 * 20 / 5 + 4 + 3))";
		String[] test15 = tokenize(infix);
		String[] test15after ={"10","20","*","5","/","4","+","3","+"};
		StringList test15PostFix = toPostFix(test15);
		
		//try to convert more infix expressions to postfix
		passed = compareAndReport(test15PostFix, test15after);
		System.out.println("Test 15 (toPostFix): " +
           (passed ?  "passed": "FAILED"));
		  
		//create is operator tests
		infix = "10 * 20 / 5 + 4^3";
		String[] test16 = tokenize(infix);
		String[] test16after ={"10","20","*","5","/","4","3","^","+"};
		StringList test16PostFix = toPostFix(test16);

		//try to convert more infix expressions to postfix
		passed = compareAndReport(test16PostFix, test16after);
		System.out.println("Test 16 (toPostFix): " +
           (passed ?  "passed": "FAILED"));
		  
		//Test behavior of toPostFix
		infix = "10*20/30+40+50";
		String[] test17 = tokenize(infix);
		String[] test17after ={"10","20","*","30","/","40","+","50","+"};
		StringList test17PostFix = toPostFix(test17);
		
		//evaluate some expressions
		passed = compareAndReport(test17PostFix, test17after);
		System.out.println("Test 17 (toPostFix): " +
           (passed ?  "passed": "FAILED"));
		  
		 
		 
		 		//Test behavior of toPostFix
		infix = "10*20/30+40+50+b";
		String evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("<noninteger>")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 18 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));
		
		
		infix = "(10*20/30+40+50+b";
		evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("<unbalanced ()>")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 19 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));
		
		infix = "10*20/+40+50+b";
		evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("<syntax error>")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 20 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));
		
		infix = "(10*20+200)/4/5";
		evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("20")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 21 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));
		 
		infix = "1*8/4+2";
		evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("4")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 22 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));
		   
		   
		infix = "3^2^3-3^1^1";
		evaluate = evaluateExpression(infix);
		//evaluate some expressions
		if(evaluate.equals("6558")) {
			passed = true;
		}
		else {
			passed = false;
		}
		System.out.println("Test 23 (evaluateExpression): " +
           (passed ?  "passed": "FAILED"));

    }
}
