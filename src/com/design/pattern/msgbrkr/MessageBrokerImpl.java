package com.design.pattern.msgbrkr;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MessageBrokerImpl implements MessageBroker {

//  private ConcurrentHashMap<String,String> queueSubscribers;

  private ConcurrentHashMap<String,String> topicSubscribers;



  private ConcurrentHashMap<String,AtomicLong> topicMessageLogIds;

  private ConcurrentHashMap<String,ConcurrentHashMap<String,Long>> subscriberTopicReadOffsetMap;


  public MessageBrokerImpl() {
    topicSubscribers = new ConcurrentHashMap<>();
    topicMessageLogIds = new ConcurrentHashMap<>();
    subscriberTopicReadOffsetMap = new ConcurrentHashMap<>();
  }

  @Override
  public boolean subscribeToTopic(String topicname, String subscriberId) {
    if (topicSubscribers.putIfAbsent(topicname,subscriberId) ==  null) {
      ConcurrentHashMap<String, Long> topicToReadOffset = new ConcurrentHashMap<String,Long>();
      topicToReadOffset.put(topicname,0l);
      this.subscriberTopicReadOffsetMap.put(subscriberId,topicToReadOffset);
      return true;
    }
    return false;
  }

  @Override
  public boolean unSubscribeTopic(String topicname, String subscriberId) {
    if (topicSubscribers.contains(topicname)) {
      topicSubscribers.remove(topicname);
      return true;
    }
    return false;
  }

  @Override
  public boolean subscribeToQueue(String qname, String subscriberId) {
    return false;
  }

  @Override
  public boolean produce(String name, String message) {

    return false;
  }

  @Override
  public String consumeFromTopic(String topicName, String subscriberId) {
    return null;
  }

  @Override
  public String consumeFromQueue(String qName, String subscriberId) {
    return null;
  }
}
