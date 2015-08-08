public class Solution {

	static String s = "a1221a";

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(new Solution().longestPalindrome(s));
		System.out.println(System.currentTimeMillis());
	}


	public String longestPalindrome(String s) {
		char [] cs = new char[s.length()*2+1];
		char [] cp = s.toCharArray();
		int p = 0;
		cs[0] = '#';
		for (int i = 0; i < s.length(); i++) {
			p++;
			cs[p] = cp[i];
			p++;
			cs[p] = '#';
		}
		int[] raduis = new int[cs.length];
		int maxR = 0;
		int maxPositon = 0;
		for (int i = 0; i < cs.length; i++) {
			int l = i - 1;
			int r = i + 1;
			int rd = 0;
			while (l >= 0 && r < cs.length) {
				if (cs[l] == cs[r]) {
					l--;
					r++;
					rd++;
				} else
					break;
			}
			raduis[i] = rd;
			if (maxR < raduis[i]) {
				maxR = raduis[i];
				maxPositon = i;
			}

		}

		String result = "";
		char c;
		for (int i = maxPositon - maxR + 1; i < (maxPositon + maxR ); i++) {
			c = cs[i];
			if (!(c == '#'))
				result += c;
		}

		return result;
	}
	
}