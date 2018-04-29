# rpn-calculator
Reverse Polish Notation Calculator:

Below are few important notes about the program :

It's a Spring Boot Application which takes user inputs and puts results back to the stack.
For Undo operation, Momento pattern is used to keep track of previous states of operations.

There are <b> three </b> ways to access this application :
* Using Concole, the program would wait for user input and pushes the results back to stack.2
* Through REST interface GET method, an endpoint is exposed through which reverse-polish-notation can be passed. For e.g. http://localhost:9000/calculate/expr=5 3 * 
* An AWS Lambda request handler is integrated with AWS API Gateway, a public URL is exposed which can be accessed by anyone.
To access , please send a POST request with raw data to https://cupdld3yo6.execute-api.ap-southeast-2.amazonaws.com/Prod 
