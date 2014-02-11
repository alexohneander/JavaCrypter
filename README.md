JavaCrypter
===========

Crypt your Files!
Secure your Files for Evil Scanner ;) 


## How to use JavaCrypter ##

You'll want to build your **exec.dat** and **seed.dat.** **exec.dat** will contain the encrypted contents of your program, and **seed.dat** will contain the seed or key used to encrypt and decrypt your program. Don't worry, it's all automatic.

Just run the following command with your file name:
Code:

    java -jar JavaCrypter.jar -build YOURFILE

Replace **YOURFILE** with an absolute or relative path to your executable. ANY type of program that can be ran through the systems standard method of execution (E.G. command prompt, run, terminal, etc) can be used here. 

Press enter, and wait for your files to be generated. **exec.dat** and **seed.dat** will be placed in the SAME directory **YOURFILE** is located.

Note: If you want to specify your own seed, you may use the following command:

    java -jar JavaCrypter.jar -build YOURFILE seed1;seed2;seed3;seed4

Seeds must be four valid integers separated by semicolon.

After you've generated your files, next you need to pack them. Open up **JavaCrypter.jar**, using any standard **.zip** explorer. I prefer **winrar.** Navigate into the **"JavaCrypter"** folder. There, drag in both **exec.dat** and **seed.dat** that were generated earlier. Close/save the file, and you're done! Now just double click the jar and it will run undetectable! Note you CAN use this for a **driveby**, however you will need to sign the file. If your file does by change get detected, it is because your jar is unsigned. There are many easy tutorials on Google on how to sign the jar. 

If you have problems opening or building, it will most likely be due to incorrect installation of Java (E.G. no environmental variables set) or incorrect file association. Otherwise, PM me.

Next release will include auto building and possibly an option to auto sign the jar.
