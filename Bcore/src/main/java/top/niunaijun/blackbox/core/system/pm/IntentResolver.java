package top.niunaijun.blackbox.core.system.pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.MutableInt;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import top.niunaijun.blackbox.core.system.pm.BPackage.IntentInfo;
import top.niunaijun.blackbox.core.system.user.BUserHandle;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IntentResolver.class */
public abstract class IntentResolver<F extends BPackage.IntentInfo, R> {
    private static final String TAG = "IntentResolver";
    private static final boolean DEBUG = false;
    private static final boolean localLOGV = false;
    private static final boolean localVerificationLOGV = false;
    private static final Comparator mResolvePrioritySorter = new Comparator() { // from class: top.niunaijun.blackbox.core.system.pm.IntentResolver.1
        @Override // java.util.Comparator
        public int compare(Object o1, Object o2) {
            int q1 = ((IntentFilter) o1).getPriority();
            int q2 = ((IntentFilter) o2).getPriority();
            if (q1 > q2) {
                return -1;
            }
            return q1 < q2 ? 1 : 0;
        }
    };
    private final HashSet<F> mFilters = new HashSet<>();
    private final ArrayMap<String, F[]> mTypeToFilter = new ArrayMap<>();
    private final ArrayMap<String, F[]> mBaseTypeToFilter = new ArrayMap<>();
    private final ArrayMap<String, F[]> mWildTypeToFilter = new ArrayMap<>();
    private final ArrayMap<String, F[]> mSchemeToFilter = new ArrayMap<>();
    private final ArrayMap<String, F[]> mActionToFilter = new ArrayMap<>();
    private final ArrayMap<String, F[]> mTypedActionToFilter = new ArrayMap<>();

    protected abstract boolean isPackageForFilter(String str, F f);

    protected abstract F[] newArray(int i);

    public void addFilter(F f) {
        this.mFilters.add(f);
        int numS = register_intent_filter(f, f.intentFilter.schemesIterator(), this.mSchemeToFilter, "      Scheme: ");
        int numT = register_mime_types(f, "      Type: ");
        if (numS == 0 && numT == 0) {
            register_intent_filter(f, f.intentFilter.actionsIterator(), this.mActionToFilter, "      Action: ");
        }
        if (numT != 0) {
            register_intent_filter(f, f.intentFilter.actionsIterator(), this.mTypedActionToFilter, "      TypedAction: ");
        }
    }

    public static boolean filterEquals(IntentFilter f1, IntentFilter f2) {
        int s1 = f1.countActions();
        int s2 = f2.countActions();
        if (s1 != s2) {
            return false;
        }
        for (int i = 0; i < s1; i++) {
            if (!f2.hasAction(f1.getAction(i))) {
                return false;
            }
        }
        int s3 = f1.countCategories();
        int s4 = f2.countCategories();
        if (s3 != s4) {
            return false;
        }
        for (int i2 = 0; i2 < s3; i2++) {
            if (!f2.hasCategory(f1.getCategory(i2))) {
                return false;
            }
        }
        int s5 = f1.countDataSchemes();
        int s6 = f2.countDataSchemes();
        if (s5 != s6) {
            return false;
        }
        for (int i3 = 0; i3 < s5; i3++) {
            if (!f2.hasDataScheme(f1.getDataScheme(i3))) {
                return false;
            }
        }
        int s7 = f1.countDataSchemeSpecificParts();
        int s8 = f2.countDataSchemeSpecificParts();
        if (s7 != s8) {
            return false;
        }
        return true;
    }

    private ArrayList<F> collectFilters(F[] array, IntentFilter matching) {
        F cur;
        ArrayList<F> res = null;
        if (array != null) {
            for (int i = 0; i < array.length && (cur = array[i]) != null; i++) {
                if (filterEquals(cur.intentFilter, matching)) {
                    if (res == null) {
                        res = new ArrayList<>();
                    }
                    res.add(cur);
                }
            }
        }
        return res;
    }

    public ArrayList<F> findFilters(IntentFilter matching) {
        if (matching.countDataSchemes() == 1) {
            return collectFilters(this.mSchemeToFilter.get(matching.getDataScheme(0)), matching);
        }
        if (matching.countDataTypes() != 0 && matching.countActions() == 1) {
            return collectFilters(this.mTypedActionToFilter.get(matching.getAction(0)), matching);
        }
        if (matching.countDataTypes() == 0 && matching.countDataSchemes() == 0 && matching.countActions() == 1) {
            return collectFilters(this.mActionToFilter.get(matching.getAction(0)), matching);
        }
        ArrayList<F> res = null;
        for (F cur : this.mFilters) {
            if (filterEquals(cur.intentFilter, matching)) {
                if (res == null) {
                    res = new ArrayList<>();
                }
                res.add(cur);
            }
        }
        return res;
    }

    public void removeFilter(F f) {
        removeFilterInternal(f);
        this.mFilters.remove(f);
    }

    void removeFilterInternal(F f) {
        int numS = unregister_intent_filter(f, f.intentFilter.schemesIterator(), this.mSchemeToFilter, "      Scheme: ");
        int numT = unregister_mime_types(f, "      Type: ");
        if (numS == 0 && numT == 0) {
            unregister_intent_filter(f, f.intentFilter.actionsIterator(), this.mActionToFilter, "      Action: ");
        }
        if (numT != 0) {
            unregister_intent_filter(f, f.intentFilter.actionsIterator(), this.mTypedActionToFilter, "      TypedAction: ");
        }
    }

    boolean dumpMap(PrintWriter out, String titlePrefix, String title, String prefix, ArrayMap<String, F[]> map, String packageName, boolean printFilter, boolean collapseDuplicates) {
        F filter;
        F filter2;
        String eprefix = prefix + "  ";
        String fprefix = prefix + "    ";
        ArrayMap<Object, MutableInt> found = new ArrayMap<>();
        boolean printedSomething = false;
        Printer printer = null;
        for (int mapi = 0; mapi < map.size(); mapi++) {
            F[] a = map.valueAt(mapi);
            int N = a.length;
            boolean printedHeader = false;
            if (collapseDuplicates && !printFilter) {
                found.clear();
                for (int i = 0; i < N && (filter2 = a[i]) != null; i++) {
                    if (packageName == null || isPackageForFilter(packageName, filter2)) {
                        Object label = filterToLabel(filter2);
                        int index = found.indexOfKey(label);
                        if (index < 0) {
                            found.put(label, new MutableInt(1));
                        } else {
                            found.valueAt(index).value++;
                        }
                    }
                }
                for (int i2 = 0; i2 < found.size(); i2++) {
                    if (title != null) {
                        out.print(titlePrefix);
                        out.println(title);
                        title = null;
                    }
                    if (!printedHeader) {
                        out.print(eprefix);
                        out.print(map.keyAt(mapi));
                        out.println(":");
                        printedHeader = true;
                    }
                    printedSomething = true;
                    dumpFilterLabel(out, fprefix, found.keyAt(i2), found.valueAt(i2).value);
                }
            } else {
                for (int i3 = 0; i3 < N && (filter = a[i3]) != null; i3++) {
                    if (packageName == null || isPackageForFilter(packageName, filter)) {
                        if (title != null) {
                            out.print(titlePrefix);
                            out.println(title);
                            title = null;
                        }
                        if (!printedHeader) {
                            out.print(eprefix);
                            out.print(map.keyAt(mapi));
                            out.println(":");
                            printedHeader = true;
                        }
                        printedSomething = true;
                        dumpFilter(out, fprefix, filter);
                        if (printFilter) {
                            if (printer == null) {
                                printer = new PrintWriterPrinter(out);
                            }
                            filter.intentFilter.dump(printer, fprefix + "  ");
                        }
                    }
                }
            }
        }
        return printedSomething;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IntentResolver$IteratorWrapper.class */
    private class IteratorWrapper implements Iterator<F> {
        private final Iterator<F> mI;
        private F mCur;

        IteratorWrapper(Iterator<F> it) {
            this.mI = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mI.hasNext();
        }

        @Override // java.util.Iterator
        public F next() {
            F next = this.mI.next();
            this.mCur = next;
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.mCur != null) {
                IntentResolver.this.removeFilterInternal(this.mCur);
            }
            this.mI.remove();
        }
    }

    public Iterator<F> filterIterator() {
        return new IteratorWrapper(this.mFilters.iterator());
    }

    public Set<F> filterSet() {
        return Collections.unmodifiableSet(this.mFilters);
    }

    public List<R> queryIntentFromList(Intent intent, String resolvedType, boolean defaultOnly, ArrayList<F[]> listCut, int userId) {
        ArrayList<R> resultList = new ArrayList<>();
        boolean debug = (intent.getFlags() & 8) != 0;
        FastImmutableArraySet<String> categories = getFastIntentCategories(intent);
        String scheme = intent.getScheme();
        int N = listCut.size();
        for (int i = 0; i < N; i++) {
            buildResolveList(intent, categories, debug, defaultOnly, resolvedType, scheme, listCut.get(i), resultList, userId);
        }
        filterResults(resultList);
        return resultList;
    }

    public List<R> queryIntent(Intent intent, String resolvedType, boolean defaultOnly, int userId) {
        int slashpos;
        String scheme = intent.getScheme();
        ArrayList<R> finalList = new ArrayList<>();
        boolean debug = (intent.getFlags() & 8) != 0;
        if (debug) {
            Slog.v(TAG, "Resolving type=" + resolvedType + " scheme=" + scheme + " defaultOnly=" + defaultOnly + " userId=" + userId + " of " + intent);
        }
        F[] firstTypeCut = null;
        F[] secondTypeCut = null;
        F[] thirdTypeCut = null;
        F[] schemeCut = null;
        if (resolvedType != null && (slashpos = resolvedType.indexOf(47)) > 0) {
            String baseType = resolvedType.substring(0, slashpos);
            if (!baseType.equals("*")) {
                if (resolvedType.length() != slashpos + 2 || resolvedType.charAt(slashpos + 1) != '*') {
                    firstTypeCut = this.mTypeToFilter.get(resolvedType);
                    if (debug) {
                        Slog.v(TAG, "First type cut: " + Arrays.toString(firstTypeCut));
                    }
                    secondTypeCut = this.mWildTypeToFilter.get(baseType);
                    if (debug) {
                        Slog.v(TAG, "Second type cut: " + Arrays.toString(secondTypeCut));
                    }
                } else {
                    firstTypeCut = this.mBaseTypeToFilter.get(baseType);
                    if (debug) {
                        Slog.v(TAG, "First type cut: " + Arrays.toString(firstTypeCut));
                    }
                    secondTypeCut = this.mWildTypeToFilter.get(baseType);
                    if (debug) {
                        Slog.v(TAG, "Second type cut: " + Arrays.toString(secondTypeCut));
                    }
                }
                thirdTypeCut = this.mWildTypeToFilter.get("*");
                if (debug) {
                    Slog.v(TAG, "Third type cut: " + Arrays.toString(thirdTypeCut));
                }
            } else if (intent.getAction() != null) {
                firstTypeCut = this.mTypedActionToFilter.get(intent.getAction());
                if (debug) {
                    Slog.v(TAG, "Typed Action list: " + Arrays.toString(firstTypeCut));
                }
            }
        }
        if (scheme != null) {
            schemeCut = this.mSchemeToFilter.get(scheme);
            if (debug) {
                Slog.v(TAG, "Scheme list: " + Arrays.toString(schemeCut));
            }
        }
        if (resolvedType == null && scheme == null && intent.getAction() != null) {
            firstTypeCut = this.mActionToFilter.get(intent.getAction());
            if (debug) {
                Slog.v(TAG, "Action list: " + Arrays.toString(firstTypeCut));
            }
        }
        FastImmutableArraySet<String> categories = getFastIntentCategories(intent);
        if (firstTypeCut != null) {
            buildResolveList(intent, categories, debug, defaultOnly, resolvedType, scheme, firstTypeCut, finalList, userId);
        }
        if (secondTypeCut != null) {
            buildResolveList(intent, categories, debug, defaultOnly, resolvedType, scheme, secondTypeCut, finalList, userId);
        }
        if (thirdTypeCut != null) {
            buildResolveList(intent, categories, debug, defaultOnly, resolvedType, scheme, thirdTypeCut, finalList, userId);
        }
        if (schemeCut != null) {
            buildResolveList(intent, categories, debug, defaultOnly, resolvedType, scheme, schemeCut, finalList, userId);
        }
        filterResults(finalList);
        if (debug) {
            Slog.v(TAG, "Final result list:");
            for (int i = 0; i < finalList.size(); i++) {
                Slog.v(TAG, "  " + finalList.get(i));
            }
        }
        return finalList;
    }

    protected boolean allowFilterResult(F filter, List<R> dest) {
        return true;
    }

    protected boolean isFilterStopped(F filter, int userId) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressWarnings("unchecked")
    protected R newResult(F f, int match, int userId) {
        return (R) f;
    }

    protected void sortResults(List<R> results) {
        Collections.sort(results, mResolvePrioritySorter);
    }

    protected void filterResults(List<R> results) {
    }

    protected void dumpFilter(PrintWriter out, String prefix, F filter) {
        out.print(prefix);
        out.println(filter);
    }

    protected Object filterToLabel(F filter) {
        return "IntentFilter";
    }

    protected void dumpFilterLabel(PrintWriter out, String prefix, Object label, int count) {
        out.print(prefix);
        out.print(label);
        out.print(": ");
        out.println(count);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addFilter(ArrayMap<String, F[]> arrayMap, String name, F filter) {
        F[] intentInfoArr = arrayMap.get(name);
        if (intentInfoArr == null) {
            F[] intentInfoArrNewArray = newArray(2);
            arrayMap.put(name, intentInfoArrNewArray);
            intentInfoArrNewArray[0] = filter;
            return;
        }
        int N = intentInfoArr.length;
        int i = N;
        while (i > 0 && intentInfoArr[i - 1] == null) {
            i--;
        }
        if (i < N) {
            intentInfoArr[i] = filter;
            return;
        }
        F[] intentInfoArrNewArray2 = newArray((N * 3) / 2);
        System.arraycopy(intentInfoArr, 0, intentInfoArrNewArray2, 0, N);
        intentInfoArrNewArray2[N] = filter;
        arrayMap.put(name, intentInfoArrNewArray2);
    }

    private final int register_mime_types(F filter, String prefix) {
        Iterator<String> i = filter.intentFilter.typesIterator();
        if (i == null) {
            return 0;
        }
        int num = 0;
        while (i.hasNext()) {
            String name = i.next();
            num++;
            String baseName = name;
            int slashpos = name.indexOf(47);
            if (slashpos > 0) {
                baseName = name.substring(0, slashpos).intern();
            } else {
                name = name + "/*";
            }
            addFilter(this.mTypeToFilter, name, filter);
            if (slashpos > 0) {
                addFilter(this.mBaseTypeToFilter, baseName, filter);
            } else {
                addFilter(this.mWildTypeToFilter, baseName, filter);
            }
        }
        return num;
    }

    private final int unregister_mime_types(F filter, String prefix) {
        Iterator<String> i = filter.intentFilter.typesIterator();
        if (i == null) {
            return 0;
        }
        int num = 0;
        while (i.hasNext()) {
            String name = i.next();
            num++;
            String baseName = name;
            int slashpos = name.indexOf(47);
            if (slashpos > 0) {
                baseName = name.substring(0, slashpos).intern();
            } else {
                name = name + "/*";
            }
            remove_all_objects(this.mTypeToFilter, name, filter);
            if (slashpos > 0) {
                remove_all_objects(this.mBaseTypeToFilter, baseName, filter);
            } else {
                remove_all_objects(this.mWildTypeToFilter, baseName, filter);
            }
        }
        return num;
    }

    private final int register_intent_filter(F filter, Iterator<String> i, ArrayMap<String, F[]> dest, String prefix) {
        if (i == null) {
            return 0;
        }
        int num = 0;
        while (i.hasNext()) {
            String name = i.next();
            num++;
            addFilter(dest, name, filter);
        }
        return num;
    }

    private final int unregister_intent_filter(F filter, Iterator<String> i, ArrayMap<String, F[]> dest, String prefix) {
        if (i == null) {
            return 0;
        }
        int num = 0;
        while (i.hasNext()) {
            String name = i.next();
            num++;
            remove_all_objects(dest, name, filter);
        }
        return num;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void remove_all_objects(ArrayMap<String, F[]> arrayMap, String name, Object object) {
        F[] intentInfoArr = arrayMap.get(name);
        if (intentInfoArr != null) {
            int LAST = intentInfoArr.length - 1;
            while (LAST >= 0 && intentInfoArr[LAST] == null) {
                LAST--;
            }
            for (int idx = LAST; idx >= 0; idx--) {
                if (intentInfoArr[idx] == object) {
                    int remain = LAST - idx;
                    if (remain > 0) {
                        System.arraycopy(intentInfoArr, idx + 1, intentInfoArr, idx, remain);
                    }
                    intentInfoArr[LAST] = null;
                    LAST--;
                }
            }
            if (LAST < 0) {
                arrayMap.remove(name);
            } else if (LAST < intentInfoArr.length / 2) {
                F[] intentInfoArrNewArray = newArray(LAST + 2);
                System.arraycopy(intentInfoArr, 0, intentInfoArrNewArray, 0, LAST + 1);
                arrayMap.put(name, intentInfoArrNewArray);
            }
        }
    }

    private static FastImmutableArraySet<String> getFastIntentCategories(Intent intent) {
        Set<String> categories = intent.getCategories();
        if (categories == null) {
            return null;
        }
        return new FastImmutableArraySet<>((String[]) categories.toArray(new String[categories.size()]));
    }

    private void buildResolveList(Intent intent, FastImmutableArraySet<String> categories, boolean debug, boolean defaultOnly, String resolvedType, String scheme, F[] src, List<R> dest, int userId) {
        F filter;
        String reason;
        String action = intent.getAction();
        Uri data = intent.getData();
        String packageName = intent.getPackage();
        int N = src != null ? src.length : 0;
        boolean hasNonDefaults = false;
        for (int i = 0; i < N && (filter = src[i]) != null; i++) {
            if (debug) {
                Slog.v(TAG, "Matching against filter " + filter);
            }
            if (packageName != null && !isPackageForFilter(packageName, filter)) {
                if (debug) {
                    Slog.v(TAG, "  Filter is not from package " + packageName + "; skipping");
                }
            } else if (!allowFilterResult(filter, dest)) {
                if (debug) {
                    Slog.v(TAG, "  Filter's target already added");
                }
            } else {
                int match = filter.intentFilter.match(action, resolvedType, scheme, data, categories, TAG);
                if (match >= 0) {
                    if (debug) {
                        Slog.v(TAG, "  Filter matched!  match=0x" + Integer.toHexString(match) + " hasDefault=" + filter.intentFilter.hasCategory("android.intent.category.DEFAULT"));
                    }
                    if (!defaultOnly || filter.intentFilter.hasCategory("android.intent.category.DEFAULT")) {
                        R oneResult = newResult(filter, match, userId);
                        if (debug) {
                            Slog.v(TAG, "    Created result: " + oneResult);
                        }
                        if (oneResult != null) {
                            dest.add(oneResult);
                        }
                    } else {
                        hasNonDefaults = true;
                    }
                } else if (debug) {
                    switch (match) {
                        case BUserHandle.USER_XPOSED /* -4 */:
                            reason = "category";
                            break;
                        case BUserHandle.USER_CURRENT_OR_SELF /* -3 */:
                            reason = "action";
                            break;
                        case BUserHandle.USER_CURRENT /* -2 */:
                            reason = "data";
                            break;
                        case -1:
                            reason = "type";
                            break;
                        default:
                            reason = "unknown reason";
                            break;
                    }
                    Slog.v(TAG, "  Filter did not match: " + reason);
                }
            }
        }
        if (debug && hasNonDefaults) {
            if (dest.size() == 0) {
                Slog.v(TAG, "resolveIntent failed: found match, but none with CATEGORY_DEFAULT");
            } else if (dest.size() > 1) {
                Slog.v(TAG, "resolveIntent: multiple matches, only some with CATEGORY_DEFAULT");
            }
        }
    }
}
