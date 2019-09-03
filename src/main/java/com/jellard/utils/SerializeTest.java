package com.jellard.utils;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializeTest {
	private static final long serialVersionUID = -5836283489677344417L;
    private transient int classValue = 10;
    private transient Date date = new Date();
    private transient static int staticValue = 10;

    public static void main(String[] args) throws Exception {
    	SerializeTest m = new SerializeTest();
        m.classValue = 11;
        SerializeTest.staticValue = 11;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                new File("0xjh000")));
        out.writeObject(m);

        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                new File("0xjh000")));
        SerializeTest m1 = (SerializeTest) in.readObject();
        in.close();

        System.out.println(m1.classValue);
        System.out.println((m1.date == null ? "date is null"
                : "date is not null"));
    }

}
/*class ExternalizableTest implements Externalizable {

    private transient String content = "哈哈~我将会被序列化，不管我是是否被transient关键字修饰";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        content = (String) in.readObject();
    }

    public static void main(String[] args) throws Exception {
        ExternalizableTest et = new ExternalizableTest();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                new File("ext0000")));
        out.writeObject(et);

        ObjectInput in = new ObjectInputStream(new FileInputStream(new File(
                "ext0000")));
        ExternalizableTest et1 = (ExternalizableTest) in.readObject();
        System.out.println(et1.content);

        out.close();
        in.close();
    }
}
*/