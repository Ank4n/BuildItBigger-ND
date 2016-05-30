# Gradle for Android and Java Final Project

This app has multiple flavors that uses multiple libraries and Google Could Endpoints. The finished app will consist
of four modules. A Java library that provides jokes (jokegenerator), a Google Could Endpoints
(GCE) project that serves those jokes (backend), an Android Library containing an
activity for displaying jokes (joketellerandroidlib), and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display (app).

## Why this Project

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.

##What did I Learn?

I learned the role of Gradle in building Android Apps and how to use Gradle to manage apps of increasing complexity. I learned to:

* Add free and paid flavors to an app, and set up the build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile the libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server

