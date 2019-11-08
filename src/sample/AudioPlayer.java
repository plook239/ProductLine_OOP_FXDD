package sample;

public class AudioPlayer extends Product implements MultimediaControl{
  Screen supportedAudioFormats;
  MonitorType supportedPlaylistFormats;

  public AudioPlayer(String name, String manufacturer,
      Screen supportedAudioFormats,
      MonitorType supportedPlaylistFormats){
    super(name, manufacturer, supportedAudioFormats, supportedPlaylistFormats);

    this.Name = name;
    this.Manufacturer = manufacturer;
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  @Override
  public void play(){
    System.out.println("Playing");
  }

  @Override
  public void stop(){
    System.out.println("Stopping");
  }

  @Override
  public void previous(){
    System.out.println("Previous");
  }

  @Override
  public void next(){
    System.out.println("Next");
  }

}
