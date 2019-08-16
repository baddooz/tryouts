package com.design.pattern.msgbrkr;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Message {

  private String msg;


  private ZonedDateTime msgTime;

  private Message() {
  }

  public Message(String message) {
    this.msg = message;
    msgTime = ZonedDateTime.now(ZoneOffset.UTC);
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public ZonedDateTime getMsgTime() {
    return msgTime;
  }

  public void setMsgTime(ZonedDateTime msgTime) {
    this.msgTime = msgTime;
  }


  public String toString() {
    return "Msg:<" + this.msg + "> produced at: " + msgTime;
  }

}
