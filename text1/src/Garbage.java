import org.apache.tools.ant.taskdefs.Java;

/**
 * chenjx
 * 测试finalize的
 * 没测出啥来！！！！！！！！
 */
public class Garbage {
    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.err.println("Usage: /n" + "<a href="http://lib.csdn.net/base/java" class='replace_word' title="Java 知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java </a>Garbage before/n or:/n" + "java Garbage after");
//            return;
//        }
        while (!Chair.f) {
            new Chair();
            new String("To take up space");
        }
        System.out.println("After all Chairs have been created:/n" + "total created = " + Chair.created +
                "， total finalized = " + Chair.finalized);
        if (args[0].equals("before")) {
            System.out.println("gc():");
            System.gc();
            System.out.println("runFinalization():");
            System.runFinalization();
        }
        System.out.println("bye!");
        if (args[0].equals("after"))
            System.runFinalizersOnExit(true);
    }
}

class Chair {
    static boolean gcrun = false;
    static boolean f = false;
    static int created = 0;
    static int finalized = 0;
    int i;

    Chair() {
        i = ++created;
        if (created == 47)
            System.out.println("Created 47");
    }

    protected void finalize() {
        if (!gcrun) {
            gcrun = true;
            System.out.println("Beginning to finalize after " + created + " Chairs have been created");
        }
        if (i == 47) {
            System.out.println("Finalizing Chair #47， " + "Setting flag to stop Chair creation");
            f = true;
        }
        finalized++;
        if (finalized >= created)
            System.out.println("All " + finalized + " finalized");
    }
}


