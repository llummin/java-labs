public enum ObjectType {
  PICTURE("Picture"),
  TEXT("Text");

  private final String type;

  ObjectType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}