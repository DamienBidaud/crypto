package subtitution;

import java.io.File;

/**
 * Created by bidau on 21/06/2016.
 */
public class main {
    public static void main(String [] args)
    {
        Sesar s = new Sesar();
        String message = "Bonjour comment aller vous";
        Integer g =(Integer) s.generateKey(new Object());
        System.out.println(g);

        s.encode(new File("message.txt"), g, new File("crypted.txt"));
        s.decode(new File("crypted.txt"), g, new File("message2.txt"));

        Subtitution subtitution = new Subtitution();

        subtitution.writeKey(new File("keySub.txt"));

        subtitution.encode(new File("message.txt"), new File("keySub.txt"), new File("cryptedSub.txt"));
        subtitution.decode(new File("cryptedSub.txt"), new File("keySub.txt"), new File("message3.txt"));

    }
}
