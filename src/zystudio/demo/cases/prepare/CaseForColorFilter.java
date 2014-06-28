package zystudio.demo.cases.prepare;


public class CaseForColorFilter {
    private static CaseForColorFilter sCase;

    public static CaseForColorFilter obtain() {
        if (sCase == null) {
            sCase = new CaseForColorFilter();
        }
        return sCase;
    }

    public void work() {

    }
}
