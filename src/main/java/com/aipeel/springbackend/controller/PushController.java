package com.aipeel.springbackend.controller;

import com.aipeel.springbackend.entity.PushSubscription;
import com.aipeel.springbackend.entity.Subscriber;
import com.aipeel.springbackend.repo.SubscriberRespository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/backend")
public class PushController {

    private static final Logger LOG = LoggerFactory.getLogger(PushController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${vapid.pubKey}")
    private String pubKey;

    @Value("${vapid.pvtKey}")
    private String pvtKey;

    @Autowired
    SubscriberRespository repo;

    @GetMapping("/hello/{name}")
    public String greet(@PathVariable("name") String name) {
        return "Hello "+name;
    }

    @PostMapping(value = "/register")
    public Subscriber register(@RequestBody Subscriber sub){
        LOG.info("Subscriber Id {} registered to topic {}",sub.getId(),sub.getTopic());
        return repo.save(sub);
    }

    @GetMapping("/notifyAll/{text}")
    public void notifyAllSubscribers(@PathVariable String text){
        try {
            List<Subscriber> subscribers = (List<Subscriber>) repo.findAll();
            PushService pushService = new PushService(pubKey, pvtKey);

            for (Subscriber sub : subscribers) {
                try {
                    PushSubscription s = objectMapper.readValue(sub.getSubscription(), PushSubscription.class);
                    Subscription subp = new Subscription(s.getEndpoint(),new Subscription.Keys(s.getKeys().getP256dh(), s.getKeys().getAuth()));

                    Notification notification = new Notification(subp, this.createStringMessage(sub.getTopic()+": "+sub.getId(), text));
                    pushService.send(notification);
                } catch (Exception e) {
                    LOG.error("Error parsing subscription: ", e);
                }
            }
        }catch (Exception ex){
            LOG.error("Error notifying all subscription: ", ex);
        }
    }

    private String createStringMessage(String title, String message){
        Map<String, String> msg = new HashMap<>();
        msg.put("title",title);
        msg.put("text", message);

        String messageStr = "";
        try {
            messageStr = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            LOG.error("Error creating message string",e);
        }
        return messageStr;
    }
}
