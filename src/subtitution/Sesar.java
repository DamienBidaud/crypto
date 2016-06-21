package subtitution;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by bidau on 21/06/2016.
 */
public class Sesar implements ICypher {
    @Override
    public void encode(File message, Object key, File crypted) {
        if(key instanceof Integer) {
            BufferedReader br;
            PrintWriter fo;
            String line;
            try {
                br = new BufferedReader(new FileReader(message));
                fo = new PrintWriter(crypted);
                Integer decalage = (Integer) key;
                while((line = br.readLine())!=null){
                    char[] character = line.toCharArray();
                    String encrypted = "";
                    for (char c :
                            character) {
                            int position = c;
                            if (c >= 97 && c <= 122) {
                                if (c + decalage > 122) {
                                    int diff = 123 - position;
                                    encrypted += (char) (97 + sub(diff, decalage));
                                } else {
                                    encrypted += (char) (c + decalage);
                                }
                            } else if (c >= 65 && c <= 90) {
                                if (c + decalage > 90) {
                                    int diff = 91 - position;
                                    encrypted += (char) (65 + sub(diff, decalage));
                                } else {
                                    encrypted += (char) (c + decalage);
                                }
                            } else {
                                encrypted += c;
                            }
                    }
                    fo.println(encrypted);
                }
                br.close();
                fo.close();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }

        }


    }

    private int sub(int a, int b){
        if(a > b){
            return a-b;
        }else{
            return b-a;
        }
    }

    @Override
    public void decode(File crypted, Object key, File message) {
        if(key instanceof Integer) {
            BufferedReader br;
            PrintWriter fo;
            String line;
            try {
                br = new BufferedReader(new FileReader(crypted));
                fo = new PrintWriter(message);
                Integer decalage = (Integer) key;
                while((line = br.readLine())!=null) {
                    char[] character = line.toCharArray();
                    String encrypted = "";
                    for (char c :
                            character) {
                            int position = c;
                            if (c >= 97 && c <= 122) {
                                if (c - decalage < 97) {
                                    int diff = (position - 96);
                                    encrypted += (char) (122 - sub(diff, decalage));
                                } else {
                                    encrypted += (char) (c - decalage);
                                }
                            } else if (c >= 65 && c <= 90) {
                                if (c - decalage < 65) {
                                    int diff = position - 64;
                                    encrypted += (char) (90 - sub(diff, decalage));
                                } else {
                                    encrypted += (char) (c - decalage);
                                }
                            } else {
                            encrypted += c;
                        }
                    }
                    fo.println(encrypted);
                }
                br.close();
                fo.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public Object generateKey(Object param) {
        Random r = new Random();
        return r.nextInt(26-1)+1;
    }

    @Override
    public Object readKey(File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            try {
                Integer vall = Integer.parseInt(br.readLine());
                return vall;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
