package com.sandeep.designpatterns.executearound;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteData {

  private FileWriter fileWriter;

  private WriteData(String fileName) throws IOException {
    fileWriter = new FileWriter(fileName);
  }

  private void close() throws IOException{
    fileWriter.close();
  }

  public WriteData write(String content) throws IOException {
    fileWriter.write(content);
    return this;
  }


  public static void use(String fileName, ConsumerWithException<WriteData,IOException> consumer) throws IOException{
    WriteData writeData = new WriteData(fileName);
    try{
      consumer.accept(writeData);
    }
    finally {
      writeData.close();
    }
  }

  public static void main(String[] args) {
    try {
      WriteData.use("testFile",
          writeData -> writeData.write("Execute around pattern\n").write("Next Line"));

      Files.lines(Paths.get("testFile")).forEach(System.out::println);
    }
    catch (IOException io){
      System.out.println(io.getMessage());
    }
  }

}
