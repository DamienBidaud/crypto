package subtitution;

import java.io.File;

/**
 * Created by bidau on 21/06/2016.
 */
public interface ICypher {
    void encode(File message, Object key, File crypted);
    void decode(File crypted, Object key, File message);
    Object generateKey(Object param);
    Object readKey(File f);
    void writeKey(File f);
}
