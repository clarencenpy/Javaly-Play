#Javaly!
Javaly is Practice-it 2.0 on steroids. It's a tool to help professors and teachers teach programming better, and to help students learn programming in a fun yet methodical way, making use (eventually...) of stats and data to enhance the learning experience and the teaching experience, all in the name of learning how to write good clean code.

We hope to have all of the functions of Practice-it, as well as the following:

1. Interactive LIVE classroom enhancing IDE
2. Hardcore practice mode for those tricky exam questions
3. Arcade mode
4. Learns what you are weak at so it can prescribe you questions that will aid you (machine learning anyone? :P)
5. Support other languages! 

##Current roadmap
1. Basic functionalities (compile, run, check answers for method based questions)
2. Adding of questions
3. Basic index, question, and checking UI

###About the PlayFramework
To get a primer on the PlayFramework, there are a few tutorials/templates available from Typesafe through the Activator UI we can make reference to.

http://typesafe.com/activator/docs

To better understand the framework, consult the Play for Java book.

##Setting up your environment
###Download and install the Play Framework

https://www.playframework.com/

https://www.playframework.com/documentation/2.3.x/Installing

###Register for a student account under jetbrains, and download IntelliJ Ultimate (you'll need the debugger!), login with your student account when you first run it! Install IntelliJ with everything! 

https://www.jetbrains.com/student/

https://www.jetbrains.com/idea/download/

>The following instructions might require some Google-fu as instructions differ from OS.

>If you're on WAMP you can ask Wai Tuck

>If you're on MAMP you can ask Clarence

###Download and install WAMP/LAMP/MAMP, or alternatively install mySQL community edition.

###Configure my.ini
Replace all instances of 3306 (the default port of mySQL) with 8889

###Run mySQL console as root, when prompted for password just press enter.
Execute the following command to change the password to 'root' (need to change evenutally haha)

`UPDATE mysql.user SET Password=PASSWORD('root') WHERE User='root';FLUSH PRIVILEGES;CREATE DATABASE javaly;USE javaly;

Restart mySQL

###Run IntelliJ
```
Go VCS > Checkout from VCS > Git
Copy this into the source url
`https://github.com/clarencenpy/Javaly.git
Then clone! The initial cloning stage will take VERY LONG. Be patient!

There's also a checkout from GitHub; you can also try that, but it doesn't really matter which way you do it.
```

###Running Play
```
Go to Run > Edit Configurations
Press the green plus
Press Play 2 App
Tick auto-reload
Click apply, then OK!
```

##That's all folks!

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
