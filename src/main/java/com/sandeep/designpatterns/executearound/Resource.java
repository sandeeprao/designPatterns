package com.sandeep.designpatterns.executearound;

import java.util.function.Consumer;

public class Resource {

  //Private constructor to disallow instantiation of object outside this class
  private Resource() {
    System.out.println("Creating com.sandeep.designpatterns.executearound.Resource");
  }

  // Closing Resources
  private void close(){
    System.out.println("Closing com.sandeep.designpatterns.executearound.Resource");
  }

  public Resource operation(String input){
    System.out.println("Performing operation with resource input: "+ input);
    return  this;
  }

  public static void use(Consumer<Resource> resourceConsumer){
     Resource resource = new Resource();
     try{
       resourceConsumer.accept(resource);
     }
     finally {
       //Cleaning up the resource
       resource.close();
     }
  }

  public static void main(String[] args) {
    //Chaining operations
    Resource.use(resource -> resource.operation("1").operation("2"));
  }

}
