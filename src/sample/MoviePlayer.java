package sample;

public class MoviePlayer extends Product implements MultimediaControl{

  MonitorType monitorType;
  Screen screen;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, screen, monitorType);

    this.Name = name;
    this.Manufacturer = manufacturer;
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }

  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: "
        + monitorType + "\n" + "Screen:\n"
        + "Resolution: " + screen.res + "\n"
        + "Refresh rate: " + screen.rr + "\n"
        + "Response time: " + screen.rt + "\n"
        + "Monitor Type: " + monitorType;
  }

}

