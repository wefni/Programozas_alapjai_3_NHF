public class Best {
    private String name;
    private String wpm;

    public Best(String sor){
        String[] s = sor.split(" ");
        name = s[0];
        wpm = s[1];
    }

    public String getWpm() {
        return wpm;
    }

    public String getName() {
        return name;
    }
}
