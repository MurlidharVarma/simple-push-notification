package com.aipeel.springbackend.entity;

public class PushSubscription {
    String endpoint;
    String expirationTime;
    Keys keys;

    public static class Keys {
        public String p256dh;
        public String auth;

        public Keys() {
            // No-args constructor
        }

        public Keys(String key, String auth) {
            this.p256dh = key;
            this.auth = auth;
        }

        public String getP256dh() {
            return p256dh;
        }

        public void setP256dh(String p256dh) {
            this.p256dh = p256dh;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }
}
