package org.gaem.core.engine.io;

/**
 * Created by Johan on 2014-10-04.
 */
public class SaveLoadTest {

    public static void main(String[] args) {
        SessionData sessionData = new SessionData();
        sessionData.add(new StringTag("stringTest", "someValue"));
        sessionData.add(new IntTag("intTest", 10345));
        sessionData.add(new LongTag("longTest", 15L));
        sessionData.add(createCompoundTag());
        sessionData.save("saveLoadTest.dat");

        SessionData loadData = new SessionData();
        loadData.load("saveLoadTest.dat");

        System.out.println("stringTest = " + loadData.get("stringTest").getValue());
        System.out.println("intTest = " + loadData.get("intTest").getValue());
        System.out.println("longTest = " + loadData.get("longTest").getValue());

        CompoundTag t = (CompoundTag) loadData.get("compoundTest");
        System.out.println(t.getName() + " {");
        for (Tag tag : t.value) {
            System.out.println("    " + tag.getName() + " = " + tag.getValue());
        }
        System.out.println("}");
    }

    private static CompoundTag createCompoundTag() {
        CompoundTag t = new CompoundTag("compoundTest");
        t.add(new StringTag("compoundedStringTest", "someCompoundedValue"));
        t.add(new IntTag("compoundedIntTest", 1000));
        return t;
    }
}
