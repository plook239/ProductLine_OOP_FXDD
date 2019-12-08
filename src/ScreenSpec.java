/**
 * @author Peter Look
 */

interface ScreenSpec {
    String getResolution();

    void setResolution(String res);

    int getRefreshRate();

    void setRefreshRate(int rr);

    int getResponseTime();

    void setResponseTime(int rt);
}
