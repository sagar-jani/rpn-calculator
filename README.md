# rpn-calculator
Reverse Polish Notation Calculator:

Below are few important notes about the program :

It's a Spring Boot Application which takes user inputs and puts results back to the stack.

It also contains RequestHandler for AWS Lambda which is integrated with AWS API Gateway. The URL is available at you can test through by sending POST reqest with raw body parameters at following URL:
https://cupdld3yo6.execute-api.ap-southeast-2.amazonaws.com/Prod 

For Undo operation, Momento pattern is used to keep track of previous states of operations.
