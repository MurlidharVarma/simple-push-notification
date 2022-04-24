package com.aipeel.springbackend;

import com.aipeel.springbackend.entity.Subscriber;
import com.aipeel.springbackend.repo.SubscriberRespository;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.security.Security;

@SpringBootApplication
public class SpringBackendApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBackendApplication.class);

	public static void main(String[] args) {
		Security.setProperty("crypto.policy", "unlimited");
		Security.addProvider(new BouncyCastleProvider());

		SpringApplication.run(SpringBackendApplication.class, args);
	}




}
