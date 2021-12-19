import java.util.HashMap;

public class SizeCalculator {

    private final static String FACTORS = "BKMGT";
    private final static int KILOBYTE = 1024;
    private static HashMap<Character, Integer> char2multiplier =
                            getMultiplier();

    public static String getHumanReadableSize(long size) {

        if(size < KILOBYTE) {
            return String.format("%d %c", size, FACTORS.charAt(0));
        }
        int multiplier = (int)(Math.log(size)/Math.log(KILOBYTE));
        char sizeFactor= FACTORS.charAt(multiplier);
        return String.format("%.2f %cb",size / Math.pow(KILOBYTE, multiplier), sizeFactor);
    }

    public static long getSizeFromHumanReadable(String size) {

        char sizeFactor = size
                .replaceAll("[0-9\\s+]","")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.parseLong(
                size.replaceAll("[^0-9]", "")
        );
        return length;
    }

    private static HashMap<Character, Integer> getMultiplier() {
        char2multiplier = new HashMap<>();
        for(int i = 0; i < FACTORS.length(); i++) {
            char2multiplier.put(
                    FACTORS.charAt(i),
                    (int) Math.pow(KILOBYTE, i)
            );
        }
        return char2multiplier;
    }

}
