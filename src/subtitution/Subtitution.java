package subtitution;

import java.io.*;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by bidau on 21/06/2016.
 */
public class Subtitution implements ICypher {


    private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public void encode(String alphabet, String key, File message, File encodeMessage){
        try {
            Reader reader = new InputStreamReader(new FileInputStream(message));
            Writer writer = new OutputStreamWriter(new FileOutputStream(encodeMessage));
            Map map = new HashMap<Integer, Character>();
            System.out.println(alphabet);

            for(int i = 0; i < alphabet.length(); i++){
                System.out.println(alphabet.charAt(i)+","+ key.charAt(i));
                map.put(alphabet.charAt(i), key.charAt(i));
            }
            int i = 0;
            for(char c: alphabet.toCharArray())
                map.put(c,i++);
            int c;
            while ((c= reader.read())!=-1){
                writer.write(null != map.get(c)?(int)map.get(c):c);
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void encode(File message, Object key, File crypted) {
        encode(alphabet, (String) readKey((File) key), message, crypted);
    }

    @Override
    public void decode(File crypted, Object key, File message) {
        encode((String)readKey((File) key), alphabet, crypted, message);
    }

    @Override
    public Object generateKey(Object param) {
        Random random = new Random();
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int alphaLenth = alphabet.length;
        while(alphaLenth > 0){
            int i = random.nextInt(alphaLenth);
            char tmp = alphabet[alphaLenth-1];
            alphabet[alphaLenth-1] = alphabet[i];
            alphabet[i] = tmp;
            alphaLenth--;
        }
        String val = new String(alphabet);
        System.out.println(val);
        return val;
    }


    @Override
    public Object readKey(File f) {
        try {
            System.out.println("dsqdqsd");
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeKey(File f) {
        try {
            PrintWriter pr = new PrintWriter(f);
            pr.println(generateKey(null));
            pr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
