public class Play {
    public static final int TRAGEDY = 0;
    public static final int COMEDY = 1;

    private String id;
    private String name;
    private int type;

    public Play(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }
    
}
