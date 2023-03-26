package sources;

import events.FileOutput;

public class FileOutputSource {

  FileOutput fileOutput;

  public FileOutputSource(FileOutput fileOutput) {
    this.fileOutput = fileOutput;
  }

  public void generateEvent() {
    fileOutput.Handler();
  }
}
