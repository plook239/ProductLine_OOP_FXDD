package sample;

public class Screen implements ScreenSpec {

  String res;
  int rr;
  int rt;


  public Screen(String res, int rr, int rt) {
    this.res = res;
    this.rr = rr;
    this.rt = rt;
  }

  public String toString(){
    return "Screen:\n"
        + "Resolution: " + res + "\n"
        + "Refresh rate: " + rr + "\n"
        + "Response time: " + rt;
  }

  @Override
  public String getResolution() {
    return res;
  }

  @Override
  public void setResolution(String res) {
    res = res;
  }

  @Override
  public int getRefreshRate() {
    return rr;
  }

  @Override
  public void setRefreshRate(int rr) {
    rr = rr;
  }

  @Override
  public int getResponseTime() {
    return rt;
  }

  @Override
  public void setResponseTime(int rt) {
    rt = rt;
  }
}
