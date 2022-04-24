# simple-push-notification
A spring boot app to do simple push notification

It uses a plain javascript service worker to do push notification.
When the service worker is registered, the push notification subscription object is stored into H2 in memory database of Spring boot. And when notify all is called spring boot will get all the subcribers object from H2 database and push the notification.

## Usage
### To register
https://localhost:8443/index.html

### To push notification to all Subscriber
https://localhost:8443/backend/notifyAll/{message-text}


