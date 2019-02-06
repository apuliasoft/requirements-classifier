package com.example.mahout;

import com.example.mahout.entity.Requirement;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SamplesCreator {

	static int [] values;
	static int n;

	public static HashMap<String, ArrayList<List<Requirement>>> generateTestSets(List<Requirement> requirements, int number) throws JSONException {
		HashMap<String, ArrayList<List<Requirement>>> map = new HashMap<>();
 		n = max(number,2);
		int iters = min(n, number);
		values = new int[n];
		boolean only_one = (number == 1);

		/* Initialize structures to Write into test sets*/
		ArrayList<List<Requirement>> test_set_reqs = new ArrayList<>();
		for (int i = 0; i < iters; i++) {
			List<Requirement> reqs = new ArrayList<>();
			test_set_reqs.add(reqs);
		}

		/* Initialize structures to read training sets */
		ArrayList<List<Requirement>> train_set_reqs = new ArrayList<>();
		for (int i = 0; i < iters; i++) {
            List<Requirement> reqs = new ArrayList<>();
			train_set_reqs.add(reqs);
		}

		int numero = 0;
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);
			double d = ThreadLocalRandom.current().nextDouble(0,n);
			numero = (int) (d + 1);
			//System.out.println(d + " ---> " + numero);
			boolean flag = true;
			while (flag == true) {
				if (validate(numero)) {
					if (only_one) {
						if (numero == 1)
							test_set_reqs.get(numero - 1).add(req);
						else
							train_set_reqs.get(0).add(req);
					} else {
						test_set_reqs.get(numero - 1).add(req);
					}
					flag = false;
				} else {
					double d2 = ThreadLocalRandom.current().nextDouble(0, n);
					numero = (int) (d2 + 1);
				}
				validateFor();
			}

		}

		if (!only_one) {
            for (int a = 0; a < n; a++) {
                List<Requirement> reqs = test_set_reqs.get(a);
                for (int i = 0; i < reqs.size(); ++i) {
                    Requirement req = reqs.get(i);
                    for (int c = 0; c < n; c++) {
                        if (c != a) {
                            train_set_reqs.get(c).add(req);
                        }
                    }
                }
            }
        }

		map.put("train_sets", train_set_reqs);
		map.put("test_sets", test_set_reqs);

		System.out.println("Finished");
		for (int i = 0; i < test_set_reqs.size(); i++)
			System.out.println("Requirement array " + i + " size: " + test_set_reqs.get(i).size());
		return map;
	}

	private static String toTSV(JSONObject req) throws JSONException {
		return req.getString("requirement_type") + "\t" + req.getString("id") + "\t" + req.getString("text") + "\n";
	}

	static void fill() {
		for(int x=0;x<n;x++)
			values[x]=x+1;

	}

	static boolean validate(int x) {
		return values[x-1]!=0;
	}

	static void validateFor() {
		int flag = 0;

		for(int x=0;x<n;x++) {
			if(values[x]==0) {
				flag++;
			}
		}

		if(flag==n) {
			fill();
		}
	}
}
