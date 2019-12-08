public class AudioPlayer extends Product implements MultimediaControl {
    /**
     * Audio player constructor.
     */
    public AudioPlayer(
            String name,
            String manufacturer,
            Screen supportedAudioFormats,
            MonitorType supportedPlaylistFormats) {
        super(name, manufacturer);

        this.Name = name;
        this.Manufacturer = manufacturer;
    }

    @Override
    public void play() {
        System.out.println("Playing");
    }

    @Override
    public void stop() {
        System.out.println("Stopping");
    }

    @Override
    public void previous() {
        System.out.println("Previous");
    }

    @Override
    public void next() {
        System.out.println("Next");
    }
}
