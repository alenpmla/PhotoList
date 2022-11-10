# Photo App

https://github.com/alenpmla/PhotoList

* An app for listing the photos from the given URL
  
* retrofit 2 is used for networks call

* Koin is used for dependency injection

* Clean architecture is used in this app

* View model with used in the app to populate the data

* The UI is build using jetpack compose

* Navigation component for jetpack is user for screen navigation

* Unit test cases for repository is covered

# Two things solved well

* App architecture
* Dependency injection

# Scope of Improvements

* Pagination(Currently all list data is being fetched at once, this is not an efficient way on the app side, we can modify the api and add pagination by loading more content while scrolling)
* Offline cache(We can cache the response and use that response and use that response when there is no network )
