Action Processor Engine

This engine reduces complexity,bugs and time of the server side development.

It makes possible to implement webservices,web applications and batch applications using pure java components, without a need of have a full team with especial knowledge in webservices, Spring Web, Batch, ...

![enginediagrame](https://cloud.githubusercontent.com/assets/12789651/8512304/0791f4d2-2313-11e5-96c9-e937eed7fef8.jpg)

In this repository you going to find a functional application with two implementations of the AbstractActionProcessor. One SpringWEB and other Javabatch.

- In the Web implementation you going to see a simple jquery exchanging json data with the server.
local URL: http://localhost:8080/ActionProcessorEngine/index.html

- The java batch implementation requests the same action-set of the Web, but producing and consuming java objects.
- To test run the Main.class

- Run this applications will be easyer with you have eclipse, maven 3,java 7 and tomcat 8. Having this tools, just import ActionProcessorEngine project to your eclipse, run maven install and deploy it in tomcat.
