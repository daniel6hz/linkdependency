import java.util.*;
import java.lang.Object;

public class linkdependency {
  public static void main(String[] args) {
		ArrayList<Pair<String, String>> arr = new ArrayList<Pair<String, String>>();
		arr.add(new Pair<String, String>("a", "d"));
		arr.add(new Pair<String, String>("a", "g"));
		arr.add(new Pair<String, String>("b", "d"));
		arr.add(new Pair<String, String>("c", "d"));
		arr.add(new Pair<String, String>("c", "e"));
		arr.add(new Pair<String, String>("d", "f"));
		arr.add(new Pair<String, String>("e", "f"));
		arr.add(new Pair<String, String>("f", "g"));
		arr.add(new Pair<String, String>("g", "i"));
		System.out.println(findpriority(arr).toString());
	}

	public static Queue<String> findpriority(ArrayList<Pair<String, String>> g) {
		int len = g.size();
		LinkedList<String> pool = new LinkedList<String>();
		LinkedList<String> output = new LinkedList<String>();
		for (int i = 0; i < len; i++) {
			if (!pool.contains(g.get(i).second))
				pool.add(g.get(i).second);
		}

		for (int i = 0; i < len; i++) {
			if (!pool.contains(g.get(i).first)&&(!output.contains(g.get(i).first)))
				output.add(g.get(i).first);
		}
		boolean flag = true;
		while (!pool.isEmpty()) {
			int lenoutput = output.size();
			for (int i = 0; i < lenoutput; i++) {
				String currentvisit = output.get(i);
				for (int k = 0; k < len; k++) {
					if (g.get(k).first == currentvisit) {
						String currentsecond = g.get(k).second;
						for (int j = 0; j < len; j++) {
							Pair<String, String> currentnode = g.get(j);
							if ((currentnode.second == currentsecond) && (pool.contains(currentnode.second)) &&(!pool.contains(currentnode.first))) {
								flag = true;
								for (int t = 0; t < len; t++) {
									if ((g.get(t).second == currentsecond) && (pool.contains(g.get(t).first)))
										flag = false;
								}
								if (flag == true) {
									int index = pool.indexOf(currentsecond);
									pool.remove(index);
									output.add(currentsecond);
								}
							}
						}
					}

				}
			}
		}
		return output;

	}
}
