package de.fh_kiel;

import org.springframework.stereotype.Service;

@Service
public class CheckNullTestService {

    private boolean testNotNullExecuted;
    private boolean testNotNullSilentExecuted;

    @CheckNull
    public void testNotNull(Object arg) {
        testNotNullExecuted = true;
    }

    @CheckNull(silent = true)
    public void testNotNullSilent(Object arg) {
        testNotNullSilentExecuted = true;
    }

    public boolean isTestNotNullExecuted() {
        return testNotNullExecuted;
    }

    public void setTestNotNullExecuted(final boolean testNotNullExecuted) {
        this.testNotNullExecuted = testNotNullExecuted;
    }

    public boolean isTestNotNullSilentExecuted() {
        return testNotNullSilentExecuted;
    }

    public void setTestNotNullSilentExecuted(final boolean testNotNullSilentExecuted) {
        this.testNotNullSilentExecuted = testNotNullSilentExecuted;
    }
}
