#Javaly!
Javaly is Practice-it 2.0 on steroids. It's a tool to help professors and teachers teach programming better, and to help students learn programming in a fun yet methodical way, making use (eventually...) of stats and data to enhance the learning experience and the teaching experience, all in the name of learning how to write good clean code.

We hope to have all of the functions of Practice-it, as well as the following:

1. Interactive LIVE classroom enhancing IDE (Code mirroring/Chat)
2. Arcade mode (Solve questions of increasing difficulty level *Hardcore*)
3. Exam Revision Mode (Review those tricky exam questions/Questions you got wrong)
4. Learns what you are weak at so it can prescribe you questions that will aid you (machine learning anyone? :P)
5. Instructor Dashboard to monitor student performance
6. Crowd-sourcing Java questions from students
5. Support other languages! 

##Current roadmap
1. Basic functionality (compile, run, check answers for method based questions)
2. Adding of questions
3. Basic index, question, and checking UI

----

## Grunt/Bower setup instructions

### Dependencies

[Node.js/npm - Runtime environment](http://nodejs.org/)  
[Grunt - Build tool](http://gruntjs.com/)  
[Bower - Dependency management](http://bower.io/)

### Usage
1. Ensure that `npm` is installed globally on your system.
1. In your terminal, `cd` to the JavalyUI root directory. I put this directory inside the Javaly directory `../Javaly/JavalyUI`
1. Run `npm install`. You should observe packages being downloaded to the `node_modules` directory.
1. Run `bower install` You should observe packages being downloaded to the `bower_components` directory.
1. Now, you can run `grunt serve` to load up the UI. Your browser window should open automatically, otherwise, navigate to `http://localhost:9001/`
1. `grunt serve` provides live reload for UI development, so any changes made to the UI code will be automatically propagated.
1. To package src files for the production environment, run `grunt build`. This will concatenate and minify files into `../public` **Important: Ensure that you are in the right directory or you might delete files in the parent directory accidentally, using the --force option**  

----

##ER Diagram 

>To View: https://www.lucidchart.com/documents/view/245c0b7e-9b44-4bd9-b009-8802c1912e1c

>To Edit: https://www.lucidchart.com/invitations/accept/da3b36ca-f937-4768-a430-dbd69fc45d06

###About the PlayFramework
To get a primer on the PlayFramework, there are a few tutorials/templates available from Typesafe through the Activator UI we can make reference to.

http://typesafe.com/activator/docs

To better understand the framework, consult the Play for Java book.

##Setting up your environment
#####Download and install the Play Framework

https://www.playframework.com/

https://www.playframework.com/documentation/2.3.x/Installing

#####Register for a student account under jetbrains, and download IntelliJ Ultimate (you'll need the debugger!), login with your student account when you first run it! Install IntelliJ with everything! 

https://www.jetbrains.com/student/

https://www.jetbrains.com/idea/download/

>The following instructions might require some Google-fu as instructions differ from OS.

>If you're on WAMP you can ask Wai Tuck

>If you're on MAMP you can ask Clarence

#####Download and install WAMP/LAMP/MAMP, or alternatively install mySQL community edition.

#####Configure my.ini
Replace all instances of 3306 (the default port of mySQL) with 8889

#####Run mySQL console as root, when prompted for password just press enter.
Execute the following command to change the password to 'root' (need to change evenutally haha)

`UPDATE mysql.user SET Password=PASSWORD('root') WHERE User='root';FLUSH PRIVILEGES;CREATE DATABASE javaly;USE javaly;`

Restart mySQL

#####Run IntelliJ
```
Go VCS > Checkout from VCS > Git
Copy this into the source url
https://github.com/clarencenpy/Javaly.git
Then clone! The initial cloning stage will take VERY LONG. Be patient!

There's also a checkout from GitHub; you can also try that, but it doesn't really matter which way you do it.
```

#####Running Play
```
Go to Run > Edit Configurations
Press the green plus
Press Play 2 App
Tick auto-reload
Click apply, then OK!
```
Alternatively, cd to the project directory and run
`activator run`

I find this to have a shorter loading time.


#####Debugging Play with IntelliJ
To use IntelliJ's built in debugger, we have some extra steps to do.

Step 1: Set up a remote debugger in IntelliJ
```
Go to Run > Edit Configurations
Press the plus button and select Remote
Select Transport: Socket
	   Debugger Mode: Attach
	   Port: 9999
	   Search sources using module's classpath: <whole project>
Hit Apply!
```
Step 2: Run Play in debug mode
```
Make sure that you are not already running Play from IntelliJ
In the terminal, cd to the project directory
Run "activator -jvm-debug 9999 run"
```
Step 3: Back in IntelliJ
```
Go to Run > Debug
Select the Remote Configuration you created in 1.
You should see the following in the debugger console:
Connected to the target VM, address: 'localhost:9999', transport: 'socket'
```
Step 4: Set breakpoints as necessary and happy debugging~

###That's all folks!

```
//BELOW by JEREMY: Understanding Play

“Listing 1.1. Files in a new Play application
├── README
├── app ** MOST IMPORTANT. Contains java codes (controller) and html codes (views). Root for src codes
│
   ├── controllers
│
   │   └── Application.java // controls result sent in response to request for http://localhost/ A.K.A. WELCOME PAGE
│   └── views // contains HTML page templates
│       ├── index.scala.html
│       └── main.scala.html
├── conf //where you configure the app
│   ├── application.conf //configure logging, database connections, server ports, etc
│   └── routes // mapping app code ('action methods' & 'binding parameters') to HTTP URL paths
               // e.g. GET /list controllers.Products.list()
├── project
│   ├── build.properties //ignore; descriptive files
│   └── plugins.sbt //ignore; descriptive files
├── public //resources served directly without processing
│   ├── images
│   │   └── favicon.png
│   ├── javascripts
│   │   └── jquery-1.9.0.min.js
│   └── stylesheets
│       └── main.css
├── build.sbt //define app's version and 'managed dependencies' (things that u need, i.e. 'classpath')
└── test
    ├── ApplicationTest.java
    └── IntegrationTest.java”

Excerpt From: Nicolas Leroux Sietse de Kaper. “Play for Java.” iBooks.
```

