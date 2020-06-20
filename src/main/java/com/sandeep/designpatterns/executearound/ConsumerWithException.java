package com.sandeep.designpatterns.executearound;

public interface ConsumerWithException<T, E extends  Exception> {
  void accept(T t) throws  E;
}
