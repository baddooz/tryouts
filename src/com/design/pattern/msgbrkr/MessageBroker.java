package com.design.pattern.msgbrkr;

public interface MessageBroker {

  // support both Topic and Queue
  // A client can subscribe to a Topic or Queue

  boolean subscribeToTopic(String topicname, String subscriberId);

  boolean unSubscribeTopic(String topicname, String subscriberId);

  boolean subscribeToQueue(String qname, String subscriberId);

  boolean produce(String name, String message);

  String consumeFromTopic(String topicName, String subscriberId);

  String consumeFromQueue(String qName, String subscriberId);
}
