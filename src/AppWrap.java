import JavaCrypter.ISAAC;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

/**
* @author NOODLE
*/
public class AppWrap {

    static SecureRandom r = new SecureRandom();

    public static void main(String[] args) throws IOException {
  if (args.length > 0) {
    if (args[0].equalsIgnoreCase("-build")) {

    File toBuild = new File(args[1]);

    int[] seed = new int[4];
    if (args.length > 2) {
    String[] sSeed = args[2].split(";");
    for (int i = 0; i < 4; i++) {
    seed[i] = Integer.parseInt(sSeed[i]);
    }

    } else {
    for (int i = 0; i < 4; i++) {
    seed[i] = r.nextInt();
    }
    }

    System.out.print("Building file \"" + toBuild + "\" with seed ");
    for (int i = 0; i < 4; i++) {
    System.out.print(seed[i] + ", ");
    }
    System.out.println();

    DataOutputStream out = null;
    try {
    out = new DataOutputStream(new FileOutputStream(toBuild.getAbsoluteFile().getParent() + "/seed.dat"));
    for (int i = 0; i < 4; i++) {
    out.writeInt(seed[i]);
    }

    } finally {
    if (out != null) {
    out.close();
    }
    }

    FileInputStream in = null;
    try {
    in = new FileInputStream(toBuild);
    try {
    out = new DataOutputStream(new FileOutputStream(toBuild.getAbsoluteFile().getParent() + "/exec.dat"));

    ISAAC isaac = new ISAAC(seed);
    byte[] buf = new byte[1024 * 1024 * 128];

    int len;
    while ((len = in.read(buf)) != -1) {
    for (int i = 0; i < len; i++) {
    buf[i] = (byte) (buf[i] + isaac.getNextKey());
    }
    out.write(buf, 0, len);
    }

    } finally {
    if (out != null) {
    out.close();
    }
    }

    } finally {
    if (in != null) {
    in.close();
    }
    }

    System.out.println("Success!");
    }

  } else {

    int[] seed = new int[4];

    {
    DataInputStream in = null;
    try {
    in = new DataInputStream(AppWrap.class.getResourceAsStream("seed.dat"));
    for (int i = 0; i < 4; i++) {
    seed[i] = in.readInt();
    }

    } finally {
    if (in != null) {
    in.close();
    }
    }
    }

    InputStream in = null;
    try {
    in = AppWrap.class.getResourceAsStream("exec.dat");
    File tmpexec = File.createTempFile(randstr(12), randstr(3));
    FileOutputStream out = null;
    try {
    out = new FileOutputStream(tmpexec);

    ISAAC isaac = new ISAAC(seed);
    byte[] buf = new byte[1024 * 1024 * 128];

    int len;
    while ((len = in.read(buf)) != -1) {
    for (int i = 0; i < len; i++) {
    buf[i] = (byte) (buf[i] - isaac.getNextKey());
    }
    out.write(buf, 0, len);
    }

    } finally {
    if (out != null) {
    out.close();
    }
    }

    Runtime.getRuntime().exec(tmpexec.getAbsolutePath());

    } finally {
    if (in != null) {
    in.close();
    }
    }
  }
    }

    static String randstr(int len) {
  StringBuilder b = new StringBuilder(len);
  for (int i = 0; i < len; i++) {
    b.append((char) ('A' + (r.nextDouble() * ('Z' - 'A'))));
  }
  return b.toString();
    }
}