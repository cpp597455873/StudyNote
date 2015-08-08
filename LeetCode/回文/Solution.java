public class Solution {

	static String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
	public static void main(String[] args) {
		System.out.println(new Solution().longestPalindrome(s));
	}

	public String longestPalindrome(String s) {
		String cs = change(s);
		int[] raduis = new int[cs.length()];

		for (int i = 0; i < cs.length(); i++) {
			int l = i - 1;
			int r = i + 1;
			int rd = 0;
			while (l >= 0 && r < cs.length()) {
				if (cs.charAt(l) == cs.charAt(r)) {
					l--;
					r++;
					rd++;
				} else
					break;
			}
			raduis[i] = rd;
		}

		int maxR = 0;
		int maxPositon = 0;

		for (int i = 0; i < cs.length(); i++) {
			if (maxR < raduis[i]) {
				maxR = raduis[i];
				maxPositon = i;
			}
		}
		return cs.substring(maxPositon - maxR + 1, maxPositon +maxR).replace("#", "");
	}

	private String change(String s) {
		String s2 = new String("#");
		for (int i = 0; i < s.length(); i++) {
			s2 += s.charAt(i) + "#";
		}
		return s2;
	}

}