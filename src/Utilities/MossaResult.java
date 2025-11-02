package Utilities;

import java.util.List;

public class MossaResult {

    private final boolean giocoTerminato;
    private final List<String> notifiche;
    private final String messaggioFinale;

    public MossaResult(boolean giocoTerminato, List<String> notifiche, String messaggioFinale) {
        this.giocoTerminato = giocoTerminato;
        this.notifiche = notifiche;
        this.messaggioFinale = messaggioFinale;
    }

    public static MossaResult ongoing(List<String> note) {
        return new MossaResult(false, note, null);
    }

    public static MossaResult finished(List<String> note, String finale) {
        return new MossaResult(true, note, finale);
    }

    public boolean isGiocoTerminato() {
        return giocoTerminato;
    }

    public List<String> getNotifiche() {
        return notifiche;
    }

    public String getMessaggioFinale() {
        return messaggioFinale;
    }

    @Override
    public String toString() {
        return "MossaResult{" +
                "giocoTerminato=" + giocoTerminato +
                ", notifiche=" + notifiche +
                ", messaggioFinale='" + messaggioFinale + '\'' +
                '}';
    }
}
