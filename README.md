# Shopping List
[![BCH compliance](https://bettercodehub.com/edge/badge/mayokunthefirst/Shopping-List?branch=master)](https://bettercodehub.com/) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4d9a7a6797fe41e782d85d4c37e3bec5)](https://www.codacy.com/manual/mayokunthefirst/Shopping-List?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mayokunthefirst/Shopping-List&amp;utm_campaign=Badge_Grade)

## Introduction
An Android application that integrates the [Android Jetpack](https://developer.android.com/jetpack/) components to help keep track of items needed when shopping.

<p align="center"><a><img src="https://github.com/mayokunthefirst/Shopping-List/blob/master/gif/gifdemo.gif" width="300"></a></p>


## Architecture
The architecture of this application relies and complies with the following points below:
* A single-activity architecture, using the [Navigation Components](https://developer.android.com/guide/navigation) to manage fragment operations.
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help to keep the application robust, testable, and maintainable.

## Technologies used:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.
* [Timber](https://github.com/JakeWharton/timber) - a logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for managing background threads and reducing needs for callbacks.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) to declaratively bind UI components in layouts to data sources.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

#### Testing
* [JUnit](https://junit.org/junit4/) - a simple framework to write repeatable tests.

## Installation
Shopping list requires a minimum API level of 19. Clone the repository and run. 

## Contributions
All contributions are welcome! Simply make a PR.
