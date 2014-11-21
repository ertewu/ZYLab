package zystudio.cases.prepare;


public class CaseSynchronizedEffect {

    private static CaseSynchronizedEffect sCase;
    public static CaseSynchronizedEffect obtain() {
        if (sCase == null) {
            sCase = new CaseSynchronizedEffect();
        }
        return sCase;
    }

    public void work(){

    }

}
