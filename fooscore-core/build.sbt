name := "fooscore-core"

scalaVersion := "2.9.1"

scalacOptions += "-deprecation"

resolvers += "55 Minutes Open Source Maven Snapshots Repository" at "http://opensource.55minutes.com/maven-snapshots"

resolvers += "55 Minutes Open Source Maven Releases Repository" at "http://opensource.55minutes.com/maven-releases"

resolvers += "apache-snapshots" at "http://repository.apache.org/snapshots"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

libraryDependencies += "net.liftweb" %% "lift-webkit" % "2.4-M5" % "compile->default" exclude("joda-time", "joda-time")

libraryDependencies += "junit" % "junit" % "4.5" % "test->default"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided->default"

libraryDependencies += "com.h2database" % "h2" % "1.2.138"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default"

libraryDependencies += "net.liftweb" %% "lift-mapper" % "2.4-M5" % "compile->default" exclude("joda-time", "joda-time")

libraryDependencies += "net.liftweb" %% "lift-wizard" % "2.4-M5" % "compile->default" exclude("joda-time", "joda-time")

libraryDependencies +=  "com.55minutes" % "fiftyfive-wicket-core" % "4.0-SNAPSHOT" % "compile->default"

libraryDependencies +=  "com.55minutes" % "fiftyfive-wicket-js" % "4.0-SNAPSHOT" % "compile->default"

libraryDependencies +=  "com.55minutes" % "fiftyfive-wicket-shiro" % "4.0-SNAPSHOT" % "compile->default"

libraryDependencies +=  "org.apache.wicket" % "wicket-core" % "1.5-SNAPSHOT"

libraryDependencies +=  "org.apache.wicket" % "wicket-datetime" % "1.5-SNAPSHOT"

libraryDependencies +=  "org.joda" % "joda-convert" % "1.1" % "provided"

libraryDependencies +=  "org.apache.wicket" % "wicket-devutils" % "1.5-SNAPSHOT"

libraryDependencies +=  "org.apache.wicket" % "wicket-extensions" % "1.5-SNAPSHOT"

libraryDependencies +=  "org.apache.wicket" % "wicket-spring" % "1.5-SNAPSHOT"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.2"

libraryDependencies += "org.apache.shiro" % "shiro-spring" % "1.2.0"

libraryDependencies +=  "org.springframework" % "spring-web" % "3.1.0.RELEASE"

libraryDependencies +=  "org.springframework" % "spring-core" % "3.1.0.RELEASE"

libraryDependencies +=  "org.springframework" % "spring-orm" % "3.1.0.RELEASE"

libraryDependencies += "org.springframework" % "spring-test" % "3.1.0.RELEASE"

libraryDependencies += "org.springframework" % "spring-beans" % "3.1.0.RELEASE"

libraryDependencies += "net.sf.ehcache" % "ehcache-core" % "2.4.6"

libraryDependencies +=  "org.hsqldb" % "hsqldb-j5" % "2.2.4"

libraryDependencies +=  "mysql" % "mysql-connector-java" % "5.1.18"

libraryDependencies += "c3p0" % "c3p0" % "0.9.0.4"

libraryDependencies +=  "javassist" % "javassist" % "3.12.1.GA"

libraryDependencies +=  "javax.transaction" % "jta" % "1.1"

libraryDependencies += "org.hibernate" % "hibernate-core" % "3.6.7.Final"

libraryDependencies +=  "org.hibernate" % "hibernate-entitymanager" % "3.6.7.Final"

libraryDependencies += "org.hibernate" % "hibernate-validator" % "4.2.0.Final"

libraryDependencies +=  "javax.servlet" % "servlet-api" % "2.5"

libraryDependencies +=  "org.slf4j" % "jcl-over-slf4j" % "1.6.2"

libraryDependencies +=  "org.slf4j" % "slf4j-log4j12" % "1.6.2"

libraryDependencies +=  "com.55minutes" % "fiftyfive-wicket-test" % "4.0-SNAPSHOT"

libraryDependencies +=  "junit" % "junit" % "4.8.2"

libraryDependencies +=  "org.mockito" % "mockito-all" % "1.8.5"

seq(webSettings :_*)

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "test,container"

libraryDependencies += "org.scala-tools.testing" %% "specs" % "1.6.9" % "test"