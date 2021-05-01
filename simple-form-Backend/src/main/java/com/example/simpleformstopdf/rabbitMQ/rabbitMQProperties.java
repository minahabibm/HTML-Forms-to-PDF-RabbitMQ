package com.example.simpleformstopdf.rabbitMQ;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitMQProperties {

    @Value("${htmlToPdf.exchange.name}")
    private String htmlToPdfExchange;

    @Value("${htmlToPdf.queue.name}")
    private String htmlToPdfQueue;

    @Value("${htmlToPdf.routing.key}")
    private String htmlToPdfRoutingKey;

    public String getHtmlToPdfExchange() {
        return htmlToPdfExchange;
    }

    public void setHtmlToPdfExchange(String htmlToPdfExchange) {
        this.htmlToPdfExchange = htmlToPdfExchange;
    }

    public String getHtmlToPdfQueue() {
        return htmlToPdfQueue;
    }

    public void setHtmlToPdfQueue(String htmlToPdfQueue) {
        this.htmlToPdfQueue = htmlToPdfQueue;
    }

    public String getHtmlToPdfRoutingKey() {
        return htmlToPdfRoutingKey;
    }

    public void setHtmlToPdfRoutingKey(String htmlToPdfRoutingKey) {
        this.htmlToPdfRoutingKey = htmlToPdfRoutingKey;
    }

}
