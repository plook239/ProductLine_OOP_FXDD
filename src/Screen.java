public class Screen implements ScreenSpec {
  String res;
  int rr;
  int rt;

  /**
   * @param res is the resolution
   * @param rr is the refresh rate
   * @param rt is the response time
   * */
  public Screen(String res, int rr, int rt) {
    this.res = res;
    this.rr = rr;
    this.rt = rt;
  }
  /**
   * Creates a string with screen data
   * */
  public String toString(){
    return "Screen:\n"
        + "Resolution: " + res + "\n"
        + "Refresh rate: " + rr + "\n"
        + "Response time: " + rt;
  }
  /**
   * Getters and setters
   * */
  @Override
  public String getResolution() {
    return res;
  }

  @Override
  public void setResolution(String res) {
    this.res = res;
  }

  @Override
  public int getRefreshRate() {
    return rr;
  }

  @Override
  public void setRefreshRate(int rr) {
    this.rr = rr;
  }

  @Override
  public int getResponseTime() {
    return rt;
  }

  @Override
  public void setResponseTime(int rt) {
    this.rt = rt;
  }
}
