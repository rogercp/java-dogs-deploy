# Project Restful Dogs

A student that completes this project shows that they can:
* troubleshoot running services using both system and programmatically generated logs
* implement server sided routing and rendering for a Spring REST service
* implement industry standard exception handling including user friendly exception messages

# Introduction

We worked with dogs in plain Java. This project returns dog data using Rest APIs with appropriate exception handling, logging, and make use of Server Side Rendering.

# Instructions

## Day Three
Deploy java-dogs to Heroku
  * Specifically check to see if Message Queues are working
  * If that are NOT, disable the use of RabbitMQ
    * This is straight forward - should be commenting out a few lines. If you are deleting signaficant amounts of code, you are on the wrong track!

 Add server side rendering using Thymeleaf to 
  * display all dogs ordered by breed
  * display all dogs suitable for apartments ordered by breed


## Day two

Starting with either the dogs-initial application or your dogs from yesterday

Add appropriate logging routines. Required logging include
  * Activating actuator endpoints
  * Tomcat logging routed to a separate log file
  * Custom logging under each Get endpoint saying the endpoint has been accessed
    * should only go to console
    * for example when a clients calls /dogs log should say "/dogs accessed"
  * Note: put the log files under the directory /var/logs/lambdajx Feel free to create and necessary subdirectories.

Create RabbitMQ messaging system with three queues
  * for each queue below, add a data and time stamp and a message what was done with any appropriate parameters. For instance include the data when creating a Dog, include the parameters when search for a dog...
  * One for each time the /dogs endpoints are accessed
  * One for each time the /dogs/breeds/{breed} endpoint is accessed
  * One for each dog that is created
  
Create a separate application that reads messages from each queue, making sure to add to the message which queue it is from before displaying to the screen.

Stretch Goal
  * Add a date and time stamp to your custom logging
  * Create an application that reads messages from each queue and writes the message it a file for that queue. So all messages about creating dogs would be logged to a file about creating dogs; all messages about accessing /dogs would be logged to a file about accessing dogs,...


## Day One

The provided application exposes the following endpoints
* /dogs -> returns a list of all dogs
* /dogs/{id} -> returns a single dog based off of its id number
* /dogs/breeds/{breed} -> returns all dogs of the given breed
    
Starting with the dogs-initial application,

Add appropriate exception handling routines. Required exceptions to handle are when
  * a resource is not found
  * the wrong data type is used for a path variable
  * a non-handled endpoint is accessed (a URL not found exception)

