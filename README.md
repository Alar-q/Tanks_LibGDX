# Tanks_LibGDX
> AITU Course Project 
> Like the Tanks 1990


## Project Description
The project "Tanks_LibGDX" is a coursework 
developed as part of an educational programme at AITU. 
It is a game inspired by the classic 
game "Tanks 1990" and it is based on [LibGDX](https://libgdx.com/wiki/).

The project aims to create a clone version of the popular 
video game "Tanks 1990", based on the LibGDX, a powerful game 
development framework. The project offers a complete package for developing, 
testing and deploying the game on a variety of platforms 
including Windows, Linux, Mac OS X, Android and Web Browser.

"Talks_LibGDX" not only showcases the technical skills of the development team, 
but also provides a solid foundation for further research and development in game programming.

## Technologies

1. [LibGDX](https://libgdx.com/wiki/ "LibGDX")
2. [Java](https://www.oracle.com/cis/java/ "Java")
3. [Python](https://www.python.org/downloads/ "Python")
4. [Node.js](https://nodejs.org/en/ "Node.js")


## Getting Started
These instructions will help you start and run a copy of the project 
on your local computer for development and testing purposes.

Start with cloning this repo on your local machine:
```sh
$ cd directory
$ git clone https://github.com/Alar-q/Tanks_LibGDX.git
$ cd ./Tanks_LibGDX
```


## Deploy to Windows/Linux/Mac OS X as JAR file

The easiest way to deploy to Windows/Linux/Mac is to create a runnable JAR file. 

This can be done via the following console command: 
```sh
$ ./gradlew desktop:dist
```

The generated JAR file will be located in the desktop/build/libs/ folder. 
It contains all necessary code as well as all your art assets from the android/assets folder 
and can be run either by double clicking or on the command line via 
```sh
$ java -jar jar-file-name.jar
```
Your audience must have a JVM installed for this to work. 
The JAR will work on Windows, Linux and Mac OS X!

Alternative (modern) ways of deployment
Distributing java applications as JAR file can be very unhandy.
A very convenient way to distribute java application is to just bundle an JRE. [Bundling a JRE](https://libgdx.com/wiki/deployment/bundling-a-jre).


## Deploy to Android

```sh
$ ./gradlew android:assembleRelease
```

This will create an unsigned APK file in the android/build/outputs/apk folder. 
Before you can install or publish this APK, you must sign it. 
The APK build by the above command is already in release mode, you only need to follow the steps for keytool and jarsigner. 
You can install this APK file on any Android device that allows installation from unknown sources.


## Deploy Web

```sh
$ gradlew html:dist
```
This will compile your app to Javascript and place the resulting Javascript, HTML and asset files in the html/build/dist/ folder. 
The contents of this folder have to be served up by a web server, e.g. Apache or Nginx. 
Just treat the contents like you’d treat any other static HTML/Javascript site. 
There is no Java or Java Applets involved!

### Usage

With Python installed, you can test your distribution by executing the following in the html/build/dist folder:

Python 3.x
```sh
$ python -m http.server 8000
```
You can then open a browser to http://localhost:8000 and see your project in action.

Node.js 
```sh
$ npm install http-server -g then 
$ http-server html/build/dist 
```
and browse at http://localhost:8080. docs


## Authors

* **Alar Akilbekov** (Backend, React) - [Alar-q](https://github.com/alar-q) - [@alar4j](https://t.me/alar4j)

See also the list of [contributors](https://github.com/Alar-q/Tanks_LibGDX/graphs/contributors) who participated in this project.

## Team
```
██████╗ ███████╗███╗   ██╗ █████╗ ██╗███████╗███████╗ █████╗ ███╗   ██╗ ██████╗███████╗   
██╔══██╗██╔════╝████╗  ██║██╔══██╗██║██╔════╝██╔════╝██╔══██╗████╗  ██║██╔════╝██╔════╝    
██████╔╝█████╗  ██╔██╗ ██║███████║██║███████╗███████╗███████║██╔██╗ ██║██║     █████╗    
██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║╚════██║╚════██║██╔══██║██║╚██╗██║██║     ██╔══╝    
██║  ██║███████╗██║ ╚████║██║  ██║██║███████║███████║██║  ██║██║ ╚████║╚██████╗███████╗     
╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝    
```

## License
[MIT](https://github.com/Alar-q/Tanks_LibGDX/blob/main/LICENSE)