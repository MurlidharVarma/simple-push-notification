self.addEventListener("push", function (event) {
    const message = event.data.json();
    console.log("MESSAGE RECIEVED");
    console.log(message);
    
    self.registration.showNotification( message.title, { body: message.text });
})